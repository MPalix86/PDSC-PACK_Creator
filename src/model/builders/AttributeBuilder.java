package model.builders;

import model.XmlAttribute;
import model.XmlNameSpace;

/**
 * Attribute's builder class.
 * 
 * @authorMirco Mirco Palese
 */

public final class AttributeBuilder {
	
/** 
 * ===============================
 * attribute's required parameters
 * ===============================
 */
	
	/** attribute's name */
	private String name;
	
	/** attribute's obligatoriness */
	private boolean required;
	
	/** attribute's possible values */
	private Object possibleValues;
	
	
	
	
/** 
 * ===============================
 * attribute's optional parameters
 * ===============================
 */
	
	/** attribute's value */
	private String value;
	
	/** attribute's default value */
	private String defaultValue;
	
	/** attribute's name space */
	private XmlNameSpace nameSpace ;
	
	
	
	
	/**
	 * Attribute's builder. Contains required parameters.
	 * 
	 * @param name attribute's name
	 * @param possibleValues attribute's possible values objcet
	 * @param required  attribute's obligatoriness
	 */
	
	public AttributeBuilder(String name, Object possibleValues, boolean required) {
		this.name = name;
		this.possibleValues = possibleValues;
		this.required = required;
	}
	
	
	
	
	/**
	 * Attribute's builder. Contains required parameters.
	 * 
	 * @param name attribute's name
	 * @param possibleValues attribute's possible values objcet
	 */
	
	public AttributeBuilder(String name, Object possibleValues) {
		this.name = name;
		this.possibleValues = possibleValues;
	}
	
	
	
	
	/**
	 * Set attribute's value
	 * 
	 * @param value attribute's value
	 * @return AttributeBuilder instance
	 */
	
	public AttributeBuilder value(String value) {
		this.value = value;
		return this;
	}
	
	
	
	
	/**
	 * Set attribute's name space
	 * 
	 * @param nameSpace attribute's name space
	 * @return AttributeBuilder instance
	 */
	
	public AttributeBuilder XmlNameSpace(XmlNameSpace nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}
	
	
	
	
	/**
	 * Set attribute's default value
	 * 
	 * @param defaultValue attribute's value
	 * @return AttributeBuilder instance
	 */
	
	public AttributeBuilder defaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}
	
	
	
	
	
	/**
	 * build XmlAttribute's instance
	 * 
	 * @return XmlAttribute's instance
	 */
	
	public XmlAttribute build(){
		return new XmlAttribute(this); 
	}
	
	
	
	
	
	/**
	 * return attribute's name
	 * 
	 * @return attribute's name
	 */
	
	public String getName() {
		return name;
	}
	
	
	
	
	
	/**
	 * true if attribute is required
	 * 
	 * @return true if attribute is required
	 */
	
	public boolean isRequired() {
		return required;
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
	 * return attribute's values
	 * 
	 * @return attribute's values
	 */
	
	public String getValue() {
		return value;
	}
	
	
	
	
	
	/**
	 * returnattribute's default values
	 * 
	 * @return attribute's default values
	 */
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	
	
	
	
	/**
	 * returnattribute's name space
	 * 
	 * @return attribute's name space
	 */
	
	public XmlNameSpace getNameSpace() {
		return nameSpace;
	}
	
	
	
	
	
}
