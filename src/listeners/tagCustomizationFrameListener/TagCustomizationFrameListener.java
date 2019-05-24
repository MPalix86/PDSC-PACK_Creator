package listeners.tagCustomizationFrameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import business.Session;
import business.TagCustomizationBusiness;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeCheckBox;
import view.comp.TagButton;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.wizardForm.Form;

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
		XmlTag tag = c.getTag();
		
		/** if attribute was selected */
		if(c.isSelected()) {
			tag.addSelectedAttr(new XmlAttribute(attr));
		}
		else {
			tag.removeSelectedAttr(attr);
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
			TagButton tagBtn = (TagButton) e.getSource();
			
			/** recovering child that user want to remove (selected child); the instance in tag's selectedChildrenArr  */
			XmlTag selectedChild = tagBtn.getTag();	
			
			/** recovering parent of the frame : tag customization frame */
			XmlTag parent = selectedChild.getParent();
			
			/** recovering model instance that contains all constraints for selected tag; i.e. the instance present in childrenArr */
			XmlTag modelChild = TagCustomizationBusiness.findModelChildFromSelectedChildName(parent, selectedChild.getName()); 
			
			/** removing selected child from selectedChildrenArr */
			parent.removeSelectedChild(selectedChild); 									
			
			/** removing tag panel from tag customization frame */
			tagCustomizationFrame.getTagContainer().removeTagPanel(selectedChild);
			
			/** maximum number of possible child in the model instance is augmented by one */
			modelChild.setMax(modelChild.getMax() + 1);										
																		
		}																				
		
		else if(command == "addTagPanel") {
			
			/** recovering TagButton instance */
			TagButton tagBtn = (TagButton) e.getSource();
			
			/** recovering model instance for selected child */
			XmlTag child = tagBtn.getTag();
			
			/** recovering parent instance for selected child */
			XmlTag parent = child.getParent();
			
			/** if max child number is > 0, add child */
			if(child.getMax() > 0 ) {
				
				/** creating new child instance of selected child with parent , passed parent */
				XmlTag newChild = new XmlTag(child, parent);
				
				/** adding new child in selectedChildArr of new parent */
				parent.addSelectedChild(newChild);
				
				/** adding tag inside tag customization frame */
				tagCustomizationFrame.getTagContainer().addNewTagPanel(newChild);
				
				/** maximum number of possible child in the model instance is reduced by one */
				child.setMax(child.getMax() -1);
				
			}
			
			/** if max child number is = 0, cannot add this child */
			else {		
				
				tagCustomizationFrame.warningMessage(	"<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
														+ " Maximum number of children reached for tag  " +tagBtn.getTag().getName() + 
														" </span></p></html>"
													); 
			}
		}
		
		
		else if(command.equals("cloneTag")) {
			
			/** recovering TagButton instance */
			TagButton tagBtn = (TagButton) e.getSource();
			
			/** recovering tag to clone from selectedChildrendArr*/
			XmlTag selectedTag = tagBtn.getTag();
			
			/** recovering parent instance for selected child */
			XmlTag parent = selectedTag.getParent();
			
			/** recovering model instance for constraints check */
			XmlTag modelTag = TagCustomizationBusiness.findModelChildFromSelectedChildName(parent, selectedTag.getName());
			
			/** recover copies number  */
			int copiesNumber = this.tagCustomizationFrame.cloneDialog();
			
			if(copiesNumber != -1){
				
				/** if max child number is > copiesNumber, add selected number of clones */
				if(modelTag.getMax() >= copiesNumber) {
					
					XmlTag clone = null;
					
					/** making copies */
					for (int i = 0; i < copiesNumber; i++) {
						clone = TagCustomizationBusiness.cloneTag(selectedTag);
						this.tagCustomizationFrame.getTagContainer().addClonedTag(clone);
					}
					
					modelTag.setMax(modelTag.getMax() - copiesNumber);
					this.tagCustomizationFrame.getTagContainer().updateView();
				}
				
				
				else {
					if(modelTag.getMax() == 0) {
						this.tagCustomizationFrame.warningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
																+ " Maximum number of children reached for tag  " +tagBtn.getTag().getName() + 
																	" </span></p></html>");
					}
					else {
						this.tagCustomizationFrame.warningMessage("You can make at least : " + modelTag.getMax() + " copy for tag <" + modelTag.getName() + " >");
					}
					
				}
			}
			
			
		
		}
		
		
		else if(command == "addInWizard") {
			
			/** recovering TagButton instance */
			TagButton tagBtn = (TagButton) e.getSource();
			
			/** check for missing dependency */
			XmlTag missingDependency = TagCustomizationBusiness.dependencyCheck(tagBtn.getTag()); 
			
			/** if there are missing dependency */
			if(missingDependency != null) {
				
				/** warn the user */ 
				boolean response = tagCustomizationFrame.yesNoWarningMessage(	"<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
																				+ " Missing dependency : " + missingDependency.getName() + 
																				" </p><br><p> Do you want to continue </span></p></html>"
																			); 
				/** if user want to continue */
				if(response) {
					
					/** add step inside wizard frame */
					XmlTag tag = tagBtn.getTag();
					XmlTag newTag = new XmlTag(tag);
					newTag.freeModelFields();
					wizardFrame.getFormContainer().addStep(new Form(newTag));
					tagCustomizationFrame.okMessage("Tag added correctly", "done");
					
					/** updating preview pane in wizard frame */
					session.getWizardFrame().updatePreview();
				}
			}
			
			/** if there aren't missing dependency */
			else {
				XmlTag tag = tagBtn.getTag();
				XmlTag newTag = new XmlTag(tag);
				newTag.freeModelFields();
				System.out.println("dsads" + newTag.getName());
				wizardFrame.getFormContainer().addStep(new Form(newTag));
				tagCustomizationFrame.okMessage("Tag added correctly", "done");
				
			}
			
			/** updating preview pane in wizard frame */
			session.getWizardFrame().updatePreview();
		}
		
		
		
		else if(command.equals("showChildren")) {
			
			/** recovering TagButton instance */
			TagButton tagBtn = (TagButton) e.getSource();
			
			XmlTag tag = tagBtn.getTag();
			tagCustomizationFrame.updateLeftPanel(tag);
		}
		
		
		
		else if(command.equals("cancel")) {
			this.tagCustomizationFrame.dispose();
		}
	}
	
	



}
