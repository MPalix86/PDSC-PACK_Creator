package view.wizardFrame.comp.tagsListBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.XmlTagBusiness;
import listeners.wizardFrameListeners.comp.TagListBarListener;
import model.XmlTag;
import view.comp.utils.ColorUtils;
import view.wizardFrame.comp.tagsListBar.comp.TagListBarButton;

/**
 * tag's children buttons list bar
 * 
 * @author Mirco Palese
 */

public class TagsListBar extends JPanel{
	
	private TagListBarListener listener;
	

	public TagsListBar() {
		this.listener = new TagListBarListener(this);
		placeComponents();
	}
	
	
	

	private void placeComponents() {

		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);

		XmlTag root = XmlTagBusiness.getRoot();
		ArrayList<XmlTag> rootChildren = XmlTagBusiness.getNotRequiredChildren(root);
		
	  	for(int i = 0; i < rootChildren.size(); i++) {
	  		XmlTag tag = rootChildren.get(i);
	  		
	  		JPanel panel = new JPanel(new BorderLayout());
	    	panel.setMaximumSize(new Dimension(300,30));
	    	
	    	TagListBarButton btn = new TagListBarButton( "< " + tag.getName() + " >"  ,  tag);
	        btn.setForeground(ColorUtils.TAG_COLOR);
	      
	        
	        btn.addActionListener(listener);
			btn.setActionCommand("addRootChild");
	        
	    	panel.add(btn);
	    	this.add(panel);	
	    }
	}
		

	
}
