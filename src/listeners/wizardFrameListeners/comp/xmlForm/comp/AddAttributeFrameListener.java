package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import business.TagCustomizationBusiness;
import business.XmlTagBusiness;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeCheckBox;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;

public class AddAttributeFrameListener implements ActionListener , ItemListener{
	private AddAttributeFrame frame;
	
	public AddAttributeFrameListener(AddAttributeFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("addAttribute")) {
			System.out.println("AddAttributeFrame");
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
				tag.addSelectedAttr(new XmlAttribute(attr,tag));
			}
			
		}
		else {
			System.out.println("rimosso");
			XmlAttribute selectedAttr = XmlTagBusiness.findChildSelectedAttrFromName(attr.getTag(), attr.getName());
			tag.removeSelectedAttr(selectedAttr);
		}	
	}
}
