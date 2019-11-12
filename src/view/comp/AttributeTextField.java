package view.comp;

import javax.swing.JTextField;

import model.xml.XmlAttribute;

public class AttributeTextField extends JTextField{
	protected XmlAttribute attr;
	
	public AttributeTextField(XmlAttribute attr) {
		this.attr = attr;
		if(attr.getValue() != null) {
			if(attr.getDefaultValue() != null) {
				this.setText(attr.getDefaultValue());
				attr.setValue(attr.getDefaultValue());
			}
		}
	}
	
	
	
	public AttributeTextField(XmlAttribute attr,String text) {
		super(text);
		this.attr = attr;
		if(attr.getValue() != null) {
			if(attr.getDefaultValue() != null) {
				this.setText(attr.getDefaultValue());
				attr.setValue(attr.getDefaultValue());
			}
		}
	}
	
	
	
	public AttributeTextField(XmlAttribute attr,String text,int size) {
		super(text,size);
		this.attr = attr;
		this.attr = attr;
		if(attr.getValue() != null) {
			if(attr.getDefaultValue() != null) {
				this.setText(attr.getDefaultValue());
				attr.setValue(attr.getDefaultValue());
			}
		}
	}
	
	public void setAttrValue() {
		attr.setValue(this.getText());
	}
	
	public XmlAttribute getAttribute() {
		return this.attr;
	}
	

}
