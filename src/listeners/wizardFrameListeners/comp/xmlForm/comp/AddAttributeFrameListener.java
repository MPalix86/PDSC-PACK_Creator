package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import business.Session;
import business.TagManager;
import business.utils.XmlTagUtils;
import mao.XmlAttributeMao;
import model.xml.XmlAttribute;
import model.xml.XmlTag;
import view.comp.AttributeButton;
import view.comp.AttributeCheckBox;
import view.wizardFrame.comp.xmlForm.comp.TagRow;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;

public class AddAttributeFrameListener implements ActionListener , ItemListener{
	private AddAttributeFrame frame;
	private Session session;
	private XmlTag tag;
	
	/** 
	 * copy of original tag.
	 * All changes are made on the copy. If user decides to confirm changes 
	 * the original is replaced with the copy
	 */
	private XmlTag tagCopy;
	
	
	
	public AddAttributeFrameListener(AddAttributeFrame frame) {
		this.frame = frame;
		session = Session.getInstance();
		this.tag = frame.geTtag();
		this.tagCopy = frame.getOriginalTagCopy();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("addAttributes")) {
			TagManager.setSelectedAttributesArr(tag, tagCopy.getSelectedAttrArr(), true);

			frame.dispose();
			TagRow row = session.getSelectedForm().getTagOpenRow(tag);
			if(row != null) {
				row.update();
				row.requestFocus();
			}
			
		}
		
		else if(command.equals("showDescription")) {
			AttributeButton b = (AttributeButton) e.getSource();
			XmlAttribute attr = b.getAttribute();
			String description = XmlAttributeMao.getAttributeDescription(attr, attr.getTag());
			if(description != null) frame.updateDescription(attr.getName() , description);
			else frame.updateDescription(attr.getName() , "NO DESCRIPTION AVAILABLE FOR THIS ATTRIBUTE");
		}
		
		else if(command.equals("cancel")) {
			frame.dispose();
		}
	}

	

	@Override
	public void itemStateChanged(ItemEvent e) {
		AttributeCheckBox c = (AttributeCheckBox) e.getItem();
		XmlAttribute attr =  c.getAttr();
		/** if attribute was selected */
		if(c.isSelected()) {
			if(XmlTagUtils.findChildSelectedAttrFromName(tagCopy, attr.getName()) == null) TagManager.addAttributeInTag(tagCopy, new XmlAttribute(attr,attr.getTag()), false, true, null);
			System.out.println(attr.getName() + " " + attr.getValue());
		}	
		else {
			XmlAttribute selectedAttr = XmlTagUtils.findAttributeFromArrayOfAttributes(tagCopy.getSelectedAttrArr(), attr.getName());
			TagManager.removeSelectedAttributeFromParent( selectedAttr , tagCopy, false, false);
		}	
	}
}
