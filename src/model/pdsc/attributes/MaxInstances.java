package model.pdsc.attributes;

import model.XmlAttribute;
import model.pdsc.attributes.values.IntEnum;

public class MaxInstances extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "maxInstances";
	
	/** attribute's possible values */
	private final static Object possibleValues = new IntEnum(1,10);
	
	
	
	
	
	/**
	 * Create new instance of attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public MaxInstances(boolean required) {
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
