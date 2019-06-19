package view.wizardFrame.comp.xmlForm.comp.addAttributeFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import listeners.wizardFrameListeners.comp.xmlForm.comp.AddAttributeFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeButton;
import view.comp.CustomColor;
import view.comp.SquareButton;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.comp.attributeListBar.AttributesListBarContainer;

public class AddAttributeFrame extends JFrame{
	
	private JPanel descriptionPanel;
	private AttributesListBarContainer bar;
	private JTextArea descriptionTextArea;
	private JPanel buttonsPanel;
	private AddAttributeFrameListener listener;
	private XmlAttribute selectedAttr;
	private JPanel DescriptionHeaderPanel;
	private XmlTag tag;
	
	
	/**
	 * @return the selectedAttr
	 */
	public XmlAttribute getSelectedAttr() {
		return selectedAttr;
	}

	public AddAttributeFrame(XmlTag tag) {
		listener = new AddAttributeFrameListener(this);
		bar = new AttributesListBarContainer(tag,listener);
		this.tag = tag;
		
		placeComponents();
	}
	
	private void placeComponents() {
		
		this.setBackground(Color.WHITE);
		
		descriptionTextArea = new JTextArea();
		descriptionPanel = new JPanel();
		buttonsPanel = new JPanel();
		
		generateDescriptionHeaderPanel();
		
		descriptionPanel.setLayout(new BorderLayout());
		descriptionPanel.add(descriptionTextArea,BorderLayout.CENTER);
		descriptionPanel.add(DescriptionHeaderPanel);
		descriptionPanel.setBackground(Color.WHITE);
		
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.setLayout(new GridLayout(1,2));
		buttonsPanel.setBorder(new MatteBorder(0,0,0,1, CustomColor.LIGHT_GRAY));
	

		
		descriptionTextArea.setBackground(Color.WHITE);
		
		AttributeButton addBtn = new AttributeButton("Add" , null);
		SquareButton cancelBtn = new SquareButton("Cancel");
		
		buttonsPanel.add(cancelBtn);
		buttonsPanel.add(addBtn);
		
		descriptionPanel.add(buttonsPanel,BorderLayout.SOUTH);
		
		this.getContentPane().add(descriptionPanel, BorderLayout.CENTER);
		this.getContentPane().add(bar, BorderLayout.WEST);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(600,450));
		this.setVisible(true);
	}
	
	
	private void generateDescriptionHeaderPanel() {
		
		DescriptionHeaderPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		DescriptionHeaderPanel.setBackground(Color.WHITE);
		
		JLabel tagLabel = new JLabel("< " + tag.getName() + " <" );
		tagLabel.setForeground(CustomColor.TAG_COLOR);
		
		DescriptionHeaderPanel.add(tagLabel,c);
		
	}
	
	
	
	public void updateDescription(String text){
		this.descriptionTextArea.setText(text);
	}

}


