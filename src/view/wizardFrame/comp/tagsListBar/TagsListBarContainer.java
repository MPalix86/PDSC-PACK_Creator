package view.wizardFrame.comp.tagsListBar;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import view.comp.utils.ColorUtils;

public class TagsListBarContainer extends JPanel{
	
	private TagsListBar bar ;
	
	
	
	
	
	public TagsListBarContainer() {
		
		bar = new TagsListBar();
		placeComponents();
	}
	
	
	
	private void placeComponents() {
		
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane(bar);
		
	   JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
        	
        	/**
        	 * override isVisible to keep the scroll bar working even if
        	 * it is hidden
        	 */
        	
            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        scrollBar.setUnitIncrement(16);
        
        
		scrollPane.setVerticalScrollBar(scrollBar);
		scrollPane.setBorder(new MatteBorder(0,0,0,1, ColorUtils.LIGHT_GRAY));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		this.add(scrollPane);
	}



	/**
	 * @return the bar
	 */
	public TagsListBar getBar() {
		return bar;
	}
	
}
