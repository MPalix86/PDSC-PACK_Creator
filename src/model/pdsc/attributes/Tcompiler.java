package model.pdsc.attributes;

import model.XmlAttribute;
import model.pdsc.attributes.values.CompilerEnum;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Tcompiler extends XmlAttribute {
	
	/** attribute's name */
	private final static String name = "Tcompiler";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new CompilerEnum();
	
	
	
	
	
	/**
	 * Create new instance of Tcompiler attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Tcompiler(boolean required) {
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
