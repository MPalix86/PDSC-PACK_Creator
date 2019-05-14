package model.pdsc.attributes;

import model.XmlAttribute;
import model.pdsc.attributes.values.DsecureEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Dsecure extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "Dsecure";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new DsecureEnum();
	
	
	
	
	
	/**
	 * Create new instance of Dsecure attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Dsecure(boolean required) {
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
