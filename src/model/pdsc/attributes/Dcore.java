package model.pdsc.attributes;

import model.XmlAttribute;
import model.pdsc.attributes.values.DcoreEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Dcore extends XmlAttribute {
	
	/** attribute's name */
	private final static String name = "Dcore";
	
	/** attribute's possible values */
	private final static Object possibleValues = new DcoreEnum();;
	
	
	
	
	
	/**
	 * Create new instance of Dcore attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Dcore(boolean required) {
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
