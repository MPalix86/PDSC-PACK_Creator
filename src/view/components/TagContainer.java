package view.components;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import listeners.TagCustomizationFrameListener;
import model.XmlAttribute;
import java.awt.*;

import model.XmlTag;
import model.XmlTagContents;
import net.miginfocom.swing.MigLayout;

public class TagContainer extends JPanel{
	private XmlTag tag;
	private TagCustomizationFrameListener listener;
	
	public TagContainer(XmlTag tag,TagCustomizationFrameListener listener) {
		this.tag = tag;
		this.listener = listener;
		
		addTagPanel(tag);		
		MigLayout layout = new MigLayout("wrap 1");
		setLayout(layout);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.WHITE);
	}
	
	public void addTagPanel(XmlTag tag){
		TagPanelComponent tagPanel = new TagPanelComponent(tag,listener);
		CollapsablePanel collPanel = new CollapsablePanel(tag.getName() , tagPanel);
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
