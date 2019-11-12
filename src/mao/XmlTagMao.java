package mao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.TagManager;
import dao.XmlAttributeDao;
import dao.XmlTagDao;
import model.xml.XmlAttribute;
import model.xml.XmlEnum;
import model.xml.XmlTag;


/**
 * Model Access Object
 * 
 * contains query that return tag's model instance
 * 
 * @author mircopalese
 */


public class XmlTagMao {
	public static XmlTagMao instance;
	private final static XmlTag root = getRoot();
	
	/**
	 * return tag description
	 * 
	 * @return tag description
	 */
	public static String getTagDescription(XmlTag tag) {
		String description = XmlTagDao.getInstance().getTagDescriptionFromTagAndParent(tag, tag.getParent());
		
		/** 
		 * if description == null,it possible that attribute for this tag is an exception 
		 * (see PDSCTagAttributeException class).
		 * in this case description is located in tags_attributes_exception table
		 */
		
		if( description == null || description.trim().length() == 0) {
			description = XmlTagDao.getInstance().getTagDescriptionFromTagChildrenExceptions(tag, tag.getParent());
		}
		return description;
	}
	
	
	
	
	
	/**
	 * 
	 */
	public static XmlTag getCompleteTagFromTagIdAndParent(int id, XmlTag parent) {
		try {
			return XmlTagDao.getInstance().getTagFromIdAndParent(id, parent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	/**
	 * return root tag, the only one that hava like parent == himself
	 * 
	 * @return root tag
	 */
	
	public static XmlTag getRoot() {
		XmlTag root = XmlTagDao.getInstance().getRootTag();
		root.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(root));
		root.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(root , root));
		root.setParent(root);
		root.setChildrenArr(XmlTagDao.getInstance().getChildrenArrFromTag(root));
		TagManager.adjustTagAttributeException(root, root);
		
		return root; 
		
	}
	
	
	
	
	
	/** 
	 * return tag's not required children;
	 * 
	 * @param parent parent tag
	 * @return tag's not required children or null
	 */
	public static  ArrayList<XmlTag> getNotRequiredChildren(XmlTag parent) {
		return XmlTagDao.getInstance().getAllChildrenFromTag(parent);
	}
	
	
	
	
	/**
	 * this function assume that tag have name , required and other filed already set.
	 * this function simply add attrArr , possibleVlaues
	 * 
	 * @param name		tag's name
	 * @param parent	parent tag 
	 * @return	tag found or null
	 */
	public static XmlTag getCompleteTagFromNameAndParent(String name , XmlTag parent) {
		XmlTag tag = XmlTagDao.getInstance().getTagFromNameAndParent(name, parent);
		if(tag != null) return getCompleteTagFromTagInstance(tag);
		//else System.out.println("il tag è null " + name + " parent " + parent.getName());
		return null;
	}
	
	
	
	
	
	/**
	 * this function assume that tag have name , required and other filed already set.
	 * this function simply add attrArr , possibleVlaues
	 * 
	 * @param tag
	 * @return passed tag with attrArr, possibleValues and TagAttributeExceptionArr set
	 */
	public static XmlTag getCompleteTagFromTagInstance(XmlTag tag) {
		
		
		tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
		tag.setPossibleValues(XmlTagDao.getInstance().getTagPossibleValues(tag));
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** avoid stack overflow and infinite recursion */
				if(!child.getName().equals(root.getName())) {
					getCompleteTagFromTagInstance(child);
				}
				
			}
		}
		
		return tag;
	}
	
	
	
	
	/**
	 * this function assume that tag have name , required and other filed already set.
	 * this function simply add attrArr and possibleVlaues
	 * 
	 * @param tag
	 * @return passed tag with attrArr and possibleValues set
	 */
	public static XmlTag getCompleteTagFromTagId(Integer id) {
		
		XmlTag tag = XmlTagDao.getInstance().getTagFromTagId(id);
		
		tag.setTagId(id);
		
		ArrayList<XmlAttribute> attrArr = XmlAttributeDao.getInstance().getTagAttributes(tag);
		XmlEnum pssibleValues = XmlTagDao.getInstance().getTagPossibleValues(tag);
		if(attrArr != null) tag.setAttrArr(attrArr);
		if(pssibleValues != null) tag.setPossibleValues(pssibleValues);
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** recursion */
				getCompleteTagFromTagId(child.getTagId());
			}
		}
		return tag;
	}
	
	
	
	
	/**
	 * return tag id of tagName passed 
	 *        
	 * @param name tag's name
	 * @return tag id of tagName passed
	 */
	public static Integer getTagIdFromTagName(String name) {
		
		return XmlTagDao.getInstance().getTagIdFromTagName(name);
	}
	
	

}
