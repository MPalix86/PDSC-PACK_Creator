package view.Components.tagCustomizationFrameComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import listeners.TagCustomizationFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import view.Components.ModelComponents.AttributeCheckBox;
import view.Components.ModelComponents.TagBtn;






/**
 * CollapsableTagPanel. Collapsable panel containig tag
 * 
 * @author Mirco Palese
 */

public class CollapsableTagElement extends JPanel{
	
	/** panel's tag */
	private XmlTag tag;
	
	/** layout */
	private GridBagConstraints gbc;
	private GridBagLayout gridBagLayout;
	
	/** attributes panel */
	private JPanel attributesPanel;
	
	/** children panel */
	private JPanel childrenPanel;
	private TagCustomizationFrameListener listener;
	
	
	
	
	/**
	 * Constructor. Create new collapsable panel with all components containing passed tag
	 * 
	 * @param tag	panel's tag
	 * @param listener	TagCustomizationFrameListener listener
	 */
	
	public CollapsableTagElement(XmlTag tag, TagCustomizationFrameListener listener ) {
		this.tag = tag;
		this.listener = listener;
	
		/** initial setup */
		setBorder(new EmptyBorder(15, 15, 15, 15));
		setBackground(Color.WHITE);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		/** tag title label setup */
		JLabel lblTagName = new JLabel("<"+this.tag.getName()+">");
		lblTagName.setForeground(new Color(0,0,128));
		lblTagName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		/** GridBagConstraints initial setup */
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
			
		add(lblTagName, gbc);
			
		/** attributesPanel setup */
		attributesPanel = new JPanel( );
		attributesPanel.setBackground(Color.WHITE);
		attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));
		attributesPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		
		/** attributeTitle setup */
		JLabel attributeTitle = new JLabel("Attributes :");
		attributeTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		attributeTitle.setForeground(Color.BLACK);
		
		/** setting up attributeTitle position */
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 2;
				
		attributesPanel.add(new JSeparator());
		attributesPanel.add(attributeTitle);
			
		/** if tag has attributes */
		if(tag.getAttrArr() != null) {
			tag.getAttrArr().forEach((a) -> addAttribute(a , tag));
			add(attributesPanel, gbc);
		}
		
		/** if tag hasn't attributes */
		else {
			attributesPanel.add(new JLabel(tag.getName() + " has no attributes")) ;
			add(attributesPanel, gbc);
		}
		
		/** childrenPanel setup */
		childrenPanel = new JPanel( );
		childrenPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		childrenPanel.setBackground(Color.WHITE);
		childrenPanel.setLayout(new BoxLayout(childrenPanel, BoxLayout.Y_AXIS));
			
		/** childLabel setup */
		JLabel childLabel = new JLabel("Child Elements :");
		childLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		childLabel.setForeground(Color.BLACK);
	
		/** childLabel position setup */
		gbc.gridy = 3;
		
		
		childrenPanel.add(new JSeparator());
		childrenPanel.add(childLabel);
		
		/** if tag has children */
		if(tag.getChildrenArr() != null) {
			tag.getChildrenArr().forEach((t) -> addChild(t));
			add(childrenPanel, gbc);   
		}
		
		/** show remove button only in children */
		if(tag.getParent() != null) {
			TagBtn removeBtn = new TagBtn(tag , "X");
			removeBtn.addActionListener(listener);
			removeBtn.setActionCommand("removeTagPanel");
			add(removeBtn);
		}

	}
	
	
	
	
	
	/**
	 * Add atrtibutes into attrbitesPanel
	 * 
	 * @param a		attribute
	 * @param tag	attribute's owner
	 * @return void
	 */
	
	private void addAttribute(XmlAttribute a , XmlTag tag) {
		
		AttributeCheckBox c = new AttributeCheckBox(a , tag);   
		c.setForeground(new Color(255, 99, 71));   
		c.addItemListener(listener);
		if(a.isRequired()) {
			c.setSelected(true);
			c.setEnabled(false);
		}
		attributesPanel.add(c); 
	}
	
	  
	
	
	/**
	 * Add child tag inside children panel
	 * 
	 * @param t tag to be added
	 * @return void
	 */
	
	private void addChild(XmlTag t) {
		TagBtn b = new TagBtn(t);
		b.addActionListener(listener);
		b.setActionCommand("addTagPanel");
		childrenPanel.add(b); 
	}
	
	
	

}
