package view.comp;

import javax.swing.JMenuItem;

import model.XmlAttribute;

public class AttributeMenuItem extends JMenuItem{
	
	private Class attrClass;
	private XmlAttribute attr;
	
	public AttributeMenuItem(String text, Class attrClass) {
		super(text);
		this.attrClass = attrClass;
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
	
	
	/**
	 * @return the tagClass
	 */
	public Class getAttrClass() {
		return attrClass;
	}


}
