/*
 * 
 */
package view.wizardFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import business.Session;
import listeners.wizardFrameListeners.WizardFrameListener;
import listeners.wizardFrameListeners.comp.ClosableTabbedPaneListener;
import model.PDSCDocument;
import view.comp.IconUtils;
import view.comp.customTabbedPane.ClosableTabbedPane;
import view.wizardFrame.comp.descriptionPane.DescriptionPaneContainer;
import view.wizardFrame.comp.menuBar.Menu;
import view.wizardFrame.comp.overviewPane.OverviewPane;
import view.wizardFrame.comp.previewPane.PreviewPaneContainer;
import view.wizardFrame.comp.tagsListBar.TagsListBarContainer;
import view.wizardFrame.comp.toolBar.ToolBarContainer;
import view.wizardFrame.comp.validatorPane.ValidatorContainer;
import view.wizardFrame.comp.xmlForm.XmlFormContainer;

/**
 * Wizard frame. Main frame of PDSC creator. 
 * 
 * @author Mirco Palese
 */

public class WizardFrame extends JFrame {

	private static JPanel contentPane;	

	private ToolBarContainer toolBarContainer;
	
	private JButton updatePreviewButton;
	
	private static WizardFrameListener listener;
	
	private Session session; 
	
	private TagsListBarContainer tagsListbarContainer;
	
	private ClosableTabbedPane closableTabbedPaneCenter;
	
	private ClosableTabbedPane closableTabbedPaneSouth;
	
	private ValidatorContainer validatorContainer;
	
	private JSplitPane splitPane;
	
	private DescriptionPaneContainer descriptionPaneContainer;
	
	private ClosableTabbedPaneListener closableTabbedPaneListener;
	
	
	
	/**
	 * Constructor of wizard frame. Create a new instance of wizard frame that
	 * place all components, initializing wizard procedure with the mandatory 
	 * tag of PDSC document
	 * 
	 * @return void
	 */
	
	public WizardFrame() {
		
		/** session instance recovery */
		session = Session.getInstance();
		
		/* setting up default close operation */
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		/** creation of new wizard frame listener instance */
		listener = new WizardFrameListener();
		
		toolBarContainer = new ToolBarContainer();
		tagsListbarContainer = new TagsListBarContainer();
		closableTabbedPaneCenter = new ClosableTabbedPane();
		closableTabbedPaneSouth = new ClosableTabbedPane();
		validatorContainer = new ValidatorContainer();
		descriptionPaneContainer =  new DescriptionPaneContainer();
		closableTabbedPaneListener = new ClosableTabbedPaneListener();
		
		closableTabbedPaneCenter.addChangeListener(closableTabbedPaneListener);
		closableTabbedPaneCenter.addTabCloseListener(closableTabbedPaneListener);
		
		closableTabbedPaneSouth.addChangeListener(closableTabbedPaneListener);
		closableTabbedPaneSouth.addTabCloseListener(closableTabbedPaneListener);
	
		/* generate and place all component */
		placeComponent();
			
		/* save frame in session */
		session.setWizardFrame(this);
		
		this.setVisible(true);
	}
	
	
	/**
	 * Generate all components and places them into content pane.
	 * 
	 * @return void
	 */
	
	private void placeComponent() {
		
	
		setBackground(Color.WHITE);
		
		/** hidden button to update preview */
		updatePreviewButton = new JButton();
		updatePreviewButton.addActionListener(listener);
		updatePreviewButton.setActionCommand("updatePreview");
		updatePreviewButton.setVisible(false);
		
		
	
		
		/** contentPane initial setup */
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane); 

		
		if (session.getSelectedForm() == null ) {
			closableTabbedPaneCenter.addTab(" Overview   ",IconUtils.FAgetPlusCircleIcon(20,null),new OverviewPane());
		}

		
		
		closableTabbedPaneSouth.addTab(" XSD Validator   ", IconUtils.getScreeIcon(20), this.validatorContainer);
		closableTabbedPaneSouth.addTab(" Description   ", IconUtils.FAgetInfoCircleIcon(20,null), this.descriptionPaneContainer);
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,closableTabbedPaneCenter, closableTabbedPaneSouth);	
		splitPane.setResizeWeight(0.8); 
				
		
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel northPanel = new JPanel(new BorderLayout());

		contentPane.add(northPanel, BorderLayout.NORTH);

		contentPane.add(tagsListbarContainer, BorderLayout.WEST);
		
		northPanel.add(new Menu(),BorderLayout.NORTH);
		northPanel.add(toolBarContainer,BorderLayout.SOUTH);
		
		/* repaint ContentPane */
		contentPane.repaint();
		contentPane.revalidate();
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
	 * show tag list bar
	 * 
	 * @return void
	 */
	
	public void ShowTagListBar() {
		if (!tagsListbarContainer.isVisible()) {
			tagsListbarContainer.setVisible(true);
		}
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	
	
	
	/**
	 * add validator frame in southTabContainer
	 */
	public void addValidatorPane() {
		boolean validatorFound = false;
		boolean tabContainerFound = false;
		
		if(closableTabbedPaneSouth == null) closableTabbedPaneSouth = new ClosableTabbedPane();
		if(validatorContainer == null) 	validatorContainer = new ValidatorContainer();
		
		/** recovering all component from splitpane */
		Component[] compList = splitPane.getComponents();
		for(Component component : compList) {
			
			/** if component  == tabContainerSouth */
			if(component == closableTabbedPaneSouth) {
				tabContainerFound = true;
				
				/** search between all tabs */
				for (int i = 0; i < closableTabbedPaneSouth.getTabCount(); i++) {
					Component c = closableTabbedPaneSouth.getTabComponentAt(i);
					if(c != null) {
						
						/** if tab == validatorContainer */
						if(c.equals(validatorContainer)) {
							
							validatorFound =true;
							break;
						}
					}
					
				}
				break;
			}
		}
		
		
		if(!validatorFound && !tabContainerFound) {
			closableTabbedPaneSouth.addTab(" XSD Validator   ", IconUtils.getScreeIcon(20), this.validatorContainer);
			splitPane.add(closableTabbedPaneSouth);
			splitPane.repaint();
		}
		
		
		if(!validatorFound && tabContainerFound) {
			closableTabbedPaneSouth.addTab(" XSD Validator   ", IconUtils.getScreeIcon(20), this.validatorContainer);
			splitPane.repaint();
		}
		
	}
	
	
	
	
	/**
	 * add Description frame in southTabContainer
	 */
	public void addDescriptionPane() {
		boolean descriptionPaneFound = false;
		boolean tabContainerFound = false;
		
		if(closableTabbedPaneSouth == null) closableTabbedPaneSouth = new ClosableTabbedPane();
		if(descriptionPaneContainer == null) 	descriptionPaneContainer = new DescriptionPaneContainer();
		
		/** recovering all component from splitpane */
		Component[] compList = splitPane.getComponents();
		for(Component component : compList) {
			
			/** if component  == tabContainerSouth */
			if(component == closableTabbedPaneSouth) {
				tabContainerFound = true;
				
				/** search between all tabs */
				for (int i = 0; i < closableTabbedPaneSouth.getTabCount(); i++) {
					Component c = closableTabbedPaneSouth.getTabComponentAt(i);
					if(c != null) {
						
						/** if tab == validatorContainer */
						if(c.equals(descriptionPaneContainer)) {
							
							descriptionPaneFound =true;
							break;
						}
					}
					
				}
				break;
			}
		}
		
		if(!descriptionPaneFound && !tabContainerFound) {
			closableTabbedPaneSouth.addTab(" Description   ", IconUtils.FAgetInfoCircleIcon(20,null), this.descriptionPaneContainer);
			splitPane.add(closableTabbedPaneSouth);
			splitPane.repaint();
		}
		
		
		if(!descriptionPaneFound && tabContainerFound) {
			closableTabbedPaneSouth.addTab(" Description   ", IconUtils.FAgetInfoCircleIcon(20,null), this.descriptionPaneContainer);
			splitPane.repaint();
		}
		
	}
	
	
	
	
	
	public void addXmlFormTab(PDSCDocument doc) {
		
		XmlFormContainer formContainer = new XmlFormContainer(doc.getForm());
		
		/** if is new file */
		if(doc.getSourcePath() == null) {
			closableTabbedPaneCenter.addTab(" Untiteled   ", IconUtils.FAgetAlignLeftIcon(20,null) , formContainer , "No path" );
		}
		else {
			
			
			/**if title is already present */
			if (tabTitleIsAlreadyPresent(doc.getSourcePath().getName())) {
				/** insert complete path like title */
				closableTabbedPaneCenter.addTab(doc.getSourcePath().getAbsolutePath() + "   ", IconUtils.FAgetAlignLeftIcon(20,null) , formContainer , doc.getSourcePath().getAbsolutePath());
			}
			else {
				/** insert filename like name */
				closableTabbedPaneCenter.addTab(doc.getSourcePath().getName() + "   ", IconUtils.FAgetAlignLeftIcon(20,null) , formContainer , doc.getSourcePath().getAbsolutePath());
			}
		}
		
		closableTabbedPaneCenter.setSelectedComponent(formContainer);
		splitPane.repaint();
	}
	
	
	
	
	
	public void updateTabTitle(PDSCDocument doc) {
		/** recovering tab index */
		int i = closableTabbedPaneCenter.indexOfComponent(doc.getForm().getParent().getParent().getParent());
		
		if (tabTitleIsAlreadyPresent(doc.getSourcePath().getName())) {
			/** insert complete path like title */
			closableTabbedPaneCenter.setTitleAt(i, doc.getSourcePath().getAbsolutePath() + "   ");
		}
		else {
			/** insert filename like name */
			closableTabbedPaneCenter.setTitleAt(i, doc.getSourcePath().getName() + "   ");
		}
	}
	
	
	
	
	
	private boolean tabTitleIsAlreadyPresent(String title) {
		boolean titleIsAlreadyPresent  = false;
		for(int j = 0; j < closableTabbedPaneCenter.getTabCount(); j ++) {
			if ( title.equals(closableTabbedPaneCenter.getTabTitleAt(j) )) {
				titleIsAlreadyPresent  = true;
			}
		}
		return titleIsAlreadyPresent;
	}
	
	

	
	
	public void setValidatorText(String text) {
		if(validatorContainer != null) {
			validatorContainer.getValidator().setText(text);
			validatorContainer.getValidator().repaint();
			validatorContainer.getValidator().revalidate();
		}
	}
	
	
	
	public void insertValidatorComnponent(JComponent comp) {
		validatorContainer.getValidator().insertComp(comp);
		validatorContainer.getValidator().repaint();
		validatorContainer.getValidator().revalidate();
	}
	
	
	
	
	public void setDescriptionText(String text) {
		if(descriptionPaneContainer != null) {
			descriptionPaneContainer.getDescriptionPane().setText(text);
			descriptionPaneContainer.getDescriptionPane().repaint();
			descriptionPaneContainer.getDescriptionPane().revalidate();
		}
	}
	
	
	public void showPreview(String text) {
		PreviewPaneContainer previewPane = new PreviewPaneContainer();
		previewPane.getPreviewPane().setText(text);
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
