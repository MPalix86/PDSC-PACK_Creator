package view.wizardFrame.comp.tagsListBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.wizardFrameListeners.comp.ChildrenListBarListener;
import model.pdsc.PackageChildrenEnum;
import view.wizardFrame.comp.tagsListBar.comp.ListBarButton;

/**
 * tag's children buttons list bar
 * 
 * @author Mirco Palese
 */

public class TagsListBar extends JPanel{
	
	
	private PackageChildrenEnum packChildrenEnum;
	
	private ChildrenListBarListener listener;
	

	public TagsListBar() {
		this.packChildrenEnum = new PackageChildrenEnum();
		this.listener = new ChildrenListBarListener(this);
		placeComponents();
	}
	
	
	

	private void placeComponents() {
		
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);

		/** packageChildEnum iteration */
		Iterator it = packChildrenEnum.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	Map.Entry child = (Map.Entry)it.next();
	    	
	    	JPanel panel = new JPanel(new BorderLayout());
	    	panel.setMaximumSize(new Dimension(300,30));
	    	
	        ListBarButton btn = new ListBarButton( "< " + (String)child.getKey() + " >"  ,(Class) child.getValue());
	        
	        btn.addActionListener(listener);
			btn.setActionCommand("addPackageChild");
	        
	    	panel.add(btn);
	    	this.add(panel);
	    }

	}
		

	
}
