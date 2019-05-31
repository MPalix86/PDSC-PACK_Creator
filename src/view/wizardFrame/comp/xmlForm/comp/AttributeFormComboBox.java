package view.wizardFrame.comp.xmlForm.comp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import model.XmlAttribute;
import view.comp.AttributeComboBox;

public class AttributeFormComboBox  extends AttributeComboBox implements FocusListener{
	private TagRow row;

	public AttributeFormComboBox(XmlAttribute attr, TagRow row) {
		super(attr);
		this.row = row;
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		row.scrollRectToVisible(this.getBounds());
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		
	}

}
