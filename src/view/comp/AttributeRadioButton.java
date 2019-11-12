package view.comp;

import javax.swing.JRadioButton;

import model.xml.XmlAttribute;

public class AttributeRadioButton extends JRadioButton{
	
	private XmlAttribute attr;
	
	public AttributeRadioButton(XmlAttribute attr) {
		this.attr = attr;
	}
	
	public XmlAttribute getAttr() {
		return attr;
	}

}
