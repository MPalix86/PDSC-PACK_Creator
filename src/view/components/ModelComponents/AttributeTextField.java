package view.Components.ModelComponents;

import javax.swing.JTextField;

import model.XmlAttribute;

public class AttributeTextField extends JTextField{
	private XmlAttribute attr;
	
	public AttributeTextField(XmlAttribute attr) {
		this.attr = attr;
		attr.setValue("");
		if(attr.getDefaultValue() != null) {
			this.setText(attr.getDefaultValue());
			attr.setValue(attr.getDefaultValue());
		}
		
	}
	
	public void setAttrValue() {
		attr.setValue(this.getText());
	}
	
	public XmlAttribute getAttribute() {
		return this.attr;
	}
}
