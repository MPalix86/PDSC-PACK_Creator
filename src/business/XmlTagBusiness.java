package business;

import java.util.ArrayList;

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
}
