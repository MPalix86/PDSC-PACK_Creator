/*
 * 
 */
package view.wizardFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import business.Session;
import listeners.wizardFrameListeners.WizardFrameListener;
import listeners.wizardFrameListeners.comp.TabContainerListener;
import model.pdsc.Pack;
import view.comp.IconUtils;
import view.wizardFrame.comp.TabContainer;
import view.wizardFrame.comp.descriptionPane.DescriptionPaneContainer;
import view.wizardFrame.comp.menuBar.Menu;
import view.wizardFrame.comp.overviewPane.OverviewPane;
import view.wizardFrame.comp.tagsListBar.TagsListBarContainer;
import view.wizardFrame.comp.toolBar.ToolBarContainer;
import view.wizardFrame.comp.validatorPane.ValidatorContainer;
import view.wizardFrame.comp.xmlForm.XmlForm;
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
	
	private TabContainer tabContainerCenter;
	
	private TabContainer tabContainerSouth;
	
	private ValidatorContainer validatorContainer;
	
	private JSplitPane splitPane;
	
	private DescriptionPaneContainer descriptionPaneContainer;
	
	
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
		
		toolBarContainer = new ToolBarContainer();
		tagsListbarContainer = new TagsListBarContainer();
		tabContainerCenter = new TabContainer();
		tabContainerSouth = new TabContainer();
		validatorContainer = new ValidatorContainer();
		descriptionPaneContainer =  new DescriptionPaneContainer();
		
		tabContainerCenter.addChangeListener(new TabContainerListener());
		tabContainerSouth.addChangeListener(new TabContainerListener());
	
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
			tabContainerCenter.addTab(" Overview   ",IconUtils.FAgetPlusCircleIcon(20,null),new OverviewPane());
		}

		
		
		tabContainerSouth.addTab(" XSD Validator   ", IconUtils.getScreeIcon(20), this.validatorContainer);
		tabContainerSouth.addTab(" Description   ", IconUtils.FAgetInfoCircleIcon(20,null), this.descriptionPaneContainer);
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tabContainerCenter, tabContainerSouth);	
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
		
		if(tabContainerSouth == null) tabContainerSouth = new TabContainer();
		if(validatorContainer == null) 	validatorContainer = new ValidatorContainer();
		
		/** recovering all component from splitpane */
		Component[] compList = splitPane.getComponents();
		for(Component component : compList) {
			
			/** if component  == tabContainerSouth */
			if(component == tabContainerSouth) {
				tabContainerFound = true;
				
				/** search between all tabs */
				for (int i = 0; i < tabContainerSouth.getTabCount(); i++) {
					Component c = tabContainerSouth.getTabComponentAt(i);
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
			tabContainerSouth.addTab(" XSD Validator   ", IconUtils.getScreeIcon(20), this.validatorContainer);
			splitPane.add(tabContainerSouth);
			splitPane.repaint();
		}
		
		
		if(!validatorFound && tabContainerFound) {
			tabContainerSouth.addTab(" XSD Validator   ", IconUtils.getScreeIcon(20), this.validatorContainer);
			splitPane.repaint();
		}
		
	}
	
	
	
	
	/**
	 * add Description frame in southTabContainer
	 */
	public void addDescriptionPane() {
		boolean descriptionPaneFound = false;
		boolean tabContainerFound = false;
		
		if(tabContainerSouth == null) tabContainerSouth = new TabContainer();
		if(descriptionPaneContainer == null) 	descriptionPaneContainer = new DescriptionPaneContainer();
		
		/** recovering all component from splitpane */
		Component[] compList = splitPane.getComponents();
		for(Component component : compList) {
			
			/** if component  == tabContainerSouth */
			if(component == tabContainerSouth) {
				tabContainerFound = true;
				
				/** search between all tabs */
				for (int i = 0; i < tabContainerSouth.getTabCount(); i++) {
					Component c = tabContainerSouth.getTabComponentAt(i);
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
			tabContainerSouth.addTab(" Description   ", IconUtils.FAgetInfoCircleIcon(20,null), this.descriptionPaneContainer);
			splitPane.add(tabContainerSouth);
			splitPane.repaint();
		}
		
		
		if(!descriptionPaneFound && tabContainerFound) {
			tabContainerSouth.addTab(" Description   ", IconUtils.FAgetInfoCircleIcon(20,null), this.descriptionPaneContainer);
			splitPane.repaint();
		}
		
	}
	
	
	
	
	
	public void addXmlFormTab(XmlForm form) {
		XmlFormContainer formContainer = new XmlFormContainer(form);
		tabContainerCenter.addTab("  New PDSC  ", IconUtils.FAgetAlignLeftIcon(20,null) , formContainer);
		splitPane.repaint();
	}
	
	
	
	

	
	
	public void setValidatorText(String text) {
		if(validatorContainer != null) {
			validatorContainer.getValidator().setText(text);
			validatorContainer.getValidator().repaint();
			validatorContainer.getValidator().revalidate();
		}
	}
	
	
	
	
	public void setDescriptionText(String text) {
		if(descriptionPaneContainer != null) {
			descriptionPaneContainer.getDescriptionPane().setText(text);
			descriptionPaneContainer.getDescriptionPane().repaint();
			descriptionPaneContainer.getDescriptionPane().revalidate();
		}
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
	
	


	

	
}
