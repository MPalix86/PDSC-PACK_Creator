package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JPopupMenu;

import listeners.wizardFrameListeners.comp.xmlForm.comp.AttributeOptionMenuListener;
import model.XmlAttribute;
import view.comp.AttributeMenuItem;

public class AttributeOptionMenu extends JPopupMenu{
	
	private XmlAttribute attr;
	private AttributeOptionMenuListener listener;
	
	public AttributeOptionMenu(XmlAttribute attr) {
		this.attr = attr;
		this.listener = new AttributeOptionMenuListener();
		
		AttributeMenuItem deleteAttrItem = new AttributeMenuItem("Delete",attr);
		
		AttributeMenuItem addPathItem = new AttributeMenuItem("Select File",attr);
		
		if(attr.isRequired()) {
			deleteAttrItem.setEnabled(false);
		}
		
		
		deleteAttrItem.addActionListener(listener);
		deleteAttrItem.setActionCommand("deleteAttribute");
		add(deleteAttrItem);
		
		if (attr.getTag().getName().equals("file") && attr.getName().equals("name")) {
			addPathItem.addActionListener(listener);
			addPathItem.setActionCommand("addPath");
			add(addPathItem);
		}

		

	
	
	}

}
