package model.pdsc.tags;

import model.XmlStandardTag;
import model.XmlTag;

/** 
 * Tag definition following PDSC CMSIS standard
 * 
 * @author Mirco Palese
 */

public class Files extends XmlStandardTag{
	
	/** tag's name */
	private final static String name = "files";
	
	
	
	
	
	/**
	 * Create new instance of tag. all parameter defined bottom was defined 
	 * inside XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public Files(boolean required, XmlTag parent, int max) {
		
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
	
	public Files(boolean required, XmlTag parent, int max, String defaultContent) {
		
		super(required, parent, max, defaultContent);
	}
	
	
	
	
	
	/**
	 * Add all children into childrenArray defined in XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @see XmlTag
	 * @return void
	 */
	
	@Override
	protected void addChildren() {
		this.addChild(new File(true, this, MAX_OCCURENCE_NUMBER));
	}
	
	
	
	
	
	/**
	 * Add all attributes into attrArray defined in XmlTag
	 * 
	 * <a href="file:../XmlStandardTag.java">XmlTagABstract</a>
	 * @see XmlTag
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
	
	
	
	
	@Override
	public String setTagName() {
		// TODO Auto-generated method stub
		return name;
	}
}
