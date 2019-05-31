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
import view.wizardFrame.comp.TabContainer;
import view.wizardFrame.comp.previewPane.PreviewPaneContainer;
import view.wizardFrame.comp.tagsListBar.TagsListBarContainer;
import view.wizardFrame.comp.toolBar.ToolBarContainer;
import view.wizardFrame.comp.xmlForm.XmlFormContainer;

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
	
	public File showNewFileFrame() {
		
		/** setting up fileChooser */
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		/** handling user's choice */
		int val = fileChooser.showSaveDialog(this);
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
	

	
}
