package dao;

import java.util.ArrayList;
import java.util.Iterator;

import dBConnection.DBConnection;
import dBConnection.TableRecord;
import model.XmlAttribute;
import model.XmlEnum;
import model.XmlNameSpace;
import model.XmlTag;

public class XmlAttributeDao {
	private static DBConnection conn;
	private static XmlAttributeDao instance;
	
	
	/*
	 * SINGLETON
	 */
	
	public static XmlAttributeDao getInstance(){
		if(conn == null)
			conn = DBConnection.getInstance();
		if(instance == null)
			instance = new XmlAttributeDao();
		return instance;
	}
	
	
	
	/**
	 * find all attributes passed tag
	 * 
	 * Note that one attribute can be defined more than one time for each tag, so
	 * in this case the decision on wich to take depends on tag's parent
	 * 
	 * @param tag 
	 * @return arraylist of attribute for passed tag
	 */
	public ArrayList<XmlAttribute> getTagAttributes(XmlTag tag) {
		
		Integer attrId;
		String name = null;
		String defaultValue = null;
		XmlEnum possibleValues = null;
		boolean required = false;
		XmlNameSpace nameSpace = null;
		ArrayList<XmlAttribute> attrArr = null;
		String possibleValuesType = null;
		
		String query =  
				"SELECT COUNT(a.id) AS count,\n" + 
				"       atr.id AS rel_id,\n" + 
				"       atr.attribute_id,\n" + 
				"		atr.value_type_id,\n" + 
				"       a.name,\n" + 
				"       a.default_value,\n" + 
				"       atr.required,\n" + 
				"       ns.prefix,\n" + 
				"       apvt.name AS value_type,\n" + 
				"       ns.url\n" + 
				"  FROM attributes AS a\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_tags_relations AS atr ON a.id = atr.attribute_id\n" + 
				"       LEFT JOIN\n" + 
				"       name_space AS ns ON atr.name_space_id = ns.id\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_possible_values_types AS apvt ON apvt.id = atr.value_type_id\n" + 
				" WHERE atr.tag_id = " + tag.getTagId() + "\n" + 
				" GROUP BY a.id" +
				" ORDER BY a.name COLLATE NOCASE ASC ";
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		XmlAttribute attr = null;
		
		if(!result.isEmpty()) 	attrArr = new ArrayList<XmlAttribute>();
		while (i.hasNext()) {
			TableRecord record = i.next();
			
			/**
			 * if count > 1, attribute is defined in DB more than one time for this tag
			 * this means that decision on wich attribute to take depends on the parent of the tag
			 */
			if(Integer.parseInt(record.get("count")) > 1) attr = getAttributeFromTagAndParentAndAttrId(tag , Integer.parseInt(record.get("attribute_id")));
			else {
				attrId 				= Integer.parseInt(record.get("attribute_id"));
				name 				= record.get("name");
				defaultValue 		= record.get("default_value");
				required 			= Boolean.parseBoolean(record.get("required"));
				possibleValuesType	= record.get("value_type");
				if(possibleValuesType == null)  possibleValuesType = "String";
				
				if(record.get("prefix") != null && record.get("url") != null) nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
				else nameSpace = null;
				possibleValues = getAttributePossibleValuesFromTypeId(Integer.parseInt(record.get("value_type_id")));
				attr = new XmlAttribute(attrId,name,required,possibleValues,defaultValue,nameSpace,tag,possibleValuesType);
			}
			if(attr != null) attrArr.add(attr);
	    }
		return attrArr;
	}
	
	
	
	
	public XmlAttribute getAttributeFromTagAndParentAndAttrId(XmlTag tag,int attrId) {
		
		String name = null;
		String defaultValue = null;
		XmlEnum possibleValues = null;
		boolean required = false;
		XmlNameSpace nameSpace = null;
		String possibleValuesType = null;
		
		String query =  
				"SELECT atr.id AS rel_id,\n" + 
				"       atr.attribute_id,\n" + 
				"       a.name,\n" + 
				"       a.default_value,\n" + 
				"		atr.value_type_id,\n" + 
				"       atr.required,\n" + 
				"       ns.prefix,\n" + 
				"       apvt.name AS value_type,\n" + 
				"       ns.url\n" + 
				"  FROM attributes AS a\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_tags_relations AS atr ON a.id = atr.attribute_id\n" + 
				"       LEFT JOIN\n" + 
				"       name_space AS ns ON atr.name_space_id = ns.id\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_possible_values_types AS apvt ON apvt.id = atr.value_type_id\n" + 
				" WHERE atr.tag_id = " + tag.getTagId() +" AND atr.parent_tag_id = " + tag.getParent().getTagId() + " AND a.id = " + attrId + " " +
				" ORDER BY a.name COLLATE NOCASE ASC ";
		System.out.println(query);
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		XmlAttribute attr = null;

		while (i.hasNext()) {
			TableRecord record = i.next();
			
				attrId 				= Integer.parseInt(record.get("attribute_id"));
				name 				= record.get("name");
				defaultValue 		= record.get("default_value");
				required 			= Boolean.parseBoolean(record.get("required"));
				possibleValuesType	= record.get("value_type");
				if(possibleValuesType == null)  possibleValuesType = "String";
				
				if(record.get("prefix") != null && record.get("url") != null) nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
				else nameSpace = null;
				possibleValues = getAttributePossibleValuesFromTypeId(Integer.parseInt(record.get("value_type_id")));
				attr = new XmlAttribute(attrId,name,required,possibleValues,defaultValue,nameSpace,tag,possibleValuesType);
	    }
		
		return attr;
	}


	
	
	public XmlEnum getAttrPossibleValuesFromAttrIdAndTagId(int attrId, int tagId) {
		XmlEnum possibleValues = null;
		String query =  "SELECT apv.value\n" + 
				"  		FROM attributes_possible_values AS apv\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_tags_relations AS atr ON atr.value_type_id = apv.type_id\n" + 
				"   WHERE atr.attribute_id = " + attrId + " AND atr.tag_id =" + tagId + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		if(i.hasNext() && result.get(0).get("value")!= null) {
			possibleValues = new XmlEnum();
			possibleValues.add("");
		}
		while (i.hasNext()) {
			TableRecord record = i.next();
			if(possibleValues != null) possibleValues.add(record.get("value"));
	    }
		return possibleValues;
	}
	
	
	
	
	public XmlEnum getAttributePossibleValuesFromTypeId(int id) {
		XmlEnum possibleValues = null;
		String query =  "SELECT * " + 
				"  FROM attributes_possible_values AS apv\n" + 
				"  WHERE apv.type_id =" + id + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		if(!result.isEmpty()) {
			possibleValues = new XmlEnum();
			possibleValues.add("");
		}
		while (i.hasNext()) {
			TableRecord record = i.next();
			if(possibleValues != null) possibleValues.add(record.get("value"));
	    }
		return possibleValues;
	}
	
	
	
	
	public XmlEnum getAttrPossibleValuesFromAttrNameAndTag(String name, XmlTag tag) {
		XmlEnum possibleValues = null;
		String query =  "SELECT apv.value \n" + 
				"  		FROM attributes_possible_values AS apv\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_tags_relations AS atr ON atr.value_type_id = apv.type_id\n" + 
				"       LEFT JOIN\n" + 
				"       attributes as a ON a.id = atr.attribute_id\n" + 
				" 		WHERE a.name = '" + name + "' AND \n" + 
				"       atr.tag_id =  " + tag.getTagId() + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		if(i.hasNext()) {
			if( result.get(0).get("value")!= null) {
				possibleValues = new XmlEnum();
				possibleValues.add("");
			}
		}

		while (i.hasNext()) {
			TableRecord record = i.next();
			if(possibleValues != null)
				possibleValues.add(record.get("value"));
			 
	    }
		return possibleValues;
	}
	
	
	public String getAttrDescriptionFromAttrAndTag(XmlAttribute attr, XmlTag tag) {
		String description = null;
		String query =  "SELECT atr.description,\n" + 
				"      	 	a.name AS attr_name,\n" + 
				"        	t.name AS tag_name\n" + 
				"  		FROM attributes_tags_relations AS atr\n" + 
				"       	LEFT JOIN\n" + 
				"        	attributes AS a ON a.id = atr.attribute_id\n" + 
				"        	LEFT JOIN\n" + 
				"        	tags AS t ON t.id = atr.tag_id\n" + 
				" 		 WHERE a.name = '" + attr.getName() + "' AND t.name = '" + tag.getName() + "'";
		
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
	    }
		return description;
	}
	
	
	
	
	public String getAttrDescriptionFromAttrTagAndParent(XmlAttribute attr, XmlTag tag, XmlTag parent) {
		String description = null;
		String query =  "SELECT atr.description,\n" + 
				"       a.name AS attr_name,\n" + 
				"       t1.name AS tag_name,\n" + 
				"       t2.name AS parent_name\n" + 
				"  FROM attributes_tags_relations AS atr\n" + 
				"       LEFT JOIN\n" + 
				"       attributes AS a ON a.id = atr.attribute_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t1 ON t1.id = atr.tag_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t2 ON t2.id = atr.parent_tag_id\n" + 
				" WHERE a.name = '" + attr.getName() + "' AND \n" + 
				"       t1.name = '" +tag.getName() + "' AND\n" + 
				"       t2.name = '" + parent.getName() + "' ;";
		
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
	    }
		return description;
	}
	
	
	public String getAttrDescriptionFromTagAttrExceptions(XmlAttribute attr, XmlTag parent) {
		String description = null;
		String query =  "SELECT tae.attribute_description,\n" + 
				"       	a.name AS attr_name,\n" + 
				"        	t.name AS tag_name\n" + 
				"  		 FROM tag_attributes_exception AS tae\n" + 
				"        	LEFT JOIN\n" + 
				"        	attributes AS a ON a.id = tae.attribute_id\n" + 
				"        	LEFT JOIN\n" + 
				"        	tags AS t ON t.id = tae.tag_id\n" + 
				" 		 WHERE " +
				"			a.name = '" + attr.getName() + "' AND \n" + 
				"       	t.name = '" + parent.getName() + "' ;";
		
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("attribute_description");
	    }
		return description;
	}
		
	
	
	public XmlAttribute getAttributeFromName(String name) {
		Integer attrId = null;
		String defaultValue = null;
		XmlEnum possibleValues = null;
		boolean required = false;
		XmlAttribute attr = null;
		String possibleValuesType = null;
		
		String query = 	"SELECT a.* \n" + 
						"  FROM attributes AS a\n" + 
						"WHERE a.name = '"+ name +"'";
			

		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record 	= i.next();
			attrId 				= Integer.parseInt(record.get("id"));
			defaultValue		= record.get("default_value");
			name 				= record.get("name");
			possibleValuesType  = "String";
	    }
		
		if(!result.isEmpty())  attr = new XmlAttribute(attrId,name,required,possibleValues,defaultValue,null, possibleValuesType);
			
		return attr;
	}
	

	

}
