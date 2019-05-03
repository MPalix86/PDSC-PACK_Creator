package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import business.Session;
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
	private XmlTag newTag;


	/*
	 * use of Java Reflection to generate, Dynamically, specific class object
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		tagCustomizationFrame = Session.getTagCustomizationFrame();
		JCheckBox c = (JCheckBox) e.getItem();
		if(c.getName() == "tag") {
			if(c.isSelected()) {}
		}
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		tagCustomizationFrame = Session.getTagCustomizationFrame();
		String command = e.getActionCommand();
		JButton b = (JButton) e.getSource();
		
		if(command == "removeTagPanel") {
			Class<XmlTag> tagClass = null;
			try {
				/* Class.forname(String classname) return class instance of classname passed like string:
				 * it's like saying: classname tagClass; but it is dynamic.
				 */
				tagClass = (Class<XmlTag>) Class.forName("model.pdscTag." + Utils.firstLetterCaps(b.getName())); 
				
				XmlTag parent = Session.getTagCustomizationFrame().getTagParent();
				for (int i = 0; i < parent.getChildren().size(); i++) {				// start child search cycle 
					XmlTag child = parent.getChildren().get(i);						
					if(child.getClass() == tagClass) {								// comparing child with user selected tag (read tagClass comment above)
						JPanel tagPanel = (JPanel) b.getParent().getParent();		// recovering tagPanel
						tagCustomizationFrame.removeTagPanel(tagPanel);				// removing child
						child.setMax(child.getMax() + 1);							// maximum number of children is augmented by one	
					}
				}
			} 
			catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		
		if(command == "addTagPanel") {
			Class<XmlTag> tagClass = null;
			try {
				/* Class.forname(String classname) return class instance of classname passed like string:
				 * it's like saying: classname tagClass; but it is dynamic.
				 */
				tagClass = (Class<XmlTag>) Class.forName("model.pdscTag." + Utils.firstLetterCaps(b.getName())); 
				
				XmlTag parent = Session.getTagCustomizationFrame().getTagParent();
				for (int i = 0; i < parent.getChildren().size(); i++) {				// start child search cycle 
					XmlTag child = parent.getChildren().get(i);						
					if(child.getClass() == tagClass) {								// comparing child with user selected tag (read tagClass comment above)
						if(child.getMax() > 0 ) {									// if max child number is > 0
							try {
								/* tagClass.newInstance() return new instance of classname;
								 * it's like saying: new classname();
								 */
								tagCustomizationFrame.addTag((XmlTag)tagClass.newInstance()); 	// add new child
								child.setMax(child.getMax() -1 );								// maximum number of children is reduced by one
								
							} 
							catch (InstantiationException | IllegalAccessException e1) {
								e1.printStackTrace();
							}
						}
						else {														// if max child number is = 0, cannot add this child
							tagCustomizationFrame.warningMessage("maximum number of children reached for tag  <" + b.getName() +">");
							break;
						}
					}
				}
			} 
			catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		
			
		}
		
	}
	
	



}
