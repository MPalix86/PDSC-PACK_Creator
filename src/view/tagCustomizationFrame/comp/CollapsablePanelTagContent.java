package view.tagCustomizationFrame.comp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import listeners.tagCustomizationFrameListener.TagCustomizationFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.AttributeCheckBox;
import view.comp.CustomColor;
import view.comp.TagButton;






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
		ImageIcon tagIcon = new ImageIcon (getClass().getClassLoader().getResource("icons/tag20.png"));
		
		/** showChildrenButton initial setup */
		TagButton showChildrenButton = new TagButton(this.tag , "").toIconButton(tagIcon);
		showChildrenButton.setToolTipText("Show all chlidren");
		
		
		/** if tag has children add showChildrenButton */
		if(tag.getChildrenArr() != null) {
			
			showChildrenButton.addActionListener(listener);
			showChildrenButton.setActionCommand("showChildren");
		}
		else showChildrenButton.setEnabled(false);
		
		buttonsPanel.add(showChildrenButton);
		
		/** setting up trash icon */
		ImageIcon trashIcon = new ImageIcon (getClass().getClassLoader().getResource("icons/colorTrash20.png"));
	
		/** trashButton initial setup */
		TagButton trashButton = new TagButton(this.tag, "").toIconButton(trashIcon);
		trashButton.setToolTipText("Remove Tag");
		
		/** setting clone trash icon */
		ImageIcon cloneIcon = new ImageIcon (getClass().getClassLoader().getResource("icons/clone20.png"));
		
		/** cloneButton initial setup */
		TagButton cloneButton = new TagButton(this.tag, "").toIconButton(cloneIcon);
		cloneButton.setToolTipText("Clone This Tag");
		
		
		if(tag.getParent() != null) {

			/** setting button's listener */
			trashButton.addActionListener(listener);
			trashButton.setActionCommand("removeTagPanel");
			
			cloneButton.addActionListener(listener);
			cloneButton.setActionCommand("cloneTag");
			
		}
		else {
			trashButton.setEnabled(false);
			cloneButton.setEnabled(false);
		}
		
		
		buttonsPanel.add(trashButton);
		buttonsPanel.add(cloneButton);

		
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
				
				
				if(tag.getSelectedAttrArr() != null){
					
					for(int j = 0; j < tag.getSelectedAttrArr().size(); j++) {
						XmlAttribute selectedAttr = tag.getSelectedAttrArr().get(j);
						if(selectedAttr.getName().equals(a.getName()) &&  !a.isRequired()) {
							
							/** attribute is already selected */
							c.setSelected(true);
							
						}
					}
				}
				
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
