package view.wizardFrame.comp.xmlForm.comp.attributeComp;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import business.Session;
import listeners.ClipboardListener;
import listeners.wizardFrameListeners.comp.xmlForm.comp.AttributeOptionMenuListener;
import model.XmlAttribute;
import view.comp.AttributeMenuItem;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconUtils;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class AttributeOptionMenu extends JPopupMenu{
	
	private XmlAttribute attr;
	private JLabel sourceLabel;
	private AttributeOptionMenuListener listener;
	private ClipboardListener clipboardListener;
	
	public AttributeOptionMenu(XmlAttribute attr, JLabel sourceLabel) {
		this.attr = attr;
		this.sourceLabel = sourceLabel;
		this.listener = new AttributeOptionMenuListener();
		this.clipboardListener = new ClipboardListener();
		
		
		AttributeMenuItem deleteAttrItem = new AttributeMenuItem("Delete",attr);
		deleteAttrItem.addActionListener(listener);
		deleteAttrItem.setActionCommand("deleteAttribute");
		deleteAttrItem.setIcon(IconUtils.getTrashIcon(16));
		
		
		if (attr.getPossibleValuesType() != null && attr.getPossibleValuesType().equals("File")) {
			AttributeMenuItem addPathItem = new AttributeMenuItem("Select File",attr);
			addPathItem.addActionListener(listener);
			addPathItem.setActionCommand("addPath");
			addPathItem.setIcon(IconUtils.FAgetFolderOpenIcon(16, ColorUtils.FOLDER_BROWN));
			add(addPathItem);
			
			AttributeMenuItem removeFileItem = new AttributeMenuItem("Remove File",attr);
			removeFileItem.addActionListener(listener);
			removeFileItem.setActionCommand("removePath");
			removeFileItem.setIcon(IconUtils.removeFileIcon(16));
			add(removeFileItem);	
			if(attr.getFile() == null) removeFileItem.setEnabled(false);
			
			add(new JSeparator());
		}
		
		if(attr.getPossibleValues() != null) {
			AttributeMenuItem CustomValueItem = new AttributeMenuItem("Insert Custom Value",attr);
			System.out.println(attr.getPossibleValues().getClass());
			CustomValueItem.addActionListener(listener);
			CustomValueItem.setActionCommand("addCustomValue");
			add(CustomValueItem);
			add(new JSeparator());
		}
		
		AttributeMenuItem copyItem = new AttributeMenuItem("Copy" , attr);
		copyItem.addActionListener(clipboardListener);
		copyItem.setActionCommand("copyAttr");
		copyItem.setIcon(IconUtils.getCopyIcon(16));
		
		AttributeMenuItem cutItem = new AttributeMenuItem("Cut" , attr);
		cutItem.addActionListener(clipboardListener);
		cutItem.setActionCommand("cutAttr");
		cutItem.setIcon(IconUtils.getCutIcon(16));
		
		
		add(deleteAttrItem);
		add(new JSeparator());
		add(copyItem);
		add(cutItem);

		
		/**
		 * IMPORTANT
		 * to avoid scrollbar reset caused from method : row.scrollRectToVisible(this.getBounds()); 
		 * present in TagFormTextField, TagFormTextArea, TagFormCombobox, 
		 * AttributeFormCombobox, AttributeFormTextField
		 * 
		 * must to remove focus when jpopupmenu is called;
		 */
		TagRow row = Session.getInstance().getSelectedForm().getTagOpenRow(attr.getTag());
		if(row != null) row.grabFocus();
	
	
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