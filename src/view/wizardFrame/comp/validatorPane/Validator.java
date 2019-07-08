package view.wizardFrame.comp.validatorPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Validator extends JPanel{
	private JTextPane textArea;
	
	public Validator() {
		textArea = new JTextPane();
		textArea.setBorder(new EmptyBorder(10,10,10,10));
		textArea.setEditable(false);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.add(textArea,BorderLayout.CENTER);

		
	}
	
	
	public void setText(String text) {
		this.textArea.setText(text);
	}
	
	public void insertComp(JComponent comp) {
		textArea.insertComponent(comp);
	}

}
