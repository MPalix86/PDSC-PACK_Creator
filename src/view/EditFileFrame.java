//package view;
//
//import java.awt.GraphicsDevice;
//import java.awt.GridLayout;
//import java.awt.MouseInfo;
//import java.awt.Rectangle;
//import java.io.File;
//
//import javax.swing.JEditorPane;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTabbedPane;
//import javax.swing.UIManager;
//import javax.swing.plaf.metal.MetalLookAndFeel;
//import javax.swing.plaf.metal.OceanTheme;
//
//import business.Session;
//import jsyntaxpane.DefaultSyntaxKit;
//import view.Components.PaneTitleTabComponent;
//
//public class EditFileFrame extends JFrame {
//	
//	Session session = Session.getInstance();
//	private TopMenuBarListener mainFramelistener = new TopMenuBarListener(this);
//	private JTabbedPane tabbedPane = new JTabbedPane();
//	private JMenuBar menuBar;
//	private JPanel centerPanel; 
//	
//	public EditFileFrame() {
//		this.setTitle("PDSC Formatter");
//		Session session = Session.getInstance();
//	}
//	
//	//--------------------------------------------------------------------------placeComponents()
//	public void placeComponents(){
//		try { 
//	        // Set metl look and feel 
//	        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); 
//
//	        // Set theme to ocean 
//	        MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
//	    } 
//	    catch (Exception e) {System.out.println("look and fell exception");}
//		this.centerPanel = new JPanel(new GridLayout(1,1));
//			this.centerPanel.add(this.tabbedPane);
//		
//		JMenuBar menuBar = createMenubar();
//	    this.setJMenuBar(menuBar);
//	    this.add(this.centerPanel);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setVisible(true);
//		this.setSize(800, 500);
//		centerWindow();
//		
//	}
//	
//	//--------------------------------------------------------------------------showNewFileFrame()
//	public File showNewFileFrame() {
//		JFileChooser fileChooser = new JFileChooser();
//			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		if(Session.getCurrentWorkingFile() != null) {fileChooser.setCurrentDirectory(Session.getCurrentWorkingFile());} 	
//		int val = fileChooser.showSaveDialog(this);
//		if(val == JFileChooser.APPROVE_OPTION) {
//			File destinationPath = fileChooser.getSelectedFile();
//			return destinationPath;
//		}
//		else if(val == JFileChooser.ERROR_OPTION) {
//			JOptionPane.showMessageDialog(this, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}
//		else if(val == JFileChooser.CANCEL_OPTION) {}
//		return null;
//	}
//	
//	//--------------------------------------------------------------------------showOpenFileFrame()
//	public File showOpenFileFrame() {
//		JFileChooser fileChooser = new JFileChooser();
//			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		if(Session.getCurrentWorkingFile() != null) {fileChooser.setCurrentDirectory(Session.getCurrentWorkingFile());} 	
//		int val = fileChooser.showOpenDialog(this);
//		if(val == JFileChooser.APPROVE_OPTION) {
//			File file = fileChooser.getSelectedFile();
//			return file;
//		}
//		else if(val == JFileChooser.ERROR_OPTION) {
//			JOptionPane.showMessageDialog(this, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
//			return null;
//		}
//		else if(val == JFileChooser.CANCEL_OPTION) {}
//		return null;
//	}
//	
//	
//	//--------------------------------------------------------------------------showMessageDialog()
//	public void showMessageDialog(String message, String title, int option) {
//		JOptionPane.showMessageDialog(this, message, title, option);
//	}
//	
//	//--------------------------------------------------------------------------centerWindow()
//	private void centerWindow() {
//	    GraphicsDevice screen = MouseInfo.getPointerInfo().getDevice();
//	    Rectangle r = screen.getDefaultConfiguration().getBounds();
//	    int x = (r.width - this.getWidth()) / 2 + r.x;
//	    int y = (r.height - this.getHeight()) / 2 + r.y;
//	    this.setLocation(x, y);
//	}
//	
//
//	private JMenuBar createMenubar() {
//		
//		this.menuBar = new JMenuBar();
//			JMenu fileMenu = new JMenu("File");
//				JMenu newFileMenu = new JMenu("New");
//					JMenuItem xmlFile = new JMenuItem("XML file");
//						xmlFile.setActionCommand("createXmlFile");
//						xmlFile.addActionListener(mainFramelistener);
//					JMenuItem pdscFile = new JMenuItem("PDSC file");
//						pdscFile.setActionCommand("createPdscFile");
//						pdscFile.addActionListener(mainFramelistener);
//				newFileMenu.add(xmlFile); 
//				newFileMenu.add(pdscFile);
//				JMenuItem openFile = new JMenuItem("Open File...");
//				openFile.setActionCommand("openFile");
//				openFile.addActionListener(mainFramelistener);
//			fileMenu.add(newFileMenu); 
//			fileMenu.add(openFile); 
//		menuBar.add(fileMenu);
//		return menuBar; 
//		
//	}
//	
//	//--------------------------------------------------------------------------addTab()
//	public void addTab(File file, String text) {
//		
//		// creating editor pane
//		DefaultSyntaxKit.initKit();
//		JPanel panel = new JPanel(new GridLayout(1,1));
//        final JEditorPane codeEditor = new JEditorPane();
//        JScrollPane scrPane = new JScrollPane(codeEditor);
//        codeEditor.setContentType("text/bash");
//		if (text != null) {
//			codeEditor.setText(text);
//		}
//		panel.add(scrPane);
//		
//		// adding tab in tabbedPane
//		this.tabbedPane.addTab(file.getAbsolutePath(), null, panel, file.getAbsolutePath()); 
//		int index = this.tabbedPane.indexOfTab(file.getAbsolutePath());
//		
//		// adding title with close button in tab
//		PaneTitleTabComponent titleTabPane = new PaneTitleTabComponent(tabbedPane , file);
//		tabbedPane.setTabComponentAt(index, titleTabPane);
//	}
//	
//
//	
//	//--------------------------------------------------------------------------removeTab()
//	public void removeTab() {
//		//int index = this.tabbedPane.indexOfTab(title);
//	}
//
//	
//
//}
