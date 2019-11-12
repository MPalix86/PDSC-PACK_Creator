package view.comp;

import javax.swing.JMenuItem;

import model.xml.XmlAttribute;

public class AttributeMenuItem extends JMenuItem{
	
	private XmlAttribute attr;

	
	public AttributeMenuItem(String text) {
		super(text);
	}
	
	public AttributeMenuItem(String text, XmlAttribute attr) {
		super(text);
		this.attr = attr;
	}
	
	/**
	 * @return the tag
	 */
	public XmlAttribute getAttr() {
		return attr;
	}


}
