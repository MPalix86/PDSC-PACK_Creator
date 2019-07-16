package listeners.tagCustomizationFrameListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import business.Session;
import business.TagCustomizationBusiness;
import business.XmlTagBusiness;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeCheckBox;
import view.comp.TagButton;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.WizardFrame;

/**
 * TagCustomizationFrame Listener
 * 
 * @author mirco Palese
 */
public class TagCustomizationFrameListener implements ItemListener, ActionListener{
	
	/** tagCustomizationFrame instance */
	private TagCustomizationFrame tagCustomizationFrame;
	
	private Session session;
	
	private WizardFrame wizardFrame;

	
	
	
	
	/**
	 * Constructor
	 * 
	 * @param tagCustomizationFrame the tag Customization frame
	 */
	
	public TagCustomizationFrameListener (TagCustomizationFrame tagCustomizationFrame) {
		
		/** new instance of session */
		session = Session.getInstance();
		
		/** recovering pdsc wizard frame */
		this.wizardFrame = session.getWizardFrame();
		
		/** recovering tag customization frame */
		this.tagCustomizationFrame = tagCustomizationFrame;
	}
	
	
	
	
	
	/** 
	 * Check for attributeCheckboxes status change
	 * 
	 * @param e the item that caused event
	 */
	
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
			XmlAttribute selectedAttr = XmlTagBusiness.findChildSelectedAttrFromName(attr.getTag(), attr.getName());
			tag.removeSelectedAttr(selectedAttr);
		}	
	}
	
	
	
	
	/** 
	 * Check for button status change
	 * 
	 * @param e the item that caused event
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command == "removeTagPanel") {
			
			/** recovering TagButton instance */
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			
			/** recovering child that user want to remove (selected child); the instance in tag's selectedChildrenArr  */
			XmlTag selectedChild = tagMenuItem.getTag();	
			
			/** recovering parent of the frame : tag customization frame */
			XmlTag parent = selectedChild.getParent();
			
			/** recovering model instance that contains all constraints for selected tag; i.e. the instance present in childrenArr */
			XmlTag modelChild = XmlTagBusiness.findModelChildFromSelectedChildName(parent, selectedChild.getName()); 

			int tagOccurrenceInParent = XmlTagBusiness.findChildOccurrenceNumber(parent,selectedChild.getName());
			
			boolean response = true;
			if (modelChild.isRequired() && tagOccurrenceInParent <= 1 ) {
				response = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n < " + parent.getName() + "> must contain at least one tag < " + modelChild.getName() + " > \n Do you want to continue ?");
			}
			
			if(response) {
				/** removing selected child from selectedChildrenArr */
				parent.removeSelectedChild(selectedChild); 									
				
				/** removing tag panel from tag customization frame */
				tagCustomizationFrame.getTagContainer().removeTagPanel(selectedChild);
				
				/** maximum number of possible child in the model instance is augmented by one */
				modelChild.setMax(modelChild.getMax() + 1);					
			}
			
							
																		
		}																				
		
		else if(command == "addTagPanel") {
			
			/** recovering TagButton instance */
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			
			/** recovering model instance for selected child */
			XmlTag child = tagMenuItem.getTag();
			
			/** recovering parent instance for selected child */
			XmlTag parent = child.getParent();
			
			/** if max child number is > 0, add child */
			if(child.getMax() > 0 ) {
				
				/** creating new child instance of selected child with parent , passed parent */
				XmlTag newChild = new XmlTag(child, parent);
				
				/** adding new child in selectedChildArr of new parent */
				parent.addSelectedChild(newChild);
				
				tagCustomizationFrame.getTagContainer().updateView();
				
				/** maximum number of possible child in the model instance is reduced by one */
				child.setMax(child.getMax() -1);
				
			}
			
			/** if max child number is = 0, cannot add this child */
			else {		
				
				DialogUtils.warningMessage(	"<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
														+ " Maximum number of children reached for tag  " +tagMenuItem.getTag().getName() + 
														" </span></p></html>"
													); 
			}
		}
		
		
		else if(command.equals("cloneTag")) {
			
			/** recovering TagButton instance */
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			
			/** recovering tag to clone from selectedChildrendArr*/
			XmlTag selectedTag = tagMenuItem.getTag();
			
			/** recovering parent instance for selected child */
			XmlTag parent = selectedTag.getParent();
			
			/** recovering model instance for constraints check */
			XmlTag modelTag = XmlTagBusiness.findModelChildFromSelectedChildName(parent, selectedTag.getName());
			
			/** recover copies number  */
			int copiesNumber = DialogUtils.cloneDialog();
			
			if(copiesNumber != -1){
				
				/** if max child number is > copiesNumber, add selected number of clones */
				if(modelTag.getMax() >= copiesNumber) {
					
					XmlTag clone = null;
					
					/** making copies */
					for (int i = 0; i < copiesNumber; i++) {
						clone = TagCustomizationBusiness.cloneTag(selectedTag);
						this.tagCustomizationFrame.getTagContainer().updateView();
					}
					
					modelTag.setMax(modelTag.getMax() - copiesNumber);
					this.tagCustomizationFrame.getTagContainer().updateView();
				}
				
				
				else {
					if(modelTag.getMax() == 0) {
						DialogUtils.warningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
																+ " Maximum number of children reached for tag  " +tagMenuItem.getTag().getName() + 
																	" </span></p></html>");
					}
					else {
						DialogUtils.warningMessage("You can make at least : " + modelTag.getMax() + " copy for tag <" + modelTag.getName() + " >");
					}
					
				}
			}
			
			
		
		}
		
		/**
		 * Add in wizard frame ( in selectedForm ) just modified tag
		 * 
		 * IMPORTANT:
		 * if selectedForm is set to null tag will be added to a new Form
		 * 
		 */
		else if(command == "addInWizard") {
			
			boolean confirm = true;
			
			/** recovering TagButton instance */
			TagButton tagBtn = (TagButton) e.getSource();
			
			/** check for missing dependency */
			XmlTag missingDependency = TagCustomizationBusiness.dependencyCheck(tagBtn.getTag()); 
			
			/** if there are missing dependency */
			if(missingDependency != null) {
				
				/** warn the user */ 
				boolean response = DialogUtils.yesNoWarningMessage(	"<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
																				+ " Missing dependency : " + missingDependency.getName() + 
																				" </p><br><p> Do you want to continue </span></p></html>"
																			); 
				/** if user dont't want to continue anyway*/
				if(!response) {
					confirm = false;
				}
			}
			
			/** if there aren't missing dependency */
			if(confirm) {
				
				/** add tag in wizard frame */
				XmlTag tag = tagBtn.getTag();
				
				XmlTag newTag;
				
				if(tag.getParent() != null) newTag = new XmlTag(tag,tag.getParent());
				else newTag = new XmlTag(tag);
			
				/** if selected PDSCDoc == null  */
				if (session.getSelectedPDSCDoc() == null) {
					DialogUtils.warningMessage("No document selected");
				}
				
				/** if PDSCDoc != null user is updating PDSCDoc on which is working */
				else {
					session.getSelectedForm().addRootChild(newTag);
				}
				
				DialogUtils.okMessage("Tag added correctly", "done");
				
				tagCustomizationFrame.dispose();
				
			}
			
		}
		
		else if (command.equals("addRequiredTags")) {
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag tag = tagMenuItem.getTag();
			TagCustomizationBusiness.addRequiredTags(tag);
			tagCustomizationFrame.getTagContainer().updateView();

		}

		  
		else if(command.equals("cancel")) {
			this.tagCustomizationFrame.dispose();
		}
	}
	
	



}
