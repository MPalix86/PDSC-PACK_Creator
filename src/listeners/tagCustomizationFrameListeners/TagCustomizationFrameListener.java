package listeners.tagCustomizationFrameListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import business.Session;
import business.XmlTagBusiness;
import business.XmlTagUtils;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeCheckBox;
import view.comp.TagButton;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;
import view.comp.utils.IconUtils;
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
		if(c.isSelected()) 
			if(XmlTagUtils.findChildSelectedAttrFromName(tag, attr.getName()) == null) tag.addSelectedAttr(new XmlAttribute(attr,tag));
			
		else {
			XmlAttribute selectedAttr = XmlTagUtils.findChildSelectedAttrFromName(attr.getTag(), attr.getName());
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
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag selectedChild = tagMenuItem.getTag();	
			XmlTagBusiness.removeSelectedChildFromParent(selectedChild, selectedChild.getParent(), true, true);
			tagCustomizationFrame.getTagContainer().updateView();															
		}																				
		
		
		else if(command == "addTagPanel") {

			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag child = tagMenuItem.getTag();
			XmlTag parent = child.getParent();
			XmlTagBusiness.addTagInParent(child, parent, false, true);
		}
		
		
		else if(command.equals("cloneTag")) {
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag selectedTag = tagMenuItem.getTag();
			int copiesNumber = DialogUtils.cloneDialog();
			XmlTagBusiness.cloneTag(selectedTag, copiesNumber, false, true);
			tagCustomizationFrame.getTagContainer().updateView();
		}
		
		else if(command == "addInWizard") {
			boolean confirm = true;
			TagButton tagBtn = (TagButton) e.getSource();
			XmlTag tag = tagBtn.getTag();
			
			/** if there are missing dependency */
			if(XmlTagUtils.dependencyCheck(tag)) {
				String message =	 "<html><p><span style=\"font-size: 14pt; color: #333333;\"> There are missing dependency for tag <" + tag.getName() + " >" +
									" </p><br><p> Do you want to continue </span></p></html>";
				
				/** warn the user */ 
				boolean response = DialogUtils.yesNoWarningMessage(message); 
				
				/** if user dont't want to continue anyway*/
				if(!response) confirm = false;
			}
			
			/** if there aren't missing dependency or user want to continue*/
			if(confirm) {
				XmlTag newTag = new XmlTag(tag,tag.getParent());
				
				/** if selected PDSCDoc == null  */
				if (session.getSelectedPDSCDoc() == null) {
					DialogUtils.warningMessage("No document selected");
				}
				
				/** if PDSCDoc != null user is updating PDSCDoc on which is working */
				else {
					session.getSelectedForm().addRootChild(newTag);
				}
				tagCustomizationFrame.dispose();
				DialogUtils.noButtonsTemporaryMessage("Tag added correctly", IconUtils.getOkIcon(48), 1000, session.getWizardFrame());	
			}
			
		}
		
		else if (command.equals("addRequiredTags")) {
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag tag = tagMenuItem.getTag();
			XmlTagBusiness.addRequiredChildren(tag, false);
			tagCustomizationFrame.getTagContainer().updateView();
		}
 
		else if(command.equals("cancel")) this.tagCustomizationFrame.dispose();
	}
	
	



}
