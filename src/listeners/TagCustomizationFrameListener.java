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
import view.TagCustomizationFrame;
import view.WizardFrame;
import view.Components.ModelComponents.AttributeCheckBox;
import view.Components.ModelComponents.TagBtn;
import view.Components.wizardFrameComponents.Form;

public class TagCustomizationFrameListener implements ItemListener, ActionListener{
	private TagCustomizationFrame tagCustomizationFrame;
	private TagBtn tagBtn;
	private TagCustomizationBusiness tagBusiness;  
	private Session session;
	private WizardFrame pdscWizardFrame;

	public TagCustomizationFrameListener (TagCustomizationFrame tagCustomizationFrame) {
		session = Session.getInstance();
		this.pdscWizardFrame = session.getWizardFrame();
		tagBusiness = new TagCustomizationBusiness();
		this.tagCustomizationFrame = tagCustomizationFrame;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		AttributeCheckBox c = (AttributeCheckBox) e.getItem();
		XmlAttribute attr =  c.getAttr();
		XmlTag tag = c.getTag();
		if(c.isSelected()) {
			tag.addSelectedAttr(attr);
		}
		else {
			tag.removeSelectedAttr(attr);
		}	
	}
		  

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		tagBtn = (TagBtn) e.getSource();
		
		if(command == "removeTagPanel") {
			XmlTag parent 		= tagCustomizationFrame.getTagParent();
			XmlTag newChild 	= tagBtn.getTag();								// recovering child
			XmlTag child 		= TagCustomizationBusiness.findChildFromSelectedChildName(parent, newChild.getName()); //recovering instance that contains all constraints from new isntance generated when this tag was added
			parent.removeSelectedChild(newChild); 								// updating selectedChild array
			JPanel tagPanel 	= (JPanel) tagBtn.getParent().getParent();		// recovering tagpanel
			tagCustomizationFrame.removeTagPanel(tagPanel);						// removing child tag Panel
			child.setMax(child.getMax() + 1);									// maximum number of children is augmented by one	
																				// child.getmax() is the maximum number of times that child tag can occur in parent
		}																				
		
		
		
		if(command == "addTagPanel") {
			XmlTag child = tagBtn.getTag();							
			XmlTag parent = child.getParent();
			if(child.getMax() > 0 ) {
				XmlTag newChild = new XmlTag(child);
				parent.addSelectedChild(newChild);
				tagCustomizationFrame.addTagPanel(newChild);
				newChild.freeModelFields();
				child.setMax(child.getMax() -1 );								// maximum number of children is reduced by one
			}
			else {																// if max child number is = 0, cannot add this child
				tagCustomizationFrame.warningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
													+ " Maximum number of children reached for tag " +tagBtn.getName() + "  </span></p></html>"); 
			}
			
		}
		
		if(command == "add") {
			XmlTag missingDependency = TagCustomizationBusiness.dependencyCheck(tagBtn.getTag()); 
			if(missingDependency != null) {
				boolean response = tagCustomizationFrame.yesNoWarningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
														+ " Missing dependency : " + missingDependency.getName() + " </p><br>"
														+ " <p> Do you want to continue </span></p></html>"); 
			
				if(response) {
					
					XmlTag tag = tagBtn.getTag();
					pdscWizardFrame.addStep(new Form(new XmlTag(tag)));
				}
			}
			else {
				XmlTag tag = tagBtn.getTag();
				pdscWizardFrame.addStep(new Form(new XmlTag(tag)));
			}
			
		}
	}

}
