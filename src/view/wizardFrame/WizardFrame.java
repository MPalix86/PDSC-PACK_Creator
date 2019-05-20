/*
 * 
 */
package view.wizardFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import business.Session;
import listeners.wizardFrameListener.WizardFrameListener;
import model.XmlTag;
import model.pdsc.tags.Description;
import model.pdsc.tags.License;
import model.pdsc.tags.Name;
import model.pdsc.tags.Package;
import model.pdsc.tags.Url;
import model.pdsc.tags.Vendor;
import view.comp.CustomColor;
import view.comp.SquareButton;
import view.wizardFrame.comp.munuBar.MenuBar;
import view.wizardFrame.comp.wizardForm.FinalStepForm;
import view.wizardFrame.comp.wizardForm.Form;
import view.wizardFrame.comp.xmlEditor.TextLineNumber;
import view.wizardFrame.comp.xmlEditor.XmlTextPane;

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
	
	private MenuBar menuBar;
	
	private JButton updatePreviewButton;
	
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
	private static JScrollPane xmlPreviewScrollPane;
	
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
		
		/** initializing updatePreviewButton */
		updatePreviewButton = new JButton();
		updatePreviewButton.addActionListener(listener);
		updatePreviewButton.setActionCommand("updatePreviewPane");
		
		/* generate and palce all component */
		placeComponent();
			
		/* setting up default close operation */
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		/* save frame in session */
		session.setWizardFrame(this);
		
		updateXmlPreview();
		
	}
	
	
	
	
	
	/**
	 * Remove left panel from content pane, recreate and place it inside content 
	 * pane. Finally repaint content pane.
	 * 
	 * @return void
	 */
	
	private void updateRightPanel() {
		contentPane.remove(rightPanel);
		generateRightPanel();
		contentPane.add(rightPanel, BorderLayout.EAST);
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
		

	}
	
	
	
	
	
	/**
	 * Generate center panel with all internal components.
	 *  
	 * @return void
	 */
	
	private void generateCenterPanel() {
		
		/** centerPanel initial setup */
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setSize(new Dimension(420,590));
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.setBorder(new EmptyBorder(0,0,0,0));
			
		/** setting up xmlPreviewPane */
		xmlPreviewPane = new XmlTextPane();
		xmlPreviewPane.setBorder(new EmptyBorder(0,0,0,0));
		xmlPreviewPane.setBackground(Color.WHITE);
		xmlPreviewPane.setEditable(false);
		
		/** adding line numbers to xmlPreviewPane */
		TextLineNumber tln = new TextLineNumber(xmlPreviewPane);
		tln.setBackground(Color.WHITE);
		tln.setCurrentLineForeground(Color.GRAY);
		tln.setForeground(Color.GRAY);
		
		/** setting up xmlPreviewScrollPane */
		xmlPreviewScrollPane = new JScrollPane(xmlPreviewPane);
		xmlPreviewScrollPane.setBackground(Color.WHITE);
		xmlPreviewScrollPane.setBorder(new EmptyBorder(10,0,0,0));
			
		/** adding TextLineNumber inside xmlPreviewPane */
		xmlPreviewScrollPane.setRowHeaderView(tln);
		
		/** placement of xmlPreviewScrollPane, spacePanel  */
		centerPanel.add(xmlPreviewScrollPane, BorderLayout.CENTER);
		

	}
	
	
	
	
	
	/**
	 * Generate right panel with all internal components.
	 *  
	 * @return void
	 */
	
	private void generateRightPanel() {
		
		/** leftPanel initial setup */
		rightPanel = new JPanel();			
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setBorder(new MatteBorder(0,1,0,0, CustomColor.LIGHT_GRAY ));
		rightPanel.setLayout(new BorderLayout());
		
		
			/** setting up backButton */
			SquareButton backButton = new SquareButton("< Back");
			backButton.addActionListener(listener);
			backButton.setActionCommand("back");
	
			/** setting up nextButton */
			SquareButton nextButton = new SquareButton("Next >");
			nextButton.addActionListener(listener);
			nextButton.setActionCommand("next");
		
			/** leftPanelBottomButtonBar contains button next and back */
			JPanel rightPanelBottomButtonBar = new JPanel();
			
			/** leftPanelBottomButtonBar initial setup */
			rightPanelBottomButtonBar.setLayout(new GridLayout(0,2));
			rightPanelBottomButtonBar.setBorder(new MatteBorder(1,0,0,0, CustomColor.LIGHT_GRAY));
			rightPanelBottomButtonBar.setBackground(Color.WHITE);
			
			/** placing backBUtton and nextButton into leftPanelBottomButtonBar */
			rightPanelBottomButtonBar.add(backButton);
			rightPanelBottomButtonBar.add(nextButton);
			
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
		rightPanel.add(rightPanelBottomButtonBar,BorderLayout.SOUTH);
		rightPanel.add(wizardScrollPane,BorderLayout.CENTER);
		
	}
	
	
	
	
	
	/**
	 * Generate top panel with all internal components. Top panel represent the
	 * progression bar of wizard.
	 * 
	 * @return void
	 */
	
	private void generateTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(224,224,224));
		menuBar = new MenuBar();
		topPanel.add(menuBar,BorderLayout.CENTER);
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
		
		updateRightPanel();
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
		updateRightPanel();
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
		updateRightPanel();
		updateTopPanel();
		repaintContentPane();
	}
	
	
	
	/**
	 * set text inside text pane.
	 *
	 * @param preview String with new content of updateXmlPreviewPane
	 */
	
	public void setXmlPreview(String preview) {
		xmlPreviewPane.setText(preview);
		xmlPreviewPane.revalidate();
	}
	
	
	
	/**
	 * updateXmlPreview .
	 *
	 * @param preview String with new content of updateXmlPreviewPane
	 */
	
	public void updateXmlPreview() {
		this.updatePreviewButton.doClick();
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
