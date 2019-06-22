package view.wizardFrame.comp.menuBar;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
	private JMenu window;
	private JMenu file;
	 
	public Menu() {
		window = new JMenu("Window");
		file = new JMenu("File");
		 
		JMenuItem validator = new JMenuItem("Validator");
		JMenuItem tagsLis = new JMenuItem("Tags List");
		JMenuItem exportXmlFile = new JMenuItem("Export Xml File");
		ImageIcon docIcon = new ImageIcon(getClass().getClassLoader().getResource("icons/document40.png"));
		exportXmlFile.setIcon(docIcon);
		JMenuItem  exportPDSCFIle = new JMenuItem("Export Xml File");
		
		
	 }

}
