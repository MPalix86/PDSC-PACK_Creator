package view.comp;

import javax.swing.ImageIcon;

import model.XmlAttribute;

public class AttributeButton extends SquareButton{
	private XmlAttribute attr;
	
	public AttributeButton(XmlAttribute attr) {
		this.attr = attr;
	}
	
	public AttributeButton(String text,XmlAttribute attr) {
		super(text);
		this.attr = attr;
	}
	
	public AttributeButton toIconButton(ImageIcon icon) {
		super.toIconButton(icon);
		return this;
	}
	
	public XmlAttribute getAttribute() {
		return this.attr;
	}
}
