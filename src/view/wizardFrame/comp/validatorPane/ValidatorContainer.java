package view.wizardFrame.comp.validatorPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ValidatorContainer extends JPanel{
	
	private JScrollPane scrollPane;
	private Validator validator;

	public ValidatorContainer() {
		validator = new Validator();
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		horizontalScrollBar.setUnitIncrement(40);
		
		JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
		verticalScrollBar.setUnitIncrement(40);

		this.scrollPane = new JScrollPane(validator);
		
		scrollPane.setHorizontalScrollBar(horizontalScrollBar);
		scrollPane.setVerticalScrollBar(verticalScrollBar);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	
	public Validator getValidator() {
		return this.validator;
	}

}
