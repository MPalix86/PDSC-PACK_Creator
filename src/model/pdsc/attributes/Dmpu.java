package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlTag;
import model.pdsc.attributes.values.DmpuEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Dmpu extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "Dmpu";
	
	/** attribute's possible values */
	private final static Object possibleValues = new DmpuEnum();
	
	
	
	
	
	/**
	 * Create new instance of Dmpu attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Dmpu(boolean required, XmlTag tag) {
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
