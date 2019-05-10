package view.Components.tagCustomizationFrameComponents;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.Components.StylizedComponents.CollapsablePanel;

public class TagContainer extends JPanel{
	private XmlTag parent;
	private TagCustomizationFrameListener listener;
	
	public TagContainer(XmlTag tag,TagCustomizationFrameListener listener) {
		this.parent = parent;
		this.listener = listener;
		
		addTagPanel(tag);		
		MigLayout layout = new MigLayout("wrap 1");
		setLayout(layout);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.WHITE);
	}
	
	public void addTagPanel(XmlTag parent){
		CollapsableTagPanel tagPanel = new CollapsableTagPanel(parent,listener);
		CollapsablePanel collPanel = new CollapsablePanel(parent.getName() , tagPanel);
		collPanel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
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
