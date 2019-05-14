package model.pdsc.tags;

import model.XmlNameSpace;
import model.XmlTag;
import model.XmlTagAbstract;
import model.pdsc.attributes.Dcore;
import model.pdsc.attributes.Dname;
import model.pdsc.attributes.Dvendor;
import model.pdsc.attributes.SchemaVersion;
import model.pdsc.attributes.Tcompiler;
import model.pdsc.attributes.XsnoNamespaceSchemaLocation;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Package extends XmlTagAbstract {
	
	
	/** tag's name */
	private final static String name = "package";
	
	/** tag's name space */
	private final static XmlNameSpace nameSpace = new XmlNameSpace("xs", "http://www.w3.org/2001/XMLSchema-instance");
	
	
	
	
	
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
	
	public Package(boolean required, XmlTag parent, int max) {
		
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
	
	public Package(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
		this.addAttr(new SchemaVersion(true));
		this.addAttr(new XsnoNamespaceSchemaLocation(true));
		this.addAttr(new Dcore(false));
		this.addAttr(new Dvendor(false));
		this.addAttr(new Dname(false));
		this.addAttr(new Tcompiler(false));
	}
	
	
	
	
	/**
	 * return tag's name
	 * 
	 * @return tag's name
	 */
	
	public String getName() {
		return name;
	}
	
	
	
	
	/**
	 * return tag's name space
	 * 
	 * @return the namespace
	 */
	public static XmlNameSpace getNamespace() {
		return nameSpace;
	}
}
