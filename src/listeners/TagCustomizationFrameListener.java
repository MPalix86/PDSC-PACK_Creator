package listeners;

import java.awt.Container;
import java.awt.event.ActionEvent;

import view.components.AttributeCheckBox;
import view.components.TagBtn;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import business.Session;
import business.TagCustomizationBusiness;
import business.Utils;
import javafx.scene.Parent;
import model.XmlAttribute;
import model.XmlTag;
import model.pdscTag.Accept;
import model.pdscTag.Conditions;
import model.pdscTag.Deny;
import model.pdscTag.Require;
import model.pdscType.TagTypeEnum;
import view.TagCustomizationFrame;
import view.components.TagContainer;

public class TagCustomizationFrameListener implements ItemListener, ActionListener{
	private TagCustomizationFrame tagCustomizationFrame;
	private TagBtn tagBtn;
	private TagCustomizationBusiness tagBusiness;  
	private Session session;


	public TagCustomizationFrameListener (TagCustomizationFrame tagCustomizationFrame) {
		tagBusiness = new TagCustomizationBusiness();
		this.tagCustomizationFrame = tagCustomizationFrame;
		session = Session.getInstance();
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
			XmlTag child 		= tagBtn.getTag();								// recovering child
			parent.removeSelecteChild(child); 									// updating selectedChild array
			JPanel tagPanel 	= (JPanel) tagBtn.getParent().getParent();		// recovering tagpanel
			tagCustomizationFrame.removeTagPanel(tagPanel);						// removing child tag Panel
			child.setMax(child.getMax() + 1);									// maximum number of children is augmented by one	
																				// child.getmax() is the maximum number of times that child tag can occur in parent
		}																				
		
		
		
		if(command == "addTagPanel") {
			XmlTag child 			= tagBtn.getTag();							// recovering child	
			XmlTag parent 	= child.getParent();
			if(child.getMax() > 0 ) {
				parent.addSelecteChild(child);
				tagCustomizationFrame.addTagPanel(child); 						// add new child panel
				child.setMax(child.getMax() -1 );								// maximum number of children is reduced by one
			}
			else {																// if max child number is = 0, cannot add this child
				tagCustomizationFrame.warningMessage("maximum number of children reached for tag  <" + tagBtn.getName() +">"); // show message
			}
			
		}
		
		if(command == "add") {
			
		}
		
		XmlTag parent = tagCustomizationFrame.getTagParent();
		TagCustomizationBusiness.printTag(parent);
	}

}
