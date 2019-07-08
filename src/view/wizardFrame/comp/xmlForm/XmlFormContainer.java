package view.wizardFrame.comp.xmlForm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import model.XmlTag;

public class XmlFormContainer extends JPanel{
	private XmlForm formPanel;
	private JScrollPane scrollPane;
	
	
	public XmlFormContainer(XmlTag root) {
		this.formPanel = new XmlForm(root);
		placeComponents();
	}
	
	public XmlFormContainer(XmlForm form) {
		this.formPanel = form;
		placeComponents();
	}
	
	private void placeComponents() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		horizontalScrollBar.setUnitIncrement(40);
		
		JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
		verticalScrollBar.setUnitIncrement(40);
		

		this.scrollPane = new JScrollPane(formPanel);
		scrollPane.setHorizontalScrollBar(horizontalScrollBar);
		scrollPane.setVerticalScrollBar(verticalScrollBar);
		this.add(scrollPane,BorderLayout.CENTER);
		
	}
	
	
	
	/**
	 * @return the formPanel
	 */
	public XmlForm getFormPanel() {
		return formPanel;
	}

}
