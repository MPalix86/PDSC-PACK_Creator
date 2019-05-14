package model;

public abstract class XmlTagAbstract extends XmlTag{
	
	/**
	 * Create new instance of tag
	 * 
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 * @param defaultContent the default content of this tag
	 */
	
	public XmlTagAbstract(boolean required, XmlTag parent, int max, String defaultContent) {
		super.setRequired(required);
		super.setParent(parent);
		super.setMax(max);
		super.setDefaultContent(defaultContent);
	}
	
	
	
	
	/**
	 * Create new instance of tag
	 * 
	 * @param required attribute's obligatoriness
	 * @param parent this tag's parent
	 * @param max max occurrence of this tag inside parent
	 */
	
	public XmlTagAbstract(boolean required, XmlTag parent, int max) {
		super.setRequired(required);
		super.setParent(parent);
		super.setMax(max);
	}
	
	
	
	
	
	/** 
	 * in this method, manually add all tag's children. If tag have no children 
	 * left the method blank 
	 * <p>
	 * EXAMPLE: addChildren(){
	 * 				add(new PredefinedXmlTag0())
	 * 				add(new PredefinedXmlTag1())
	 * 				add(new PredefinedXmlTag2())
	 * 			}
	 * <p>
	 * you can find all predefined standard PDSC tag in scr/model/pdsc/tags/
	 * 
	 * @return void
	 */
	
	protected abstract void addChildren();
	
	
	
	
	
	/** 
	 * in this method, manually add all tag's attributes. If tag have no attributes 
	 * left the method blank
	 * <p>
	 * EXAMPLE: addChildren(){
	 * 				add(new PredefinedXmlAttribute0())
	 * 				add(new PredefinedXmlAttribute0())
	 * 				add(new PredefinedXmlAttribute0())
	 * 			}
	 * <p>
	 * you can find all predefined standard PDSC attributes in scr/model/pdsc/attributes/
	 * 
	 * @return void
	 */
	
	protected abstract void addAttributes();
}
