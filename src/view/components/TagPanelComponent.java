package view.components;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.CheckBox;
import listeners.TagCustomizationFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import model.XmlTagContents;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

public class TagPanelComponent extends JPanel{
	private XmlTag tag;
	private GridBagConstraints gbc;
	private GridBagLayout gridBagLayout;

	private JPanel AttributesPanel;
	private JPanel childrenPanel;
	
	public TagPanelComponent(XmlTag tag) {
		this.tag = tag;
		
		/* initial layout setup */
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(Color.WHITE);
			gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
			/* tag title label setup */
			JLabel lblTagName = new JLabel("<"+this.tag.getName()+">");
//			if(tag.getMax() != null) {
//				lblTagName = new JLabel("<"+this.tag.getName()+">");
//				JLabel lblMax = new JLabel("<"+this.tag.getName()+">");
//				lblMax.setForeground(new Color(0,0,0));
//				lblMax.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
//				gbc.gridx = 2;
//				gbc.gridy = 1;
//				add(lblTagName, gbc);
//			}

			lblTagName.setForeground(new Color(0,0,128));
			lblTagName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			
			gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 0, 5, 5);
			gbc.gridx = 1;
			gbc.gridy = 1;
			
		add(lblTagName, gbc);
			
			/* AttributesPanel setup */
			AttributesPanel = new JPanel( );
			AttributesPanel.setBackground(Color.WHITE);
			AttributesPanel.setLayout(new BoxLayout(AttributesPanel, BoxLayout.Y_AXIS));
			AttributesPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
			
				JLabel label = new JLabel("Attributes :");
				label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				label.setForeground(Color.BLACK);
			
		        gbc.weightx = 1.0;
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.gridy = 2;
				
			AttributesPanel.add(new JSeparator());
			AttributesPanel.add(label);
			
		if(tag.getAttrArr() != null) {
			tag.getAttrArr().forEach((a) -> addAttribute(a));
			add(AttributesPanel, gbc);
		}
		
			/* childrenPanel setup */
			childrenPanel = new JPanel( );
			childrenPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
			childrenPanel.setBackground(Color.WHITE);
			childrenPanel.setLayout(new BoxLayout(childrenPanel, BoxLayout.Y_AXIS));
			
				JLabel childLabel = new JLabel("Child Elements :");
				childLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
				childLabel.setForeground(Color.BLACK);
				 
				gbc.gridy = 3;
				
			childrenPanel.add(new JSeparator());
			childrenPanel.add(childLabel);
		
		if(tag.getChildren() != null) {
			tag.getChildren().forEach((t) -> addChild(t));
			add(childrenPanel, gbc);   
		}
		
		JButton removeBtn = new JButton("x");
		removeBtn.addActionListener(new TagCustomizationFrameListener());
		removeBtn.setActionCommand("removeTagPanel");
		removeBtn.setName(tag.getName());
		add(removeBtn,gbc);
		
	}
	
	//--------------------------------------------------------------------------addAttribute()
	private void addAttribute(XmlAttribute a) {
		
		JCheckBox c = new JCheckBox(a.getName());
		c.setForeground(new Color(255, 99, 71));
		c.setName("attribute");
		//c.addItemListener(new TagCustomizationFrameListener());
		if(a.isRequired()) {
			c.setSelected(true);
			c.setEnabled(false);
		}
		AttributesPanel.add(c); 
	}
	
	//--------------------------------------------------------------------------addChild()
	private void addChild(XmlTag t) {
			JButton b = new JButton(t.getName());
			b.addActionListener(new TagCustomizationFrameListener());
			b.setName(t.getName());
			b.setActionCommand("addTagPanel");
		if(t.isRequired()) {
			
		}
		childrenPanel.add(b); 
	}
	
	
	

}
