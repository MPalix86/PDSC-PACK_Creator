package view.wizardFrame.comp.xmlForm.comp.tagComp;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import business.Session;
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
		
		TagMenuItem addAttributeItem = new TagMenuItem("Add/Remove Attributes",tag);
		addAttributeItem.addActionListener(listener);
		addAttributeItem.setActionCommand("addAttribute");
		if(tag.getAttrArr() == null || tag.getAttrArr().size() == 0) addAttributeItem.setEnabled(false);
			
		TagMenuItem addCustomAttributeItem = new TagMenuItem("Add Custom Attribute",tag);
		addCustomAttributeItem.addActionListener(listener);
		addCustomAttributeItem.setActionCommand("addCustomAttribute");
		
		
		JMenu addTagMenu = new JMenu("Add Tag");
			
			if(tag.getChildrenArr() != null) {
				for (int i =0;  i < tag.getChildrenArr().size(); i++){
					XmlTag child = tag.getChildrenArr().get(i);
					
					TagMenuItem childMenuItem;
					if(child.isRequired()) childMenuItem = new TagMenuItem("< "+ child.getName() + " > *",child);
					else  childMenuItem = new TagMenuItem("< "+ child.getName() + " >",child);
					
					childMenuItem.addActionListener(listener);
					childMenuItem.setActionCommand("addTag");
					addTagMenu.add(childMenuItem);
				}
				addTagMenu.add(addTagMenu);
			}
			else {
				addTagMenu.setEnabled(false);
				addTagMenu.add(addTagMenu);
			}
		
		
		TagMenuItem addCustomTagItem = new TagMenuItem("Add Custom Tag",tag);
		addCustomTagItem.addActionListener(listener);
		addCustomTagItem.setActionCommand("addCustomTag");
		if(tag.getContent() != null && tag.getContent().trim().length() > 0) addCustomTagItem.setEnabled(false);
		
		
		if(tag.getName().equals("license") || tag.getName().equals("doc")) {
			TagMenuItem addPathItem = new TagMenuItem("Select File",tag);
			addPathItem.addActionListener(listener);
			addPathItem.setActionCommand("addPath");
			add(addPathItem);
			add(new JSeparator());
		}
		
		
			
		add(deleteTagItem);
		add(new JSeparator());
		add(addAttributeItem);
		add(addCustomAttributeItem);
		add(new JSeparator());
		add(addTagMenu);
		add(addCustomTagItem);
		
		
		
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
