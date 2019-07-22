package business;

import java.util.ArrayList;

import dao.XmlAttributeDao;
import dao.XmlTagDao;
import model.PDSCTagAttributeException;
import model.Response;
import model.XmlAttribute;
import model.XmlTag;

public class XmlTagBusiness {
	private static XmlTagBusiness instance;
	
	public final static int  IS_STANDARD_FOR_TAG = 0;
	public final static int  IS_GENERAL_PDSC = 1;
	public final static int  IS_NEW = 2;
	public final static int  MAX_REACHED = 3;
	
	
	/*
	 * SINGLETON
	 */
	public static XmlTagBusiness getInstance(){
		if(instance == null)
			instance = new XmlTagBusiness();
		return instance;
	}
	
	
	
	
	
	
	/**
	 * starting from tagName verify if tag is PDSC standard tag for this parent
	 * or f tag is PDSC standard tag in general or if tag is new tag not present in PDSC standard
	 * 
	 * @param childName
	 * @param parent
	 * @return
	 */
	
	public static Response verifyTagFromName(String childName , XmlTag parent) {
		
		if(childName != null) {
			XmlTag child;
			if(parent != null) {
				/** check if tag is PDSC standard tag for this parent */
				XmlTag modelChild = XmlTagBusiness.findModelChildFromSelectedChildName(parent, childName);
				
				if(modelChild != null) {
					child = new XmlTag(modelChild , parent);
					XmlTagBusiness.addRequiredAttr(child);
					if(modelChild.getMax() <= 0) {
						return new Response.ResponseBuilder()
								.flag(true)
								.status(XmlTagBusiness.MAX_REACHED)
								.message(" tag is standard attribute for this parent " )
								.object(child)
								.build();
					}
					
					else {
						return new Response.ResponseBuilder()
								.flag(true)
								.status(XmlTagBusiness.IS_STANDARD_FOR_TAG)
								.message(" tag is standard attribute for this parent " )
								.object(child)
								.build();
					}
				}
				
			}
			
					
			/** check if tag is PDSC standard tag in general */
			Integer childId = XmlTagBusiness.getTagIdFromTagName(childName);
			
			if(childId != null) {
				child = XmlTagBusiness.getCompleteTagFromTagId(childId);
				if(child != null) {
					child.setParent(parent);
					XmlTagBusiness.addRequiredAttr(child);
					return new Response.ResponseBuilder()
							.flag(true)
							.status(XmlTagBusiness.IS_GENERAL_PDSC)
							.object(child)
							.build();
				}
			}
		}
		
		return 	new Response.ResponseBuilder()
				.flag(true)
				.status(XmlAttribute.IS_NEW)
				.message(" Attribute " + childName + "is not PDSC standard tag" )
				.object(new XmlTag(childName , false , parent , XmlTag.MAX_OCCURENCE_NUMBER))
				.build();
	}
	
	
	
	
	/**
	 * debug function that print on standard output tag's tree starting from model tag
	 * 
	 * @param tag	tag to print
	 * @param level starting indentation level (usually 0)
	 */
	
	public static void printModelTag(XmlTag tag,int level) {
		
		System.out.println("");
		XmlTag parent =  tag;
		for (int i = 0; i < level ; i++) System.out.print("	");
		System.out.print("< " + tag.getName());
		if (parent.getAttrArr() != null) {
			for(int i = 0; i < parent.getAttrArr().size(); i++) {
				XmlAttribute attr =  parent.getAttrArr().get(i);
				System.out.print(" " + attr.getName());
			}
		}
		System.out.print(" > ");
		level++;
		if( parent.getChildrenArr() != null) {
			ArrayList<XmlTag> xmlChildren = parent.getChildrenArr();
			/** iterating trough selected children */
			for(int i = 0; i < xmlChildren.size(); i++) {		
				XmlTag child = xmlChildren.get(i);	
				
				/** recursion */
				printModelTag(child,level);
			}
		}	
	}
	
	
	
	
	/**
	 * debug function that print on standard output tag's tree starting from local tag
	 * 
	 * @param tag	tag to print
	 * @param level starting indentation level (usually 0)
	 */
	
	public static void printLocalTag(XmlTag tag,int level) {
		
		System.out.println("");
		XmlTag parent =  tag;
		for (int i = 0; i < level ; i++) System.out.print("	");
		System.out.print("< " + tag.getName());
		if (parent.getSelectedAttrArr() != null) {
			for(int i = 0; i < parent.getSelectedAttrArr().size(); i++) {
				XmlAttribute attr =  parent.getSelectedAttrArr().get(i);
				System.out.print(" " + attr.getName());
			}
		}
		System.out.print(" > ");
		level++;
		if( parent.getSelectedChildrenArr() != null) {
			ArrayList<XmlTag> xmlChildren = parent.getSelectedChildrenArr();
			/** iterating trough selected children */
			for(int i = 0; i < xmlChildren.size(); i++) {		
				XmlTag child = xmlChildren.get(i);	
				
				/** recursion */
				printLocalTag(child,level);
			}
		}	
	}
	
	
	
	
	/**
	 * return model child that contains all original constraints. 
	 * 
	 * @param parent
	 * @param childName
	 * @return model child found or null
	 */
	
	public static XmlTag findModelChildFromSelectedChildName(XmlTag parent, String childName) {
		ArrayList <XmlTag> children = new ArrayList();
		children.add(parent);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			children.remove(element);
			if(element.getName().equals(childName)) {	
				return element;
			}
			if( element.getChildrenArr() != null ) {
				element.getChildrenArr().forEach((c)-> children.add(c));
			}
		}
		return null;
	}
	
	
	
	
	

	/**
	 * find in selected child array , all children that have passed name
	 * 
	 * @param root			tag on which to search
	 * @param childName		child name
	 * @return	arrayLyst with all found children
	 */
	
	public static XmlTag findSelectedChildFromTagName(XmlTag root, String childName) {
		ArrayList <XmlTag> children = new ArrayList();
		children.add(root);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			children.remove(element);
			if(element.getName().equals(childName)) {	
				return element;
			}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		return null;
	}
	
	
	
	
	/**
	 * Find the number of times the child (cildName) appears in  tag
	 * 
	 * @param parent 	parent tag on which search
	 * @param childName	name of child tag to find
	 * @return number of times that child appears in parent
	 */
	
	public static int findChildOccurrenceNumber(XmlTag parent, String childName) {
		int childOccurrence = 0;
		ArrayList <XmlTag> children = new ArrayList<XmlTag>();
		children.add(parent);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			children.remove(element);
			if(element.getName().equals(childName)) {	
				childOccurrence++;
			}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		return childOccurrence;
		
	}
	
	
	
	
	/**
	 * find  if attrName is already present in selectedAttrArr and if true return
	 * found attr
	 * 
	 * @param parent	parent tag on which search attribute
	 * @param attrName	the attribute to find
	 * @return the attribute found or null
	 */
	
	public static XmlAttribute findChildSelectedAttrFromName(XmlTag parent, String attrName) {
		ArrayList<XmlAttribute> selectedAttrArr = parent.getSelectedAttrArr();
		if (selectedAttrArr != null) {
			for(int i = 0; i < selectedAttrArr.size(); i++ ) {
				XmlAttribute attr = selectedAttrArr.get(i);
				if(attr.getName().equals(attrName) ){
					return attr;
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * find  attr by name in passed array of attributes.
	 * found attr
	 * 
	 * @param parent	parent tag on which search attribute
	 * @param attrName	the attribute to find
	 * @return the attribute found or null
	 */
	
	public static XmlAttribute findAttributeFromArrayOfAttributes(ArrayList<XmlAttribute> attrArr, String attrName) {
		if (attrArr != null) {
			for(int i = 0; i < attrArr.size(); i++ ) {
				XmlAttribute attr = attrArr.get(i);
				if(attr.getName().equals(attrName) ){
					return attr;
				}
			}
		}
		return null;
	}
	
	
	
	
	
	/**
	 * return root tag, the only one that hava parent == null
	 * 
	 * @return root tag
	 */
	
	public static XmlTag getRoot() {
		XmlTag root = XmlTagDao.getInstance().getRootTag();
		root.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(root));
		return root; 
		
	}
	
	
	
	
	/** 
	 * return tag's not required children;
	 * 
	 * @param parent parent tag
	 * @return tag's not required children or null
	 */
	
	public static  ArrayList<XmlTag> getNotRequiredChildren(XmlTag parent) {
		return XmlTagDao.getInstance().getNotRequiredChildrenFromTag(parent);
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
		return null;
	}
	
	
	
	
	/**
	 * Verify tagAttribute exception, adding or removing attribute based on exception
	 * 
	 * example : Tag  " component " have attribute " Cvendor ", but 
	 * 			 must not be specified for a component within a bundle.
	 * 
	 * for more info uncomment all System.out.println();
	 */
	public static void verifyTagAttributeException(XmlTag tag) {
		
		System.out.println(" veifying exceptions for tag " + tag.getName());
		
		/** verify consistency */
		if(tag.getTagAttributeExceptionArr() == null || tag.getTagAttributeExceptionArr().size() <= 0) return ;
		if(tag.getParent() == null) return ;
		if(tag.getAttrArr() == null || tag.getAttrArr().size() <= 0) return ;
		
		/** for each exception */
		for (int j = 0; j < tag.getTagAttributeExceptionArr().size(); j++) {
			
			/** flag to know if attribute is present in tag */
			boolean foundException = false;
			
			/** recovering exception */
			PDSCTagAttributeException exception = tag.getTagAttributeExceptionArr().get(j);
			
			/** if tags aren't the same return */
			if(!tag.getName().equals(exception.getTag().getName())) return;
				
			/** if parents aren't the same return */
			if(!tag.getParent().getName().equals(exception.getParent().getName())) return ;
			
			System.out.println("name and parent control passed: verifying if exception for attr : " + exception.getAttribute().getName() + " is present in attrArr ");
			
			/** for each attr */
			for (int i = 0; i < tag.getAttrArr().size(); i ++) {

				/** recovering attribute */
				XmlAttribute attr = tag.getAttrArr().get(i);
				
				/** if attributes coincides */
				if(attr.getName().equals(exception.getAttribute().getName())) {
					
					System.out.println("ok exception is present in attrArr");
					
					/** if this tag contains attribute change flag to true */
					foundException = true;
					
					/**
					 *  verify exception.
					 * 
					 *  0 = tag must not contains attribute
					 *  1 = tag must contains attribute
					 */
					if(exception.getException() == 0) {
						
						System.out.println("removing attr");
						
						tag.getAttrArr().remove(attr);
						
						/** removing attribute from selectedAttrArr */
						if(tag.getSelectedAttrArr() != null && tag.getSelectedAttrArr().size() > 0) {
							
							/** for each attr in selectedAttrArr */
							for (int h = 0; h < tag.getSelectedAttrArr().size(); h ++) {
								
								/** recovering selectedAttr */
								XmlAttribute selectedAttr = tag.getSelectedAttrArr().get(h);
								
								/** if selectedAttr coincides with exceptioAttr, remove it*/
								if(selectedAttr.getName().equals(exception.getAttribute().getName())) {
									
									tag.getSelectedAttrArr().remove(selectedAttr);
								}
							}
						}
					}
				}
			}
			
			/** if attrException is not present in tag */
			if(!foundException) {
				/**
				 *  verify exception.
				 * 
				 *  0 = tag must not contains attribute
				 *  1 = tag must contains attribute
				 */
				if(exception.getException() == 1) {
					
					/** adding attrbute */
					tag.getAttrArr().add(new XmlAttribute(exception.getAttribute() , tag));
				}
			}
			
		}
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
		
		/** verifying tag attribute exception */
		tag.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(tag));
		if(tag.getTagAttributeExceptionArr() != null ) verifyTagAttributeException(tag);
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** recursion */
				getCompleteTagFromTagInstance(child);
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

		tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
		tag.setPossibleValues(XmlTagDao.getInstance().getTagPossibleValues(tag));
		
		tag.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(tag));
		if(tag.getTagAttributeExceptionArr() != null ) verifyTagAttributeException(tag);
		
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
	
	
	
	
	/**
	 * add required attributes inside passed tag
	 * 
	 * @param tag 
	 */
	
	public static void addRequiredAttr(XmlTag tag) {
		if(tag.getAttrArr() != null) {
			for ( int i = 0; i < tag.getAttrArr().size(); i++) {
				XmlAttribute attr = tag.getAttrArr().get(i);
				if(attr.isRequired()) tag.addSelectedAttr(attr);
			}
		}
	}
	
	
	
	
	/**
	 * return tag description
	 */
	public static String getTagDescription(XmlTag tag) {
		return XmlTagDao.getInstance().getTagDescriptionFromTagName(tag);
	}
	
	
	

	
	
}
