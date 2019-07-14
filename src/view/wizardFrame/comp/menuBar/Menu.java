package view.wizardFrame.comp.menuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

import listeners.FileOptionListener;
import listeners.PDSCOptionListener;
import listeners.wizardFrameListeners.comp.MenuListener;
import view.comp.CustomColor;
import view.comp.utils.IconUtils;

public class Menu extends JMenuBar {
	private JMenu window;
	private JMenu file;
	private JMenu document;
	private MenuListener menuListener;
	private FileOptionListener fileListener;
	private PDSCOptionListener PdscOptionListener;
	 
	public Menu() {
		this.setBorder(new MatteBorder(0,0,1,0,CustomColor.DARK_GRAY));
		
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
		
		JMenuItem newPDSC = new JMenuItem(" New PDSC File");
		newPDSC.addActionListener(fileListener);
		newPDSC.setActionCommand("createNewPDSC");
		
		
		JMenuItem save = new JMenuItem("save");
		save.addActionListener(fileListener);
		save.setActionCommand("savePDSC");
		save.setIcon(IconUtils.getSaveIcon(16));
		
		JMenuItem savePDSCAs = new JMenuItem(" Save As");
		savePDSCAs.addActionListener(fileListener);
		savePDSCAs.setActionCommand("savePDSCAs");
		savePDSCAs.setIcon(IconUtils.getSaveAsIcon(16));
		
		JMenuItem showPreview = new JMenuItem("show PDSC file preview");
		showPreview.addActionListener(fileListener);
		showPreview.setActionCommand("showPDSCPreview");

		/******************************************** Document */
		
		JMenuItem validateXSD = new JMenuItem("Validate with XSD");
		validateXSD.addActionListener(PdscOptionListener);
		validateXSD.setActionCommand("validateXSD");
		validateXSD.setIcon(IconUtils.getPlayIcon(16));
		
		JMenuItem createPack = new JMenuItem(" Create Pack");
		createPack.addActionListener(PdscOptionListener);
		createPack.setActionCommand("createPack");
		createPack.setIcon(IconUtils.getPackIcon(16));
		
		
		file.add(newPDSC);
		file.add(openPDSCFile);
		file.add(new JSeparator());
		file.add(save);
		file.add(savePDSCAs);
		file.add(new JSeparator());
		file.add(showPreview);
		file.add(new JSeparator());
		file.add(createPack);
		
		window.add(tagsList);
		window.add(consoleItem);
		window.add(DescriptionPane);
		
		document.add(validateXSD);
	
		
		
		
		this.add(file);
		this.add(window);
		this.add(document);
	 }

}
