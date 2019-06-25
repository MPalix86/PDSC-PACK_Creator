package view.wizardFrame.comp.descriptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class DescriptionPaneContainer extends JPanel{
	
	private JScrollPane scrollPane;
	private DescriptionPane descriptionPane;

	public DescriptionPaneContainer() {
		descriptionPane = new DescriptionPane();
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		horizontalScrollBar.setUnitIncrement(40);
		
		JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
		verticalScrollBar.setUnitIncrement(40);

		this.scrollPane = new JScrollPane(descriptionPane);
		
		scrollPane.setHorizontalScrollBar(horizontalScrollBar);
		scrollPane.setVerticalScrollBar(verticalScrollBar);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	
	public DescriptionPane getDescriptionPane() {
		return this.descriptionPane;
	}

}
