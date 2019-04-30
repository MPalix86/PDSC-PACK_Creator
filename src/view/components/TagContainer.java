package view.components;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.XmlAttribute;
import java.awt.*;

import model.XmlTag;
import model.XmlTagContents;

public class TagContainer extends JPanel{
	private XmlTag tag;
	
	public TagContainer(XmlTag tag) {
		
		
		this.tag = tag;
		addTag(this.tag);		
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setLayout(new FlowLayout());
	}
	
	public void addTag(XmlTag tag){
		CollapsablePanel collPanel = new CollapsablePanel(tag.getName() , new TagPanelComponent(tag));
		add(collPanel);
		if(tag.getChildren() != null) {
			tag.getChildren().forEach((c)-> addTag(c));
		}
	}
	
}
