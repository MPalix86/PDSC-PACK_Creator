package view.comp;

import model.XmlTag;

/**
 * This button contains an XmlTag instance
 * 
 * @author mircopalese
 */
public class TagButton extends SquareButton{
	
	private XmlTag	tag;
	

    
	/**
	 * initialize tag instance and setting text and color
	 * 
	 * @param tag button's associated tag
	 */
	
	public TagButton(XmlTag tag ) {
		super("< " + tag.getName() + " >");
		super.setForeground(CustomColor.TAG_COLOR);
		this.tag = tag;
		
	}
	
	
	
	public TagButton(XmlTag tag , String text ) {
		
		super(text);
		this.tag = tag;
		
	}
	
	
	
	
	/**
	 * return button's associated tag
	 * 
	 * @return button's associated tag
	 */
	
	public XmlTag getTag() {
		return tag;
	}
	
	
	
	
	/**
	 * set button's associated tag
	 * 
	 * @param tag  button's associated tag
	 */
	
	public void setTag(XmlTag tag) {
		this.tag = tag;
	}
}
