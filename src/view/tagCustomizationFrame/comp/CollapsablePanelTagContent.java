package view.tagCustomizationFrame.comp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import listeners.tagCustomizationFrameListeners.TagCustomizationFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeCheckBox;






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
		
		/** generating attributesPanel */
		JPanel attributesPanel = generateAttributesPanel();
		
		/** generating all panels in contentPane */
		contentPane.add(attributesPanel, BorderLayout.CENTER);	
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
				AttributeCheckBox c = new AttributeCheckBox(a); 
				if (a.isRequired()) {
					c.setText(a.getName() + " *");
					c.setSelected(true);
				}
				c.setForeground(new Color(255, 99, 71));   
	
				c.addItemListener(listener);
				
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
