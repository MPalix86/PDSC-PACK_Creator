package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import business.CustomUtils;

public class TextAreaPanel extends JPanel implements DocumentListener{
	private TagLabel label;
	private TagFormTextArea textArea;
	
	public TextAreaPanel(TagFormTextArea textArea) {
		this.textArea = textArea;
		textArea.getDocument().addDocumentListener(this);
		
		this.setBackground(Color.WHITE);
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		String lines[] = CustomUtils.separateText(textArea.getText(), "\n");
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
