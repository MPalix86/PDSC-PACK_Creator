package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import business.Session;
import business.TagBusiness;
import business.Utils;
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
	private JButton b;
	private TagBusiness tagBusiness;  
	private Session session;


	public TagCustomizationFrameListener () {
		Session session = Session.getInstance();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		tagCustomizationFrame = session.getTagCustomizationFrame();
		JCheckBox c = (JCheckBox) e.getItem();
		if(c.getName() == "tag") {
			if(c.isSelected()) {}
		}
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		tagCustomizationFrame = session.getTagCustomizationFrame();
		String command = e.getActionCommand();
		b = (JButton) e.getSource();
		
		if(command == "removeTagPanel") {
			String childSought 	= b.getName();											// recovering child sought
			XmlTag parent 		= session.getTagCustomizationFrame().getTagParent();	// recovering parent
			XmlTag child 		= TagBusiness.findSelectedChild(parent, childSought);	// recovering selected child								
			JPanel tagPanel 	= (JPanel) b.getParent().getParent();					// recovering tagPanel
			tagCustomizationFrame.removeTagPanel(tagPanel);								// removing child tag Panel
			tagCustomizationFrame.removeTag(child);										// removing child from XmlModel	
			child.setMax(child.getMax() + 1);											// maximum number of children is augmented by one	
		}																				// child.getmax() is the maximum number of times that child tag can occur in parent
		
		if(command == "addTagPanel") {
			String slectedChild 	= b.getName();
			XmlTag parent 			= session.getTagCustomizationFrame().getTagParent();
			XmlTag child 			= TagBusiness.findSelectedChild(parent,slectedChild);
			if(child.getMax() > 0 ) {															// for child.getmax() see comment above
				XmlTag newTagInstance = TagBusiness.getClassInstanceFromClassName(slectedChild);
				tagCustomizationFrame.addTagPanel(newTagInstance); 								// add new child panel
				tagCustomizationFrame.addTag(child);											// add new child panel into XmlModel
				child.setMax(child.getMax() -1 );												// maximum number of children is reduced by one
			}
			else {																				// if max child number is = 0, cannot add this child
				tagCustomizationFrame.warningMessage("maximum number of children reached for tag  <" + b.getName() +">");
			}	
		}
		
	}

}
