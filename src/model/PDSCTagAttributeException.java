package model;

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
	 *  0 = tag must not contains attribute
	 *  1 = tag must containns attribute
	 */
	private Integer exception;
	
	
	public PDSCTagAttributeException(XmlTag tag, XmlTag parent, XmlAttribute attr, Integer exception) {
		this.tag = tag;
		this.parent = parent;
		this.attr = attr;
		this.exception = exception;
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
