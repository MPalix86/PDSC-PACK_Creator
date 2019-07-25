package listeners.wizardFrameListeners.comp.xmlForm;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import business.Session;
import business.XmlAttributeBusiness;
import business.XmlTagBusiness;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.utils.ColorUtils;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.comp.TagRow;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeFormTextField;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeLabel;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeOptionMenu;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagFormTextArea;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagFormTextField;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagLabel;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagOptionMenu;

public class XmlFormListener implements FocusListener, MouseListener{
	
	
	private XmlForm xmlForm;
	
	
	public XmlFormListener (XmlForm xmlForm) {
		this.xmlForm = xmlForm;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
	
		XmlTag tag = null;
		XmlAttribute attr = null;
		
		if(e.getSource().getClass().equals(AttributeFormTextField.class)) {
			AttributeFormTextField textField = (AttributeFormTextField) e.getSource();
			attr = textField.getAttribute();
			tag = attr.getTag();
		}
		else if(e.getSource().getClass().equals(AttributeFormComboBox.class)) {
			AttributeFormComboBox comboBox = (AttributeFormComboBox) e.getSource();
			attr = comboBox.getAttr();
			tag = attr.getTag();
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
			if( openRow != null) openRow.highlightBckGround(null);
			
			
			String tagDescription = "TAG : " + tag.getName() + "\n";
			if(XmlTagBusiness.getTagDescription(tag) == null) tagDescription += "No description found for tag " + tag.getName() + "\n\n";
			else tagDescription += tagDescription + XmlTagBusiness.getTagDescription(tag);
			
			if(attr != null) {
				String attrDescription = "ATTRIBUTE : " + attr.getName() + "\n" ;
				if( XmlAttributeBusiness.getAttributeDescription(attr) == null) attrDescription += "No description found for attribute " + attr.getName();
				else attrDescription += XmlAttributeBusiness.getAttributeDescription(attr);
				Session.getInstance().getWizardFrame().setDescriptionText(tagDescription + attrDescription);
			}
			else Session.getInstance().getWizardFrame().setDescriptionText(tagDescription);
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
		
		/**
		 * IMPORTANT : saving state of root tag for undo redo action
		 */
		Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		
		
		if(tag != null) {
			TagRow openRow = xmlForm.getTagOpenRow(tag);
			if(openRow != null) openRow.unsetHighlightBackGround();
			
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
			label.setForeground(ColorUtils.ATTR_COLOR);
		}
		
	}



}
