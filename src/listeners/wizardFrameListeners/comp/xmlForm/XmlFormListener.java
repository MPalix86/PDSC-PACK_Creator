package listeners.wizardFrameListeners.comp.xmlForm;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import business.Session;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.comp.AttributeFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.AttributeFormTextField;
import view.wizardFrame.comp.xmlForm.comp.AttributeLabel;
import view.wizardFrame.comp.xmlForm.comp.AttributeOptionMenu;
import view.wizardFrame.comp.xmlForm.comp.TagFormTextField;
import view.wizardFrame.comp.xmlForm.comp.TagLabel;
import view.wizardFrame.comp.xmlForm.comp.TagOptionMenu;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class XmlFormListener implements FocusListener, MouseListener{
	
	private TagRow tagRow;
	
	private XmlForm xmlForm;
	
	private Session session = Session.getInstance();
	
	
	
	
	
	public XmlFormListener (XmlForm xmlForm) {
		this.xmlForm = xmlForm;
	}
	
	public XmlFormListener (TagRow tagRow) {
		this.tagRow = tagRow;
	}
	
	public XmlFormListener () {
	}
	
	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().getClass().equals(AttributeFormTextField.class)) {
			AttributeFormTextField textField = (AttributeFormTextField) e.getSource();
			textField.setAttrValue();
		}
		else if(e.getSource().getClass().equals(AttributeFormComboBox.class)) {
			AttributeFormComboBox comboBox = (AttributeFormComboBox) e.getSource();
			comboBox.setAttrValue();
		}
		else if(e.getSource().getClass().equals(TagFormTextField.class)) {
			TagFormTextField textField = (TagFormTextField) e.getSource();
			textField.setTagContent();
		}
		session.getWizardFrame().updatePreview();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 if (e.isPopupTrigger()) {
			 if(e.getSource().getClass().equals(TagLabel.class)) {
				 TagLabel label = (TagLabel) e.getSource();
					XmlTag tag = label.getTag();
					new TagOptionMenu(tag).show(e.getComponent(), e.getX(), e.getY());

				}
				else if(e.getSource().getClass().equals(AttributeLabel.class)) {
					AttributeLabel label = (AttributeLabel) e.getSource();
					XmlAttribute attr = label.getAttr();
					new AttributeOptionMenu(attr).show(e.getComponent(), e.getX(), e.getY());
					System.out.println(attr.getName());
				}
		 }
	           

			
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().getClass().equals(TagLabel.class)) {
			TagLabel label = (TagLabel) e.getSource();
			label.setForeground(label.getForeground().brighter());
			
		}
		else if(e.getSource().getClass().equals(AttributeLabel.class)) {
			AttributeLabel label = (AttributeLabel) e.getSource();
			label.setForeground(label.getForeground().brighter().brighter());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource().getClass().equals(TagLabel.class)) {
			TagLabel label = (TagLabel) e.getSource();
			label.setForeground(CustomColor.TAG_COLOR);
			
		}
		else if(e.getSource().getClass().equals(AttributeLabel.class)) {
			AttributeLabel label = (AttributeLabel) e.getSource();
			label.setForeground(CustomColor.ATTR_COLOR);
		}
		
	}
}
