package business;

import java.util.ArrayList;

import dao.XmlAttributeDao;
import dao.XmlTagDao;
import model.XmlAttribute;
import model.XmlTag;

public class XmlTagBusiness {
	private static XmlTagBusiness instance;
	
	
	/*
	 * SINGLETON
	 */
	public static XmlTagBusiness getInstance(){
		if(instance == null)
			instance = new XmlTagBusiness();
		return instance;
	}

	
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
	
	
	
//	public XmlTag addChildInSelectecChild(XmlTag parent , XmlTag child) {
//		
//		XmlTag ModelChild = 
//		
//		/** if max child number is > 0, add child */
//		if(child.getMax() > 0 ) {
//			
//			/** creating new child instance of selected child with parent , passed parent */
//			XmlTag newChild = new XmlTag(child, parent);
//			
//			/** adding new child in selectedChildArr of new parent */
//			parent.addSelectedChild(newChild);
//			
//			/** maximum number of possible child in the model instance is reduced by one */
//			child.setMax(child.getMax() -1);
//			
//		}
//		
//		return parent;
//	}
	
	
	
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
	
	
	
	public static XmlTag getRoot() {
		XmlTag root = XmlTagDao.getInstance().getRootTag();
		root.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(root));
		return root; 
		
	}
	
	public static  ArrayList<XmlTag> getNotRequiredChildren(XmlTag parent) {

		return XmlTagDao.getInstance().getNotRequiredChildrenFromTag(parent);
	}
	
	

	public static XmlTag getCompleteTagFromNameAndParent(String name , XmlTag parent) {
		XmlTag tag = XmlTagDao.getInstance().getTagFromNameAndParent(name, parent);
		if(tag != null) return getCompleteTag(tag);
		return null;
	}
	
	
	public static XmlTag getCompleteTag(XmlTag tag) {
		
		tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
		tag.setPossibleValues(XmlTagDao.getInstance().getTagPossibleValues(tag));
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** recursion */
				getCompleteTag(child);
			}
		}
		
		return tag;
	}
	
	
	
}
