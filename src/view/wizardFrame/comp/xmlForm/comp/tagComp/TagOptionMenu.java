package view.wizardFrame.comp.xmlForm.comp.tagComp;

import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import business.Session;
import business.XmlTagUtils;
import listeners.ClipboardListener;
import listeners.wizardFrameListeners.comp.xmlForm.comp.TagOptionMenuListener;
import model.XmlTag;
import model.XmlTagSelection;
import view.comp.TagMenuItem;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconUtils;

public class TagOptionMenu extends JPopupMenu {
	
	private TagOptionMenuListener listener =  new TagOptionMenuListener(this);
	private ClipboardListener clipboardListener = new ClipboardListener();
	
	
	private XmlTag tag;
	

	
	public TagOptionMenu(XmlTag tag) {
		this.tag = tag;
		
		TagMenuItem deleteTagItem = new TagMenuItem("Delete",tag);
		deleteTagItem.addActionListener(listener);
		deleteTagItem.setActionCommand("deleteTag");
		deleteTagItem.setIcon(IconUtils.getTrashIcon(16));
		
		TagMenuItem copyItem = new TagMenuItem("Copy" , tag);
		copyItem.addActionListener(clipboardListener);
		copyItem.setActionCommand("copyTag");
		copyItem.setIcon(IconUtils.getCopyIcon(16));
		
		TagMenuItem cutItem = new TagMenuItem("Cut" , tag);
		cutItem.addActionListener(clipboardListener);
		cutItem.setActionCommand("cutTag");
		cutItem.setIcon(IconUtils.getCutIcon(16));
		
		
		
		JMenu pasteMenu = new JMenu("Paste");
		pasteMenu.setIcon(IconUtils.FAgetClipboardIcon(16, ColorUtils.FOLDER_BROWN));
		if (Session.getInstance().getClipboard().getContents(XmlTagSelection.xmlTagFlavor) == null ) pasteMenu.setEnabled(false);

			TagMenuItem pasteFirsrItem = new TagMenuItem("Paste As First Element" , tag);
			pasteFirsrItem.addActionListener(clipboardListener);
			pasteFirsrItem.setActionCommand("pasteFirst");
			pasteFirsrItem.setIcon(IconUtils.FAgetClipboardIcon(16, ColorUtils.FOLDER_BROWN));
			pasteFirsrItem.setIcon(null);
			
			TagMenuItem pasteLastItem = new TagMenuItem("Paste As Last Element" , tag);
			pasteLastItem.addActionListener(clipboardListener);
			pasteLastItem.setActionCommand("pasteLast");
			pasteLastItem.setIcon(IconUtils.FAgetClipboardIcon(16, ColorUtils.FOLDER_BROWN));
			pasteLastItem.setIcon(null);
			
			TagMenuItem pasteAtPositionItem = new TagMenuItem("Paste At Position" , tag);
			pasteAtPositionItem.addActionListener(clipboardListener);
			pasteAtPositionItem.setActionCommand("pasteAtPosition");
			pasteAtPositionItem.setIcon(IconUtils.FAgetClipboardIcon(16, ColorUtils.FOLDER_BROWN));
			pasteAtPositionItem.setIcon(null);
			
		pasteMenu.add(pasteFirsrItem);
		pasteMenu.add(pasteLastItem);
		pasteMenu.add(pasteAtPositionItem);
					
		TagMenuItem cloneItem = new TagMenuItem("Clone" , tag);
		cloneItem.addActionListener(listener);
		cloneItem.setActionCommand("cloneTag");
		cloneItem.setIcon(IconUtils.getCloneIcon(16));
		
		TagMenuItem addAttributeItem = new TagMenuItem("Add/Remove Attributes",tag);
		addAttributeItem.addActionListener(listener);
		addAttributeItem.setActionCommand("addAttribute");
		addAttributeItem.setIcon(IconUtils.FAgetPencilIcon(16, ColorUtils.ATTR_COLOR.brighter()));
		if(tag.getAttrArr() == null || tag.getAttrArr().size() == 0) addAttributeItem.setEnabled(false);
			
		TagMenuItem addCustomAttributeItem = new TagMenuItem("Add Custom Attribute",tag);
		addCustomAttributeItem.addActionListener(listener);
		addCustomAttributeItem.setActionCommand("addCustomAttribute");
		addCustomAttributeItem.setIcon(IconUtils.FAgetPlusIcon(16, ColorUtils.ATTR_COLOR.brighter()));
		
		TagMenuItem AddReuquiredChildredItem = new TagMenuItem("Add All Required Elements",tag);
		AddReuquiredChildredItem.addActionListener(listener);
		AddReuquiredChildredItem.setActionCommand("addRequiredChildren");
		AddReuquiredChildredItem.setIcon(IconUtils.getStructureIcon(16));
		if(!XmlTagUtils.dependencyCheck(tag)) AddReuquiredChildredItem.setEnabled(false);
		
		
		JMenu addTagMenu = new JMenu("Add Element");
			
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
		
		
		TagMenuItem addCustomTagItem = new TagMenuItem("Add Custom Element",tag);
		addCustomTagItem.addActionListener(listener);
		addCustomTagItem.setActionCommand("addCustomTag");
		addCustomTagItem.setIcon(IconUtils.FAgetPlusIcon(16, ColorUtils.TAG_COLOR_BRIGHTER));
		if(tag.getContent() != null && tag.getContent().trim().length() > 0) addCustomTagItem.setEnabled(false);
		
		
		if(tag.getName().equals("license") || tag.getName().equals("doc")) {
			TagMenuItem addPathItem = new TagMenuItem("Select File",tag);
			addPathItem.addActionListener(listener);
			addPathItem.setActionCommand("addPath");
			addPathItem.setIcon(IconUtils.FAgetFolderOpenIcon(16, ColorUtils.FOLDER_BROWN));
			add(addPathItem);
			add(new JSeparator());
		}
		
		
			
		add(deleteTagItem);
		add(new JSeparator());
		add(cloneItem);
		add(copyItem);
		add(cutItem);
		add(pasteMenu);
		add(new JSeparator());
		add(addAttributeItem);
		add(addCustomAttributeItem);
		add(new JSeparator());
		add(addTagMenu);
		add(addCustomTagItem);
		add(AddReuquiredChildredItem);


		
		
		
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
	
	public XmlTag getTag() {
		return this.tag;
	}


}
