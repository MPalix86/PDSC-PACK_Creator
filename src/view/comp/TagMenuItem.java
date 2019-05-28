package view.comp;

import javax.swing.JMenuItem;

import model.XmlTag;

public class TagMenuItem extends JMenuItem {
	
	private Class tagClass;
	private XmlTag tag;
	
	public TagMenuItem(String text, Class tagClass) {
		super(text);
		this.tagClass = tagClass;
	}
	
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
	
	
	
	/**
	 * @return the tagClass
	 */
	public Class getTagClass() {
		return tagClass;
	}

	/**
	 * @param tagClass the tagClass to set
	 */
	public void setTagClass(Class tagClass) {
		this.tagClass = tagClass;
	}

}
