package model;

import java.io.File;

/**
 * Xml attribute abstraction 
 * 
 * @author Mirco Palese
 */

public class XmlAttribute {
	
	/** attribute id (id of attributes table on db ) */
	private Integer attrId;
	
	/** attribute's name */
	private String name;
	
	/** attribute's obligatoriness */
	protected boolean required;
	
	private String possibleValuesType;
	
	/** attribute's possible values */
	protected XmlEnum possibleValues;
	
	/** attribute's value */
	protected String value ;
	
	/** attribute's default value */
	protected String defaultValue;
	
	/** attribute's name space */
	protected XmlNameSpace nameSpace ;
	
	/** tag's owner of this attribute*/
	protected XmlTag tag;
	
	/** 
	 * Some tag can have associated source file for pack creation;
	 * this file represent source file path on filesystem
	 * 
	 * NOTE: before setting control that possibleValuesType is equals to File
	 */
	private File file;
	
	
	
	
	public final static  int ALREADY_PRESENT = 1;
	public final static  int IS_STANDARD_FOR_TAG = 2;
	public final static  int IS_GENRAL_PDSC = 3;
	public final static  int IS_NEW = 4;
	public final static  int INVALID_NAME = 5;
	
	
	





	/** 
	 * Return new Attribute instance
	 * 
	 * @param name attribute's name
	 * @param required attribute's obligatoriness
	 * @param possibleValues attribute's possibleVlaues
	 */
	
	public XmlAttribute(String name, boolean required, XmlEnum possibleValues,XmlTag tag ,String possiValuesType) {
		this.name = name;
		this.required = required;
		this.possibleValues = possibleValues;
		this.possibleValuesType = possiValuesType;
		this.tag = tag;
	}
	
	
	
	
	/** 
	 * Return new Attribute instance
	 * 
	 * @param name attribute's name
	 */
	
	public XmlAttribute(String name,XmlTag tag, String possibleValuesType) {
		this.name = name;
		this.tag = tag;
		this.possibleValuesType = possibleValuesType;
		this.required = false;
	}
	
	

	
	public XmlAttribute(Integer attrId, String name, boolean required, XmlEnum possibleValues,String defaultValue,XmlNameSpace nameSpace,XmlTag tag, String possibleValuesType) {
		this.name = name;
		this.required = required;
		this.possibleValues = possibleValues;
		this.tag = tag;
		this.nameSpace = nameSpace;
		this.defaultValue = defaultValue;
		this.attrId = attrId;
		this.possibleValuesType = possibleValuesType;
	}
	
	
	
	public XmlAttribute(Integer attrId, String name, boolean required, XmlEnum possibleValues,String defaultValue,XmlTag tag , String possibleValuesType) {
		this.name = name;
		this.required = required;
		this.possibleValues = possibleValues;
		this.tag = tag;
		this.defaultValue = defaultValue;
		this.attrId = attrId;
		this.possibleValuesType = possibleValuesType;
	}
	
	
	
	
	/** 
	 * Return new Attribute instance
	 * 
	 * @param name attribute's name
	 * @param required attribute's obligatoriness
	 * @param possibleValues attribute's possibleVlaues
	 * @param defaultValue attribute's defaultValue
	 * @param tag tag attribute's owner (or parent)
	 */
	
	public XmlAttribute(String name, boolean required, XmlEnum possibleValues, String defaultValue, XmlTag tag , String possibleValuesType) {
		this.name = name;
		this.required = required;
		this.possibleValues = possibleValues;
		this.tag = tag;
		this.possibleValuesType = possibleValuesType;
	}
	
	
	
	
	
	/** 
	 * Return new Attribute instance copying passed instance of XmlAttribute. 
	 * Tag parent is set to passed tag.
	 * 
	 * @param attribute to be copied
	 */
	
	public XmlAttribute(XmlAttribute attr,XmlTag tag) {
		if ( attr.getName() != null ) this.name = attr.getName();
		
		this.required = attr.isRequired();
		
		if ( attr.getPossibleValues() != null ) this.possibleValues = attr.getPossibleValues();
		if ( attr.getValue() != null) this.value = new String(attr.getValue());
		if ( attr.getDefaultValue() != null ) this.defaultValue = new String(attr.getDefaultValue());
		if ( attr.getNameSpace() != null ) this.nameSpace = new XmlNameSpace(attr.getNameSpace());
		if ( attr.getAttrId() != null ) this.attrId = attr.getAttrId();
		if ( attr.getPossibleValuesType() != null ) this.possibleValuesType = attr.getPossibleValuesType();
		if ( attr.getFile()!= null ) this.file = attr.getFile();
		 
		this.tag=tag;
	}
	
	

	
	
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
	
	public XmlEnum getPossibleValues() {
		return possibleValues;
	}
	
	
	
	
	
	/**
	 * set attribute's possible values
	 * 
	 * @param possibleValues the possibleValues to set
	 */
	
	public void setPossibleValues(XmlEnum possibleValues) {
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
	
	
	
	
	/**
	 * set tag attribute's owner
	 * 
	 * @param tag the tag to set
	 */
	
	public void setTag(XmlTag tag) {
		this.tag = tag;
	}
	
	
	
	
	/**
	 * get tag attribute's owner
	 * 
	 * @return tag attribute's owner
	 */
	
	public XmlTag getTag() {
		return this.tag;
	}
	
	
	
	
	
	/**
	 * Return attrID
	 * 
	 * @return the attrId
	 */
	
	public Integer getAttrId() {
		return attrId;
	}




	/**
	 * Set attrId
	 * 
	 * @param attrId the attrId to set
	 */
	
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	
	
	
	/**
	 * Return possibleValuesType
	 * 
	 * @return possibleValuesType
	 */
	public String getPossibleValuesType() {
		return this.possibleValuesType;
	}
	
	
	
	
	/**
	 * set possibleValuesType
	 * 
	 * @param s possibleValuesType
	 */
	public void setPossibleValuesType(String s) {
		this.possibleValuesType = s;
	}
	
	
	
	
	/**
	 * Return file 
	 * 
	 * @return file
	 */
	public File getFile() {
		return this.file;
	}
	
	
	
	
	
	/**
	 * Set file
	 * 
	 * @param file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	
	

	
	
	
	
}
