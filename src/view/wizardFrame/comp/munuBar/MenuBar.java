package view.wizardFrame.comp.munuBar;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import listeners.WizardFrameListener;
import model.pdsc.PackageChildEnum;
import view.comp.IconButton;
import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.munuBar.comp.DropDownButton;
import view.wizardFrame.comp.munuBar.comp.TagMenuItem;

/**
 *  Menu bar creation
 * 
 * @author Mirco Palese
 */

public class MenuBar extends JPanel{
	
	
	private IconButton  createPackButton;
	
	
	private IconButton  saveAsButton;
	
	
	private IconButton  trashButton;

	
	
	private JPopupMenu tagsMenu;
	
	
	private DropDownButton dropDownTagsButton;
	
	
	/** wizardFrame instance */
	private WizardFrame wizardFrame;
	
	/** WizardFrameListener */
	private WizardFrameListener listener;
	
	
	
	
	/**
	 * 
	 * @param wizardFrame wizardFrame instance
	 */
	public MenuBar(WizardFrame wizardFrame, WizardFrameListener listener) {
		this.wizardFrame = wizardFrame;
		this.listener = listener ;
		
		placeComponents();
	}
	
	
	
	
	/**
	 * create bar with all components
	 * 
	 * @return void
	 */
	private void placeComponents() {
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		setBackground(new Color(224,224,224));
		
		JToolBar toolBar = new JToolBar();
		
		/** save icon */
		ImageIcon createPackIcon = new ImageIcon(this.getClass().getResource("/view/wizardFrame/comp/munuBar/icons/pack20.png"));  
		createPackButton = new IconButton(createPackIcon);
		
		/** saveAs icon */
		ImageIcon saveAsIcon = new ImageIcon(this.getClass().getResource("/view/wizardFrame/comp/munuBar/icons/saveAs20.png"));  
		saveAsButton = new IconButton(saveAsIcon);
		
		generateTagsMenu();

		
		/** adding element inside toolbar */ 
		toolBar.add(dropDownTagsButton);
		toolBar.addSeparator();
		toolBar.add(createPackButton);
		toolBar.add(saveAsButton);
		
		
		
		toolBar.setRollover(true);
		
		
		
		add(toolBar);
	
	}
	
	
	
	/**
	 * Generate tags menu
	 * 
	 * @return void
	 */
	
	private void generateTagsMenu(){
		
		/** show  tags icon */
		ImageIcon showTagsIcon = new ImageIcon(this.getClass().getResource("/view/wizardFrame/comp/munuBar/icons/showTags.png"));
		
		/** PackageChildEnum */
		PackageChildEnum packageChildEnum = new PackageChildEnum();
		
		JPopupMenu tagsMenu = new JPopupMenu();
		
		/** packageChildEnum iteration */
		Iterator it = packageChildEnum.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	        Map.Entry child = (Map.Entry)it.next();
	        TagMenuItem menuItem = new TagMenuItem( "new < " + (String)child.getKey() + " >"  ,(Class) child.getValue());
	        menuItem.addActionListener(listener);
	        menuItem.setActionCommand("addPackageChild");
	        tagsMenu.add(menuItem);
	        
	    }
	    
		dropDownTagsButton = new DropDownButton(tagsMenu,showTagsIcon );

	}
	
	
	
	
	
	/**
	 * @return the wizardFrame
	 */
	public WizardFrame getWizardFrame() {
		return wizardFrame;
	}


	/**
	 * @param wizardFrame the wizardFrame to set
	 */
	public void setWizardFrame(WizardFrame wizardFrame) {
		this.wizardFrame = wizardFrame;
	}

	
}

