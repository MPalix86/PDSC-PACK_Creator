/*
 * 
 */
package view.wizardFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Session;
import listeners.wizardFrameListeners.WizardFrameListener;
import model.XmlTag;
import model.pdsc.Pack;
import view.wizardFrame.comp.TabContainer;
import view.wizardFrame.comp.previewPane.PreviewPaneContainer;
import view.wizardFrame.comp.tagsListBar.TagsListBarContainer;
import view.wizardFrame.comp.toolBar.ToolBarContainer;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.XmlFormContainer;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

/**
 * Wizard frame. Main frame of PDSC creator. 
 * 
 * @author Mirco Palese
 */

public class WizardFrame extends JFrame {

	private static JPanel contentPane;	

	private ToolBarContainer toolBarContainer;
	
	private PreviewPaneContainer previewPaneContainer;
	
	private JButton updatePreviewButton;
	
	private static WizardFrameListener listener;
	
	private Session session; 
	
	private TagsListBarContainer tagsListbarContainer;
	
	private XmlFormContainer formPanelContainer;
	
	private TabContainer tabContainer;
	
	private Pack pack;
	
	
	
	/**
	 * Constructor of wizard frame. Create a new instance of wizard frame that
	 * place all components, initializing wizard procedure with the mandatory 
	 * tag of PDSC document
	 * 
	 * @return void
	 */
	
	public WizardFrame() {
		
		/* setting up default close operation */
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		/** creation of new wizard frame listener instance */
		listener = new WizardFrameListener();
		
		
		/** session instance recovery */
		session = Session.getInstance();
		
		pack = new Pack();
		
		formPanelContainer = new XmlFormContainer();
		toolBarContainer = new ToolBarContainer();
		previewPaneContainer = new PreviewPaneContainer();
		tagsListbarContainer = new TagsListBarContainer();
		tabContainer = new TabContainer();
	
		/* generate and place all component */
		placeComponent();
			
		/* save frame in session */
		session.setWizardFrame(this);
		
		updatePreview();
		
		this.setVisible(true);
	}
	
	
	/**
	 * Generate all components and places them into content pane.
	 * 
	 * @return void
	 */
	
	private void placeComponent() {
		
		updatePreviewButton = new JButton();
		updatePreviewButton.addActionListener(listener);
		updatePreviewButton.setActionCommand("updatePreview");
		updatePreviewButton.setVisible(false);
		
		tabContainer.addTab("Form",new ImageIcon(getClass().getResource("/icons/form64.png")),formPanelContainer);
		tabContainer.addTab("PDSC Preview",new ImageIcon(getClass().getResource("/icons/document64.png")),previewPaneContainer);


		
		/** frame initial setup */
		setBackground(Color.WHITE);
		
		/** contentPane initial setup */
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane); 
		
		/* place all all component into contentPane */
//		contentPane.add(textFormContainer, BorderLayout.CENTER);
		contentPane.add(tabContainer, BorderLayout.CENTER);
		//contentPane.add(this.previewPaneContainer, BorderLayout.CENTER);
		contentPane.add(toolBarContainer, BorderLayout.NORTH);
		//contentPane.add(formContainer, BorderLayout.EAST);
		contentPane.add(tagsListbarContainer, BorderLayout.WEST);
		
		/* repaint ContentPane */
		contentPane.repaint();
		contentPane.revalidate();
	}
	


	/**
	 * Update xmlpreviewPane
	 * 
	 * @return void
	 */
	public void updatePreview() {
		this.updatePreviewButton.doClick();
	}
	
	
	
	
	/**
	 * show - hide tag list bar
	 * 
	 * @return void
	 */
	public void ShowHideTagListBar() {
		if (tagsListbarContainer.isVisible()) {
			tagsListbarContainer.setVisible(false);
			toolBarContainer.getToolBar().setShowIconShowHideTagsListButton();
			
		}
		else{
			tagsListbarContainer.setVisible(true);
			toolBarContainer.getToolBar().setHideIconShowHideTagsListButton();
		}
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	
	
	
	/**
	 * Show new file creation frame.
	 *
	 * @return chosen destination path 
	 */
	
	public File showChooseDirectoryFrame() {
		
		/** setting up fileChooser */
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Select destination folder");
		
		/** handling user's choice */
		int val = fileChooser.showOpenDialog(this);
		if(val == JFileChooser.APPROVE_OPTION) {
			File destinationPath = fileChooser.getSelectedFile();
			return destinationPath;
		}
		else if(val == JFileChooser.ERROR_OPTION) {  
			JOptionPane.showMessageDialog(this, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else if(val == JFileChooser.CANCEL_OPTION) {}
		return null;
	}
	
	
	
	
	
	/**
	 * Show browse file frame.
	 *
	 * @return chosen path 
	 */
	
	public File showChooseFileFrame() {
		
		/** setting up fileChooser */
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(session.getLastDirectoryOpenPath() != null) {
			fileChooser.setCurrentDirectory(session.getLastDirectoryOpenPath());
		}
		
		/** handling user's choice */
		int val = fileChooser.showOpenDialog(this);
		if(val == JFileChooser.APPROVE_OPTION) {
			File destinationPath = fileChooser.getSelectedFile();
			session.setLastDirectoryOpenPath(destinationPath);
			return destinationPath;
		}
		else if(val == JFileChooser.ERROR_OPTION) {  
			JOptionPane.showMessageDialog(this, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else if(val == JFileChooser.CANCEL_OPTION) {}
		return null;
	}
	
	
	
	

	/**
	 * Show option pane warning message with "yes-no" option
	 * 
	 * @param message	message to show inside option pane
	 * @return true if yes was selected, false otherwise
	 */
	
	public boolean yesNoWarningMessage(String message) {
		ImageIcon icon = new ImageIcon (getClass().getClassLoader().getResource("icons/warning48.png"));
		Object[] options = { "YES", "NO" };
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
		if(value == 0) return true;
		else return false;
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public void warningMessage(String message) {
		ImageIcon icon = new ImageIcon (getClass().getClassLoader().getResource("icons/warning48.png"));
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
	}
	
	
	
	

	public String showInputDialog(String title, String fieldLabelText) {
        ImageIcon icon = new ImageIcon("src/images/turtle32.png");
        String n = (String)JOptionPane.showInputDialog(null, fieldLabelText, title, JOptionPane.QUESTION_MESSAGE, icon, null, null);
        return n;
	}



	/**
	 * @return the formContainer
	 */
	public XmlFormContainer getFormPanelContainer() {
		return formPanelContainer;
	}

	
	
	/**
	 * @return the previewPaneContainer
	 */
	public PreviewPaneContainer getPreviewPaneContainer() {
		return previewPaneContainer;
	}



	

	
	/**
	 * @return the listener
	 */
	public static WizardFrameListener getListener() {
		return listener;
	}
	
	
	
	/**
	 * @return the toolBarContainer
	 */
	public ToolBarContainer getToolBarContainer() {
		return toolBarContainer;
	}
	
	
	/**
	 * @return the pack
	 */
	public Pack getPack() {
		return pack;
	}
	
	
	
	public XmlForm getFormPanel() {
		return this.formPanelContainer.getFormPanel();
	}
	
	
	public TagRow getTagRow(XmlTag tag) {
		return  this.getFormPanelContainer().getFormPanel().getTagOpenRowHashMap().get(tag);
	}
	

	
}
