package view.wizardFrame.comp.xmlForm.comp.attributeComp;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import business.Session;
import model.XmlAttribute;
import view.comp.AttributeComboBox;
import view.comp.utils.ColorUtils;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class AttributeFormComboBox  extends AttributeComboBox implements FocusListener{

	private Session session;
	private  TagRow row;

	public AttributeFormComboBox(XmlAttribute attr, TagRow row) {
		super(attr);
		this.row = row;
	    this.setForeground(ColorUtils.ATTR_VALUE_COLOR);
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		/**
		 * follow user selected element on screen
		 */
		if(!row.isVisible()) {
			row.scrollRectToVisible(this.getBounds());	
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}


}
