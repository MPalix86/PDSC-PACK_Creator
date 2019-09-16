package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import dBConnection.DBConnection;
import dBConnection.TableRecord;
import model.PDSCTagAttributeException;
import model.PDSCTagChildrenException;
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
						"       tpvt.name AS content_type,\n" + 
						"       ttr.max_occurrence,\n" + 
						"       ttr.required,\n" + 
						"       ttr.parent_id,\n" + 
						"       ns.prefix,\n" + 
						"       ns.url,\n" +
						"  FROM tags_tags_relations AS ttr\n" + 
						"       LEFT JOIN\n" + 
						"       tags AS t ON t.id = ttr.tag_id\n" + 
						"       LEFT JOIN\n" + 
						"       name_space AS ns ON ns.id = ttr.name_space_id\n" + 
						" 		LEFT JOIN \n" + 
						"       tags_possible_values_types AS tpvt ON tpvt.id = t.possible_values_type_id\n" +
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
	        		nameSpace,
	        		record.get("content_type")
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
						"       tpvt.name AS content_type,\n" +
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
						" 		LEFT JOIN \n" + 
						"       tags_possible_values_types AS tpvt ON tpvt.id = ttr.content_type_id" +
		
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
	        		nameSpace, 
	        		record.get("content_type")
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
						"       tpvt.name AS content_type,\n" + 
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
						"		LEFT JOIN\n" +
					    "       tags_possible_values_types AS tpvt ON tpvt.id = ttr.content_type_id\n" +
						" WHERE ttr.parent_id = 1 AND ttr.tag_id = 1;";
	
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
        		null,
        		Integer.parseInt(record.get("max_occurrence")),
        		possibleValues,
        		record.get("default_content"),
        		nameSpace,
        		record.get("content_type")
        );
        
		return tag;
	}
	
	
	
	
	
	
	
	public XmlEnum getTagPossibleValues(XmlTag tag) {
		XmlEnum possibleValues = null;
		String query = "SELECT tpv.value\n" + 
				"  FROM tags_possible_values AS tpv\n" + 
				"       LEFT JOIN\n" + 
				"       tags_tags_relations AS ttr ON tpv.type_id = ttr.content_type_id\n" + 
				" WHERE ttr.tag_id = " + tag.getTagId() + " AND \n" + 
				"       ttr.parent_id = " + tag.getParent().getTagId() + ""; 		
		
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
						"       tpvt.name AS content_type,\n" + 
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
						"		LEFT JOIN \n" + 
						"        tags_possible_values_types AS tpvt ON tpvt.id = ttr.content_type_id\n" +
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
		    		nameSpace,
		    		record.get("content_type")
		    );
			 
			childrenArr.add(tag);
			
		}
		return childrenArr;
	}
	
	
	
	public ArrayList<XmlTag> getAllChildrenFromTag(XmlTag parent) {

		ArrayList<XmlTag> childrenArr = null;
		String query = "--GET SELECT TAG FROM TAGID AND PARENT\n" + 
						"  SELECT t.id as tag_id,\n" + 
						"       ttr.id as rel_id, \n" + 
						"       t.name,\n" + 
						"       t.default_content,\n" + 
						"       tpvt.name AS content_type,\n" + 
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
						"		LEFT JOIN \n" + 
						"       tags_possible_values_types AS tpvt ON tpvt.id = ttr.content_type_id\n" +
						" WHERE ttr.parent_id =" + parent.getTagId() + 
						" ORDER BY t.name COLLATE NOCASE ASC ";
		
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
	        		nameSpace,
	        		record.get("content_type")
	        );
			
			tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
			
			childrenArr.add(tag);
		}
		return childrenArr;
	}
	
	
	
	
	public XmlTag getTagFromTagId(int tagId) {
		String query = "SELECT t.*\n" + 
				" FROM  tags as t\n" + 
				" WHERE t.id = '" + tagId + "'";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		XmlTag tag  = null;
		
		while(i.hasNext()){
			TableRecord record = i.next();
			
			tag = new XmlTag (
					record.get("name"),
					null /** possible values */,
					record.get("default_content"),
					"All" /** content_type */
	        );
		}	
		return tag;
	}
	
	
	
	
	
	
	
	public String getTagDescriptionFromTagAndParent(XmlTag tag, XmlTag parent) {
		String description = null;
		String query =  "SELECT ttr.description,\n" + 
				"       t1.name AS parent_name,\n" + 
				"       t2.name AS tag_name\n" + 
				"  FROM tags_tags_relations AS ttr\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t1 ON t1.id = ttr.parent_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t2 ON t2.id = ttr.tag_id\n" + 
				" WHERE t1.name = '" + parent.getName() + "' AND \n" + 
				"       t2.name = '" + tag.getName() + "';";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
	    }
		return description;
	}
	
	
	
	
	public String getTagDescriptionFromTagChildrenExceptions(XmlTag child, XmlTag tag) {
		String description = null;
		String query =  "SELECT tce.child_description,\n" + 
				"       	t1.name AS child_name,\n" + 
				"        	t.name AS tag_name\n" + 
				"  		 FROM tag_children_exception AS tce\n" + 
				"        	LEFT JOIN\n" + 
				"        	tags AS t1 ON t1.id = tce.child_id\n" + 
				"        	LEFT JOIN\n" + 
				"        	tags AS t ON t.id = tce.tag_id\n" + 
				" 		 WHERE " +
				"			t1.name = '" + child.getName() + "' AND \n" + 
				"       	t.name = '" + tag.getName() + "' ;";
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		while (i.hasNext()) {
			TableRecord record = i.next();
			description = record.get("description");
	    }
		return description;
	}
	
	
	public ArrayList<PDSCTagAttributeException> getTagAttributeExceptionArr(XmlTag tag, XmlTag parent){
		String query = 	"SELECT tags1.name AS p_name,\n" + 
				"       tags.name AS t_name,\n" + 
				"       attributes.name AS a_name,\n" + 
				"       tag_attributes_exception.*,\n" + 
				"       apvt.name AS value_type\n" + 
				"  FROM tag_attributes_exception\n" + 
				"       LEFT JOIN\n" + 
				"       tags ON tags.id = tag_attributes_exception.tag_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS tags1 ON tags1.id = tag_attributes_exception.parent_id\n" + 
				"       LEFT JOIN\n" + 
				"       attributes ON attributes.id = tag_attributes_exception.attribute_id\n" + 
				"       LEFT JOIN\n" + 
				"       attributes_possible_values_types AS apvt ON apvt.id = tag_attributes_exception.value_type_id\n" + 
				" WHERE tag_attributes_exception.tag_id = " + tag.getTagId() + " AND tag_attributes_exception.parent_id = " + parent.getTagId() + ";";
		
		ArrayList<PDSCTagAttributeException> exceptions = null;
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		if(!result.isEmpty()) {
			exceptions = new ArrayList<PDSCTagAttributeException>();
			while (i.hasNext()) {		
				TableRecord record = i.next();
				XmlAttribute attr = XmlAttributeDao.getInstance().getAttributeFromName(record.get("a_name"));
				
				if(Integer.parseInt(record.get("exception")) == 1 && attr != null ) {
					attr.setRequired(Boolean.parseBoolean(record.get("required")));
					XmlEnum possibleVlaues = XmlAttributeDao.getInstance().getAttributePossibleValuesFromTypeId(Integer.parseInt(record.get("value_type_id")));
					if(possibleVlaues != null) attr.setPossibleValues(possibleVlaues);
				}
				
				
				PDSCTagAttributeException exception = new PDSCTagAttributeException(
						tag,
						this.getTagFromTagId(Integer.parseInt(record.get("parent_id"))),
						attr,
						Integer.parseInt(record.get("exception"))
				);
				exceptions.add(exception);
		    }
		}		
		return exceptions;
	}
	
	
	
	
	
	
	public ArrayList<PDSCTagChildrenException> getTagChildrenExceptionArr(XmlTag tag, XmlTag parent){
		String query = 	"SELECT tag_children_exception.*,\n" + 
				"       t1.name AS tag_name,\n" + 
				"       t2.name AS parent_name,\n" + 
				"       t3.name AS child_name,\n" + 
				" 		tpvt.name AS value_type" +
				"  FROM tag_children_exception\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t1 ON t1.id = tag_children_exception.tag_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t2 ON t2.id = tag_children_exception.parent_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags AS t3 ON t3.id = tag_children_exception.child_id\n" + 
				"       LEFT JOIN\n" + 
				"       tags_possible_values_types AS tpvt ON tpvt.id = tag_children_exception.value_type_id\n" + 
				" WHERE tag_children_exception.tag_id = " + tag.getTagId() + " AND tag_children_exception.parent_id = " + parent.getTagId() + " ;";
		
		ArrayList<PDSCTagChildrenException> exceptions = null;
		
		ArrayList<TableRecord> result = conn.query(query);
		Iterator<TableRecord> i = result.iterator();
		
		if(!result.isEmpty()) {
			exceptions = new ArrayList<PDSCTagChildrenException>();
			while (i.hasNext()) {		
				TableRecord record = i.next();
				XmlTag child = XmlTagDao.getInstance().getTagFromTagId(Integer.parseInt(record.get("child_id")));
				
				if(Integer.parseInt(record.get("exception")) == 1 && child != null ) {
					child.setRequired(Boolean.parseBoolean(record.get("required")));
					child.setParent(parent);
					child.setMax(Integer.parseInt(record.get("max_occurrence")));
					child.setValueType(record.get("value_type"));
					child.setTagId(Integer.parseInt(record.get("child_id")));
				}
				
				
				PDSCTagChildrenException exception = new PDSCTagChildrenException(
						tag,
						parent,
						child,
						Integer.parseInt(record.get("exception"))
				);
				
				exceptions.add(exception);
		    }
		}		
		return exceptions;
	}
	
}
