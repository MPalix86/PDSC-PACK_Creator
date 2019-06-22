package view.wizardFrame.comp.xmlForm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import view.comp.CustomColor;

public class XmlFormContainer extends JPanel{
	private XmlForm formPanel;
	private JScrollPane scrollPane;
	
	public XmlFormContainer() {
		this.formPanel = new XmlForm();
		placeComponents();
	}
	
	private void placeComponents() {
		this.setBackground(Color.WHITE);
		this.setBorder(new MatteBorder(8,0,0,0,CustomColor.LIGHT_GRAY));
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
	public XmlForm getFormPanel() {
		return formPanel;
	}

}
