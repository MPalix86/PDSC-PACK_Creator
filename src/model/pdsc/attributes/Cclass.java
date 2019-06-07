package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlTag;

public class Cclass extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "Cclass";
	
	/** attribute's possible values */
	private final static Object possibleValues = new String("");
	
	
	
	
	
	/**
	 * Create new instance of Cclass attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Cclass(boolean required, XmlTag tag) {
		super(name, required, possibleValues, tag);
	}
	
	
	
	
	/**
	 * return attribute's name
	 * 
	 * @return the name
	 */
	
	public String getName() {
		return name;
	}
	
	
	
	
	
	/**
	 * return attribute's possible value
	 * 
	 * @return the possiblevalues
	 */
	
	public static Object getPossiblevalues() {
		return possibleValues;
	}


}
