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
		
		String query =  "  SELECT atr.id as rel_id,\n" + 
						"       atr.attribute_id,\n" + 
						"       a.name,\n" + 
						"       a.default_value,\n" + 
						"       a.possible_values_type_id,\n" + 
						"       atr.required,\n" + 
						"       ns.prefix,\n" + 
						"       ns.url\n" + 
						"  FROM attributes AS a\n" + 
						"       LEFT JOIN\n" + 
						"       attributes_tags_relations AS atr ON a.id = atr.attribute_id\n" + 
						"       LEFT JOIN\n" + 
						"       name_space AS ns ON atr.name_space_id = ns.id\n" + 
						" WHERE atr.tag_id = " + parent.getTagId() + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		if(i.hasNext()) attrArr = new ArrayList<XmlAttribute>();
		while (i.hasNext()) {
			TableRecord record = i.next();
			attrId 				= Integer.parseInt(record.get("attribute_id"));
			relId 				= Integer.parseInt(record.get("rel_id"));
			name 				= record.get("name");
			defaultValue 		= record.get("default_value");
			required 			= Boolean.parseBoolean(record.get("required"));
			
			
			if(record.get("prefix") != null && record.get("url") != null) nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			else nameSpace = null;
			if(record.get("possible_values_type_id") != null) {
				possibleValues = getAttrPossibleValuesFromAttrId(attrId);
			}
			else possibleValues = null;
			
			XmlAttribute attr = new XmlAttribute(attrId,relId,name,required,possibleValues,defaultValue,nameSpace,parent);
			attrArr.add(attr);
	    }
		return attrArr;
	}


	
	
	public XmlEnum getAttrPossibleValuesFromAttrId(int id) {
		XmlEnum possibleValues = null;
		String query =  "SELECT apv.value\n" + 
						"  FROM attributes AS a\n" + 
						"  LEFT JOIN attributes_possible_values as apv ON a.possible_values_type_id = apv.type_id\n" + 
						"WHERE a.id =" + id + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		if(i.hasNext()) {
			possibleValues = new XmlEnum();
			possibleValues.add("");
		}
		while (i.hasNext()) {
			TableRecord record = i.next();
			possibleValues.add(record.get("value"));
	    }
		return possibleValues;
	}
	
	
	
	
	
	public XmlEnum getAttrPossibleValuesFromAttrName(String name) {
		
		XmlEnum possibleValues = null;
		String query =  "SELECT apv.value\n" + 
						"  FROM attributes AS a\n" + 
						"  LEFT JOIN attributes_possible_values as apv ON a.possible_values_type_id = apv.type_id\n" + 
						"WHERE a.name = '" + name + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		if(i.hasNext()) {
			possibleValues = new XmlEnum();
			possibleValues.add("");
		}
		while (i.hasNext()) {
			TableRecord record = i.next();
			possibleValues.add(record.get("value"));
	    }
		return possibleValues;
	}
	
	
	
	
	public String getAttrDescription(XmlAttribute attr) {
		String description = null;
		String query =  "Select a.description From attributes AS a WHere a.id =" + attr.getAttrId() + "";
		
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
		Object possibleValues = null;
		boolean required = false;
		XmlAttribute attr = null;
		
		String query = "SELECT a.*\n" + 
					"  FROM attributes AS a\n" + 
					" WHERE name = '" + name  + "';";
			

		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record 	= i.next();
			attrId 				= Integer.parseInt(record.get("id"));
			defaultValue		= record.get("default_value");
			name 				= record.get("name");
	    }
		attr = new XmlAttribute(attrId,name,required,possibleValues,defaultValue,null);
		return attr;
	}

}
