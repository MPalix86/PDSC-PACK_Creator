package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JPopupMenu;

import business.Session;
import business.XmlAttributeBusiness;
import listeners.wizardFrameListeners.comp.xmlForm.comp.TagOptionMenuListener;
import model.XmlTag;
import view.comp.TagMenuItem;

public class TagOptionMenu extends JPopupMenu {
	
	TagOptionMenuListener listener =  new TagOptionMenuListener();
	
	private XmlTag tag;
	

	
	public TagOptionMenu(XmlTag tag) {
		this.tag = tag;
		
		TagMenuItem deleteTagItem = new TagMenuItem("Delete",tag);
		deleteTagItem.addActionListener(listener);
		deleteTagItem.setActionCommand("deleteTag");
		
		TagMenuItem addAttributeItem = new TagMenuItem("Add Attributes",tag);
		addAttributeItem.addActionListener(listener);
		addAttributeItem.setActionCommand("addAttribute");
		if(XmlAttributeBusiness.getNotSelectedAttributes(tag) == null) addAttributeItem.setEnabled(false);
		else System.out.println("c'è qualcosa");
			
		TagMenuItem addCustomAttributeItem = new TagMenuItem("Add Custom Attribute",tag);
		addCustomAttributeItem.addActionListener(listener);
		addCustomAttributeItem.setActionCommand("addCustomAttribute");
	
		add(deleteTagItem);
		add(addAttributeItem);
		add(addCustomAttributeItem);
		
		
		/**
		 * IMPORTANT
		 * to avoid scrollbar reset caused from method : row.scrollRectToVisible(this.getBounds()); 
		 * present in TagFormTextField, TagFormTextArea, TagFormCombobox, 
		 * AttributeFormCombobox, AttributeFormTextField
		 * 
		 * must to remove focus when jpopupmenu is called;
		 */
		Session.getInstance().getSelectedForm().getTagOpenRow(tag).grabFocus();

	}
	


}
