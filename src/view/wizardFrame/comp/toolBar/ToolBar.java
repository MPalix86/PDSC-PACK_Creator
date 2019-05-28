package view.wizardFrame.comp.toolBar;

import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import business.Session;
import listeners.wizardFrameListener.comp.ToolBarListener;
import model.pdsc.PackageChildrenEnum;
import view.comp.IconButton;
import view.comp.TagMenuItem;
import view.wizardFrame.comp.toolBar.comp.DropDownButton;

/**
 *  Menu bar creation
 * 
 * @author Mirco Palese
 */

public class ToolBar extends JToolBar{
	
	
	private IconButton  createPackButton;
	
	private IconButton  saveAsButton;
	
	private DropDownButton dropDownTagsButton;
	
	private IconButton showHideTagsListButton;

	private ToolBarListener listener;
	
	private Session session;
	
	
	
	
	
	public ToolBar() {
		this.listener = new ToolBarListener(this) ;
		
		session = Session.getInstance();
		
		placeComponents();
	}
	
	
	
	
	/**
	 * create bar with all components
	 * 
	 * @return void
	 */
	private void placeComponents() {
		
		ImageIcon createPackIcon = new ImageIcon(this.getClass().getResource("/icons/pack20.png"));  
		createPackButton = new IconButton(createPackIcon);
	
		ImageIcon saveAsIcon = new ImageIcon(this.getClass().getResource("/icons/saveAs20.png"));  
		saveAsButton = new IconButton(saveAsIcon);

		ImageIcon showHideChildrenListIcon = new ImageIcon(this.getClass().getResource("/icons/hideList20.png"));  
		showHideTagsListButton = new IconButton(showHideChildrenListIcon);
		showHideTagsListButton.addActionListener(listener);
		showHideTagsListButton.setToolTipText("Hide tags list");
		showHideTagsListButton.setActionCommand("showHideTagsListBar");
		
		
		generateTagsMenu();

		/** adding element inside toolbar */ 
		this.add(dropDownTagsButton);
		this.add(showHideTagsListButton);
		
		this.addSeparator();
		
		this.add(createPackButton);
		this.add(saveAsButton);

		this.setRollover(true);
	
	}
	
	
	

	/**
	 * Generate tags menu
	 * 
	 * @return void
	 */
	private void generateTagsMenu(){
		
		ImageIcon showTagsIcon = new ImageIcon(this.getClass().getResource("/icons/tagList.png"));
		
		PackageChildrenEnum packageChildEnum = new PackageChildrenEnum();
		
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
	
	
	
	public void setShowIconShowHideTagsListButton() {
		ImageIcon showTagsIcon = new ImageIcon(this.getClass().getResource("/icons/showList20.png"));
		showHideTagsListButton.setIcon(showTagsIcon);
		showHideTagsListButton.setToolTipText("Show tags list");
		showHideTagsListButton.repaint();
		showHideTagsListButton.revalidate();
	}
	
	
	
	public void setHideIconShowHideTagsListButton() {
		ImageIcon hideTagsIcon = new ImageIcon(this.getClass().getResource("/icons/hideList20.png"));
		showHideTagsListButton.setIcon(hideTagsIcon);
		showHideTagsListButton.setToolTipText("Hide tags list");
		showHideTagsListButton.repaint();
		showHideTagsListButton.revalidate();
	}
	
	
	
	/**
	 * @return the createPackButton
	 */
	public IconButton getCreatePackButton() {
		return createPackButton;
	}




	/**
	 * @return the saveAsButton
	 */
	public IconButton getSaveAsButton() {
		return saveAsButton;
	}




	/**
	 * @return the dropDownTagsButton
	 */
	public DropDownButton getDropDownTagsButton() {
		return dropDownTagsButton;
	}




	/**
	 * @return the showHideTagsListButton
	 */
	public IconButton getshowHideTagsListButton() {
		return showHideTagsListButton;
	}




	/**
	 * @return the listener
	 */
	public ToolBarListener getListener() {
		return listener;
	}




	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}
	
}

