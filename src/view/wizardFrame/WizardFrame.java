/*
 * 
 */
package view.wizardFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import business.Session;
import listeners.wizardFrameListener.WizardFrameListener;
import view.wizardFrame.comp.TextPaneForm.TextPaneForm;
import view.wizardFrame.comp.previewPane.PreviewPaneContainer;
import view.wizardFrame.comp.tagsListBar.TagsListBarContainer;
import view.wizardFrame.comp.toolBar.ToolBarContainer;
import view.wizardFrame.comp.wizardForm.FormContainer;

/**
 * Wizard frame. Main frame of PDSC creator. 
 * 
 * @author Mirco Palese
 */

public class WizardFrame extends JFrame {
	
	
	private static JPanel contentPane;	

	private ToolBarContainer toolBarContainer;
	
	private FormContainer formContainer;
	
	private PreviewPaneContainer previewPaneContainer;
	
	private JButton updatePreviewButton;
	
	private static WizardFrameListener listener;
	
	private Session session; 
	
	private TagsListBarContainer tagsListbarContainer;
	
	private TextPaneForm textForm;
	
	
	
	
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
	
		formContainer = new FormContainer();
		toolBarContainer = new ToolBarContainer();
		previewPaneContainer = new PreviewPaneContainer();
		tagsListbarContainer = new TagsListBarContainer();  
	
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
		
		/** frame initial setup */
		setBackground(Color.WHITE);
		
		/** contentPane initial setup */
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane); 
		
		textForm.setBorder(new EmptyBorder(0,0,0,0));
		textForm.setBackground(Color.WHITE);
		textForm.setEditable(false);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(textForm, BorderLayout.CENTER);
		
		/* place all all component into contentPane */
		contentPane.add(new JScrollPane(p), BorderLayout.CENTER);
		//contentPane.add(this.previewPaneContainer, BorderLayout.CENTER);
		contentPane.add(toolBarContainer, BorderLayout.NORTH);
		contentPane.add(formContainer, BorderLayout.EAST);
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
	public FormContainer getFormContainer() {
		return formContainer;
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
	
	
	
	
	public JTextPane getTextPane() {
		return this.textPane;
	}
	
	
	

	
}
