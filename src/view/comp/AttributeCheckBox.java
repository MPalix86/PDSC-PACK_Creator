package view.comp;

import javax.swing.JCheckBox;

import model.XmlAttribute;

public class AttributeCheckBox extends JCheckBox{
	private XmlAttribute attr;
	
	public AttributeCheckBox( XmlAttribute attr) {
		super();
		this.attr = attr;
		super.setText(attr.getName());
		
	}

	public XmlAttribute getAttr() {
		return attr;
	}

	public void setAttr(XmlAttribute attr) {
		this.attr = attr;
	}
}


