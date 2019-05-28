package model;

public abstract class XmlStandardTag extends XmlTag{
	
	/** force user to define name for tag */
	protected String name = setTagName();
	
	/** return in this function tag's name */
	public abstract String setTagName() ;
	
	
	/**
	 * Extends this abstract class to avoid inconsistency for new STANDARD (for example pdsc) tag creation.
	 * 
	 * 
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 * @param defaultContent the default content of this tag
	 * 	
	 * IMPORTANT :  set tag's default content to void string "" if tag can 
	 * 				have content, set to desire content (example "myDefaultContent")
	 * 				if tag has predefined content, set to null if tag
	 * 				can't have any content (tag can only contains other tags).
	 * <p>
	 * NOTE : if tag can't have content you can use directly constructor 
	 *        without defaultContent value.   
	 */
	
	public XmlStandardTag(boolean required, XmlTag parent, int max, String defaultContent) {
		super.setRequired(required);
		super.setParent(parent);
		super.setMax(max);
		super.setDefaultContent(defaultContent);
		
		this.addChildren();
		this.addAttributes();
	}
	
	
	
	
	/**
	 * Create new instance of tag
	 * 
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public XmlStandardTag(boolean required, XmlTag parent, int max) {
		super.setRequired(required);
		super.setParent(parent);
		super.setMax(max);
		
		this.addChildren();
		this.addAttributes();
	}
	
	
	
	
	
	/** 
	 * in this method, manually add all tag's children. If tag have no children 
	 * left the method blank 
	 * <p>
	 * EXAMPLE: addChildren(){
	 * 				this.addChild(new PredefinedXmlTag0())
	 * 				this.addChild(new PredefinedXmlTag1())
	 * 				this.addChild(new PredefinedXmlTag2())
	 * 			}
	 * <p>
	 * you can find all predefined standard PDSC tag in scr/model/pdsc/tags/
	 * 
	 * 
	 * @return void
	 */
	
	protected abstract void addChildren();
	
	
	
	
	
	/** 
	 * in this method, manually add all tag's attributes. If tag have no attributes 
	 * left the method blank
	 * <p>
	 * EXAMPLE: addChildren(){
	 * 				this.addAttr(new PredefinedXmlAttribute0())
	 * 				this.addAttr(new PredefinedXmlAttribute0())
	 * 				this.addAttr(new PredefinedXmlAttribute0())
	 * 			}
	 * <p>
	 * you can find all predefined standard PDSC attributes in scr/model/pdsc/attributes/
	 * 
	 * @return void
	 */
	
	protected abstract void addAttributes();
}
