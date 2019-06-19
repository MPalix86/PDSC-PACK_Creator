package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import listeners.wizardFrameListeners.comp.xmlForm.comp.AttributeOptionMenuListener;
import model.XmlAttribute;
import view.comp.AttributeMenuItem;

public class AttributeOptionMenu extends JPopupMenu{
	
	private XmlAttribute attr;
	private JLabel sourceLabel;
	private AttributeOptionMenuListener listener;
	
	public AttributeOptionMenu(XmlAttribute attr, JLabel sourceLabel) {
		this.attr = attr;
		this.sourceLabel = sourceLabel;
		this.listener = new AttributeOptionMenuListener();
		
		AttributeMenuItem deleteAttrItem = new AttributeMenuItem("Delete",attr);
		AttributeMenuItem addPathItem = new AttributeMenuItem("Select File",attr);
		AttributeMenuItem CustomValueItem = new AttributeMenuItem("Insert Custom Value",attr);
		
		
		deleteAttrItem.addActionListener(listener);
		deleteAttrItem.setActionCommand("deleteAttribute");
		add(deleteAttrItem);
		
		if (attr.getTag().getName().equals("file") && attr.getName().equals("name")) {
			addPathItem.addActionListener(listener);
			addPathItem.setActionCommand("addPath");
			add(addPathItem);
		}
		
		if(attr.getPossibleValues() != null) {
			System.out.println(attr.getPossibleValues().getClass());
			CustomValueItem.addActionListener(listener);
			CustomValueItem.setActionCommand("addCustomValue");
			add(CustomValueItem);
		}

		

	
	
	}

	/**
	 * @return the attr
	 */
	public XmlAttribute getAttr() {
		return attr;
	}

	/**
	 * @return the sourceLabel
	 */
	public JLabel getSourceLabel() {
		return sourceLabel;
	}

}
