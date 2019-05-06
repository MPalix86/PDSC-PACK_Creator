package business;

import java.util.ArrayList;

import model.XmlTag;

public class TagCustomizationBusiness {
	private XmlTag model;

	
	
	
	
	public XmlTag getModel() {
		return model;
	}






	public void setModel(XmlTag model) {
		this.model = model;
	}






	/* 
	 * Breadth first search algorithm:
	 * it is exploited BFS algorithm to find user's selected child tag starting 
	 * from parent
	 * 
	 * to find user's selected child starting from parent, it is compared every 
	 * child's class with user's selected tag class. 
	 * 
	 * NOTE: name of the class that represents tag, is always the same as the 
	 * name of the tag 
	 * 
	 */
	public static XmlTag findSelectedChild(XmlTag parent, String childClassName) {
		ArrayList <XmlTag> children = new ArrayList();
		children.add(parent);
		while(!children.isEmpty()) {
			Class<XmlTag> tagClass = getClassFromClassName(childClassName);
			XmlTag element = children.get(0);
			children.remove(element);
			if(element.getClass() == tagClass) {								
				return element;
			}
			if( element.getChildren() != null ) {
				element.getChildren().forEach((c)-> children.add(c));
			}
		}
		return null;
	}
	
	
	
	
	
	
	/*
	 * return Class object that represent user's selected tag.
	 * 
	 * Class.forname(String classname) return class instance of classname passed 
	 * like string: it's like saying: classname tagClass; but it is dynamic.
	 */
	public static Class<XmlTag> getClassFromClassName(String className){
		Class<XmlTag> tagClass = null;
		try {
			tagClass = (Class<XmlTag>) Class.forName("model.pdscTag." + Utils.firstLetterCaps(className));
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return tagClass;
	}
	
	
	
	
	
	
	/* 
	 * Return instance of passed class
	 * 
	 * tagClass.newInstance() return new instance of classname;
	 * it's like saying: new classname();
	 */
	public static XmlTag getClassInstanceFromClassName(String className) {
		Class<XmlTag> cl = getClassFromClassName(className);
		XmlTag instance = null;
		try {
			instance = (XmlTag)cl.newInstance(); 									
		} 
		catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return instance;
	}
	
	
	
	public static void printTag(XmlTag parent) {
		
		ArrayList <XmlTag> children = new ArrayList();
		children.add(parent);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			children.remove(element);
			if (element.getParent()!= null) {
				System.out.print("parent : " + element.getParent().getName());
			}
			Utils.print("tag: " + element.getName());
			if(element.getSelectedAttrArr() !=  null) {
				element.getSelectedAttrArr().forEach((a)->Utils.print(" " + a.getName()));
			}
			if( element.getSelectedChildren() != null ) {
				element.getSelectedChildren().forEach((c)-> children.add(c));
			}
		}
	}
	
	
	
	
}
