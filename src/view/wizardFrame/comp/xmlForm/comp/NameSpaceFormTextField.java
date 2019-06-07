package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import business.Session;
import model.XmlNameSpace;
import view.comp.CustomColor;

public class NameSpaceFormTextField extends JTextField implements DocumentListener, FocusListener{

	private Session session = Session.getInstance();
	
	private TagRow row;
	
	private XmlNameSpace nameSpace;

	public NameSpaceFormTextField(XmlNameSpace nameSpace , TagRow row) {

		
		this.nameSpace = nameSpace;
		
		if(nameSpace.getUrl() != null) {
			this.setText(nameSpace.getUrl());
		}
		
		this.setForeground(CustomColor.ATTR_VALUE_COLOR);
		this.setBorder(new MatteBorder (0,0,1,0,CustomColor.LIGHT_GRAY));
		
		this.row = row;

		if(this.getText().length() == 0) {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		} 
		
		this.getDocument().addDocumentListener(this);
		this.addFocusListener(this);
	}
	
	
	
	public NameSpaceFormTextField(XmlNameSpace nameSpace,String text,TagRow row) {
		super(text);
		this.nameSpace = nameSpace;
		this.setForeground(CustomColor.ATTR_VALUE_COLOR);
		this.setBorder(new MatteBorder (0,0,1,0,CustomColor.LIGHT_GRAY));
		
		this.row = row;

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
		row.scrollRectToVisible(this.getBounds());
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void updateSize() {
		if(this.getText().length()>0) {
			int width = this.getGraphics().getFontMetrics().stringWidth(this.getText());
			this.setPreferredSize(new Dimension(width+1,this.getPreferredSize().height));
		}
		else {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		}
		row.scrollRectToVisible(this.getBounds());
		row.repaint();
		row.revalidate();
	}

	

}
