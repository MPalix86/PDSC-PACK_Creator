package model.pdsc.attributes;

import model.XmlAttribute;
import model.XmlNameSpace;

/** 
 * Attribute definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class XsnoNamespaceSchemaLocation extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "noNamespaceSchemaLocation";
	
	/** attribute's posssible values */
	private final static Object possibleValues = new String();
	
	/** attribute's name space */
	private final static XmlNameSpace nameSpace = new XmlNameSpace("xs", "http://www.w3.org/2001/XMLSchema-instance");
	
	
	
	
	/**
	 * Create new instance of noNamespaceSchemaLocation attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public XsnoNamespaceSchemaLocation(boolean required) {
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
	
	
	
	
	/**
	 * return attribute's possible value
	 * 
	 * @return the possiblevalues
	 */
	
	public XmlNameSpace getNameSpace() {
		return nameSpace;
	}
	
}
