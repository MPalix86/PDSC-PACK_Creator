package view.wizardFrame.comp.menuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

import listeners.FileOptionListener;
import listeners.PDSCOptionListener;
import listeners.wizardFrameListeners.comp.MenuListener;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconUtils;

public class Menu extends JMenuBar {
	private JMenu window;
	private JMenu file;
	private JMenu document;
	private MenuListener menuListener;
	private FileOptionListener fileListener;
	private PDSCOptionListener PdscOptionListener;
	 
	public Menu() {
		this.setBorder(new MatteBorder(0,0,1,0,ColorUtils.DARK_GRAY));
		
		window = new JMenu("Window");
		file = new JMenu("File");
		document = new JMenu("Document");
		
		menuListener = new MenuListener();
		fileListener = new FileOptionListener();
		PdscOptionListener = new PDSCOptionListener();
		
		/******************************************** Window */
		
		JMenuItem consoleItem = new JMenuItem("Show Console");
		consoleItem.addActionListener(menuListener);
		consoleItem.setActionCommand("showValidator");
		consoleItem.setIcon(IconUtils.getScreeIcon(16));
		
		JMenuItem tagsList = new JMenuItem("Show Tags List");
		tagsList.addActionListener(menuListener);
		tagsList.setActionCommand("showTagListBar");
		tagsList.setIcon(IconUtils.getChildrenListIcon(16));
		
		JMenuItem DescriptionPane = new JMenuItem("Show Description Panel");
		DescriptionPane.addActionListener(menuListener);
		DescriptionPane.setActionCommand("showDescriptionPane");
		DescriptionPane.setIcon(IconUtils.FAgetInfoCircleIcon(16,null));
		
		/******************************************** File */
		
		JMenuItem openPDSCFile = new JMenuItem(" Open PDSC File ...");
		openPDSCFile.addActionListener(fileListener);
		openPDSCFile.setActionCommand("openPDSCFile");
		openPDSCFile.setIcon(IconUtils.FAgetFolderOpenIcon(16, ColorUtils.FOLDER_BROWN));
		
		JMenuItem newPDSC = new JMenuItem(" New PDSC File");
		newPDSC.addActionListener(fileListener);
		newPDSC.setActionCommand("createNewPDSC");
		newPDSC.setIcon(IconUtils.FAgetPlusIcon(20, ColorUtils.SYSTEM_GREEN_COLOR_DARK));
		
		
		JMenuItem save = new JMenuItem(" Save");
		save.addActionListener(fileListener);
		save.setActionCommand("savePDSC");
		save.setIcon(IconUtils.getSaveIcon(16));
		
		JMenuItem savePDSCAs = new JMenuItem(" Save As");
		savePDSCAs.addActionListener(fileListener);
		savePDSCAs.setActionCommand("savePDSCAs");
		savePDSCAs.setIcon(IconUtils.getSaveAsIcon(16));
		
		JMenuItem showPreview = new JMenuItem("Show PDSC file preview");
		showPreview.addActionListener(fileListener);
		showPreview.setActionCommand("showPDSCPreview");

		/******************************************** Document */
		
		JMenuItem validateXSD = new JMenuItem("Validate with XSD");
		validateXSD.addActionListener(PdscOptionListener);
		validateXSD.setActionCommand("validateXSD");
		validateXSD.setIcon(IconUtils.getPlayIcon(16));
		
		JMenuItem createPack = new JMenuItem(" Export Pack");
		createPack.addActionListener(PdscOptionListener);
		createPack.setActionCommand("createPack");
		createPack.setIcon(IconUtils.getPackIcon(16));
		
		
		file.add(newPDSC);
		file.add(openPDSCFile);
		file.add(new JSeparator());
		file.add(save);
		file.add(savePDSCAs);
		file.add(new JSeparator());
		file.add(createPack);
		
		window.add(tagsList);
		window.add(consoleItem);
		window.add(DescriptionPane);
		
		document.add(validateXSD);
		document.add(new JSeparator());
		document.add(showPreview);
	
		
		
		
		this.add(file);
		this.add(window);
		this.add(document);
	 }

}
