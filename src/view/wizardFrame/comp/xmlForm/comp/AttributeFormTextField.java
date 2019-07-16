package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import business.Session;
import model.XmlAttribute;
import view.comp.AttributeTextField;
import view.comp.utils.ColorUtils;

public class AttributeFormTextField extends AttributeTextField implements DocumentListener, FocusListener{
	
	private Session session = Session.getInstance();
	private TagRow row;
	
	
	public AttributeFormTextField(XmlAttribute attr , TagRow row) {
		
		super(attr);
		this.row = row;
		setup();
	}
	
	
	
	public AttributeFormTextField(XmlAttribute attr,String text,TagRow row) {
		super(attr,text);
		this.row = row;
		setup();
	}

	
	
	private void setup() {
		
		this.setForeground(ColorUtils.ATTR_VALUE_COLOR);
		this.setBorder(new MatteBorder (0,0,1,0,ColorUtils.LIGHT_GRAY));
		

		if(this.getText().length() == 0) {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		}
		
		this.getDocument().addDocumentListener(this);
		this.addFocusListener(this);
		
	}
	
	

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateSize();
		
	}
		

	@Override
	public void removeUpdate(DocumentEvent e) {
		
		updateSize();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
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
	
	
	private void updateSize() {
		if(this.getText().length()>0) {
			int width = this.getGraphics().getFontMetrics().stringWidth(this.getText());
			this.setPreferredSize(new Dimension(width+2,this.getPreferredSize().height));
		}
		else {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		}
		row.scrollRectToVisible(this.getBounds());
		row.repaint();
		row.revalidate();
	}

	

}
