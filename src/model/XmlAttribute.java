package model;

import model.builders.AttributeBuilder;

/**
 * Xml attribute abstraction 
 * 
 * @author Mirco Palese
 */

public class XmlAttribute {
	
	/** attribute's name */
	private String name;
	
	/** attribute's obligatoriness */
	protected boolean required;
	
	/** attribute's possible values */
	protected Object possibleValues;
	
	/** attribute's value */
	protected String value = "";
	
	/** attribute's default value */
	protected String defaultValue;
	
	/** attribute's name space */
	protected XmlNameSpace nameSpace ;
	
	
	/** 
	 * builder pattern constructor
	 * 
	 * @param builder attribute builder builder pattern
	 */
	
	public XmlAttribute(AttributeBuilder builder) {
		this.name = builder.getName();
		this.value = builder.getValue();
		this.defaultValue = builder.getDefaultValue();
		this.required = builder.isRequired();
		this.possibleValues = builder.getPossibleValues();
		this.nameSpace = builder.getNameSpace();
	}
	
	
	
	
	/** 
	 * Return new Attribute instance
	 * 
	 * @param name attribute's name
	 * @param required attribute's obligatoriness
	 * @param possibleValues attribute's possibleVlaues
	 */
	
	public XmlAttribute(String name, boolean required, Object possibleValues) {
		this.name = name;
		this.required = required;
		this.possibleValues = possibleValues;
	}
	
	
	
	
	/** 
	 * Return new Attribute instance
	 * 
	 * @param name attribute's name
	 * @param required attribute's obligatoriness
	 * @param possibleValues attribute's possibleVlaues
	 * @param defaultValue attribute's defaultValue
	 */
	
	public XmlAttribute(String name, boolean required, Object possibleValues, String defaultValue) {
		this.name = name;
		this.required = required;
		this.possibleValues = possibleValues;
	}
	
	
	
	
	/** 
	 * Return new Attribute instance copying passed instance of XmlAttribute
	 * 
	 * @param attribute to be copied
	 */
	
	public XmlAttribute(XmlAttribute attr) {
		if ( attr.getName() != null ) this.name = attr.getName();
		
		this.required = attr.isRequired();
		
		if ( attr.getPossibleValues() != null ) this.possibleValues = attr.getPossibleValues();
		if ( attr.getValue() != null) this.value = new String(attr.getValue());
		if ( attr.getDefaultValue() != null) this.defaultValue = new String(attr.getDefaultValue());
		if ( attr.getNameSpace() != null) this.nameSpace = new XmlNameSpace(attr.getNameSpace());
	}
	
	
	
	
	/** 
	 * void constructor
	 * 
	 * @return new void instance
	 */
	
	//public XmlAttribute() {	}
	
	
	
	
	
	/**
	 * return attribute's name
	 * 
	 * @return return attribute's name
	 */
	
	public String getName() {
		return name;
	}
	
	
	
	
	
	/**
	 * set attribute's name
	 * 
	 * @param name attribute's name
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	/**
	 * return attribute's obligatoriness
	 * 
	 * @return return attribute's obligatoriness
	 */
	
	public boolean isRequired() {
		return required;
	}
	
	
	
	
	
	/**
	 * set attribute's obligatoriness
	 * 
	 * @param required attribute's obligatoriness (true-false)
	 */
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	
	
	
	/**
	 * return attribute's possible values
	 * 
	 * @return attribute's possible values
	 */
	
	public Object getPossibleValues() {
		return possibleValues;
	}
	
	
	
	
	
	/**
	 * set attribute's possible values
	 * 
	 * @param possibleValues the possibleValues to set
	 */
	
	public void setPossibleValues(Object possibleValues) {
		this.possibleValues = possibleValues;
	}
	
	
	
	
	
	/**
	 * return attribute's values
	 * 
	 * @return the value
	 */
	
	public String getValue() {
		return value;
	}
	
	
	
	
	
	/**
	 * set attribute's values
	 * 
	 * @param value the value to set
	 */
	
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
	/**
	 * return attribute's default values
	 * 
	 * @return the defaultValue
	 */
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	
	
	
	
	/**
	 * set attribute's default values
	 * 
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	
	
	
	/**
	 * return attribute's name space
	 *  
	 * @return the nameSpace
	 */
	
	public XmlNameSpace getNameSpace() {
		return nameSpace;
	}
	
	
	
	
	
	/**
	 * set attribute's name space
	 * 
	 * @param nameSpace the nameSpace to set
	 */
	
	public void setNameSpace(XmlNameSpace nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	
	
	
}
