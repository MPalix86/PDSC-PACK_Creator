package listeners;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import business.Session;
import business.XmlTagBusiness;
import business.XmlTagUtils;
import model.XmlAttribute;
import model.XmlAttributeSelection;
import model.XmlTag;
import model.XmlTagSelection;
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
		//System.out.println("status of clipboard changed");
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
				//System.out.println("tag copied in clipboard " + tag.getName());
			}
			
			else if (command.equals("cutTag")) {
				XmlTagSelection xmlTagSelection = new XmlTagSelection(tag);
				session.getClipboard().setContents(xmlTagSelection, null);
				XmlTagBusiness.removeSelectedChildFromParent(tag, tag.getParent(), true, true);
				session.getSelectedForm().UpdateView();
			}
			
			else if (command.equals("pasteFirst")) {
				
				/** if paste Tag */
				if(clipboard.getContents(null).getClass().equals(XmlTagSelection.class)) {
					XmlTagSelection xmlTagSelection = (XmlTagSelection) clipboard.getContents(null);
					XmlTag tagInClipboard = null;
					XmlTag tagToPaste = null;
					
					try {
						tagInClipboard = (XmlTag) xmlTagSelection.getTransferData(XmlTagSelection.xmlTagFlavor);
						tagToPaste = new XmlTag(tagInClipboard);
					} catch (UnsupportedFlavorException | IOException e1) {
						e1.printStackTrace();
					}
					XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(tag, tagToPaste.getName());
					if(tagToPaste != null) XmlTagBusiness.pasteTagInParent(tagToPaste, modelTag, tag, true, true,0);
					tagToPaste.setRequired(false);
		
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
					XmlTagBusiness.addAttributeInTag(tag, attrToPaste, true, true, 0);
				}
				
				session.getSelectedForm().UpdateView();
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
					XmlTagBusiness.pasteTagInParent(tagToPaste, modelTag, tag, true, true,index);
					session.getSelectedForm().UpdateView();
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
				XmlTagBusiness.removeSelectedAttributeFromParent(attr, attr.getTag(), true, true);
				session.getClipboard().setContents(xmlAttrSelection, null);
				session.getSelectedForm().getTagOpenRow(attr.getTag()).update();
			}
		}
	}
	
	
	
}