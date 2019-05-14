package model.pdsc.attributes;

import model.XmlAttribute;
import model.pdsc.attributes.values.DtzEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Dtz extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "Dtz";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new DtzEnum();
	
	
	
	
	
	/**
	 * Create new instance of Dtz attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Dtz(boolean required) {
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
 