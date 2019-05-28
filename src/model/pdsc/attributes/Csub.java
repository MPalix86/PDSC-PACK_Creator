package model.pdsc.attributes;

import model.XmlAttribute;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Csub extends XmlAttribute {

	/** attribute's name */
	private final static String name = "Csub";
	
	/** attribute's possible values */
	private final static Object possibleValues = new String("");
	
	private final static int minLenght = 0;
	private final static int maxLenght = 32;
	
	
	
	
	
	/**
	 * Create new instance of Cgroup attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Csub(boolean required) {
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