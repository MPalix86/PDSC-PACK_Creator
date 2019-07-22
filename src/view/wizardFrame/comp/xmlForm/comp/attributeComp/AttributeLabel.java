package view.wizardFrame.comp.xmlForm.comp.attributeComp;

import javax.swing.JLabel;

import model.XmlAttribute;
import view.comp.utils.ColorUtils;

public class AttributeLabel extends JLabel{
	
	private XmlAttribute attr;
	
	public AttributeLabel(String text, XmlAttribute attr) {
		super(text);
		this.attr = attr;
		customize();
	}
	
	
	private void customize() {
		this.setForeground(ColorUtils.ATTR_COLOR);
	}


	/**
	 * @return the attr
	 */
	public XmlAttribute getAttr() {
		return attr;
	}
}
