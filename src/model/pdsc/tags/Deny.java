package model.pdsc.tags;

import model.XmlTag;
import model.XmlTagAbstract;
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

public class Deny extends XmlTagAbstract{
	
	/** tag's name */
	private final static String name = "deny";
	
	
	
	
	
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
	
	public Deny(boolean required, XmlTag parent, int max) {
		
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
	
	public Deny(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
		// TODO Auto-generated method stub
		
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
}
