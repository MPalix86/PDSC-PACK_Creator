package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JLabel;

import model.XmlAttribute;
import model.XmlTag;
import view.comp.CustomColor;

public class AttributeLabel extends JLabel{
	
	private XmlAttribute attr;
	
	private XmlTag tag;
	
	public AttributeLabel(String text, XmlAttribute attr,XmlTag tag) {
		super(text);
		this.tag = tag;
		this.attr = attr;
		customize();
	}
	
	
	private void customize() {
		this.setForeground(CustomColor.ATTR_COLOR);
	}


	/**
	 * @return the attr
	 */
	public XmlAttribute getAttr() {
		return attr;
	}
}
