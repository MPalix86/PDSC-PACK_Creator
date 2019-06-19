package business;

import java.util.ArrayList;

import model.XmlAttribute;
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
						if(!found) {/*System.out.println(requiredChild.getName() + " not found ");*/return requiredChild;}
					}
					else {/*System.out.println(requiredChild.getName() + " not found ");*/return requiredChild;}

				}
			}else {/*System.out.println(element.getName() + " has no dependencies");*/}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		return null;
	}
	
	
	
	
	/* 
	 * dependencies research on xml tag (graph).
	 * uncomment all Sytem.out.println(), run program and press add button on 
	 * tag customization frame to see how it works (based on Breadth first search)
	 */
	public static void addRequiredTags(XmlTag parent) {
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
						if(!found) {
							/** if max child number is > 0, add child */
							if(requiredChild.getMax() > 0 ) {
								
								/** creating new child instance of selected child with parent , passed parent */
								XmlTag newChild;
								if(requiredChild.getParent() != null) newChild = new XmlTag(requiredChild, requiredChild.getParent());
								else newChild = new XmlTag(requiredChild);
								
								/** adding new child in selectedChildArr of new parent */
								requiredChild.getParent().addSelectedChild(newChild);
								
								/** maximum number of possible child in the model instance is reduced by one */
								requiredChild.setMax(requiredChild.getMax() -1);
								
							}
						}
					}
					else {
						/** if max child number is > 0, add child */
						if(requiredChild.getMax() > 0 ) {
							
							/** creating new child instance of selected child with parent , passed parent */
							XmlTag newChild;
							if(requiredChild.getParent() != null) newChild = new XmlTag(requiredChild, requiredChild.getParent());
							else newChild = new XmlTag(requiredChild);
							
							/** adding new child in selectedChildArr of new parent */
							requiredChild.getParent().addSelectedChild(newChild);
							
							/** maximum number of possible child in the model instance is reduced by one */
							requiredChild.setMax(requiredChild.getMax() -1);
							
						}
					}

				}
			}else {/*System.out.println(element.getName() + " has no dependencies");*/}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
	}
	
	
	
	
	/* 
	 * if there are required children return requiredChildren array otherwise 
	 * return empty array 
	 */ 
	public static ArrayList<XmlTag> getRequiredChildren(XmlTag parent){
		ArrayList <XmlTag> requiredChildren = new ArrayList();
		if(parent.getChildrenArr() != null) {
			ArrayList <XmlTag> children = new ArrayList(parent.getChildrenArr());
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
	
	
	
	
	/**
	 * make selected number of copy for selected tag
	 * 
	 * @return void
	 */
	
	public static XmlTag cloneTag(XmlTag tag) {
		XmlTag parent = null;
		XmlTag clone = null;
		if (tag.getParent() != null) {
			parent = tag.getParent();
			
			clone = new XmlTag(tag,tag.getParent());
			
			parent.addSelectedChild(clone);
		}	
		
		return clone;
	}
		
		
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
			
		

}
