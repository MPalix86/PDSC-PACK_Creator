package model.pdsc.tags;

import model.XmlNameSpace;
import model.XmlStandardTag;
import model.XmlTag;
import model.pdsc.attributes.Dcore;
import model.pdsc.attributes.Dname;
import model.pdsc.attributes.Dvendor;
import model.pdsc.attributes.SchemaVersion;
import model.pdsc.attributes.Tcompiler;
import model.pdsc.attributes.XsnoNamespaceSchemaLocation;

/** 
 * Tag definition following PDSC CMSIS standard.
 * 
 * NOTE :	Package is the root element. 
 * 			Theoretically all the others tags are children of package. 
 * 			but to safeguard memory childrenArr was leaved null;
 * 
 * @author Mirco Palese
 */

public class Package extends XmlStandardTag {
	
	
	/** tag's name */
	private final static String name = "package";
	
	/** tag's name space */
	private final static XmlNameSpace nameSpace = new XmlNameSpace("xs", "http://www.w3.org/2001/XMLSchema-instance");
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public Package(boolean required, XmlTag parent, int max) {
		
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
	
	public Package(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
		this.addAttr(new SchemaVersion(true,this));
		this.addAttr(new XsnoNamespaceSchemaLocation(true,this));
		this.addAttr(new Dcore(false,this));
		this.addAttr(new Dvendor(false,this));
		this.addAttr(new Dname(false,this));
		this.addAttr(new Tcompiler(false,this));
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
	
	public  XmlNameSpace getNameSpace() {
		return nameSpace;
	}
	
	
	
	
	
	@Override
	public String setTagName() {
		// TODO Auto-generated method stub
		return name;
	}
}
