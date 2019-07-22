package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import business.CustomUtils;
import business.Session;
import business.XmlAttributeBusiness;
import business.XmlTagBusiness;
import model.Response;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;
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
					if(modelTag != null) modelTag.setMax(modelTag.getMax() + 1);
					parent.removeSelectedChild(tag);
				}
			}
			else {
				session.getSelectedForm().getRoot().removeSelectedChild(tag);
			}
			/**
			 * IMPORTANT : saving state of root tag for undo redo action
			 */
			Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
			
			session.getSelectedForm().UpdateView();
			
		}
		
		
		else if(command.equals("addAttribute")) {
			/** adding attributes */
			new AddAttributeFrame(tag);
		}
		
		
		else if (command.equals("addPath")) {
			File file = DialogUtils.showChooseFileFrame();
			if(file != null) {
				
				/** setting tag content */
				if(tag.getContent()!= null) tag.setContent(tag.getContent().replace(FilenameUtils.getName(tag.getContent()), "") + file.getName()) ;
				else tag.setContent(file.getName()) ;
				
				/** adding source path in pdscDoc*/
				session.getSelectedPDSCDoc().addTagPath(tag, file.getAbsolutePath());
				
				/** updating row */
				session.getSelectedForm().getTagOpenRow(tag).update();
				
			}		
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
						row.requestFocus();
						
						/**
						 * IMPORTANT : saving state of root tag for undo redo action
						 */
						Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
					}
				}
				if(!errorMessage.contentEquals("")) DialogUtils.warningMessage(errorMessage);
					 
			}
		}
		
		
		
		else if(command.equals("addTag")) {
			
			/** recovering TagButton instance */
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			
			/** recovering model instance for selected child */
			XmlTag child = tagMenuItem.getTag();
			
			boolean response = true;
			
			/** if maximum number of children reached for parent tag ask confirmation */
			if(child.getMax() <= 0) {
				response = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n maximum number of children reached for tag < " + child.getName() + " > Do you want to continue ?");
			}
			if(response) {
				
				/** recovering parent instance for selected child */
				XmlTag parent = child.getParent();

				/** creating new child instance of selected child with parent , passed parent */
				XmlTag newChild = new XmlTag(child, parent);
				
				/** adding new child in selectedChildArr of new parent */
				parent.addSelectedChildAtIndex(newChild, 0);
				
				/** maximum number of possible child in the model instance is reduced by one */
				child.setMax(child.getMax() -1);
				
				/** updating view */
				session.getSelectedForm().UpdateView();
				
				/** to avoid focus lost on AddAttributeFrame caused by UpdaView above, it is added at the end of awt event queue */
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						if(newChild.getAttrArr() != null) {
							new AddAttributeFrame(newChild).toFront();
						}
					}
				});
				
				/**
				 * IMPORTANT : saving state of root tag for undo redo action
				 */
				Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
				
			}
		}
		
		else if(command.equals("addCustomTag")) {
			
			/** verify if tag haven't content setted*/
			if(tag.getContent() != null && tag.getContent().trim().length() > 0) {
				DialogUtils.warningMessage("Following PDSC standard : \n tags with textual content cannot have children");
			}
			
			else{
				String tagNames = DialogUtils.showInputDialog("Add Custom Tag", "Add one or more tags separated by space \n tag1 tag2 ...");
				
				if (tagNames != null){
					/** separating string by comma */
					String[] names = CustomUtils.separateText(tagNames, " ");
					
					/** reverse array */
					names = (String[]) CustomUtils.reverseArray(names);
					
					for (String name : names){
						
						Response response = XmlTagBusiness.verifyTagFromName(name, tag);
						
						boolean confirmation = true ;
						if (response.getStatus() == XmlTagBusiness.MAX_REACHED) {
							confirmation = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n maximum number of children reached for tag < " + name + " > Do you want to continue ?");
						}
						if(confirmation) {
							XmlTag child = (XmlTag) response.getObject();
							tag.addSelectedChildAtIndex(child, 0);
							session.getSelectedForm().UpdateView();
							/**
							 * IMPORTANT : saving state of root tag for undo redo action
							 */
							Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
						}
					}	 
				}
			}
		}
	}
}
