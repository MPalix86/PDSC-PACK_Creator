package view.tagCustomizationFrame.comp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.tagCustomizationFrameListeners.TagCustomizationFrameListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.tagCustomizationFrame.TagCustomizationFrame;



/**
 * tag container. Contains all selected tag panel and
 * 
 * @author Mirco Palese
 */
public class TagContainer extends JPanel{

	
	private TagCustomizationFrameListener listener;
	
	private TagCustomizationFrame tagCustomizationFrame;
	
	private HashMap <XmlTag,JPanel> selectedChildrenPanelsHashMap ;
	
	private XmlTag FrameParentTag;
	
	
	
	/**
	 * Constructor 
	 * 
	 * @param tagCustomizationFrame tag customization frame instance
	 */
	public TagContainer(TagCustomizationFrame tagCustomizationFrame) {
		
		this.tagCustomizationFrame = tagCustomizationFrame;
		this.listener = tagCustomizationFrame.getListener();
		selectedChildrenPanelsHashMap = new HashMap	<XmlTag,JPanel>();	
		
		MigLayout layout = new MigLayout("wrap 1");
		setLayout(layout);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.WHITE);
		
		FrameParentTag = this.tagCustomizationFrame.getTagParent();
		placeComponents();
		
	}
	
	
	
	/**
	 * Place all components
	 * 
	 * @return void
	 */
	private void placeComponents() {
		this.removeAll();
		selectedChildrenPanelsHashMap.clear();
		paintTag(this.FrameParentTag, 0);
	}
	
	
	
	
	
	/**
	 * Paint all selected tag with correct indentation.
	 * Iterate in depth trough all selected children starting from the 
	 * top most parent ( this.tagCustomizationFrame.getTagParent() ) to calculate
	 * indentation level and painting it correctly: 
	 * <p>
	 * NOTE : uncomment all System.out.println in this function and 
	 * 		  execute for more details;
	 * 
	 * 
	 * @param tag	=  this.tagCustomizationFrame.getTagParent() i.e. the topmost parent
	 * @param level    starting level of indentation  
	 */
	
	private void paintTag(XmlTag tag, int level) {
		
		XmlTag parent =  tag;
		
		CollapsablePanelTagContent newTagElement = new CollapsablePanelTagContent(tag,listener);
		CollapsablePanel newCollPanel = new CollapsablePanel("< " + tag.getName() + " >", newTagElement, tag,listener);
		this.selectedChildrenPanelsHashMap.put(tag, newCollPanel);
		
		/** calculating left border based on levelCounter */
		int leftBorder = level * 40;
		
		/** setting calculated border */
		newCollPanel.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
		
		/** adding parent inside tagContainer */
		this.add(newCollPanel);
		
		this.repaint();
		this.revalidate();
		
		/** increases indentation level */
		level++;
		
		if( parent.getSelectedChildrenArr() != null) {
			ArrayList<XmlTag> xmlChildren = parent.getSelectedChildrenArr();

			/** iterating trough selected children */
			for(int i = 0; i < xmlChildren.size(); i++) {		
				XmlTag child = xmlChildren.get(i);	
				
				/** recursion */
				paintTag(child,level);
			}
		}
	}
	
	
	
	
	/**
	 * Iterate in depth trou all selected children starting from the 
	 * top most parent (selected tag in this case)  deleting selected tag and all
	 * children under him recursighvely
	 * 
	 * @param tag
	 */
	public void removeTagPanel(XmlTag tag){
		
		XmlTag parent = tag;
		if( parent.getSelectedChildrenArr() != null) {
			ArrayList<XmlTag> xmlChildren = parent.getSelectedChildrenArr();
			for(int i = 0; i < xmlChildren.size(); i++) {		
				this.selectedChildrenPanelsHashMap.remove(parent);
				XmlTag child = xmlChildren.get(i);	
				removeTagPanel(child);
			}
		}
		placeComponents();
	}
	
	
	public void updateView() {
		placeComponents();
	}
	

}