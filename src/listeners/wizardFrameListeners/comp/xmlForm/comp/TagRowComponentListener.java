package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import business.Session;
import business.TagManager;
import business.XmlAttributeManager;
import mao.XmlAttributeMao;
import mao.XmlTagMao;
import model.PDSC.PDSCConditionsChecker;
import model.xml.XmlAttribute;
import model.xml.XmlEnum;
import model.xml.XmlTag;
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

public class TagRowComponentListener implements FocusListener, MouseListener, ItemListener, ActionListener{
	
	private Session session;
	private XmlForm form;
	
	public TagRowComponentListener(XmlForm form) {
		session = Session.getInstance();
		this.form = form;
	}
	
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
			session.getSelectedForm().restoreComponentsBackGround();
			TagRow openRow = form.getTagOpenRow(tag);
			if( openRow != null) openRow.highlightBckGround(null);
			
			
			String tagDescription = "ELEMENT : <" + tag.getName() + ">\n";
			if(XmlTagMao.getTagDescription(tag) == null) tagDescription += "No description found for tag " + tag.getName() + "\n\n";
			else tagDescription += XmlTagMao.getTagDescription(tag);
			
			if(attr != null) {
				String attrDescription = "ATTRIBUTE : " + attr.getName() + "\n" ;
				if( XmlAttributeMao.getAttributeDescription(attr , tag) == null) attrDescription += "No description found for attribute " + attr.getName() + "\n\n";
				else attrDescription += XmlAttributeMao.getAttributeDescription(attr , tag) + "\n\n";
				Session.getInstance().getWizardFrame().setDescriptionText(attrDescription + tagDescription);
			}			
			else Session.getInstance().getWizardFrame().setDescriptionText(tagDescription);
			
			if(tag.getParent().getName().equals("condition")) {
				XmlAttribute cbundle = null;
				if(tag.containsAttr("Cbundle")) cbundle = tag.getSelectedAttrByName("Cbundle");
				if(cbundle != null && session.getSelectedPDSCDoc() != null) {
					PDSCConditionsChecker checker = session.getSelectedPDSCConditionsChecker();
					checker.checkComponents();
					XmlEnum cbundleEnum = checker.getCbundleEnum();
					if(cbundleEnum != null && !cbundleEnum.equals(cbundle.getPossibleValues())) {
						cbundle.setPossibleValues(cbundleEnum);
						session.getSelectedForm().getTagOpenRow(tag).update();
					}
				}
				
			}
		}
		
		
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		XmlTag tag = null;
		/** update tag content on focus lost */
		if(e.getSource().getClass().equals(AttributeFormTextField.class)) {
			AttributeFormTextField textField = (AttributeFormTextField) e.getSource();
			XmlAttribute attr = textField.getAttribute();
			XmlAttributeManager.setAttributeValue(attr, textField.getText(), true);
			tag = textField.getAttribute().getTag();
		}
		
		if(e.getSource().getClass().equals(AttributeFormComboBox.class)) {
			AttributeFormComboBox comboBox = (AttributeFormComboBox) e.getSource();
			XmlAttribute attr = comboBox.getAttr();
			tag = attr.getTag();
		}

		else if(e.getSource().getClass().equals(TagFormTextField.class)) {
			TagFormTextField textField = (TagFormTextField) e.getSource();
			tag = textField.getTag();
			TagManager.setTagContent(tag, textField.getText(), true);
		}
		else if(e.getSource().getClass().equals(TagFormTextArea.class)) {
			TagFormTextArea textArea = (TagFormTextArea) e.getSource();
			tag = textArea.getTag();
			TagManager.setTagContent(tag, textArea.getText(), true);
		}
		else if(e.getSource().getClass().equals(TagFormComboBox.class)) {
			TagFormComboBox comboBox = (TagFormComboBox) e.getSource();
			tag = comboBox.getTag();
			TagManager.setTagContent(tag, (String)comboBox.getSelectedItem(), true);
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
		 
		 session.getSelectedForm().restoreComponentsBackGround();
			
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

			TagRow openRow  = form.getTagOpenRow(tag);
			TagRow closeRow  = form.getTagCloseRow(tag);
			
			if(openRow != null) openRow.setTagLabelBrighter();
			if(closeRow != null) closeRow.setTagLabelBrighter();
			
			
			form.drawOpenCloseTagLine(tag);		
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
			
			TagRow openRow  = form.getTagOpenRow(tag);
			TagRow closeRow  = form.getTagCloseRow(tag);
			if(openRow != null) openRow.unsetTagLabelBrighter();
			if(closeRow != null) closeRow.unsetTagLabelBrighter();
			form.removeLine(tag);
		}
		else if(e.getSource().getClass().equals(AttributeLabel.class)) {
			AttributeLabel label = (AttributeLabel) e.getSource();
			label.setForeground(ColorUtils.ATTR_COLOR);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		XmlTag tag = null;
		if(e.getSource().getClass().equals(AttributeFormComboBox.class)) {
			AttributeFormComboBox comboBox = (AttributeFormComboBox) e.getSource();
			XmlAttribute attr = comboBox.getAttr();
			tag = attr.getTag();
			XmlAttributeManager.setAttributeValue(attr,(String) comboBox.getSelectedItem(), true);
			if(attr.getTag().getParent().getName().equals("condition")  && attr.getName().equals("Cbundle") && attr.getPossibleValues() != null && session.getSelectedPDSCDoc() != null) {
				PDSCConditionsChecker checker = session.getSelectedPDSCConditionsChecker();
				System.out.println("NO, Sono io ");
				boolean somethingChange = checker.setAttributesValues(attr.getTag());
				if(somethingChange) session.getSelectedForm().getTagOpenRow(attr.getTag()).update();
			}	
		}
		
	}

}
