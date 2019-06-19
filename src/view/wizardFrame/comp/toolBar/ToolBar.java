package view.wizardFrame.comp.toolBar;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import business.Session;
import business.XmlTagBusiness;
import listeners.wizardFrameListeners.comp.ToolBarListener;
import model.XmlTag;
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
		createPackButton.addActionListener(listener);
		createPackButton.setActionCommand("createPack");
		createPackButton.setToolTipText("createPack");
	
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
	
	
	

	private void generateTagsMenu() {
		
		ImageIcon showTagsIcon = new ImageIcon(this.getClass().getResource("/icons/tagList.png"));
		
		XmlTag root = XmlTagBusiness.getRoot();
		ArrayList<XmlTag> rootChildren = XmlTagBusiness.getNotRequiredChildren(root);
		
		JPopupMenu tagsMenu = new JPopupMenu();
		
	  	for(int i = 0; i < rootChildren.size(); i++) {
	  		XmlTag tag = rootChildren.get(i);
	    	
	        TagMenuItem menuItem = new TagMenuItem( "new < " + tag.getName() + " >"  , tag);
	        menuItem.addActionListener(listener);
	        menuItem.setActionCommand("addRootChild");
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

