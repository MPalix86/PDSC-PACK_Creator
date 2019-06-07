package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlTag;
import model.pdsc.attributes.values.DeviceVendorEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Dvendor extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "Dvendor";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new DeviceVendorEnum();
	
	
	
	
	
	/**
	 * Create new instance of Cclass attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Dvendor(boolean required, XmlTag tag) {
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
