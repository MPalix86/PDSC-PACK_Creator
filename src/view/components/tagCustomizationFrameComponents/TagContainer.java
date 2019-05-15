package view.Components.tagCustomizationFrameComponents;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.Components.StylizedComponents.CollapsablePanel;

public class TagContainer extends JPanel{
	private XmlTag parent;
	private TagCustomizationFrameListener listener;
	private CollapsablePanel collPanel;
	
	public TagContainer(XmlTag tag,TagCustomizationFrameListener listener) {
		this.parent = tag;
		this.listener = listener;
				
		MigLayout layout = new MigLayout("wrap 1");
		setLayout(layout);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.WHITE);
		CollapsableTagElement tagElement = new CollapsableTagElement(parent,listener);
		collPanel = new CollapsablePanel(parent.getName() , tagElement);
		this.add(collPanel);
	}
	
	public void addTagPanel(XmlTag parent){
		CollapsableTagElement tagElement = new CollapsableTagElement(parent,listener);
		collPanel.add(tagElement);
		add(collPanel, "span");
		this.repaint();
		this.revalidate();
	}
	
	public void removeTagPanel(JPanel tagPanel){
		this.remove(tagPanel);
		this.repaint();
		this.revalidate();
	}
	
	
}
