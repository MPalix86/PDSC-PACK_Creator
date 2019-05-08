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
	 * dependencies research on xml tag (graph).
	 * uncomment all Sytem.out.println(), run program and press add button on 
	 * tag customization frame to see how it works (based on Breadth first search)
	 */
	public static XmlTag dependencyCheck(XmlTag parent) {
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
					if(element.getSelectedChildren() != null) {
						for(int j = 0; j < element.getSelectedChildren().size(); j++) {
							XmlTag child = element.getSelectedChildren().get(j);
							if(requiredChild.getClass() == child.getClass()) {
								found = true;
							}
							if( found ) {/* System.out.println(child.getName() + " found ");*/break;}
						}
						if(!found) {/*System.out.println(requiredChild.getName() + " not found ");*/return requiredChild;}
					}
					else {/*System.out.println(requiredChild.getName() + " not found ");*/return requiredChild;}

				}
			}else {/*System.out.println(element.getName() + " has no dependencies");*/}
			if( element.getSelectedChildren() != null ) {
				element.getSelectedChildren().forEach((c)-> children.add(c));
			}
		}
		return null;
	}
	
	
	
	/* 
	 * if there are required children return requiredChildren array otherwise 
	 * return empty array 
	 */ 
	public static ArrayList<XmlTag> getRequiredChildren(XmlTag parent){
		ArrayList <XmlTag> requiredChildren = new ArrayList();
		if(parent.getChildren() != null) {
			ArrayList <XmlTag> children = new ArrayList(parent.getChildren());
			for (int i = 0 ; i < children.size(); i++) {
				if(children.get(i).isRequired()) {
					requiredChildren.add(children.get(i));
				}
			}
		}
		return requiredChildren;
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
			tagClass = (Class<XmlTag>) Class.forName(className);
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
	
	
	

	/*
	 * return new instance of passed class
	 */
	public static XmlTag getNewinstance(Class cl) {
		XmlTag tag = new XmlTag();
		try {
			tag = (XmlTag) cl.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return tag;
	}
	
	
	
	
}
