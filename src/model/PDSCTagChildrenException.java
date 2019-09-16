package model;

/**
 * Attribute exception represent exception for tag under certain 
 * conditions defined by PDSC standard.
 * 
 * example : Tag  <component> if is parent is components have children:
 *  		 <deprecated>
 *  		 <description>
 *  		 <RTE_Components_h> 
 *  		 ...
 *  		 ...
 * 
 * but if his parent is <attributes>, tag <component> have no children
 * 
 * @author Mirco Palese
 */
public class PDSCTagChildrenException {
	
	/** tag in which exception occurs */
	private XmlTag tag;
	
	/** parent tag that determines the occurrence of the condition */
	private XmlTag parent;
	
	/** child in which exception occurs */
	private XmlTag child;
	
	/**
	 * The exception.
	 * 
	 *  0 = only and only if tag is "tagX" and his parent his "tagY", "tagX" must NOT contain the specified child 
	 *  1 = only and only if tag is "tagX" and his parent his "tagY", "tagX" must contain the specified child
	 */
	private Integer exception;
	
	/** 
	 * child's description 
	 * 
	 * Note that description it only make sense if exception is 1, if value of 
	 * exception is 0 description will be completely ignored
	 * 
	 * this value is stored in tag_children_exception table
	 * but is not saved in this class. it Is saved directly in new tag during query.
	 */
	private String description;
	
	/**
	 * required
	 * 
	 * Note that required it only make sense if exception is 1
	 * exception is 0 required will be completely ignored.
	 * 
	 * this value is stored in tag_children_exception table
	 * but is not saved in this class. it Is saved directly in new tag during query.
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
	 * but is not saved in this class. it Is saved directly in new tag during query.
	 */
	private String valueType;
	
	/**
	 * possibleValues
	 * 
	 * Note that possibleValues it only make sense if exception is 1
	 * exception is 0 required will be completely ignored. 
	 *
 	 * this value is stored in tag_children_exception table
	 * but is not saved in this class. it Is saved directly in new tag during query. during query.
	 */
	private XmlEnum possibleValues;
	
	
	
	
	public PDSCTagChildrenException(XmlTag tag, XmlTag parent, XmlTag child, int exception){
		this.tag = tag;
		this.parent = parent;
		this.child = child;
		this.exception = exception;
	}
	
	
	
	
	public PDSCTagChildrenException(PDSCTagChildrenException e) {
		if (e.getTag() != null) this.tag = e.getTag();
		if(e.getParent() != null) this.parent = e.getParent();
		if(e.getChild() != null) this.child = e.getChild();
		if(e.getException() != null) this.exception = e.getException();
	}




	/**
	 * @return the tag
	 */
	public XmlTag getTag() {
		return tag;
	}




	/**
	 * @param tag the tag to set
	 */
	public void setTag(XmlTag tag) {
		this.tag = tag;
	}




	/**
	 * @return the parent
	 */
	public XmlTag getParent() {
		return parent;
	}




	/**
	 * @param parent the parent to set
	 */
	public void setParent(XmlTag parent) {
		this.parent = parent;
	}




	/**
	 * @return the child
	 */
	public XmlTag getChild() {
		return child;
	}




	/**
	 * @param child the child to set
	 */
	public void setChild(XmlTag child) {
		this.child = child;
	}




	/**
	 * @return the exception
	 */
	public Integer getException() {
		return exception;
	}




	/**
	 * @param exception the exception to set
	 */
	public void setException(Integer exception) {
		this.exception = exception;
	}





	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	


}
