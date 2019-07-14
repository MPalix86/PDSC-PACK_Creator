package view.wizardFrame.comp.console;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Console extends JPanel{
	private JTextPane textArea;
	
	public Console() {
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
	
	public void append(String text) {
		String cotentn = textArea.getText().concat(text);
		this.textArea.setText(cotentn);
	}
	
	public void insertComp(JComponent comp) {
		textArea.insertComponent(comp);
	}

}
