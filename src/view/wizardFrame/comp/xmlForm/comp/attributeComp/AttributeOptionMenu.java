package view.wizardFrame.comp.xmlForm.comp.attributeComp;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import business.Session;
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
		deleteAttrItem.addActionListener(listener);
		deleteAttrItem.setActionCommand("deleteAttribute");
		
		
		if (attr.getTag().getName().equals("file") && attr.getName().equals("name")) {
			AttributeMenuItem addPathItem = new AttributeMenuItem("Select File",attr);
			addPathItem.addActionListener(listener);
			addPathItem.setActionCommand("addPath");
			add(addPathItem);
			add(new JSeparator());
		}
		
		if(attr.getPossibleValues() != null) {
			AttributeMenuItem CustomValueItem = new AttributeMenuItem("Insert Custom Value",attr);
			System.out.println(attr.getPossibleValues().getClass());
			CustomValueItem.addActionListener(listener);
			CustomValueItem.setActionCommand("addCustomValue");
			add(CustomValueItem);
		}
		
		
		add(deleteAttrItem);

		
		/**
		 * IMPORTANT
		 * to avoid scrollbar reset caused from method : row.scrollRectToVisible(this.getBounds()); 
		 * present in TagFormTextField, TagFormTextArea, TagFormCombobox, 
		 * AttributeFormCombobox, AttributeFormTextField
		 * 
		 * must to remove focus when jpopupmenu is called;
		 */
		Session.getInstance().getSelectedForm().getTagOpenRow(attr.getTag()).grabFocus();
	
	
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
