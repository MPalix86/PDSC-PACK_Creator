package view.wizardFrame.comp.tagsListBar;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.XmlTagBusiness;
import listeners.wizardFrameListeners.comp.TagListBarListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.TagButton;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconUtils;
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
		this.setLayout(new MigLayout("wrap 2"));
		this.setBackground(Color.WHITE);

		XmlTag root = XmlTagBusiness.getRoot();
		ArrayList<XmlTag> rootChildren = XmlTagBusiness.getNotRequiredChildren(root);
		
	  	for(int i = 0; i < rootChildren.size(); i++) {
	  		XmlTag tag = rootChildren.get(i);
	    	
	    	TagListBarButton btn = new TagListBarButton( "< " + tag.getName() + " >"  ,  tag);
	        btn.setForeground(ColorUtils.TAG_COLOR);
	        
	        TagButton descriptionButton = new TagButton(tag).toIconButton(IconUtils.FAgetInfoCircleIcon(18, null));
	        descriptionButton.setToolTipText("Click here for tag description");
	        descriptionButton.addActionListener(listener);
	        descriptionButton.setActionCommand("showDescription");
	        
	        btn.addActionListener(listener);
			btn.setActionCommand("addRootChild");
	        
	    	this.add(btn);
	    	this.add(descriptionButton);
	    		
	    }
	}
		

	
}
