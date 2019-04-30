package view.components;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.CheckBox;
import listeners.AttributesListener;
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
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

public class TagPanelComponent extends JPanel{
	private XmlTag tag;
	private GridBagConstraints gbc;
	private GridBagLayout gridBagLayout;

	private JPanel checkBoxPanel;
	
	public TagPanelComponent(XmlTag tag) {
		this.tag = tag;
		
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(Color.WHITE);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTagname = new JLabel("<"+this.tag.getName()+">");
		lblTagname.setForeground(new Color(0,0,128));
		lblTagname.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		
	
		add(lblTagname, gbc);
		checkBoxPanel = new JPanel( );
		checkBoxPanel.setBackground(Color.WHITE);
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        gbc.weightx = 1.0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridwidth = gbc.REMAINDER;
		gbc.gridy = 2;
		checkBoxPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		JLabel label = new JLabel("Attributes :");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		label.setForeground(Color.BLACK);
		checkBoxPanel.add(new JSeparator());
		checkBoxPanel.add(label);
		
		add(checkBoxPanel, gbc);
		tag.getAttrArr().forEach((a) -> addAttribute(a));
		
		
	}
	
	private void addAttribute(XmlAttribute a) {
		
		JCheckBox c = new JCheckBox(a.getName());
		c.setForeground(new Color(255, 99, 71));
		c.addItemListener(new AttributesListener());
		if(a.isRequired()) {
			c.setSelected(true);
			c.setEnabled(false);
		}
		checkBoxPanel.add(c); 
	}
	
	
	

}
