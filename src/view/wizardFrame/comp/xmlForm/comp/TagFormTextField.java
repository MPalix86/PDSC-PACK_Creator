package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.XmlTag;
import view.comp.CustomColor;
import view.comp.TagTextField;

public class TagFormTextField extends TagTextField implements DocumentListener, FocusListener, ActionListener{
	
	
	private TagRow row;
	
	public TagFormTextField(XmlTag tag, TagRow row) {
		super(tag);
		this.row = row;
		setup();
	}
	
	
	
	public TagFormTextField(XmlTag tag, String text,TagRow row) {
		super(tag,text);
		this.row = row;
		setup();
	}
	
	
	private void setup() {
		this.setForeground(Color.DARK_GRAY);
		this.setBorder(new MatteBorder (0,0,1,0,CustomColor.LIGHT_GRAY));
		

		if(this.getText().length() == 0) {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		}
		this.getDocument().addDocumentListener(this);
		this.addFocusListener(this);
		this.addActionListener(this);
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		/** 
		 * Generate event when enter was pressed
		 * assume that if enter was pressed in textfield (this) user want to add new line
		 */
		int position = this.getCaretPosition();
		
		String text = this.getText();
		
		final String textBeforeNewLine = text.substring(0, position) + System.lineSeparator();
		
		String textAfterNewLine = text.substring(position, text.length());

		tag.setContent(textBeforeNewLine + textAfterNewLine);
		
		
		/** updating TagRow */
		row.update();
		
		/** adjust focus on row*/
		row.setFocusOnTagContentField();
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	if(textBeforeNewLine.length() < tag.getContent().length()) {
	        		row.setTagContentFieldCaretPosition(textBeforeNewLine.length());
	        	}
	        	else row.setTagContentFieldCaretPosition(row.getTagTextArea().getDocument().getLength());
	        }
	    });
		
	}
	
	
	/**
	 * Update textField (this) width based on String length
	 */
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
