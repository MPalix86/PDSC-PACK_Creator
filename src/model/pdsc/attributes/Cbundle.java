package model.pdsc.attributes;

import model.XmlAttribute;

public class Cbundle extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "Cbundle";
	
	/** attribute's possible values */
	private final static Object possibleValues = new String("");
	
	
	
	
	
	/**
	 * Create new instance of Cclass attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Cbundle(boolean required) {
		super(name, required, possibleValues);
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
