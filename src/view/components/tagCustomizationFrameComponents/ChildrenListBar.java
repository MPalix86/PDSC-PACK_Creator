package view.Components.tagCustomizationFrameComponents;

import java.util.ArrayList;

import javax.swing.JPanel;

import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.Components.ModelComponents.TagBtn;

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
		this.setLayout(new MigLayout("wrap 1"));
		
		ArrayList<XmlTag> children = tag.getChildrenArr();
		
		if (children != null) {
			for(int i = 0; i < children.size(); i++) {
				
				XmlTag child = children.get(i);
				
				TagBtn btn = new TagBtn(child);
				btn.addActionListener(listener);
				btn.setActionCommand("addTagPanel");
				this.add(btn);
			}
		
		}
		
		
		
	}
		

	
}
