package view.wizardFrame.comp.xmlForm.comp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.UIManager;

import business.Session;
import model.XmlAttribute;
import view.comp.AttributeComboBox;
import view.comp.CustomColor;

public class AttributeFormComboBox  extends AttributeComboBox implements FocusListener{

	private Session session;

	public AttributeFormComboBox(XmlAttribute attr) {
		super(attr);
	    UIManager.put("ComboBox.foreground", CustomColor.ATTR_VALUE_COLOR);
	    UIManager.put("ComboBox.selectionForeground", CustomColor.ATTR_VALUE_COLOR);
	    String text  =  (String) this.getSelectedItem();
		session = Session.getInstance();
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		TagRow row = session.getWizardFrame().getFormPanel().getTagOpenRowHashMap().get(attr.getTag());
		try {
			row.scrollRectToVisible(this.getBounds());
		}catch(Exception ex) {
			
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}


}
