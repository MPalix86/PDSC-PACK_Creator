package listeners.wizardFrameListeners.comp.xmlForm;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.XmlAttribute;
import model.XmlTag;
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.comp.AttributeFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.AttributeFormTextField;
import view.wizardFrame.comp.xmlForm.comp.AttributeLabel;
import view.wizardFrame.comp.xmlForm.comp.AttributeOptionMenu;
import view.wizardFrame.comp.xmlForm.comp.TagFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.TagFormTextArea;
import view.wizardFrame.comp.xmlForm.comp.TagFormTextField;
import view.wizardFrame.comp.xmlForm.comp.TagLabel;
import view.wizardFrame.comp.xmlForm.comp.TagOptionMenu;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class XmlFormListener implements FocusListener, MouseListener{
	
	
	private XmlForm xmlForm;
	
	
	public XmlFormListener (XmlForm xmlForm) {
		this.xmlForm = xmlForm;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
	
		XmlTag tag = null;
		
		if(e.getSource().getClass().equals(AttributeFormTextField.class)) {
			AttributeFormTextField textField = (AttributeFormTextField) e.getSource();
			tag = textField.getAttribute().getTag();
		}
		else if(e.getSource().getClass().equals(AttributeFormComboBox.class)) {
			AttributeFormComboBox comboBox = (AttributeFormComboBox) e.getSource();
			tag = comboBox.getAttr().getTag();
		}
		else if(e.getSource().getClass().equals(TagFormTextField.class)) {
			TagFormTextField textField = (TagFormTextField) e.getSource();
			tag = textField.getTag();
		}
		else if(e.getSource().getClass().equals(TagFormTextArea.class)) {
			TagFormTextArea textArea = (TagFormTextArea) e.getSource();
			tag = textArea.getTag();
		}
		else if(e.getSource().getClass().equals(TagFormComboBox.class)) {
			TagFormComboBox comboBox = (TagFormComboBox) e.getSource();
			tag = comboBox.getTag();
		}
		
		if(tag != null) {
			TagRow openRow = xmlForm.getTagOpenRow(tag);
			openRow.highlightBckGround(CustomColor.LIGHT_GRAY);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		XmlTag tag = null;
		/** update tag content on focus lost */
		if(e.getSource().getClass().equals(AttributeFormTextField.class)) {
			AttributeFormTextField textField = (AttributeFormTextField) e.getSource();
			textField.setAttrValue();
			tag = textField.getAttribute().getTag();
		}
		else if(e.getSource().getClass().equals(AttributeFormComboBox.class)) {
			AttributeFormComboBox comboBox = (AttributeFormComboBox) e.getSource();
			comboBox.setAttrValue();
			tag = comboBox.getAttr().getTag();
		}
		else if(e.getSource().getClass().equals(TagFormTextField.class)) {
			TagFormTextField textField = (TagFormTextField) e.getSource();
			textField.setTagContent();
			tag = textField.getTag();
		}
		else if(e.getSource().getClass().equals(TagFormTextArea.class)) {
			TagFormTextArea textArea = (TagFormTextArea) e.getSource();
			textArea.setTagContent();
			tag = textArea.getTag();
		}
		else if(e.getSource().getClass().equals(TagFormComboBox.class)) {
			TagFormComboBox comboBox = (TagFormComboBox) e.getSource();
			comboBox.setTagContent();
			tag = comboBox.getTag();
		}
		
		
		if(tag != null) {
			TagRow openRow = xmlForm.getTagOpenRow(tag);
			openRow.unsetHighlightBackGround();
		}
		
	}

	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		/** calling option menu if mouse was clicked */
		 if(e.getSource().getClass().equals(TagLabel.class)) {
			 TagLabel label = (TagLabel) e.getSource();
				XmlTag tag = label.getTag();
				new TagOptionMenu(tag).show(e.getComponent(), e.getX(), e.getY());

			}
			else if(e.getSource().getClass().equals(AttributeLabel.class)) {
				AttributeLabel label = (AttributeLabel) e.getSource();
				XmlAttribute attr = label.getAttr();
				new AttributeOptionMenu(attr,label).show(e.getComponent(), e.getX(), e.getY());
			}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		/** set tag label  bright */
		if(e.getSource().getClass().equals(TagLabel.class)) {
			
			/** recovering tag label */
			TagLabel label = (TagLabel) e.getSource();

			/** recovering tag */
			XmlTag tag = label.getTag();

			TagRow openRow  = xmlForm.getTagOpenRow(tag);
			TagRow closeRow  = xmlForm.getTagCloseRow(tag);
			
			openRow.setTagLabelBrighter();
			if(closeRow != null) closeRow.setTagLabelBrighter();
			
			
			xmlForm.drawOpenCloseTagLine(tag);		
		}
		else if(e.getSource().getClass().equals(AttributeLabel.class)) {
			AttributeLabel label = (AttributeLabel) e.getSource();
			label.setForeground(label.getForeground().brighter().brighter());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		/** remove tagLabel bright */
		if(e.getSource().getClass().equals(TagLabel.class)) {
			
			/** recovering tag label */
			TagLabel label = (TagLabel) e.getSource();
			
			/** recovering tag */
			XmlTag tag = label.getTag();
			
			TagRow openRow  = xmlForm.getTagOpenRow(tag);
			TagRow closeRow  = xmlForm.getTagCloseRow(tag);
			openRow.unsetTagLabelBrighter();
			if(closeRow != null) closeRow.unsetTagLabelBrighter();
			xmlForm.removeLine(tag);
		}
		else if(e.getSource().getClass().equals(AttributeLabel.class)) {
			AttributeLabel label = (AttributeLabel) e.getSource();
			label.setForeground(CustomColor.ATTR_COLOR);
		}
		
	}



}
