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
import model.pdscTag.Package;
import view.Components.ModelComponents.xmlEditor.XmlTextPane;
import view.Components.StylizedComponents.WizardMoveButton;
import view.Components.wizardFrameComponents.FinalStepForm;
import view.Components.wizardFrameComponents.Form;
import view.Components.wizardFrameComponents.TagListBar;

public class WizardFrame extends JFrame {
	
	private static JScrollPane wizardScrollPane;
	private static JScrollBar wizardScrollPaneScrollBar;
	private static JScrollPane tagListScrollPane;

	private static JPanel contentPane;								/* contentPane */
	private static JPanel centerPanel;								/* centerPanel it's inside contentPane */
	
	private static JPanel leftPanelBottomButtonBar;					/* centerPanel_bottom_lv1 it's inside centerPanel_center_lv1 */	
	private static JPanel leftPanel; 								/* contains actual form frame , it's inside contentPane*/						
		
	private static ArrayList<JPanel> steps; 						/* contains all steps panel */
	private static int step_number = 0;								/* current form number */						
	private static XmlTextPane xmlPreviewPane;						/* description label */
	private static WizardFrameListener listener;
	private static JPanel topPanel;
	private static JPanel rightPanel;
	private Session session; 
	


	//--------------------------------------------------------------------------constructor()
	public WizardFrame() {
		listener = new WizardFrameListener(this);
		session = Session.getInstance();
		
		steps = new ArrayList();
		Package pac = new Package();  
   
		pac.setSelectedAttrArr(pac.getAttrArr());
		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
		tagArr.add(pac);
		steps.add(new Form(tagArr));
		steps.add(new FinalStepForm());
		
		/* frame initial setup */
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 598);
		
		/* contentPane initial setup */
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
			
			
			
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	
		session.setWizardFrame(this);
		
	}
	

	
    /*
	 * UI elements update function
	 * this series of functions allow to update and repaint element in JFrame 
	 */
	
	private void updateLeftPanel() {
		contentPane.remove(leftPanel);
		generateLeftPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		repaintContentPane();
	}
	
	private void updateCenterPanel() {
		contentPane.remove(centerPanel);
		generateCenterPanel();
		contentPane.add(centerPanel, BorderLayout.WEST);
		repaintContentPane();
	}
	
	private void updateTopPanel() {
		contentPane.remove(topPanel);
		generateTopPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		repaintContentPane();
	}

	private void repaintContentPane() {
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	
	
	/*
	 * Ui element creation and setup function
	 * this series of functions allow to update and repaint element in JFrame 
	 */
	
	private void generateLeftPanel(){
		
		/* leftPanel initial setup */
		leftPanel = new JPanel();			
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBorder(new MatteBorder(0,0,0,2, new Color(52, 73, 94).brighter()));
		leftPanel.setLayout(new BorderLayout());
		
		
			/* setting up backButton */
			WizardMoveButton backButton = new WizardMoveButton("< Back");
			backButton.addActionListener(listener);
			backButton.setActionCommand("back");
	
			/* setting up nextButton */
			WizardMoveButton nextButton = new WizardMoveButton("Next >");
			nextButton.addActionListener(listener);
			nextButton.setActionCommand("continue");
		
			/* adding button into leftPanelBottomButtonBar */
			leftPanelBottomButtonBar = new JPanel();
			leftPanelBottomButtonBar.setLayout(new GridLayout(0,2));
			leftPanelBottomButtonBar.setBorder(new EmptyBorder(0,0,0,0));
			leftPanelBottomButtonBar.setBackground(Color.WHITE);
			leftPanelBottomButtonBar.add(backButton);
			leftPanelBottomButtonBar.add(nextButton);
			
			/* setting up wizardPane */
			JPanel wizardPane = new JPanel();
			wizardPane.setBorder(new EmptyBorder(0,0,0,0));
			wizardPane.setBackground(Color.WHITE);
			wizardPane.setLayout(new BorderLayout());
			wizardPane.add(steps.get(step_number), BorderLayout.CENTER);
			
			/* setting up wizardScrollPaneScrollBar */
	        wizardScrollPaneScrollBar = new JScrollBar(JScrollBar.VERTICAL) {

	            @Override
	            public boolean isVisible() {
	                return true;
	            }
	        }; 
	        wizardScrollPaneScrollBar.setUnitIncrement(16);
			
			/* setting up wizardScrollPane*/
			wizardScrollPane = new JScrollPane(wizardPane);
			wizardScrollPane.setBorder(new EmptyBorder(0,0,0,0));
			wizardScrollPane.setVerticalScrollBar(wizardScrollPaneScrollBar);
			wizardScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		    wizardScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		/* adding alll elements into leftPanel*/
		leftPanel.add(leftPanelBottomButtonBar,BorderLayout.SOUTH);
		leftPanel.add(wizardScrollPane,BorderLayout.CENTER);
	}
	
	private void generateCenterPanel() {
		
		/* centerPanel initial setup */
		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(52, 73, 94));
		centerPanel.setSize(new Dimension(420,590));
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.setBorder(new EmptyBorder(0,0,0,0));
			
			/* setting up xmlPreviewPane */
			xmlPreviewPane = new XmlTextPane();
			xmlPreviewPane.setBorder(new EmptyBorder(0,0,0,0));
			xmlPreviewPane.setBackground(Color.WHITE);
			xmlPreviewPane.setEditable(false);
		
			/* setting up XmlPreviewScrollPane */
			JScrollPane XmlPreviewScrollPane = new JScrollPane(xmlPreviewPane);
			XmlPreviewScrollPane.setBackground(Color.WHITE);
			XmlPreviewScrollPane.setBorder(new EmptyBorder(0,0,0,0));
			
				
		/* placement of previewPanel, tagListPanel into centerPanel */
		centerPanel.add(tagListScrollPane, BorderLayout.EAST);
		centerPanel.add(XmlPreviewScrollPane, BorderLayout.CENTER);
	
	
	}
	
	private void generateRightPanel() {
		/* setting up rightPanel */
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBackground(Color.WHITE);
		
		/* setting up tagListPanelScrollBar Panel */
		JScrollBar tagListScrollPaneScrollBar = new JScrollBar(JScrollBar.VERTICAL) {

            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        tagListScrollPaneScrollBar.setUnitIncrement(16);
        
		/* setting up tagListScrollPane */
		tagListScrollPane = new JScrollPane(new TagListBar());
		tagListScrollPane.setVerticalScrollBar(tagListScrollPaneScrollBar);
        tagListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tagListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        tagListScrollPane.setBorder(new MatteBorder(0,2,0,0, new Color(52, 73, 94)));
        
        rightPanel.add(tagListScrollPane, BorderLayout.CENTER);
	}
	
	private void generateTopPanel() {
		
		topPanel = new JPanel();
		topPanel.setBackground(UIManager.getColor(Color.WHITE));
		topPanel.setMinimumSize(new Dimension(10, 200));
		topPanel.setLayout(new GridLayout(1,steps.size()));
		topPanel.setBorder(new EmptyBorder(0,0,0,0));
		for (int i = 0 ; i < steps.size(); i++) {
			JPanel panel = new JPanel();
			if(i <= step_number) {
				panel.setBackground(new Color(52, 73, 94).brighter());
			}
			else {
				panel.setBackground(UIManager.getColor(Color.WHITE));
			}
			topPanel.add(panel);
		}
	
	}
	
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
	
	
	
	/*
	 * Public function
	 * this series of function allow to modify UI from outside
	 */
	
	public static ArrayList<JPanel> getSteps() {
		return steps;
	}
	
	public void addStep(JPanel form) {
		int index = steps.size();
		steps.add(index - 1, form);
		updateLeftPanel();
		updateTopPanel();
		repaintContentPane();
	}     
	
	public ArrayList<XmlTag> getTagArr(){
		session.setWizardFrame(this);
		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
		XmlTag tag = new XmlTag();
		for (int i = 0; i < steps.size() - 1; i++) {   					/* -1 because last steps have no tag */
			Form form = (Form) steps.get(i);
			for (int j = 0; j < form.getTagArr().size(); j++) {
				tag = form.getTagArr().get(j);
				tagArr.add(tag);
			}
		}
		return tagArr;
	}
	

	public void next() {
		if(step_number < steps.size()-1) { 
			step_number += 1;
		}
		updateLeftPanel();
		updateTopPanel();
		repaintContentPane();
	}
	
	
	public void back() {
		if(step_number > 0) { 
			step_number -= 1;
		}
		updateLeftPanel();
		updateTopPanel();
		repaintContentPane();
	}
	
	public void updateXmlPreviewPane(String preview) {
		xmlPreviewPane.setText(preview);
		xmlPreviewPane.revalidate();
	}
	
	public File showNewFileFrame() {
		JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(session.getCurrentWorkingFile() != null) {fileChooser.setCurrentDirectory(session.getCurrentWorkingFile());} 	
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
