package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlTag;
import model.pdsc.attributes.values.BooleanEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class IsDefaultVariant extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "isDefaultVariant";
	
	/** attribute's possible values */
	private final static Object possibleValues = new BooleanEnum();
	
	
	
	   
	
	/**
	 * Create new instance of id attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public IsDefaultVariant(boolean required, XmlTag tag) {
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