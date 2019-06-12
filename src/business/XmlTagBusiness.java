package business;

import java.util.ArrayList;

import model.XmlAttribute;
import model.XmlTag;

public class XmlTagBusiness {

	
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
	
	
	
	public static void printTag(XmlTag tag,int level) {
		
		System.out.println("");
		
		XmlTag parent =  tag;
		
		for (int i = 0; i < level ; i++) {
			System.out.print("	");
		}
		
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
				printTag(child,level);
			}
		}	
	}
}
