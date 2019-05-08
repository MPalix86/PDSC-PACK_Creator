package business;

import java.util.ArrayList;

import model.XmlTag;

public class Utils {
	private static Utils instance;
	
	//--------------------------------------------------------------------------getInstance() 
	public static synchronized Utils getInstance(){
		if(instance==null)
			instance = new Utils();
		return instance;
	}
	
	//--------------------------------------------------------------------------firstLetterCaps() 
	  public static String firstLetterCaps ( String data )
	  {
	      String firstLetter = data.substring(0,1).toUpperCase();
	      String restLetters = data.substring(1).toLowerCase();
	      return firstLetter + restLetters;
	  }
	  
		//--------------------------------------------------------------------------firstLetterCaps() 
	  public static void print( String message )
	  {
		  System.out.println(message);
	  }
	  
	  
		public static void printTag(XmlTag parent) {
			
			ArrayList <XmlTag> children = new ArrayList();
			children.add(parent);
			while(!children.isEmpty()) {
				Utils.print("");
				XmlTag element = children.get(0);
				children.remove(element);
				if(element.getParent() != null) {
					System.out.print("parent -> " + element.getParent().getName()+ "   ");
				}
				System.out.print("tag: " + element.getName());
				if(element.getSelectedAttrArr() !=  null) {
					element.getSelectedAttrArr().forEach((a)->System.out.print(" " + a.getName()));
				}
				if( element.getSelectedChildren() != null ) {
					element.getSelectedChildren().forEach((c)-> children.add(c));
				}	
			
			}
		}
}
