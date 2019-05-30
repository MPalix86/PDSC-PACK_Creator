package view.wizardFrame.comp.TextPaneForm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class FormPanelContainer extends JPanel{
	private FormPanel formPanel;
	private JScrollPane scrollPane;
	
	public FormPanelContainer() {
		this.formPanel = new FormPanel();
		placeComponents();
	}
	
	private void placeComponents() {
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BorderLayout());
		
		JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		horizontalScrollBar.setUnitIncrement(40);
		
		JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
		verticalScrollBar.setUnitIncrement(40);

		this.scrollPane = new JScrollPane(formPanel);
		
		scrollPane.setHorizontalScrollBar(horizontalScrollBar);
		scrollPane.setVerticalScrollBar(verticalScrollBar);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	
	
	/**
	 * @return the formPanel
	 */
	public FormPanel getFormPanel() {
		return formPanel;
	}

}
