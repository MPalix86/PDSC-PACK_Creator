package view.comp;

import javax.swing.JCheckBox;

import model.XmlAttribute;
import model.XmlTag;

public class AttributeCheckBox extends JCheckBox{
	private XmlTag tag;
	private XmlAttribute attr;
	
	public AttributeCheckBox( XmlAttribute attr, XmlTag tag) {
		super();
		this.tag = tag;
		this.attr = attr;
		super.setText(attr.getName());
		
	}

	public XmlTag getTag() {
		return tag;
	}

	public void setTag(XmlTag tag) {
		this.tag = tag;
	}

	public XmlAttribute getAttr() {
		return attr;
	}

	public void setAttr(XmlAttribute attr) {
		this.attr = attr;
	}
}


