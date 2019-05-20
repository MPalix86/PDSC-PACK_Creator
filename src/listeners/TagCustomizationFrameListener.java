package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import business.Session;
import business.TagCustomizationBusiness;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeCheckBox;
import view.comp.TagBtn;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.Form;

/**
 * TagCustomizationFrame Listener
 * 
 * @author mirco Palese
 */
public class TagCustomizationFrameListener implements ItemListener, ActionListener{
	
	/** tagCustomizationFrame instance */
	private TagCustomizationFrame tagCustomizationFrame;
	
	private Session session;
	
	private WizardFrame pdscWizardFrame;

	
	
	
	
	/**
	 * Constructor
	 * 
	 * @param tagCustomizationFrame the tag Customization frame
	 */
	
	public TagCustomizationFrameListener (TagCustomizationFrame tagCustomizationFrame) {
		
		/** new instance of session */
		session = Session.getInstance();
		
		/** recovering pdsc wizard frame */
		this.pdscWizardFrame = session.getWizardFrame();
		
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
		
		/** recovering TagButton instance */
		TagBtn tagBtn = (TagBtn) e.getSource();
		
		if(command == "removeTagPanel") {
			
			/** recovering parent of the frame : tag customization frame */
			XmlTag parent = tagCustomizationFrame.getTagParent();
			
			/** recovering child that user want to remove (selected child); the instance in tag's selectedChildrenArr  */
			XmlTag selectedChild = tagBtn.getTag();			
			
			/** recovering model instance that contains all constraints for selected tag; i.e. the instance present in childrenArr */
			XmlTag modelChild = TagCustomizationBusiness.findModelChildFromSelectedChildName(parent, selectedChild.getName()); 
			
			/** removing selected child from selectedChildrenArr */
			parent.removeSelectedChild(selectedChild); 								
			
			/** recovering tag panel */
			JPanel tagPanel = (JPanel) tagBtn.getParent().getParent().getParent().getParent();	
			
			/** removing tag panel from tag customization frame */
			tagCustomizationFrame.removeTagPanel(tagPanel);
			
			/** maximum number of possible child in the model instance is augmented by one */
			modelChild.setMax(modelChild.getMax() + 1);										
																		
		}																				
		
		if(command == "addTagPanel") {
			
			System.out.println("premuto");
			
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
				tagCustomizationFrame.addTagPanel(newChild);
				
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
		
		
		if(command == "addInWizard") {
			
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
					pdscWizardFrame.addStep(new Form(newTag));
					tagCustomizationFrame.okMessage("Tag added correctly", "done");
					
					/** updating preview pane in wizard frame */
					session.getInstance().getWizardFrame().updateXmlPreview();
				}
			}
			
			/** if there aren't missing dependency */
			else {
				XmlTag tag = tagBtn.getTag();
				XmlTag newTag = new XmlTag(tag);
				newTag.freeModelFields();
				pdscWizardFrame.addStep(new Form(newTag));
				tagCustomizationFrame.okMessage("Tag added correctly", "done");
				
			}
			
			/** updating preview pane in wizard frame */
			session.getInstance().getWizardFrame().updateXmlPreview();
		}
		
		
		
		if(command.equals("showChildren")) {
			System.out.println("pressed");
			XmlTag tag = tagBtn.getTag();
			tagCustomizationFrame.updateRightPanel(tag);
		}
	}



}
