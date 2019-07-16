package view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.comp.attributeListBar;


import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import listeners.wizardFrameListeners.comp.xmlForm.comp.AddAttributeFrameListener;
import model.XmlTag;
import view.comp.utils.ColorUtils;


public class AttributesListBarContainer extends JPanel{
	
	private AttributesListBar bar;
	
	
	
	public AttributesListBarContainer(XmlTag tag , AddAttributeFrameListener listener) {
		bar = new AttributesListBar(tag,listener);
		placeComponents();
	}
	
	
	
	private void placeComponents() {
		
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
	
		
		this.add(scrollPane,BorderLayout.CENTER);
	}



	/**
	 * @return the bar
	 */
	public AttributesListBar getBar() {
		return bar;
	}
	
}

	