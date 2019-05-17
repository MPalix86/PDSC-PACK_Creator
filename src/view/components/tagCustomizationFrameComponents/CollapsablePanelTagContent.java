package view.Components.tagCustomizationFrameComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import business.Utils;
import listeners.TagCustomizationFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.Components.ModelComponents.AttributeCheckBox;
import view.Components.ModelComponents.CustomColor;
import view.Components.ModelComponents.TagBtn;






/**
 * CollapsableTagPanel. Collapsable panel containig tag
 * 
 * @author Mirco Palese
 */

public class CollapsablePanelTagContent extends JPanel{
	
	/** panel's tag */
	private XmlTag tag;
	
	/** content pane */
	private JPanel contentPane;
	
	private TagCustomizationFrameListener listener;
	
	
	
	
	/**
	 * Constructor. Create new collapsable panel with all components containing passed tag
	 * 
	 * @param tag	panel's tag
	 * @param listener	TagCustomizationFrameListener listener
	 */
	
	public CollapsablePanelTagContent(XmlTag tag, TagCustomizationFrameListener listener) {
		
		this.listener = listener;
		this.tag = tag;
		
		this.setLayout(new BorderLayout());
		generateContentPane();
		this.add(contentPane,BorderLayout.CENTER);
	}
	
	
	
	
	/**
	 * Return contentPane with all tag's buttons
	 * 
	 * @return void
	 */
	
	private void generateContentPane() {
		
		contentPane = new JPanel(new BorderLayout());
		
		/** generating buttonsPanel */
		JPanel buttonsPanel = generatebuttonsPanel();
		
		/** generating attributesPanel */
		JPanel attributesPanel = generateAttributesPanel();
		
		/** generating all panels in contentPane */
		contentPane.add(attributesPanel, BorderLayout.CENTER);
		contentPane.add(buttonsPanel, BorderLayout.WEST);
		
	}
	
	
	
	
	/**
	 * Return panel with all tag's buttons
	 * 
	 * @return buttons panel
	 */
	
	private JPanel generatebuttonsPanel() {
		
		JPanel buttonsPanel = new JPanel(new MigLayout("wrap 1"));
		buttonsPanel.setBorder(new MatteBorder(0,0,0,1,CustomColor.LIGHT_GRAY));

		/** setting up children icon */
		Image menu = Utils.getScaledImage(20, 20, 20, 20, "/icons/tag96.png");
		ImageIcon menuIcon = new ImageIcon (menu);
		
		/** showChildrenButton initial setup */
		TagBtn showChildrenButton = new TagBtn(this.tag , "");
		showChildrenButton.setBorderPainted(false);
		showChildrenButton.setBorder(null);
		showChildrenButton.setFocusable(false);
		showChildrenButton.setMargin(new Insets(0, 0, 0, 0));
		showChildrenButton.setContentAreaFilled(false);
		showChildrenButton.setPressedBackgroundColor(Color.WHITE);
		showChildrenButton.setIcon(menuIcon);
		showChildrenButton.setToolTipText("Show all chlidren");
		
		
		/** if tag has children add showChildrenButton */
		if(tag.getChildrenArr() != null) {
			
			showChildrenButton.addActionListener(listener);
			showChildrenButton.setActionCommand("showChildren");
		}
		else showChildrenButton.setEnabled(false);
		
		buttonsPanel.add(showChildrenButton);
		
		/** setting up trash icon */
		Image trash = Utils.getScaledImage(20, 20, 20, 20, "/icons/colorTrash128.png");
		ImageIcon trashIcon = new ImageIcon (trash);
	
		/** trashButton initial setup */
		TagBtn trashButton = new TagBtn(this.tag, "");
		trashButton.setBorderPainted(false);
		trashButton.setBorder(null);
		trashButton.setFocusable(false);
		trashButton.setMargin(new Insets(0, 0, 0, 0));
		trashButton.setContentAreaFilled(false);
		trashButton.setPressedBackgroundColor(Color.WHITE);
		trashButton.setIcon(trashIcon);
		trashButton.setToolTipText("Remove Tag");
		
		
		/** if tag is'n parent add trashButton */
		if(tag.getParent() != null) {

			/** setting button's listener */
			trashButton.addActionListener(listener);
			trashButton.setActionCommand("removeTagPanel");
			
		}
		else trashButton.setEnabled(false);
		
		
		buttonsPanel.add(trashButton);

		
		return buttonsPanel;
	}
	
	
	
	
	
	/**
	 * Return attributesPanel
	 * 
	 * @return attributesPanel
	 */
	
	private JPanel generateAttributesPanel() {
		
		JPanel attributesPanel = new JPanel();
		attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));
		
		/** add attributes */
		if (tag.getAttrArr() != null) {
			for( int i = 0; i < tag.getAttrArr().size(); i++) {
				
				/** recovering attribute */
				XmlAttribute a = tag.getAttrArr().get(i);
				
				/** add attribute inside checkBox */
				AttributeCheckBox c = new AttributeCheckBox(a , tag);   
				c.setForeground(new Color(255, 99, 71));   
				c.addItemListener(listener);
				
				/** if attribute is required, check checkBox and make it non editable */
				if(a.isRequired()) {
					c.setSelected(true);
					c.setEnabled(false);
				}
				
				attributesPanel.add(c,BorderLayout.CENTER); 
			}
		}
		else {
			
		}
		return attributesPanel;
	}
	
	
	
	
	public XmlTag getTag() {
		return this.tag;
	}

}
