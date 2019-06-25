package view.wizardFrame.comp.menuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.MatteBorder;

import listeners.wizardFrameListeners.comp.MenuListener;
import view.comp.CustomColor;
import view.comp.IconUtils;

public class Menu extends JMenuBar {
	private JMenu window;
	private JMenu file;
	private MenuListener listener;
	 
	public Menu() {
		this.setBorder(new MatteBorder(0,0,1,0,CustomColor.DARK_GRAY));
		
		window = new JMenu("Window");
		file = new JMenu("File");
		listener = new MenuListener();
		 
		JMenuItem validator = new JMenuItem(" Validator");
		validator.addActionListener(listener);
		validator.setActionCommand("showValidator");
		validator.setIcon(IconUtils.FAgetDesktopIcon(16));
		
		JMenuItem tagsList = new JMenuItem(" Tags List");
		tagsList.addActionListener(listener);
		tagsList.setActionCommand("showTagListBar");
		tagsList.setIcon(IconUtils.getChildrenListIcon(16));
		
		JMenuItem DescriptionPane = new JMenuItem(" Description Panel");
		DescriptionPane.addActionListener(listener);
		DescriptionPane.setActionCommand("showDescriptionPane");
		DescriptionPane.setIcon(IconUtils.FAgetInfoCircleIcon(16));
		
		
		
		
		JMenuItem openPDSCFile = new JMenuItem(" Open PDSC File ...");
		openPDSCFile.addActionListener(listener);
		openPDSCFile.setActionCommand("openPDSCFile");
		
		JMenuItem exportPDSCFile = new JMenuItem(" Save PDSC As");
		exportPDSCFile.addActionListener(listener);
		exportPDSCFile.setActionCommand("savePDSCAs");
		

		
		
		
		file.add(exportPDSCFile);
		file.add(openPDSCFile);
		
		window.add(tagsList);
		window.add(validator);
		window.add(DescriptionPane);
		
		this.add(file);
		this.add(window);
	 }

}
