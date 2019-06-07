package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlTag;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class DsubFamily extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "DsubFamily";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new String();
	
	
	
	
	
	/**
	 * Create new instance of DsubFamily attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public DsubFamily(boolean required, XmlTag tag) {
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
