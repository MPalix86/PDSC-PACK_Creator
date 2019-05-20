package view.tagCustomizationFrame.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.CollapsablePanel;

public class TagContainer extends JPanel{
	
	
	private XmlTag parent;
	
	
	private TagCustomizationFrameListener listener;
	
	
	private CollapsablePanel collPanel;
	
	
	/** children panel */
	private ArrayList<JPanel> selectedChildrenPanelsArr;
	
	
	
	
	
	public TagContainer(XmlTag tag,TagCustomizationFrameListener listener) {
		this.selectedChildrenPanelsArr = new ArrayList<JPanel>();
		this.parent = tag;
		this.listener = listener;
				
		MigLayout layout = new MigLayout("wrap 1");
		setLayout(layout);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.WHITE);
		CollapsablePanelTagContent tagElement = new CollapsablePanelTagContent(parent,listener);
		collPanel = new CollapsablePanel("< " + parent.getName() + " >", tagElement);
		this.selectedChildrenPanelsArr.add(collPanel);
		
		this.add(collPanel);
	}
	
	public void addTagPanel(XmlTag tag){
		
		if (tag.getParent() != null ) {
			//System.out.println("c'ha il parent, ed è " + tag.getParent());
			for (int i = 0; i < selectedChildrenPanelsArr.size(); i++) {
				
				CollapsablePanel collPanel = (CollapsablePanel) selectedChildrenPanelsArr.get(i);
				CollapsablePanelTagContent content = (CollapsablePanelTagContent) collPanel.getContentPanel();
				
				XmlTag candidateParent = content.getTag();
				//System.out.println("il candidati parent è " + candidateParent.getName());
				
				CollapsablePanelTagContent newTagElement = null;
				CollapsablePanel newCollPanel = null;
				
				
				if(tag.getParent().equals(candidateParent)) {
					
					//System.out.println("bella coincidono, creo il nuovo pannello");
					newTagElement = new CollapsablePanelTagContent(tag,listener);
					newCollPanel = new CollapsablePanel("< " + tag.getName() + " >", newTagElement);
					
					//System.out.println("aggiungo il nuovo pannello alla posizione " + i+1);
					selectedChildrenPanelsArr.add(i+1 ,newCollPanel);
					
					
					
					break;
				}
						
			}
		}
		System.out.println("provo a ristampare tutto");
		placeComponents();
	}
	
	
	public void placeComponents() {
		
		this.removeAll();
	
		/** scrolling vector to show to analyze tag */
		for (int i = 0; i < selectedChildrenPanelsArr.size() ; i++) {
			
			/** recovering collapsablePanel */
			CollapsablePanel collPanel = (CollapsablePanel) selectedChildrenPanelsArr.get(i);
			
			/** recovering CollapsablePanelTagContent that contains selected tag*/
			CollapsablePanelTagContent content = (CollapsablePanelTagContent) collPanel.getContentPanel();
			
			/** recovering tag */
			XmlTag xmlTag = content.getTag();
			
			
			//System.out.println("analyzing tag "+ xmlTag.getName());
			
			/** level counter to calculate indentation dynamically */
			int levelCount = 0;
			
			/** scroll back from selected tag to recover all parent */
			while(xmlTag.getParent() != null) {
				
				//System.out.println("tag "+ xmlTag.getName() + " has parent ");
				
				/** if tag have parent increase levelCount */
				levelCount += 1;
				
				/** set selectedTag to parent of analyzed tag */
				xmlTag = xmlTag.getParent();
				
				//System.out.println("level count augmented, now i'm analyzing tag "+ xmlTag.getName());
			}
			
			/** if selected tag has no longer parent */
			System.out.println( xmlTag.getName() + "hasn't parent , counter level :" + levelCount);
			
			JPanel panel = new JPanel(new BorderLayout());
			
			/** calculating left border based on levelCounter */
			int border = levelCount * 50;
			
			/** setting calculated border */
			panel.setBorder(new EmptyBorder( 0, border, 0, 0));
			
			/** adding panel inside container */
			panel.add(collPanel);
			this.add(panel);
		}
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void removeTagPanel(JPanel tagPanel){
		System.out.println(tagPanel.toString());
		
		CollapsablePanel selectedPanel = (CollapsablePanel)tagPanel;
		this.selectedChildrenPanelsArr.remove(selectedPanel);
		
		this.remove(selectedPanel.getParent());
		this.repaint();
		this.revalidate();
	}
	
	
}