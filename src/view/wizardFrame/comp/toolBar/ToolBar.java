package view.wizardFrame.comp.toolBar;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import business.Session;
import business.XmlTagBusiness;
import listeners.IndependentFrameListener.FileOptionListener;
import listeners.wizardFrameListeners.comp.ToolBarListener;
import model.XmlTag;
import view.comp.DropDownButton;
import view.comp.IconUtils;
import view.comp.SquareButton;
import view.comp.TagMenuItem;

/**
 *  Menu bar creation
 * 
 * @author Mirco Palese
 */

public class ToolBar extends JToolBar{
	
	
	private SquareButton  createPackButton;
	
	private SquareButton  saveAsButton;
	
	private DropDownButton dropDownTagsButton;
	
	private SquareButton showHideTagsListButton;

	private ToolBarListener listener;
	
	private Session session;
	
	private FileOptionListener fileOptionListener;
	
	
	
	
	
	public ToolBar() {
		fileOptionListener = new FileOptionListener();
		this.listener = new ToolBarListener(this) ;
		session = Session.getInstance();
		this.setFloatable(false);
		placeComponents();
	}
	
	
	
	
	/**
	 * create bar with all components
	 * 
	 * @return void
	 */
	private void placeComponents() {

		createPackButton = new SquareButton().toIconButton(IconUtils.getPackIcon(20));
		createPackButton.addActionListener(listener);
		createPackButton.setActionCommand("createPack");
		createPackButton.setToolTipText("createPack");
	

	
		showHideTagsListButton = new SquareButton().toIconButton(IconUtils.getHideChildrenListIcon(20));
		showHideTagsListButton.addActionListener(listener);
		showHideTagsListButton.setToolTipText("Hide tags list");
		showHideTagsListButton.setActionCommand("showHideTagsListBar");
		


		SquareButton validateXsd = new SquareButton().toIconButton(IconUtils.getPlayIcon(20));
		validateXsd.setToolTipText("Validate with XSD");
		validateXsd.addActionListener(listener);
		validateXsd.setActionCommand("validateXSD");
		
		SquareButton saveButton = new SquareButton().toIconButton(IconUtils.getSaveIcon(20));
		saveButton.setToolTipText("Save current PDSC");
		saveButton.addActionListener(fileOptionListener);
		saveButton.setActionCommand("savePDSC");
		
		SquareButton saveAsButton = new SquareButton().toIconButton(IconUtils.getSaveAsIcon(20));
		saveAsButton.setToolTipText("Save PDSC as");
		saveAsButton.addActionListener(fileOptionListener);
		saveAsButton.setActionCommand("savePDSCAs");
		
		
		generateTagsMenu();

		/** adding element inside toolbar */ 
		this.add(dropDownTagsButton);
		this.add(showHideTagsListButton);
		
		
		this.add(saveButton);
		this.add(saveAsButton);
		
		
		this.add(validateXsd);
		this.add(createPackButton);

		this.setRollover(true);
	
	}
	
	
	

	private void generateTagsMenu() {
		
		ImageIcon showTagsIcon = IconUtils.getChildrenListIArrowIcon(20);
		
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
	
	
	
	/** change imageIcon (show) tag list bar */
	public void setShowIconShowHideTagsListButton() {
		ImageIcon showTagsIcon = IconUtils.getShowChildrenListIcon(20);
		showHideTagsListButton.setIcon(showTagsIcon);
		showHideTagsListButton.setToolTipText("Show tags list");
		showHideTagsListButton.repaint();
		showHideTagsListButton.revalidate();
	}
	
	
	/** change imageIcon (hide) tag list bar */
	public void setHideIconShowHideTagsListButton() {
		ImageIcon hideTagsIcon = IconUtils.getHideChildrenListIcon(20);
		showHideTagsListButton.setIcon(hideTagsIcon);
		showHideTagsListButton.setToolTipText("Hide tags list");
		showHideTagsListButton.repaint();
		showHideTagsListButton.revalidate();
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

