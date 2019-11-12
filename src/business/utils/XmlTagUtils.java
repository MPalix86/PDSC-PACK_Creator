package business.utils;

import java.util.ArrayList;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import model.xml.XmlAttribute;
import model.xml.XmlNameSpace;
import model.xml.XmlTag;

/**
 * Utils to manage PDSC XmlTag objects
 * 
 * NOTE : all functions in someNameUils class do not change the instance of the object
 *
 * @author mircopalese
 */
public class XmlTagUtils {

	/**
	 * Compare 2 tags, the comparison take place only on the "textual component"
	 * of the tag. This menas that the 2 tags are first converted in xml and the 
	 * resulting xml was compared.
	 * 
	 * @param tag1	tag to compare
	 * @param tag2	tag to compare
	 * @return	true if textual component of tags are equals
	 */
	public static boolean compareTagText (XmlTag tag1 , XmlTag tag2) {
		
		Document doc1 = new Document();
		Document doc2 = new Document();
		Element el1 = getJDomElementFromXmlTag(tag1);
		Element el2 = getJDomElementFromXmlTag(tag2);
		doc1.setRootElement(el1);
		doc2.setRootElement(el2);
		String str1 =  PDSCDocumentUtils.getStringFromJDomDocument(doc1);
		String str2 = PDSCDocumentUtils.getStringFromJDomDocument(doc2);
		if(str1.equals(str2)) return true;
		return false;
	}
	
	
	
	
	
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
		ArrayList <XmlTag> children = new ArrayList<XmlTag>();
		children.add(parent);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			children.remove(element);
			if(element != null && element.getName().equals(childName)) return element;
			if(element != null && element.getChildrenArr() != null ) element.getChildrenArr().forEach((c)-> children.add(c));
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
			if(element != null) {
				if(element.getName().equals(childName)) {	
					childOccurrence++;
				}
				if( element.getSelectedChildrenArr() != null ) {
					element.getSelectedChildrenArr().forEach((c)-> children.add(c));
				}
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
	 * Dependencies research on xml tag (graph).
	 * uncomment all Sytem.out.println(), run program and press add button on 
	 * tag customization frame to see how it works (based on Breadth first search)
	 * 
	 * @param parent tag on which check dependency
	 * @return first missing dependency
	 */
	public static boolean dependencyCheck(XmlTag parent) {
		ArrayList <XmlTag> children = new ArrayList();
		children.add(parent);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			//System.out.println("analyzing " +  element.getName());
			children.remove(element);
			ArrayList <XmlTag> requiredChildren = new ArrayList<XmlTag>(getRequiredChildren(element));
			if(!requiredChildren.isEmpty()) {
				//System.out.println(element.getName() + " has dependencies");
				for(int i = 0; i < requiredChildren.size(); i++) {
					XmlTag requiredChild = requiredChildren.get(i);
					//System.out.println("check if there is the denpendency :  "  + requiredChild.getName());
					boolean found = false;
					if(element.getSelectedChildrenArr() != null) {
						//System.out.println("let's see if " +  requiredChild.getName() + " is present in selected children of " +element.getName());
						for(int j = 0; j < element.getSelectedChildrenArr().size(); j++) {
							XmlTag selectedChild = element.getSelectedChildrenArr().get(j);
							//System.out.println("analizyng selected child " + selectedChild.getName());
							
							String requiredChildName = requiredChild.getName();
							requiredChildName.replaceAll("\\s+","");
							
							String selectedChildName = selectedChild.getName();
							selectedChildName.replaceAll("\\s+","");
							
							//WTF requiredChildName == requiredChildName don't work !!!!
							
							if(requiredChildName.equals(selectedChildName)) {
								found = true;
							}
							if( found ) { /*System.out.println(selectedChild .getName() + " found ");*/break;}
						}
						if(!found) {/*System.out.println(requiredChild.getName() + " not found ");*/return true;}
					}
					else {/*System.out.println(requiredChild.getName() + " not found ");*/return true;}

				}
			}else {/*System.out.println(element.getName() + " has no dependencies");*/}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		return false;
	}
	
	
	
	
	
	/** 
	 * if there are required children return requiredChildren array otherwise 
	 * return empty array 
	 * 
	 * @param parent tag on which required children
	 * @return ArrayList containing required children
	 */ 
	public static ArrayList<XmlTag> getRequiredChildren(XmlTag parent){
		ArrayList <XmlTag> requiredChildren = new ArrayList<XmlTag>();
		if(parent.getChildrenArr() != null) {
			ArrayList <XmlTag> children = new ArrayList<XmlTag>(parent.getChildrenArr());
			for (int i = 0 ; i < children.size(); i++) {
				if(children.get(i).isRequired()) {
					requiredChildren.add(children.get(i));
				}
			}
		}
		return requiredChildren;
	}
	
	
	
	/**
	 * Verify if attribute is already present in selctedAttrArr
	 * 
	 * @param tag tag to control
	 * @param attr attribute to control
	 * @return true if attribute is in selctedAttrArr
	 */
	public static boolean tagHasAttribute(XmlTag tag, XmlAttribute attr){
		if(tag.getSelectedAttrArr() != null) {
			for(int i = 0; i < tag.getSelectedAttrArr().size(); i++) {
				XmlAttribute selectedAttr = tag.getSelectedAttrArr().get(i);
				if (attr.getName().equals(selectedAttr.getName())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	
	/**
	 * Transform a XmlTag into JDom element
	 * 
	 * @param tag tag to transform in JDom element
	 * @return corresponding JDom element for passed tag
	 */
	public static Element getJDomElementFromXmlTag(XmlTag tag) {
		Element parent = new Element(tag.getName());
		
		addAttributeFromXmlTagToJdomElement(tag,parent);
		
		if( tag.getSelectedChildrenArr() != null) {								
			ArrayList<XmlTag> xmlChildren = tag.getSelectedChildrenArr();
			for(int i = 0; i < xmlChildren.size(); i++) {						
				XmlTag child = xmlChildren.get(i);	
					parent.addContent(getJDomElementFromXmlTag(child));		
			}
		}
		else if(tag.getContent() != null) parent.setText(tag.getContent());	
		return parent;
		
	}
	
	
	
	
	
	/**
	 * add all XmlTag's (tag) selected attributes inside passed JDom Element (el)
	 * 
	 * @param tag XmlTag with selected attributes
	 * @param el  JDom Element in which to move attributes
	 */

	private static void addAttributeFromXmlTagToJdomElement(XmlTag tag,Element parent) {
		
		ArrayList<XmlAttribute> xmlAttrArr = tag.getSelectedAttrArr();
		
		/** if tag contains attributes */
		if(xmlAttrArr != null) {
			
			for(int j = 0; j < xmlAttrArr.size(); j++) {	
				
				/** saving current attribute */
				XmlAttribute xmlAttr = xmlAttrArr.get(j);
				
				/** conversion from XmlAttribute to JDom attribute */
				Attribute attribute ;
				if(xmlAttr.getValue() != null )  attribute = new Attribute( xmlAttr.getName(),xmlAttr.getValue());
				else attribute = new Attribute( xmlAttr.getName(),"");
				
				/** check attribute's value */
				if( xmlAttr.getValue() == "" || xmlAttr == null ) {		
					//System.out.println("Attribute :" + xmlAttr.getName() + " was no inserted ");
				}
				
				/** if attribute have name space */
				if(xmlAttr.getNameSpace() != null) {
					XmlNameSpace xmlNameSpace = xmlAttr.getNameSpace();
					Namespace ns = Namespace.getNamespace(xmlNameSpace.getPrefix(), xmlNameSpace.getUrl());
					attribute.setNamespace(ns);
				}
				
				/** add JDom attribute inside JDome element parent */
				if (attribute != null) parent.setAttribute(attribute);
			}
		}
	}
	
	
	
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	public static Document getJDomDocumentFromXmlTag(XmlTag tag) {
		
		Document doc = new Document();	
		XmlTag xmlTag = tag;
		Element el = XmlTagUtils.getJDomElementFromXmlTag(xmlTag);														
		doc.setRootElement(el);
		return doc;
		
	}
	
	
	
	
	
	

	

}
