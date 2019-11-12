package model.PDSC;

import model.xml.XmlAttribute;
import model.xml.XmlEnum;
import model.xml.XmlTag;

/**
 * Attribute exception represent exception for the attributes under certain 
 * conditions defined by PDSC standard.
 * 
 * example : 	Tag  " component " have attribute " Cvendor ", but 
 * 				must not be specified for a component within a bundle.
 * 
 * @author Mirco Palese
 */
public class PDSCTagAttributeException {
	
	/** tag in which exception occurs */
	private XmlTag tag;
	
	/** 
	 * parent tag that determines the occurrence of the condition 
	 * example : if(parent == bundle && tag == component) remove attribute
	 */
	private XmlTag parent;
	
	/** attribute in which exception occurs */
	private XmlAttribute attr;
	
	/**
	 * The exception.
	 * 
	 *  0 = only and only if tag is "tagX" and his parent his "tagY", "tagX" must NOT contain attribute 
	 *  1 = only and only if tag is "tagX" and his parent his "tagY", "tagX" must contain attribute
	 */
	private Integer exception;
	
	/** 
	 * attribute's description 
	 * 
	 * Note that description it only make sense if exception is 1, if value of 
	 * exception is 0 description will be completely ignored
	 * 
	 * this value is stored in tag_attr_exception table
	 * but is not saved in this class. it Is sav ed directly in attribute during query.
	 * For more details see XmlTagDao.getTagAttributeExceptionArr
	 */
	private String description;

	
	/**
	 * required
	 * 
	 * Note that required it only make sense if exception is 1
	 * exception is 0 required will be completely ignored.
	 * 
	 * this value is stored in tag_attr_exception table
	 * but is not saved in this class. it Is sav ed directly in attribute during query.
	 * For more details see XmlTagDao.getTagAttributeExceptionArr
	 * 
	 * default value is false
	 */
	private boolean required;
	
	
	/**
	 * valueType
	 * 
	 * Note that valueType it only make sense if exception is 1
	 * exception is 0 required will be completely ignored. 
	 *
 	 * this value is stored in tag_attr_exception table
	 * but is not saved in this class. it Is sav ed directly in attribute during query.
	 * For more details see XmlTagDao.getTagAttributeExceptionArr
	 */
	private String valueType;
	
	
	
	
	/**
	 * possibleValues
	 * 
	 * Note that possibleValues it only make sense if exception is 1
	 * exception is 0 required will be completely ignored. 
	 *
 	 * this value is stored in tag_attr_exception table
	 * but is not saved in this class. it Is saved directly in attribute during query.
	 * For more details see XmlTagDao.getTagAttributeExceptionArr
	 */
	private XmlEnum possibleValues;
	
	
	public PDSCTagAttributeException(XmlTag tag, XmlTag parent, XmlAttribute attr, Integer exception) {
		this.tag = tag;
		this.parent = parent;
		this.attr = attr;
		this.exception = exception;
	}
	
	public PDSCTagAttributeException(PDSCTagAttributeException e) {
		if(e.getAttribute() != null) this.attr = e.getAttribute();
		if(e.getException() != null) this.exception = e.getException();
		if(e.getParent() != null) this.parent = e.getParent();
		if(e.getTag() != null) this.tag = e.getTag();
	}
	
	
	
	
	/**
	 * Return parent tag
	 * 
	 * @return parent
	 */
	
	public XmlTag getParent() {
		return this.parent;
	}
	
	
	
	
	/**
	 * Return tag
	 * 
	 * @return tag
	 */
	
	public XmlTag getTag() {
		return this.tag;
	}
	
	
	
	
	/**
	 * Return attribute
	 * 
	 * @return attribute
	 */
	
	public XmlAttribute getAttribute() {
		return this.attr;
	}
	
	
	
	
	/**
	 * Return exception
	 * 
	 * @return exception
	 */

	public Integer getException() {
		return this.exception;
	}
	
	
	
	
	
}
