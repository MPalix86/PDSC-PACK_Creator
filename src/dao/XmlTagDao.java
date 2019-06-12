package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import dBConnection.DBConnection;
import dBConnection.TableRecord;
import model.XmlAttribute;
import model.XmlEnum;
import model.XmlNameSpace;
import model.XmlTag;

public class XmlTagDao {
	private static DBConnection conn;
	private static XmlTagDao instance;
	
	
	
	/*
	 * SINGLETON
	 */
	public static XmlTagDao getInstance(){
		if(conn == null)
			conn = DBConnection.getInstance();
		if(instance == null)
			instance = new XmlTagDao();
		return instance;
	}
	
	
	
	
	public XmlTag getTagFromIdAndParent(int tagId , XmlTag parent) throws SQLException {
		XmlTag tag = null;
		
		String query = "--GET SELECT TAG FROM TAGID AND PARENT\n" + 
						"  SELECT t.id as tag_id,\n" + 
						"       ttr.id as rel_id, \n" + 
						"       ttr.tag_id as tag_id,\n" + 
						"       t.name,\n" + 
						"       t.default_content,\n" + 
						"       t.possible_values_type_id,\n" + 
						"       ttr.max_occurrence,\n" + 
						"       ttr.required,\n" + 
						"       ttr.parent_id,\n" + 
						"       ns.prefix,\n" + 
						"       ns.url\n" + 
						"  FROM tags_tags_relations AS ttr\n" + 
						"       LEFT JOIN\n" + 
						"       tags AS t ON t.id = ttr.tag_id\n" + 
						"       LEFT JOIN\n" + 
						"       name_space AS ns ON ns.id = ttr.name_space_id\n" + 
						" WHERE ttr.parent_id =" + parent.getTagId() + " AND ttr.tag_id =" + tagId + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		XmlNameSpace nameSpace = null;
		XmlEnum possibleValues = null;
		
		 while(i.hasNext()){
			TableRecord record = i.next();
			
			if(record.get("prefix") != null && record.get("url") != null)  nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			if (record.get("possible_values_types_id") != null) possibleValues = getTagPossibleValuesFromTagId(Integer.parseInt(record.get("tag_id")));
			
			tag = new XmlTag (
					Integer.parseInt(record.get("tag_id")),
					Integer.parseInt(record.get("rel_id")),
					record.get("name"),
	        		Boolean.parseBoolean(record.get("required")),
	        		parent,
	        		Integer.parseInt(record.get("max_occurrence")),
	        		possibleValues,
	        		record.get("default_content"),
	        		nameSpace
	        );
			
		 }
		 tag.setAttrArr(getTagAttributes(tag));
		 
    	 ArrayList<Integer> childrenIdArr = getChildrenFromTagId(tag.getTagId());

    	if(childrenIdArr != null) {
    		for(int j = 0; j < childrenIdArr.size(); j++) {
    			Integer childId = childrenIdArr.get(j);
    			XmlTag child = getTagFromIdAndParent(childId,tag);
    			tag.addChild(child);
    		}
    	}
        return tag;
	}
	
	
	
	public XmlTag getRootTag() throws SQLException {
		/** query */
		String query = 	"  SELECT t.id as tag_id,\n" + 
						"       ttr.id as rel_id, \n" + 
						"       t.name,\n" + 
						"       t.default_content,\n" + 
						"       t.possible_values_type_id,\n" + 
						"       ttr.max_occurrence,\n" + 
						"       ttr.required,\n" + 
						"       ttr.parent_id,\n" + 
						"       ns.prefix,\n" + 
						"       ns.url\n" + 
						"  FROM tags_tags_relations AS ttr\n" + 
						"       LEFT JOIN\n" + 
						"       tags AS t ON t.id = ttr.tag_id\n" + 
						"       LEFT JOIN\n" + 
						"       name_space AS ns ON ns.id = ttr.name_space_id\n" + 
						" WHERE ttr.parent_id IS NULL;";
	
		/** making query */
		ArrayList<TableRecord> result = conn.query(query);
    	TableRecord record = result.get(0);
		
    	/** definition of complex objects */
    	XmlNameSpace nameSpace = null;
    	XmlEnum possibleValues = null;
    	
    	if(record.get("prefix") != null && record.get("url") != null)  nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
    			
		XmlTag tag = new XmlTag (
				Integer.parseInt(record.get("tag_id")),
				Integer.parseInt(record.get("rel_id")),
				record.get("name"),
        		Boolean.parseBoolean(record.get("required")),
        		null/** parent */,
        		Integer.parseInt(record.get("max_occurrence")),
        		null/** possibleValues */,
        		record.get("default_content"),
        		nameSpace
        );
		
		if(record.get("possible_values_type_id") != null) {
			possibleValues = getTagPossibleValuesFromTagId(tag.getTagId());
			tag.setPossibleValues(possibleValues);
		}
        
        tag.setAttrArr(getTagAttributes(tag));
        
		return tag;
	}
	
	
	
	public XmlEnum getTagPossibleValuesFromTagId(int tagId) throws SQLException {
		XmlEnum possibleValues = new XmlEnum();
		String query = "--SELECT ALL POSSIBLE VALUES FROM TAG ID\n" + 
						"SELECT tpv.value\n" + 
						"  FROM tags AS t\n" + 
						"       LEFT JOIN\n" + 
						"       tags_possible_values AS tpv ON tpv.type_id = t.possible_values_type_id\n" + 
						" WHERE t.id = " + tagId + ""; 		
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		possibleValues.add("");
		
		 while(i.hasNext()){
			TableRecord record = i.next();
			possibleValues.add(record.get("value"));
		 }
		
		return possibleValues;
	}

	
	
	
	
	public ArrayList<XmlAttribute> getTagAttributes(XmlTag parent) throws SQLException {
		
		Integer attrId;
		Integer relId;
		String name = null;
		String defaultValue = null;
		Object possibleValues = null;
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
		
		if(result != null) {
			attrArr = new ArrayList<XmlAttribute>();
		}
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			attrId 				= Integer.parseInt(record.get("rel_id"));
			relId 				= Integer.parseInt(record.get("attribute_id"));
			attrId 				= Integer.parseInt(record.get("attribute_id"));
			name 				= record.get("name");
			defaultValue 		= record.get("default_value");
			required 			= Boolean.parseBoolean(record.get("required"));
			
			
			if(record.get("prefix") != null && record.get("url") != null)  nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			if(record.get("possible_values_type_id") != null) possibleValues = getAttrPossibleValuesFromAttrId(attrId);
			else possibleValues = new String("");
			
			XmlAttribute attr = new XmlAttribute(attrId,relId,name,required,possibleValues,defaultValue,nameSpace,parent);
			attrArr.add(attr);
	    }
		return attrArr;
	}


	
	
	public XmlEnum getAttrPossibleValuesFromAttrId(int id) throws SQLException{
		
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
	
	
	
	public ArrayList<Integer> getChildrenFromTagId(int id) throws SQLException {

		ArrayList<Integer> childrenIdArr = new ArrayList<Integer>();
		String query = "SELECT * FROM tags_tags_relations WHERE parent_id =" + id + "";
		

		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			childrenIdArr.add(Integer.parseInt(record.get("tag_id")));
		}
		return childrenIdArr;
	}
	
	
	public Integer getTagIdFromRelId(int relId) {
		String query = "Select ttr.tag_id FROM tags_tags_relations AS ttr WHERE ttr.id =" + relId + "";
		ArrayList<TableRecord> result = conn.query(query);
		Integer tagId = Integer.parseInt(result.get(0).get("tag_id"));
		return tagId;
	}
	
}
