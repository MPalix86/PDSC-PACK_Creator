package view.comp;

import javax.swing.JCheckBox;

import model.xml.XmlAttribute;

public class AttributeCheckBox extends JCheckBox{
	private XmlAttribute attr;
	
	public AttributeCheckBox( XmlAttribute attr) {
		super();
		this.attr = attr;
		super.setText(attr.getName());
		setup();
		
	}

	public XmlAttribute getAttr() {
		return attr;
	}

	public void setAttr(XmlAttribute attr) {
		this.attr = attr;
	}
	
	private void setup() {
		this.setContentAreaFilled(false);
	}
}


