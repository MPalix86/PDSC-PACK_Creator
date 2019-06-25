package view.wizardFrame.comp.descriptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class DescriptionPane extends JPanel{
	private JTextArea textArea;
	
	public DescriptionPane() {
		textArea = new JTextArea();
		textArea.setBorder(new EmptyBorder(10,0,0,0));
		textArea.setEditable(false);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.add(textArea,BorderLayout.CENTER);

		
	}
	
	
	public void setText(String text) {
		this.textArea.setText(text);
	}

}
