package model.pdsc.tags;

import model.XmlStandardTag;
import model.XmlTag;
import model.pdsc.attributes.Capiversion;
import model.pdsc.attributes.Cclass;
import model.pdsc.attributes.Cgroup;
import model.pdsc.attributes.Condition;
import model.pdsc.attributes.Csub;
import model.pdsc.attributes.Cvariant;
import model.pdsc.attributes.Cvendor;
import model.pdsc.attributes.Cversion;
import model.pdsc.attributes.Generator;
import model.pdsc.attributes.IsDefaultVariant;
import model.pdsc.attributes.MaxInstances;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Component extends XmlStandardTag{
	
	
	/** tag's name */
	private final static String name = "component";
	
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public Component(boolean required, XmlTag parent, int max) {
		
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
	
	public Component(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
		this.addChild(new Deprecated(false, this, 1));
		this.addChild(new Description(true, this, 1, ""));
		this.addChild(new RTE_Components_h(false, this, 1, ""));
		this.addChild(new Pre_Include_Global_h(false, this, 1,""));
		this.addChild(new Pre_Include_Local_Component_h(false, this, 1,""));
		this.addChild(new Files(true, this, 1));
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
		this.addAttr(new Cvendor(false,this));
		this.addAttr(new Cclass(true,this));
		this.addAttr(new Cgroup(true,this));
		this.addAttr(new Csub(false,this));
		this.addAttr(new Cvariant(false,this));
		this.addAttr(new Cversion(true,this));
		this.addAttr(new Capiversion(false,this));  
		this.addAttr(new Condition(false,this)); 
		this.addAttr(new MaxInstances(false,this));
		this.addAttr(new IsDefaultVariant(false,this));
		this.addAttr(new Generator(false,this));
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
