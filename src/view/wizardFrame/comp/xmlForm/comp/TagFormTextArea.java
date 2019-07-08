package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import business.CustomUtils;
import model.XmlTag;
import view.comp.CustomColor;
 
public class TagFormTextArea extends JTextArea implements DocumentListener, FocusListener{
	private XmlTag tag;
	private TagRow row;
	
	public TagFormTextArea(XmlTag tag, TagRow row) {
		this.tag = tag;
		this.row = row;
		setup();
	}
	
	
	
	public TagFormTextArea(XmlTag tag, String text,TagRow row) {
		super(text);
		this.row = row;
		this.tag = tag;
		setup();
	}
	
	
	private void setup() {
		this.setForeground(Color.DARK_GRAY);
		this.setBorder(new MatteBorder (0,0,0,0,CustomColor.LIGHT_GRAY));
		

		if(this.getText().length() == 0) {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		}
		this.getDocument().addDocumentListener(this);
		this.addFocusListener(this);
	}


	
	@Override
	public void insertUpdate(DocumentEvent e) {
		/** if there is only one line in document */
		if(!CustomUtils.thereAreMoreLinesInString(this.getText())){
			int position = this.getCaretPosition();
			tag.setContent(this.getText());
			row.update();
		}
		else updateSize();			
	}
		

	@Override
	public void removeUpdate(DocumentEvent e) {
		/** if there is only one line in document */
		if(!CustomUtils.thereAreMoreLinesInString(this.getText())){
			int position = this.getCaretPosition();
			tag.setContent(this.getText());
			row.update();
			row.setFocusOnTagContentField();
			SwingUtilities.invokeLater(new Runnable() {
		        @Override
		        public void run() {
		        	if (position < tag.getContent().length()) 	row.setTagContentFieldCaretPosition(position - 1);
		        	else row.setTagContentFieldCaretPosition(tag.getContent().length());
		        }
		    });
		}
		else updateSize();	
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
		// TODO Auto-generated method stub
		
	}
	
	
	public void setTagContent() {
		tag.setContent(this.getText());
	}
	
	
	
	
	/**
	 * Update textArea (this) width based on longest string in text
	 */
	private void updateSize() {
		
		String lines[] = CustomUtils.separateText(this.getText(), "\n");
		
		String maxLine = "";
		if(lines.length >= 1) {
			maxLine = lines[0];
		}
		
		
		for (String line : lines){
			if (line.length() > maxLine.length()) maxLine = line;
		}
		
		if(maxLine.length() > 0) {
			int width = this.getGraphics().getFontMetrics().stringWidth(maxLine);
			this.setPreferredSize(new Dimension(width+2,this.getPreferredSize().height));
		}
		else {
			this.setPreferredSize(new Dimension(50,this.getPreferredSize().height));
		}
	
		row.scrollRectToVisible(this.getBounds());
		row.repaint();
		row.revalidate();
	}
	
	
	public XmlTag getTag() {
		return this.tag;
	}

}
