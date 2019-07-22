package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import dBConnection.DBConnection;
import dBConnection.TableRecord;
import model.PDSCTagAttributeException;
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
			else nameSpace = null;
			
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
        return tag;
	}
	
	
	
	
	public Integer getTagIdFromTagName(String name) {
		String query = "SELECT id FROM tags As t WHERE t.name = '" + name + "' ";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();

		 while(i.hasNext()){
			TableRecord record = i.next();
			return Integer.parseInt(record.get("id"));
		 }
		return null;
	}
	
	
	
	
	public XmlTag getTagFromNameAndParent(String name , XmlTag parent) {
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
						" WHERE ttr.parent_id =" + parent.getTagId() + " AND t.name = '" + name + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		XmlNameSpace nameSpace = null;
		XmlEnum possibleValues = null;
		
		 while(i.hasNext()){
			TableRecord record = i.next();
			
			if(record.get("prefix") != null && record.get("url") != null)  nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			else nameSpace = null;
			
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
        return tag;
	}
	
	
	
	public XmlTag getRootTag()  {
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
		else nameSpace = null;
		
    			
		XmlTag tag = new XmlTag (
				Integer.parseInt(record.get("tag_id")),
				Integer.parseInt(record.get("rel_id")),
				record.get("name"),
        		Boolean.parseBoolean(record.get("required")),
        		null/** parent */,
        		Integer.parseInt(record.get("max_occurrence")),
        		possibleValues,
        		record.get("default_content"),
        		nameSpace
        );
        
		return tag;
	}
	
	
	
	
	
	
	
	public XmlEnum getTagPossibleValues(XmlTag tag) {
		XmlEnum possibleValues = null;
		String query = "SELECT tpv.value\n" + 
						"  FROM tags_possible_values AS tpv\n" + 
						"       LEFT JOIN\n" + 
						"       tags AS t ON tpv.type_id = t.possible_values_type_id\n" + 
						" WHERE t.id =" + tag.getTagId() + ""; 		
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		if(i.hasNext()) possibleValues = new XmlEnum();
		while(i.hasNext()){
			TableRecord record = i.next();
			possibleValues.add(record.get("value"));
		}
		
		return possibleValues;
	}

	
	
	public ArrayList<XmlTag> getChildrenArrFromTag(XmlTag parent) {

		ArrayList<XmlTag> childrenArr = null;

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
						" WHERE ttr.parent_id =" + parent.getTagId()  + "";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		XmlNameSpace nameSpace = null;
		XmlEnum possibleValues = null;
		
		if(i.hasNext()) childrenArr = new ArrayList<XmlTag>();
		while(i.hasNext()){
			TableRecord record = i.next();
			
			if(record.get("prefix") != null && record.get("url") != null)  nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			else nameSpace = null;
			
			
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
			 
			if(tag.getName().equals("description")) childrenArr.add(0, tag);
			if(tag.getName().equals("doc")) childrenArr.add(1, tag);
			else  childrenArr.add(tag);
			
		}
		return childrenArr;
	}
	
	
	
	public ArrayList<XmlTag> getNotRequiredChildrenFromTag(XmlTag parent) {

		ArrayList<XmlTag> childrenArr = null;
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
						" WHERE ttr.parent_id =" + parent.getTagId() + " AND ttr.required = 'false'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		XmlNameSpace nameSpace = null;
		XmlEnum possibleValues = null;
		
		if(i.hasNext())  childrenArr = new ArrayList<XmlTag>();
		
		while(i.hasNext()){
			TableRecord record = i.next();
			
			if(record.get("prefix") != null && record.get("url") != null)  nameSpace = new XmlNameSpace(record.get("prefix"), record.get("url"));
			else nameSpace = null;
			
			XmlTag tag = new XmlTag (
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
			
			tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
			
			childrenArr.add(tag);
		}
		return childrenArr;
	}
	
	
	
	
	public XmlTag getTagFromTagId(int tagId) {
		String query = "SELECT * FROM tags WHERE id = '" + tagId + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		XmlTag tag  = null;
		
		while(i.hasNext()){
			TableRecord record = i.next();
			
			tag = new XmlTag (
					record.get("name"),
					null /** possible values */,
					record.get("default_content")
	        );
		}	
		return tag;
	}
	
	
	
	
	
	
	
	public String getTagDescription(XmlTag tag) {
		String description = null;
		String query =  "Select * From tags AS t WHere t.name ='" + tag.getTagId() + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
	    }
		return description;
	}
	
	
	
	
	public String getTagDescriptionFromTagName(XmlTag tag) {
		String description = null;
		String query =  "Select * From tags AS t WHere t.name ='" + tag.getName() + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
	    }
		return description;
	}
	
	
	public ArrayList<PDSCTagAttributeException> getTagAttributeExceptionArr(XmlTag tag){
		String query = 	"SELECT tag_attributes_exception.* , tags.name as t_name , attributes.name as a_name\n" + 
						"FROM tag_attributes_exception\n" + 
						"    LEFT JOIN tags ON tags.id = tag_attributes_exception.tag_id \n" + 
						"    LEFT JOIN attributes ON attributes.id = tag_attributes_exception.attribute_id\n" + 
						"WHERE tag_attributes_exception.tag_id =" + tag.getTagId() + "";
		
		ArrayList<PDSCTagAttributeException> exceptions = null;
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		if(i.hasNext()) exceptions = new ArrayList<PDSCTagAttributeException>();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			PDSCTagAttributeException exception = new PDSCTagAttributeException(
					tag,
					this.getTagFromTagId(Integer.parseInt(record.get("parent_id"))),
					XmlAttributeDao.getInstance().getAttributeFromName(record.get("a_name")),
					Integer.parseInt(record.get("exception"))
			);
			exceptions.add(exception);
	    }
		
		return exceptions;
	}
	
}
