package listeners;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import business.Session;
import business.TagManager;
import business.utils.XmlTagUtils;
import model.xml.XmlAttribute;
import model.xml.XmlAttributeSelection;
import model.xml.XmlTag;
import model.xml.XmlTagSelection;
import view.comp.AttributeMenuItem;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;

public class ClipboardListener implements FlavorListener , ActionListener{
	
	private Session session;
	private Clipboard clipboard;
	
	public ClipboardListener() {
		session = Session.getInstance();
		this.clipboard = session.getClipboard();
	}
	

	@Override
	public void flavorsChanged(FlavorEvent e) {
		System.out.println("status of clipboard changed");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); 
		
		/** 
		 * if command comes from tag 
		 */
		if(e.getSource().getClass().equals(TagMenuItem.class)) {
			
			TagMenuItem item = (TagMenuItem)e.getSource();
			XmlTag tag = item.getTag();
			if(command.equals("copyTag")) {
				XmlTagSelection xmlTagSelection = new XmlTagSelection(tag);
				session.getClipboard().setContents(xmlTagSelection, null);
			}
			
			else if (command.equals("cutTag")) {
				XmlTagSelection xmlTagSelection = new XmlTagSelection(tag);
				session.getClipboard().setContents(xmlTagSelection, null);
				TagManager.removeSelectedChildFromParent(tag, tag.getParent(), true, true);
				session.getSelectedForm().repaintView();
			}
			
			else if (command.equals("pasteFirst")) {
				
				if(clipboard.getContents(null).getClass().equals(XmlTagSelection.class)) {
					XmlTagSelection xmlTagSelection = (XmlTagSelection) clipboard.getContents(null);
					XmlTag tagInClipboard = null;
					XmlTag tagToPaste = null;
					try {
						tagInClipboard = (XmlTag) xmlTagSelection.getTransferData(XmlTagSelection.xmlTagFlavor);
						tagToPaste = tagInClipboard;
						
					} catch (UnsupportedFlavorException | IOException e1) {
						e1.printStackTrace();
					}
					XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(tag, tagToPaste.getName());
					
					/** if root contains tag in clipboard, we have to paste copied tag */
					if(session.getSelectedRoot().containsChild(tagInClipboard)) {
						tagToPaste = new XmlTag(tagInClipboard);
						
					}
					/** if root not contains tag in clipboard user cutted tag */
					else {
						tagToPaste = tagInClipboard;
					}
					if(tagToPaste != null) TagManager.pasteTagInParent(tagToPaste, modelTag, tag, true, true,0);
					session.getSelectedForm().repaintView();
					
				}
				
				/** if paste attribute */
				else if(clipboard.getContents(null).getClass().equals(XmlAttributeSelection.class)) {
					XmlAttributeSelection xmlAttrSelection = (XmlAttributeSelection) clipboard.getContents(null);
					XmlAttribute attrInClipboard = null;
					XmlAttribute attrToPaste = null;
					
					try {
						attrInClipboard = (XmlAttribute) xmlAttrSelection.getTransferData(XmlAttributeSelection.xmlAttrFlavor);
						attrToPaste = new XmlAttribute(attrInClipboard, attrInClipboard.getTag());
					} catch (UnsupportedFlavorException | IOException e1) {
						e1.printStackTrace();
					}
					attrToPaste.setRequired(false);
					TagManager.addAttributeInTag(tag, attrToPaste, true, true, 0);
					
					session.getSelectedForm().getTagOpenRow(tag).update();
				}
				
			}
			
			
			else if (command.equals("pasteAtPosition")) {
		
				XmlTagSelection xmlTagSelection = (XmlTagSelection) session.getClipboard().getContents(null);
				XmlTag tagInClipboard = null;
				XmlTag tagToPaste = null;
				int index = DialogUtils.indexTagEnumJoptionPane(tag);
				try {
					tagInClipboard = (XmlTag) xmlTagSelection.getTransferData(XmlTagSelection.xmlTagFlavor);
					tagToPaste = new XmlTag(tagInClipboard);
				} catch (UnsupportedFlavorException | IOException e1) {
					e1.printStackTrace();
				}
				if(index > 0) {
					XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(tag, tagToPaste.getName());
					TagManager.pasteTagInParent(tagToPaste, modelTag, tag, true, true,index);
					session.getSelectedForm().repaintView();
				}
			}
			
			
			
		}
		
		
		/** 
		 * if command comes from attribute 
		 */
		else if(e.getSource().getClass().equals(AttributeMenuItem.class)) {
			
			if(command.equals("copyAttr")) {
				AttributeMenuItem attrItem =  (AttributeMenuItem) e.getSource();
				XmlAttribute attr = attrItem.getAttr();
				XmlAttributeSelection xmlAttrSelection = new XmlAttributeSelection(attr);
				session.getClipboard().setContents(xmlAttrSelection, null);
			}
			
			if(command.equals("cutAttr")) {
				AttributeMenuItem attrItem =  (AttributeMenuItem) e.getSource();
				XmlAttribute attr = attrItem.getAttr();
				XmlAttributeSelection xmlAttrSelection = new XmlAttributeSelection(attr);
				TagManager.removeSelectedAttributeFromParent(attr, attr.getTag(), true, true);
				session.getClipboard().setContents(xmlAttrSelection, null);
				session.getSelectedForm().getTagOpenRow(attr.getTag()).update();
			}
		}
	}
	
	
	
}