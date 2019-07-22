package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import business.Session;
import business.TagCustomizationBusiness;
import business.XmlAttributeBusiness;
import business.XmlTagBusiness;
import model.XmlAttribute;
import model.XmlTag;
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
			
			tag.replaceWith(tagCopy);
			
			frame.dispose();
		
			TagRow row = session.getSelectedForm().getTagOpenRow(tag);
			row.update();
			row.requestFocus();
			
			/**
			 * IMPORTANT : saving state of root tag for undo redo action
			 */
			Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		}
		
		else if(command.equals("showDescription")) {
			AttributeButton b = (AttributeButton) e.getSource();
			XmlAttribute attr = b.getAttribute();
			String description = XmlAttributeBusiness.getAttrDescription(attr);
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
			
			/** if the attribute has not already been added */
			if(!TagCustomizationBusiness.tagHasAttribute(tag,attr)) {
				tagCopy.addSelectedAttrAtIndex(new XmlAttribute(attr, tagCopy), 0);
			}
			
		}
		
		/** if attribute was unset */
		else {
			XmlAttribute selectedAttr = XmlTagBusiness.findAttributeFromArrayOfAttributes(tagCopy.getSelectedAttrArr(), attr.getName());
			if(tagCopy.getSelectedAttrArr().remove(selectedAttr)) {
			}
		}	
	}
}
