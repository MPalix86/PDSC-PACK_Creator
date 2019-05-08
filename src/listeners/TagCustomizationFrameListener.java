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
import view.PdscWizardFrame;
import view.TagCustomizationFrame;
import view.components.AttributeCheckBox;
import view.components.FormContainer;
import view.components.TagBtn;

public class TagCustomizationFrameListener implements ItemListener, ActionListener{
	private TagCustomizationFrame tagCustomizationFrame;
	private TagBtn tagBtn;
	private TagCustomizationBusiness tagBusiness;  
	private Session session;
	private PdscWizardFrame pdscWizardFrame;

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
			XmlTag child 		= TagCustomizationBusiness.findSelectedChild(parent, newChild.getClass().getName()); //recovering instance that contains all constraints from new isntance generated when this tag was added
			parent.removeSelecteChild(newChild); 								// updating selectedChild array
			JPanel tagPanel 	= (JPanel) tagBtn.getParent().getParent();		// recovering tagpanel
			tagCustomizationFrame.removeTagPanel(tagPanel);						// removing child tag Panel
			child.setMax(child.getMax() + 1);									// maximum number of children is augmented by one	
																				// child.getmax() is the maximum number of times that child tag can occur in parent
		}																				
		
		
		
		if(command == "addTagPanel") {
			XmlTag child 			= tagBtn.getTag();							// recovering child	
			XmlTag parent 	= child.getParent();
			if(child.getMax() > 0 ) {
				Class cl = child.getClass();									// recovering class
				XmlTag newChild = TagCustomizationBusiness.getNewinstance(cl);	// new instance creation of selected tag
				parent.addSelecteChild(newChild);
				tagCustomizationFrame.addTagPanel(newChild); 					// add new child panel
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
				boolean flag = tagCustomizationFrame.yesNoWarningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
														+ " Missing dependency : " + missingDependency.getName() + " </p><br>"
														+ " <p> Do you want to continue </span></p></html>"); 
				
				if(flag) pdscWizardFrame.addStep(new FormContainer(tagBtn.getTag()));
				
			}
			else {pdscWizardFrame.addStep(new FormContainer(tagBtn.getTag()));}
			
		}
	}

}
