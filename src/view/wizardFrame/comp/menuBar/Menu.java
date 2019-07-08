package view.wizardFrame.comp.menuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

import listeners.IndependentFrameListener.FileOptionListener;
import listeners.wizardFrameListeners.comp.MenuListener;
import view.comp.CustomColor;
import view.comp.IconUtils;

public class Menu extends JMenuBar {
	private JMenu window;
	private JMenu file;
	private MenuListener menuListener;
	private FileOptionListener fileListener;
	 
	public Menu() {
		this.setBorder(new MatteBorder(0,0,1,0,CustomColor.DARK_GRAY));
		
		window = new JMenu("Window");
		file = new JMenu("File");
		menuListener = new MenuListener();
		fileListener = new FileOptionListener();
		
		/** window item */
		JMenuItem validator = new JMenuItem(" Validator");
		validator.addActionListener(menuListener);
		validator.setActionCommand("showValidator");
		validator.setIcon(IconUtils.getScreeIcon(16));
		
		JMenuItem tagsList = new JMenuItem(" Tags List");
		tagsList.addActionListener(menuListener);
		tagsList.setActionCommand("showTagListBar");
		tagsList.setIcon(IconUtils.getChildrenListIcon(16));
		
		JMenuItem DescriptionPane = new JMenuItem(" Description Panel");
		DescriptionPane.addActionListener(menuListener);
		DescriptionPane.setActionCommand("showDescriptionPane");
		DescriptionPane.setIcon(IconUtils.FAgetInfoCircleIcon(16,null));
		
		
		
		/** file item */
		JMenuItem openPDSCFile = new JMenuItem(" Open PDSC File ...");
		openPDSCFile.addActionListener(fileListener);
		openPDSCFile.setActionCommand("openPDSCFile");
		
		JMenuItem newPDSC = new JMenuItem(" New PDSC File");
		newPDSC.addActionListener(fileListener);
		newPDSC.setActionCommand("createNewPDSC");
		
		
		JMenuItem save = new JMenuItem("save");
		save.addActionListener(fileListener);
		save.setActionCommand("savePDSC");
		save.setIcon(IconUtils.getSaveIcon(16));
		
		JMenuItem savePDSCAs = new JMenuItem(" Save PDSC As");
		savePDSCAs.addActionListener(fileListener);
		savePDSCAs.setActionCommand("savePDSCAs");
		savePDSCAs.setIcon(IconUtils.getSaveAsIcon(16));
		
		JMenuItem showPreview = new JMenuItem("show PDSC file preview");
		showPreview.addActionListener(fileListener);
		showPreview.setActionCommand("showPDSCPreview");

		
		file.add(newPDSC);
		file.add(openPDSCFile);
		file.add(new JSeparator());
		file.add(save);
		file.add(savePDSCAs);
		file.add(new JSeparator());
		file.add(showPreview);
		
		
		window.add(tagsList);
		window.add(validator);
		window.add(DescriptionPane);
		
		this.add(file);
		this.add(window);
	 }

}
