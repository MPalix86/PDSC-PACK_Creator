package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JPopupMenu;

import model.XmlAttribute;
import view.comp.AttributeMenuItem;

public class AttributeOptionMenu extends JPopupMenu{
	
	private XmlAttribute attr;
	
	public AttributeOptionMenu(XmlAttribute attr) {
		this.attr = attr;
		AttributeMenuItem deleteAttrItem = new AttributeMenuItem("Delete",attr);
		add(deleteAttrItem);
	}

}
