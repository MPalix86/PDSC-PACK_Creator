package view.tagCustomizationFrame.comp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import listeners.tagCustomizationFrameListener.TagCustomizationFrameListener;
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
