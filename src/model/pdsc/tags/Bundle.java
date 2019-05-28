package model.pdsc.tags;

import model.XmlStandardTag;
import model.XmlTag;
import model.pdsc.attributes.Cbundle;
import model.pdsc.attributes.Cclass;
import model.pdsc.attributes.Cvendor;
import model.pdsc.attributes.Cversion;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Bundle extends XmlStandardTag{
	
	/** tag's name */
	private final static String name = "bundle";
	
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public Bundle(boolean required, XmlTag parent, int max) {
		
		super(required, parent, max);
	}
	
	
	
	
	/**
	 * Create new instance of tag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 * @param defaultContent the default content of this tag
	 */
	
	public Bundle(boolean required, XmlTag parent, int max, String defaultContent) {
		
		super(required, parent, max, defaultContent);
	}
	
	
	
	/**
	 * Add all children into childrenArray defined in XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @see #XmlTag
	 * @return void
	 */
	
	@Override
	protected void addChildren() {
		this.addChild(new Description(true, this, 1, ""));
		this.addChild(new Doc(true, this, 1, ""));
		this.addChild(new Component(true, this, 1));

	}
	
	
	
	
	
	/**
	 * Add all attributes into attrArray defined in XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @see #XmlTag
	 * @return void
	 */
	
	@Override
	protected void addAttributes() {
		this.addAttr(new Cbundle(true));
		this.addAttr(new Cvendor(false));  
		this.addAttr(new Cclass(true));
		this.addAttr(new Cversion(true));  

	}
	
	
	
	
	/**
	 * return tag's name
	 * 
	 * @return tag's name
	 */
	
	public String getName() {
		return name;
	}




	@Override
	public String setTagName() {
		// TODO Auto-generated method stub
		return name;
	}
}
