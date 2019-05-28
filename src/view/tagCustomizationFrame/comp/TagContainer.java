package view.tagCustomizationFrame.comp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.tagCustomizationFrameListener.TagCustomizationFrameListener;
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
	
	private ImageIcon tagIcon = new ImageIcon (getClass().getClassLoader().getResource("icons/tagList20.png"));
	
	private ImageIcon trashIcon = new ImageIcon (getClass().getClassLoader().getResource("icons/colorTrash20.png"));
	
	private ImageIcon cloneIcon = new ImageIcon (getClass().getClassLoader().getResource("icons/clone20.png"));
	
	
	
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
	
		XmlTag FrameParent = this.tagCustomizationFrame.getTagParent();
		
		CollapsablePanelTagContent newTagElement = new CollapsablePanelTagContent(FrameParent,listener);
		CollapsablePanel newCollPanel = new CollapsablePanel("< " + FrameParent.getName() + " >", newTagElement,FrameParent,listener);
		this.selectedChildrenPanelsHashMap.put(FrameParent, newCollPanel);
		
		addNewTagPanel(this.tagCustomizationFrame.getTagParent());

	}
	
	
	
	
	/**
	 * add panel containing new selected tag
	 * 
	 * @param tag tag to add 
	 */
	public void addNewTagPanel(XmlTag tag){
		
		if (tag.getParent() != null ) {
			CollapsablePanelTagContent newTagElement = new CollapsablePanelTagContent(tag,listener);
			CollapsablePanel newCollPanel = new CollapsablePanel("< " + tag.getName() + " >", newTagElement, tag,listener);
			this.selectedChildrenPanelsHashMap.put(tag, newCollPanel);
		}

		placeComponents();
	}
	
	
	
	
	
	/**
	 * Place all components
	 * 
	 * @return void
	 */
	public void placeComponents() {
		this.removeAll();
		paintTag(this.tagCustomizationFrame.getTagParent(), 0);
		this.repaint();
		this.revalidate();
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
		
		/** calculating left border based on levelCounter */
		int leftBorder = level * 40;
		
		/** setting calculated border */
		this.selectedChildrenPanelsHashMap.get(parent).setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
		
		/** adding parent inside tagContainer */
		this.add(this.selectedChildrenPanelsHashMap.get(parent));
		
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
	 * Iterate in depth trough all selected children starting from the 
	 * top most parent (selected tag in this case)  deleting selected tag and all
	 * children under him recursively
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
	
	
	
	
	/**
	 * generate recursively one panel for every selected tag and add it inside
	 * selectedChildrenPanelsHashMap linking it to relative tag
	 * 
	 * @param tag cloned tag
	 */
	public void addClonedTag(XmlTag tag) {
		XmlTag parent =  tag;
		
		CollapsablePanelTagContent newTagElement = new CollapsablePanelTagContent(parent,listener);
		CollapsablePanel newCollPanel = new CollapsablePanel("< " + parent.getName() + " >", newTagElement ,parent ,listener);
		this.selectedChildrenPanelsHashMap.put(parent, newCollPanel);
		
		if( parent.getSelectedChildrenArr() != null) {
			ArrayList<XmlTag> xmlChildren = parent.getSelectedChildrenArr();

			/** iterating trough selected children */
			for(int i = 0; i < xmlChildren.size(); i++) {		
				XmlTag child = xmlChildren.get(i);	
				
				/** recursion */
				addClonedTag(child);
			}
		}
	}
	
	
	
	
	
	
	public void updateView() {
		placeComponents();
	}
	
	
	

}