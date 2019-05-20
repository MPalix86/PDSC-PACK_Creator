package view.tagCustomizationFrame.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import view.comp.TagBtn;

/**
 * tag's children buttons list bar
 * 
 * @author Mirco Palese
 */

public class ChildrenListBar extends JPanel{
	
	/** parent tag */
	private XmlTag tag;
	
	/** listener */
	private TagCustomizationFrameListener listener;
	
	
	
	
	/**
	 * Set up listener and tag parent and generate ChildrenListBar
	 * 
	 * @param tag parent tag
	 * @param listener listener for the ChildrenListBar
	 */
	
	public ChildrenListBar(XmlTag tag, TagCustomizationFrameListener listener) {
		this.listener = listener;
		
		if (tag != null) {
			this.tag = tag;
		}
		
		generateChildrenListBar();
	}
	
	
	
	
	/**
	 * Generate generateChildrenListBar placing all components
	 * 
	 * @return void
	 */
	
	private void generateChildrenListBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ArrayList<XmlTag> children = tag.getChildrenArr();
		
		if (children != null) {
			for(int i = 0; i < children.size(); i++) {
				
				XmlTag child = children.get(i);
				JPanel panel = new JPanel(new BorderLayout());
			
				TagBtn btn = new TagBtn(child);
				
				panel.add(btn);
				panel.setMaximumSize(new Dimension(300,30));
				btn.addActionListener(listener);
				btn.setActionCommand("addTagPanel");
				this.add(panel);
			}
		
		}
		
		
		
	}
		

	
}
