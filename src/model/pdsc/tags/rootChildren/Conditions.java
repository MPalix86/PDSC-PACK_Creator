package model.pdsc.tags.rootChildren;

import model.XmlTag;
import model.XmlTagAbstract;
import model.pdsc.attributes.Id;
import model.pdsc.tags.Accept;
import model.pdsc.tags.Deny;
import model.pdsc.tags.Require;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Conditions extends XmlTagAbstract{
	
	
	/** tag's name */
	private final static String name = "conditions";
	
	
	
	
	
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
	
	public Conditions(boolean required, XmlTag parent, int max) {
		
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
	
	public Conditions(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
		this.addChild(new Description(false, this, 1));
		this.addChild(new Accept(false, this, MAX_OCCURENCE_NUMBER));
		this.addChild(new Deny(false, this, MAX_OCCURENCE_NUMBER));
		this.addChild(new Require(false, this, MAX_OCCURENCE_NUMBER));
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
		this.addAttr(new Id(true));
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
