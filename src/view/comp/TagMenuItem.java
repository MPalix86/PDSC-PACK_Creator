package view.comp;

import javax.swing.JMenuItem;

import model.xml.XmlTag;

public class TagMenuItem extends JMenuItem {
	
	private XmlTag tag;
	
	
	public TagMenuItem(String text, XmlTag tag) {
		super(text);
		this.tag = tag;
	}
	
	/**
	 * @return the tag
	 */
	public XmlTag getTag() {
		return tag;
	}

	public TagMenuItem(String text) {
		super(text);
	}
	
	



}
