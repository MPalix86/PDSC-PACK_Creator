package view.comp;

import javax.swing.JButton;

import model.XmlAttribute;

public class AttributeButton extends JButton{
	private XmlAttribute attr;
	
	public AttributeButton(XmlAttribute attr) {
		this.attr = attr;
	}
}
