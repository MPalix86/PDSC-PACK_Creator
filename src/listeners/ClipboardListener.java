package listeners;

import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import business.Session;
import model.XmlTag;
import model.XmlTagSelection;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;

public class ClipboardListener implements FlavorListener , ActionListener{
	
	private Session session;
	
	public ClipboardListener() {
		session = Session.getInstance();
	}
	

	@Override
	public void flavorsChanged(FlavorEvent e) {
		System.out.println("status of clipboard changed");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(); 
		
		if(e.getSource().getClass().equals(TagMenuItem.class)) {
			TagMenuItem item = (TagMenuItem)e.getSource();
			XmlTag tag = item.getTag();
			if(command.equals("copyTag")) {
				XmlTagSelection xmlTagSelection = new XmlTagSelection(tag);
				session.getClipboard().setContents(xmlTagSelection, null);
				System.out.println("tag copied in clipboard " + tag.getName());
			}
			
			else if (command.equals("cutTag")) {
				XmlTagSelection xmlTagSelection = new XmlTagSelection(tag);
				session.getClipboard().setContents(xmlTagSelection, null);
				tag.getParent().removeSelectedChild(tag);
				session.getSelectedForm().UpdateView();
				System.out.println("tag cutted in clipboard " + tag.getName() + " " + tag.getParent().getName());
			}
			
			else if (command.equals("pasteFirst")) {
				XmlTagSelection xmlTagSelection = (XmlTagSelection) session.getClipboard().getContents(null);
				XmlTag tagInClipboard = null;
				XmlTag tagToPaste = null;
				try {
					tagInClipboard = (XmlTag) xmlTagSelection.getTransferData(XmlTagSelection.xmlTagFlavor);
					tagToPaste = new XmlTag(tagInClipboard);
				} catch (UnsupportedFlavorException | IOException e1) {
					e1.printStackTrace();
				}
				if(tagToPaste != null) tag.addSelectedChildAtIndex(tagToPaste, 0);
				tagToPaste.setRequired(false);
				tagToPaste.setParent(tag);
				session.getSelectedForm().UpdateView();
			}
			
			else if (command.equals("pasteLast")) {
				XmlTagSelection xmlTagSelection = (XmlTagSelection) session.getClipboard().getContents(null);
				XmlTag tagInClipboard = null;
				XmlTag tagToPaste = null;
				try {
					tagInClipboard = (XmlTag) xmlTagSelection.getTransferData(XmlTagSelection.xmlTagFlavor);
					tagToPaste = new XmlTag(tagInClipboard);
				} catch (UnsupportedFlavorException | IOException e1) {
					e1.printStackTrace();
				}
				if(tagToPaste != null) tag.addSelectedChildAtIndex(tagToPaste, tag.getSelectedChildrenArr().size());
				tagToPaste.setRequired(false);
				tagToPaste.setParent(tag);
				session.getSelectedForm().UpdateView();
			}
			
			else if (command.equals("pasteAtPosition")) {
		
				XmlTagSelection xmlTagSelection = (XmlTagSelection) session.getClipboard().getContents(null);
				XmlTag tagInClipboard = null;
				XmlTag tagToPaste = null;
				int index = DialogUtils.intEnumJoptionPane(tag);
				try {
					tagInClipboard = (XmlTag) xmlTagSelection.getTransferData(XmlTagSelection.xmlTagFlavor);
					tagToPaste = new XmlTag(tagInClipboard);
				} catch (UnsupportedFlavorException | IOException e1) {
					e1.printStackTrace();
				}
				if(index > 0) {
					tag.addSelectedChildAtIndex(tagToPaste, index);
					tagToPaste.setRequired(false);
					tagToPaste.setParent(tag);
					session.getSelectedForm().UpdateView();
				}
			}
		}
	}
}
