package view.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.XmlAttribute;
import java.awt.*;

import model.XmlTag;
import model.XmlTagContents;
import net.miginfocom.swing.MigLayout;

public class TagContainer extends JPanel{
	private XmlTag tag;
	
	public TagContainer(XmlTag tag) {
		
		
		this.tag = tag;
		addTag(this.tag);		
		MigLayout layout = new MigLayout("wrap 1");
		setLayout(layout);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setBackground(Color.WHITE);
		//setLayout(new FlowLayout());
	}
	
	public void addTag(XmlTag tag){
		CollapsablePanel collPanel = new CollapsablePanel(tag.getName() , new TagPanelComponent(tag));
		add(collPanel, "span");
		if(tag.getChildren() != null) {
			tag.getChildren().forEach((c)-> addTag(c));
		}
		
	}
	
}
