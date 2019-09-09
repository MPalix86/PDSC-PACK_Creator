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
	
	
	
	
	public ArrayList<XmlAttribute> getTagAttributes(XmlTag parent) {
		
		Integer attrId;
		Integer relId;
		String name = null;
		String defaultValue = null;
		XmlEnum possibleValues = null;
		boolean required = false;
		XmlNameSpace nameSpace = null;
		ArrayList<XmlAttribute> attrArr = null;
		String possibleValuesType = null;
		
		String query =  "SELECT atr.id AS rel_id,\n" + 
				"       atr.attribute_id,\n" + 
				"       a.name,\n" + 
				"       a.default_value,\n" + 
				"       atr.required,\n" + 
				"       ns.prefix,\n" + 
				"		apvt.name as value_type,\n" + 
				"       ns.url\n" + 
				"  FROM attributes AS a\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_tags_relations AS atr ON a.id = atr.attribute_id\n" + 
				"       LEFT JOIN\n" + 
				"       name_space AS ns ON atr.name_space_id = ns.id\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_possible_values_types AS apvt ON apvt.id = atr.value_type_id \n" + 
				" WHERE atr.tag_id = " + parent.getTagId() + "\n" + 
				" ORDER BY a.name;";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		if(!result.isEmpty()) 	attrArr = new ArrayList<XmlAttribute>();
		while (i.hasNext()) {
			TableRecord record = i.next();
			attrId 				= Integer.parseInt(record.get("attribute_id"));
			relId 				= Integer.parseInt(record.get("rel_id"));
			name 				= record.get("name");
			defaultValue 		= record.get("default_value");
			required 			= Boolean.parseBoolean(record.get("required"));
			possibleValuesType	= record.get("value_type");
			if(possibleValuesType == null)  possibleValuesType = "String";
			
			
			if(record.get("prefix") != null && record.get("url") != null) nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			else nameSpace = null;
			possibleValues = getAttrPossibleValuesFromAttrIdAndTagId(attrId, parent.getTagId());
			
			XmlAttribute attr = new XmlAttribute(attrId,relId,name,required,possibleValues,defaultValue,nameSpace,parent,possibleValuesType);
			attrArr.add(attr);
	    }
		return attrArr;
	}


	
	
	public XmlEnum getAttrPossibleValuesFromAttrIdAndTagId(int attrId, int tagId) {
		XmlEnum possibleValues = null;
		String query =  "SELECT apv.value\n" + 
				"  		FROM attributes_possible_values AS apv\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_tags_relations AS atr ON atr.value_type_id = apv.type_id\n" + 
				"   WHERE atr.attribute_id = " + attrId + " AND atr.tag_id =" + tagId + ";";
		
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
	
	
	public String getAttrDescriptionFromAttrAndParent(XmlAttribute attr, XmlTag parent) {
		String description = null;
		String query =  "SELECT atr.description,\n" + 
				"      	 	a.name AS attr_name,\n" + 
				"        	t.name AS tag_name\n" + 
				"  		FROM attributes_tags_relations AS atr\n" + 
				"       	LEFT JOIN\n" + 
				"        	attributes AS a ON a.id = atr.attribute_id\n" + 
				"        	LEFT JOIN\n" + 
				"        	tags AS t ON t.id = atr.tag_id\n" + 
				" 		 WHERE a.name = '" + attr.getName() + "' AND t.name = '" + parent.getName() + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
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
