/*
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import business.Session;
import listeners.WizardFrameListener;
import model.XmlTag;
import model.pdsc.tags.Description;
import model.pdsc.tags.License;
import model.pdsc.tags.Name;
import model.pdsc.tags.Package;
import model.pdsc.tags.Url;
import model.pdsc.tags.Vendor;
import view.Components.ModelComponents.CustomColor;
import view.Components.ModelComponents.SquareButton;
import view.Components.ModelComponents.xmlEditor.XmlTextPane;
import view.Components.wizardFrameComponents.FinalStepForm;
import view.Components.wizardFrameComponents.Form;
import view.Components.wizardFrameComponents.TagListBar;

/**
 * Wizard frame. Main frame of PDSC creator. 
 * 
 * @author Mirco Palese
 */

public class WizardFrame extends JFrame {
	
	
	/**  Content pane of JFrame. */
	private static JPanel contentPane;	
	
	/** Center panel. Panel placed in BorderLayout.CENTER */
	private static JPanel centerPanel;
	
	/** Left panel. Panel placed in BorderLayout.WEST */
	private static JPanel leftPanel; 
	
	/** Top panel. Panel placed in BorderLayout.NORTH */
	private static JPanel topPanel;
	
	/** Center panel. Panel placed in BorderLayout.EAST */
	private static JPanel rightPanel;
	
	/** JPanel's array. Contains all panels which are part of the wizard */
	private static ArrayList<JPanel> steps; 
	
	/**  index of the current displayed step. */
	private static int stepIndex = 0;	
	
	/** 
	 * Xml preview panel. Panel placed inside center panel that contains 
	 * document preview. 
	 */
	private static XmlTextPane xmlPreviewPane;	
	
	/** scroll pane that contains xmlPreviewPane*/
	private static JScrollPane XmlPreviewScrollPane;
	
	/** wizard frame listener. */
	private static WizardFrameListener listener;
	
	/** session */
	private Session session; 
	
	
	
	
	
	/**
	 * Constructor of wizard frame. Create a new instance of wizard frame that
	 * place all components, initializing wizard procedure with the mandatory 
	 * tag of PDSC document
	 * 
	 * @return void
	 */
	
	public WizardFrame() {
		
		/** creation of new wizard frame listener instance */
		listener = new WizardFrameListener(this);
		
		/** session instance recovery */
		session = Session.getInstance();
		
		/** array step initialization */
		steps = new ArrayList();
		
		/** new instance of root tag of the document */
		Package pac = new Package(true, null, 1);
		pac.setSelectedAttrArr(pac.getAttrArr());
		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
		tagArr.add(pac);
		
		/** other mandatory tag instances */
		ArrayList<XmlTag> tagArr1 = new ArrayList<XmlTag>();
		tagArr1.add(new Vendor(true, null, 1, "STMicroelectronics"));
		tagArr1.add(new Name(true, null, 1, ""));
		tagArr1.add(new Description(true, null, 1, ""));
		tagArr1.add(new License(true, null, 1, ""));
		tagArr1.add(new Url(true, null, 1,"http://sw-center.st.com/packs/x-cube/"));
		
		/** populating steps array */
		steps.add(new Form(tagArr));
		steps.add(new Form(tagArr1));
		steps.add(new FinalStepForm());
		
		/** frame initial setup */
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 598);
		
		/** contentPane initial setup */
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
			BorderLayout b = new BorderLayout(0, 0);
			b.setHgap(0);
			b.setVgap(0);
		contentPane.setLayout(b);
		setContentPane(contentPane); 
		
		/* generate and palce all component */
		placeComponent();
			
		/* setting up default close operation */
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		/* save frame in session */
		session.setWizardFrame(this);
		
	}
	
	
	
	
	
	/**
	 * Remove left panel from content pane, recreate and place it inside content 
	 * pane. Finally repaint content pane.
	 * 
	 * @return void
	 */
	
	private void updateLeftPanel() {
		contentPane.remove(leftPanel);
		generateLeftPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		repaintContentPane();
	}
	
	
	
	
	
	/**
	 * Remove center panel from content pane, recreate and place it inside content 
	 * pane. Finally repaint content pane.
	 * 
	 * @return void
	 */
	
	private void updateCenterPanel() {
		contentPane.remove(centerPanel);
		generateCenterPanel();
		contentPane.add(centerPanel, BorderLayout.WEST);
		repaintContentPane();
	}
	
	
	
	
	
	/**
	 * Remove top panel from content pane, recreate and place it inside content 
	 * pane. Finally repaint content pane.
	 * 
	 * @return void
	 */
	
	private void updateTopPanel() {
		contentPane.remove(topPanel);
		generateTopPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		repaintContentPane();
	}
	
	
	
	
	
	/**
	 * Repaint content pane.
	 *  
	 * @return void
	 */
	
	private void repaintContentPane() {
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	
	
	
	
	/**
	 * Generate left panel with all internal components.
	 *  
	 * @return void
	 */ 
	
	private void generateLeftPanel(){
		
		/** leftPanel initial setup */
		leftPanel = new JPanel();			
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBorder(new MatteBorder(0,0,0,2, new Color(52, 73, 94).brighter()));
		leftPanel.setLayout(new BorderLayout());
		
		
			/** setting up backButton */
			SquareButton backButton = new SquareButton("< Back", CustomColor.ST_BLUE, Color.WHITE);
			backButton.addActionListener(listener);
			backButton.setActionCommand("back");
	
			/** setting up nextButton */
			SquareButton nextButton = new SquareButton("Next >" , CustomColor.ST_BLUE, Color.WHITE);
			nextButton.addActionListener(listener);
			nextButton.setActionCommand("continue");
		
			/** leftPanelBottomButtonBar contains button next and back */
			JPanel leftPanelBottomButtonBar = new JPanel();
			
			/** leftPanelBottomButtonBar initial setup */
			leftPanelBottomButtonBar.setLayout(new GridLayout(0,2));
			leftPanelBottomButtonBar.setBorder(new EmptyBorder(0,0,0,0));
			leftPanelBottomButtonBar.setBackground(Color.WHITE);
			
			/** placing backBUtton and nextButton into leftPanelBottomButtonBar */
			leftPanelBottomButtonBar.add(backButton);
			leftPanelBottomButtonBar.add(nextButton);
			
			/** wizardPane contains current step panel */
			JPanel wizardPane = new JPanel();
			
			/** setting up wizardPane */
			wizardPane.setBorder(new EmptyBorder(0,0,0,0));
			wizardPane.setBackground(Color.WHITE);
			wizardPane.setLayout(new BorderLayout());
			
			/** adding current step into wizardpane */
			wizardPane.add(steps.get(stepIndex), BorderLayout.CENTER);
			
			/** wizardScrollPaneScrollBar. Scroll bar of wizardPane */
	        JScrollBar wizardScrollPaneScrollBar = new JScrollBar(JScrollBar.VERTICAL) {
	        	
	        	/**
	        	 * override isVisible to keep the scroll bar working even if
	        	 * it is hidden
	        	 */
	        	
	            @Override
	            public boolean isVisible() {
	                return true;
	            }
	        }; 
	        wizardScrollPaneScrollBar.setUnitIncrement(16);
			
			/** wizardScrollPane contains wizardPane*/
			JScrollPane wizardScrollPane = new JScrollPane(wizardPane);
			
			/** setting upwizardScrollPane */
			wizardScrollPane.setBorder(new EmptyBorder(0,0,0,0));
			wizardScrollPane.setVerticalScrollBar(wizardScrollPaneScrollBar);
			wizardScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		    wizardScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		/** adding allelements into leftPanel*/
		leftPanel.add(leftPanelBottomButtonBar,BorderLayout.SOUTH);
		leftPanel.add(wizardScrollPane,BorderLayout.CENTER);
	}
	
	
	
	
	
	/**
	 * Generate center panel with all internal components.
	 *  
	 * @return void
	 */
	
	private void generateCenterPanel() {
		
		/** centerPanel initial setup */
		centerPanel = new JPanel();
		centerPanel.setBackground(CustomColor.ST_BLUE);
		centerPanel.setSize(new Dimension(420,590));
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.setBorder(new EmptyBorder(0,0,0,0));
			
		/** setting up xmlPreviewPane */
		xmlPreviewPane = new XmlTextPane();
		xmlPreviewPane.setBorder(new EmptyBorder(0,0,0,0));
		xmlPreviewPane.setBackground(Color.WHITE);
		xmlPreviewPane.setEditable(false);
	
		/** setting up XmlPreviewScrollPane */
		XmlPreviewScrollPane = new JScrollPane(xmlPreviewPane);
		XmlPreviewScrollPane.setBackground(Color.WHITE);
		XmlPreviewScrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		/** setting up space pane */
		JPanel spacePanel = new JPanel();
		spacePanel.setBackground(Color.white);
				
		/** placement of XmlPreviewScrollPane, spacePanel  */
		centerPanel.add(XmlPreviewScrollPane, BorderLayout.CENTER);
		centerPanel.add(spacePanel, BorderLayout.NORTH);
	
	}
	
	
	
	
	
	/**
	 * Generate right panel with all internal components.
	 *  
	 * @return void
	 */
	
	private void generateRightPanel() {
		
		/** setting up rightPanel */
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBackground(Color.WHITE);
		
		/** setting up tagListPanelScrollBar Panel */
		JScrollBar tagListScrollPaneScrollBar = new JScrollBar(JScrollBar.VERTICAL) {
			
        	/**
        	 * override isVisible to keep the scroll bar working even if
        	 * it is hidden
        	 */
			
            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        tagListScrollPaneScrollBar.setUnitIncrement(16);
        
		/** setting up tagListScrollPane */
		JScrollPane tagListScrollPane = new JScrollPane(new TagListBar());
		tagListScrollPane.setVerticalScrollBar(tagListScrollPaneScrollBar);
        tagListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tagListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        tagListScrollPane.setBorder(new MatteBorder(0,2,0,0, CustomColor.ST_BLUE));
        
        /** adding tagListScrollPane into centerPanel */
        rightPanel.add(tagListScrollPane, BorderLayout.CENTER);
	}
	
	
	
	
	
	/**
	 * Generate top panel with all internal components. Top panel represent the
	 * progression bar of wizard.
	 * 
	 * @return void
	 */
	
	private void generateTopPanel() {
		
		/** setting up top panel */
		topPanel = new JPanel();
		topPanel.setBackground(UIManager.getColor(Color.WHITE));
		topPanel.setMinimumSize(new Dimension(10, 200));
		topPanel.setLayout(new GridLayout(1,steps.size()));
		//topPanel.setBorder(new MatteBorder(0,0,2,0,stBlue));
		
		/** creation and filling of progression bar */
		for (int i = 0 ; i < steps.size(); i++) {
			JPanel panel = new JPanel();
			if(i <= stepIndex) {
				panel.setBackground(CustomColor.ST_BLUE);
			}
			else {
				panel.setBackground(UIManager.getColor(Color.WHITE));
			}
			topPanel.add(panel);
		}
	
	}
	
	
	
	
	
	/**
	 * Generate all components and places them into content pane.
	 * 
	 * @return void
	 */
	
	private void placeComponent() {
		
		/* generating main panels */
		generateLeftPanel();
		generateRightPanel();
		generateCenterPanel();
		generateTopPanel();
		
		/* place all all component into contentPane */
		contentPane.add(leftPanel, BorderLayout.WEST);  
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		/* repaint ContentPane */
		repaintContentPane();
	}
	
	
	
	
	
	/**
	 * return steps array.
	 *
	 * @return return steps array.
	 */
	
	public static ArrayList<JPanel> getSteps() {
		return steps;
	}
	
	
	
	
	
	/**
	 * Add step into steps array.
	 *
	 * @param form	new form to insert in the wizard
	 * @return void
	 */
	
	public void addStep(JPanel form) {
		
		int index = steps.size();
		
		/** adding steps as the penultimate element */
		steps.add(index - 1, form);
		
		updateLeftPanel();
		updateTopPanel();
		repaintContentPane();
	}     
	
	
	
	
	/**
	 * Return array that contains all tag children of root tag inserted during wizard.
	 * every root tag child contains relative children. 
	 *
	 * @return Array that contains all tag inserted during wizard
	 */
	
	public ArrayList<XmlTag> getTagArr(){
		
		session.setWizardFrame(this);
		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
		XmlTag tag = new XmlTag();
		
		for (int i = 0; i < steps.size() - 1; i++) {   					
			Form form = (Form) steps.get(i);
			for (int j = 0; j < form.getTagArr().size(); j++) {
				tag = form.getTagArr().get(j);
				tagArr.add(tag);
			}
		}
		return tagArr;
	}
	
	
	
	
	
	/**
	 * Go to the next step of the wizard.
	 *
	 * @return void
	 */
	
	public void next() {
		if(stepIndex < steps.size()-1) { 
			stepIndex += 1;
		}
		updateLeftPanel();
		updateTopPanel();
		repaintContentPane();
	}
	
	
	
	
	/**
	 * Go to the previous step of the wizard.
	 *
	 */
	
	public void back() {
		if(stepIndex > 0) { 
			stepIndex -= 1;
		}
		updateLeftPanel();
		updateTopPanel();
		repaintContentPane();
	}
	
	
	
	/**
	 * update xml preview text pane.
	 *
	 * @param preview String with new content of updateXmlPreviewPane
	 */
	
	public void updateXmlPreviewPane(String preview) {
		xmlPreviewPane.setText(preview);
		xmlPreviewPane.revalidate();
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
	
	
}
