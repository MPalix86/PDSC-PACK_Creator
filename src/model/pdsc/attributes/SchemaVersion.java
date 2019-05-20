package model.pdsc.attributes;

import model.XmlAttribute;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class SchemaVersion extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "schemaVersion";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new String();
	
	
	
	
	
	/**
	 * Create new instance of schemaVersion attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public SchemaVersion(boolean required) {
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