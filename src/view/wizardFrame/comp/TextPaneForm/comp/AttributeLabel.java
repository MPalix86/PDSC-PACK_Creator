package view.wizardFrame.comp.TextPaneForm.comp;

import javax.swing.JLabel;

import model.XmlAttribute;
import view.comp.CustomColor;

public class AttributeLabel extends JLabel{
	
	private XmlAttribute attr;
	
	public AttributeLabel(String text, XmlAttribute attr) {
		super(text);
		this.attr = attr;
		customize();
	}
	
	
	private void customize() {
		this.setForeground(CustomColor.ATTR_COLOR);
	}
}
