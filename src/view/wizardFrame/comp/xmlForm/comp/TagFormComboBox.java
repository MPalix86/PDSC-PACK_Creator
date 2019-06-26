package view.wizardFrame.comp.xmlForm.comp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import model.XmlTag;
import view.comp.TagComboBox;

public class TagFormComboBox extends TagComboBox implements FocusListener{
	
	private TagRow row;
	
	public TagFormComboBox(XmlTag tag, TagRow row) {
		super(tag);
		this.row = row;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		/**
		 * follow user selected element on screen
		 */
		row.scrollRectToVisible(this.getBounds());
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		
	}
}
