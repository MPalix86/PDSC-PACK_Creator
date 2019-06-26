package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import business.Session;
import business.TagCustomizationBusiness;
import business.XmlTagBusiness;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeButton;
import view.comp.AttributeCheckBox;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;

public class AddAttributeFrameListener implements ActionListener , ItemListener{
	private AddAttributeFrame frame;
	private Session session;
	
	public AddAttributeFrameListener(AddAttributeFrame frame) {
		this.frame = frame;
		session = Session.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("addAttributes")) {
			ArrayList<XmlAttribute> newSelectedAttr = frame.getAddeAttrArr();
			XmlTag tag = frame.geTtag();
			if(newSelectedAttr != null) newSelectedAttr.forEach(a -> tag.addSelectedAttrAtIndex(a, 0));
			frame.dispose();
			session.getSelectedForm().getTagOpenRow(tag).update();

		}
		
		else if(command.equals("showDescription")) {
			AttributeButton b = (AttributeButton) e.getSource();
			XmlAttribute attr = b.getAttribute();
			frame.updateDescription(attr.getName() , "EHHHHHHHHHHHH");
		}
		
		else if(command.equals("cancel")) {
			frame.dispose();
		}
	}

	

	@Override
	public void itemStateChanged(ItemEvent e) {
		AttributeCheckBox c = (AttributeCheckBox) e.getItem();
		XmlAttribute attr =  c.getAttr();
		XmlTag tag = attr.getTag();
		
		/** if attribute was selected */
		if(c.isSelected()) {
			
			/** if the attribute has not already been added */
			if(!TagCustomizationBusiness.tagHasAttribute(tag,attr)) {
				frame.getAddeAttrArr().add(new XmlAttribute(attr,tag));
			}
			
		}
		else {
			XmlAttribute selectedAttr = XmlTagBusiness.findChildSelectedAttrFromName(attr.getTag(), attr.getName());
			frame.getAddeAttrArr().remove(selectedAttr);
		}	
	}
}
