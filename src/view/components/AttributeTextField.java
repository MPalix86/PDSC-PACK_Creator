package view.components;

import javax.swing.JTextField;

import model.XmlAttribute;

public class AttributeTextField extends JTextField{
	private XmlAttribute attr;
	
	public AttributeTextField(XmlAttribute attr) {
		this.attr = attr;
	}
	
	public void setAttrValue() {
		attr.setValue(this.getText());
	}
}
