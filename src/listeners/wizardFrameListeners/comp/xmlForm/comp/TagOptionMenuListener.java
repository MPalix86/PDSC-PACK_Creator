package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.CustomUtils;
import business.Session;
import business.XmlAttributeBusiness;
import business.XmlTagBusiness;
import model.Response;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.DialogUtils;
import view.comp.TagMenuItem;
import view.wizardFrame.comp.xmlForm.comp.TagRow;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;

public class TagOptionMenuListener implements ActionListener{
	
	private Session session = Session.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		TagMenuItem item = (TagMenuItem) e.getSource();
		XmlTag tag = item.getTag();
		
		if(command.equals("deleteTag")) {
			
			if(tag.getParent() != null) {
				XmlTag parent = tag.getParent();
				int tagOccurrenceInParent = XmlTagBusiness.findChildOccurrenceNumber(parent,tag.getName());
				boolean response = true;
				if(tag.isRequired() && tagOccurrenceInParent <= 1) {
					response = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n < " + parent.getName() + "> must contain at least one tag < " + tag.getName() + " > \n Do you want to continue ?");
				}
				if(response) {
					XmlTag modelTag = XmlTagBusiness.findModelChildFromSelectedChildName(parent, tag.getName());
					modelTag.setMax(modelTag.getMax() + 1);
					parent.removeSelectedChild(tag);
				}
			}
			else {
				session.getSelectedForm().getRoot().removeSelectedChild(tag);
			}
			session.getSelectedForm().UpdateView();
		}
		
		
		else if(command.equals("addAttribute")) {
			new AddAttributeFrame(tag);
		}

		
		
		else if(command.equals("addCustomAttribute")) {
			
			String attrNames = DialogUtils.showInputDialog("Add Custom Attribute", "Add one or more attributes separated by space \n attr1 attr2 ...");
			
			if (attrNames != null){
				/** separating string by comma */
				String[] names = CustomUtils.separateText(attrNames, " ");
				
				/** reverse array */
				names = (String[]) CustomUtils.reverseArray(names);
				
				
				String errorMessage = "";
				
				for (String name : names){
					
					Response response = XmlAttributeBusiness.verifyAttributeFromName(tag, name);
					
					if(response.getStatus() == XmlAttribute.INVALID_NAME) errorMessage += " '" + name + "' Invalid name \n";
					else if (response.getStatus() == XmlAttribute.ALREADY_PRESENT) errorMessage += "Attribute \" " + name  + " \" is already present \n" ;
					else {
						XmlAttribute attr = (XmlAttribute) response.getObject();
						attr.setTag(tag);
						tag.addSelectedAttrAtIndex(attr, 0);
						TagRow row = session.getSelectedForm().getTagOpenRow(tag);
						row.update();
					}
				}
				if(!errorMessage.contentEquals("")) DialogUtils.warningMessage(errorMessage);
					 
			}
		}
		
	}

}
