package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlTag;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Source extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "source";
	
	/** attribute's possible values */
	private final static Object possibleValues = new String();
	
	
	
	
	
	/**
	 * Create new instance of attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Source(boolean required, XmlTag tag) {
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
	 * @return the possible values
	 */
	
	public static Object getPossiblevalues() {
		return possibleValues;
	}
}
