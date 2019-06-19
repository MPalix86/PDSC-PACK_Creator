package view.wizardFrame.comp.xmlForm.comp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import business.Session;
import model.XmlTag;
import view.comp.TagComboBox;

public class TagFormComboBox extends TagComboBox implements FocusListener{
	
	private TagRow row;
	private Session session;
	
	public TagFormComboBox(XmlTag tag) {
		super(tag);
		session = Session.getInstance();
		row = session.getWizardFrame().getFormPanel().getTagOpenRowHashMap().get(tag) ;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		row.scrollRectToVisible(this.getBounds());
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		
	}
}
