package model.pdsc.tags;

import model.XmlStandardTag;
import model.XmlTag;
import model.pdsc.attributes.Dcore;
import model.pdsc.attributes.Dfamily;
import model.pdsc.attributes.Dfpu;
import model.pdsc.attributes.Dmpu;
import model.pdsc.attributes.Dname;
import model.pdsc.attributes.DsubFamily;
import model.pdsc.attributes.Dvariant;
import model.pdsc.attributes.Dvendor;
import model.pdsc.attributes.Pname;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Accept extends XmlStandardTag{
	
	/** tag's name */
	private final static String name = "accept";
	
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public Accept(boolean required, XmlTag parent, int max) {
		
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
	
	public Accept(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
		this.addChild(new Deny(false, this, MAX_OCCURENCE_NUMBER));
		// TODO Auto-generated method stub
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
		this.addAttr(new Dvendor(false));
		this.addAttr(new Dfamily(false));
		this.addAttr(new DsubFamily(false));
		this.addAttr(new Dname(false));
		this.addAttr(new Dvariant(false));
		this.addAttr(new Pname(false));
		this.addAttr(new Dcore(false));
		this.addAttr(new Dfpu(false));
		this.addAttr(new Dmpu(false));
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
