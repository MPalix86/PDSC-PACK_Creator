package model.pdsc.tags.rootChildren;

import model.XmlTag;
import model.XmlTagAbstract;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Name extends XmlTagAbstract{
	
	/** tag's name */
	private final static String name = "name";
	
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * @see src/model/xmlTag
	 * @see src/model/xmlTagAbstract
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 * @param defaultContent the default content of this tag
	 */
	
	public Name(boolean required, XmlTag parent, int max) {
		
		super(required, parent, max);
		
		addAttributes();
		addChildren();
	}
	
	
	
	
	/**
	 * Create new instance of tag
	 * 
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 * @param defaultContent the default content of this tag
	 */
	
	public Name(boolean required, XmlTag parent, int max, String defaultContent) {
		
		super(required, parent, max, defaultContent);
		
		addAttributes();
		addChildren();
	}
	
	
	
	
	
	/**
	 * Add all children into childrenArray defined in XmlTag
	 * 
	 * @see #XmlTag
	 * @see #src/model/XmlTagMethods
	 * @return void
	 */
	
	@Override
	protected void addChildren() {
		
	}
	
	
	
	
	
	/**
	 * Add all attributes into attrArray defined in XmlTag
	 * 
	 * @see #XmlTag
	 * @see #src/model/XmlTagMethods
	 * @return void
	 */
	
	@Override
	protected void addAttributes() {
		
	}
	
	
	
	
	/**
	 * return tag's name
	 * 
	 * @return tag's name
	 */
	
	public String getName() {
		return name;
	}
}
