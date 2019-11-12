package view.wizardFrame.comp.toolBar;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import business.Session;
import listeners.FileOptionListener;
import listeners.PDSCOptionListener;
import listeners.wizardFrameListeners.comp.ToolBarListener;
import mao.XmlTagMao;
import model.xml.XmlTag;
import view.comp.DropDownButton;
import view.comp.SquareButton;
import view.comp.TagMenuItem;
import view.comp.utils.IconsUtils;

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
	
	private PDSCOptionListener PdscOptionListener;
	
	private SquareButton undo;
	
	private SquareButton redo;
	
	
	
	
	
	public ToolBar() {
		fileOptionListener = new FileOptionListener();
		PdscOptionListener = new PDSCOptionListener();
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

		createPackButton = new SquareButton().toIconButton(IconsUtils.getPackIcon(20));
		createPackButton.addActionListener(PdscOptionListener);
		createPackButton.setActionCommand("createPack");
		createPackButton.setToolTipText("Export Pack");
	

	
		showHideTagsListButton = new SquareButton().toIconButton(IconsUtils.getHideChildrenListIcon(20));
		showHideTagsListButton.addActionListener(listener);
		showHideTagsListButton.setToolTipText("Hide tags list");
		showHideTagsListButton.setActionCommand("showHideTagsListBar");
		


		SquareButton validateXsd = new SquareButton().toIconButton(IconsUtils.getPlayIcon(20));
		validateXsd.setToolTipText("Validate with XSD");
		validateXsd.addActionListener(PdscOptionListener);
		validateXsd.setActionCommand("validateXSD");
		
		SquareButton saveButton = new SquareButton().toIconButton(IconsUtils.getSaveIcon(24));
		saveButton.setToolTipText("Save current PDSC");
		saveButton.addActionListener(fileOptionListener);
		saveButton.setActionCommand("savePDSC");
		
		SquareButton saveAsButton = new SquareButton().toIconButton(IconsUtils.getSaveAsIcon(24));
		saveAsButton.setToolTipText("Save PDSC as");
		saveAsButton.addActionListener(fileOptionListener);
		saveAsButton.setActionCommand("savePDSCAs");
		
		redo = new SquareButton().toIconButton(IconsUtils.getRedoIcon(20));
		redo.setToolTipText("Redo");
		redo.addActionListener(PdscOptionListener);
		redo.setActionCommand("redo");
		redo.setEnabled(false);
		
		undo = new SquareButton().toIconButton(IconsUtils.getUndoIcon(20));
		undo.setToolTipText("Undo");
		undo.addActionListener(PdscOptionListener);
		undo.setActionCommand("undo");
		undo.setEnabled(false);
		
		
		generateTagsMenu();

		/** adding element inside toolbar */ 
		this.add(dropDownTagsButton);
		this.add(showHideTagsListButton);
		
		
		this.add(saveButton);
		this.add(saveAsButton);
		
		
		this.add(validateXsd);
		this.add(createPackButton);
		
		this.add(undo);
		this.add(redo);

		this.setRollover(true);
	
	}
	
	private void generateTagsMenu() {
		
		XmlTag root = XmlTagMao.getRoot();
		ArrayList<XmlTag> rootChildren = XmlTagMao.getNotRequiredChildren(root);
		
		JPopupMenu tagsMenu = new JPopupMenu();
		
	  	for(int i = 0; i < rootChildren.size(); i++) {
	  		XmlTag tag = rootChildren.get(i);
	    	
	        TagMenuItem menuItem = new TagMenuItem( "new < " + tag.getName() + " >"  , tag);
	        menuItem.addActionListener(listener);
	        menuItem.setActionCommand("addRootChild");
	        tagsMenu.add(menuItem);
	    }
	    
		dropDownTagsButton = new DropDownButton(tagsMenu,IconsUtils.getChildrenListIArrowIcon(35));

	}
	
	
	
	/** change imageIcon (show) tag list bar */
	public void setShowIconShowHideTagsListButton() {
		ImageIcon showTagsIcon = IconsUtils.getShowChildrenListIcon(20);
		showHideTagsListButton.setIcon(showTagsIcon);
		showHideTagsListButton.setToolTipText("Show tags list");
		showHideTagsListButton.repaint();
		showHideTagsListButton.revalidate();
	}
	
	
	/** change imageIcon (hide) tag list bar */
	public void setHideIconShowHideTagsListButton() {
		ImageIcon hideTagsIcon = IconsUtils.getHideChildrenListIcon(20);
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
	
	
	

	/**
	 * @return the undo
	 */
	public SquareButton getUndo() {
		return undo;
	}




	/**
	 * @return the redo
	 */
	public SquareButton getRedo() {
		return redo;
	}
	
	public void disableRedo() {
		this.redo.setEnabled(false);
	}
	
	public void disableUndo() {
		this.undo.setEnabled(false);
	}
	
	public void enableRedo() {
		this.redo.setEnabled(true);
	}
	
	public void enableUndo() {
		this.undo.setEnabled(true);
	}
	
	
}

