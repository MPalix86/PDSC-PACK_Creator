package model.pdsc.tags;

import model.XmlStandardTag;
import model.XmlTag;
import model.pdsc.attributes.Attr;
import model.pdsc.attributes.Category;
import model.pdsc.attributes.Condition;
import model.pdsc.attributes.Name;
import model.pdsc.attributes.Path;
import model.pdsc.attributes.Public;
import model.pdsc.attributes.Select;
import model.pdsc.attributes.Source;
import model.pdsc.attributes.Version;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class File extends XmlStandardTag {
	
	
	/** tag's name */
	private final static String name = "file";
	
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public File(boolean required, XmlTag parent, int max) {
		
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
	
	public File(boolean required, XmlTag parent, int max, String defaultContent) {
		
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
	 *  <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @see #XmlTag
	 * @return void
	 */
	
	@Override
	protected void addAttributes() {
		this.addAttr(new Name(true,this));    
		this.addAttr(new Path(false,this));  
		this.addAttr(new Category(true,this));  
		this.addAttr(new Attr(false,this));  
		this.addAttr(new Condition(false,this));
		this.addAttr(new Select(false,this));  
		this.addAttr(new Source(false,this));  
		this.addAttr(new Version(false,this));  
		this.addAttr(new Public(false,this));  
		
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
