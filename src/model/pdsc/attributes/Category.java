package model.pdsc.attributes;

import model.XmlAttribute;
import model.pdsc.attributes.values.FileCategoryEnum;

public class Category extends XmlAttribute{
	
	/** attribute's name */
	private final static String name = "category";
	
	/** attribute's possible values */
	private final static Object possibleValues = new FileCategoryEnum();
	
	
	
	
	
	/**
	 * Create new instance of attribute
	 * 
	 * @param required attribute's obligatoriness
	 */
	
	public Category(boolean required) {
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
	 * @return the possible values
	 */
	
	public static Object getPossiblevalues() {
		return possibleValues;
	}


}
