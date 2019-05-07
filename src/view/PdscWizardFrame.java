package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import business.Session;
import listeners.WizardFrameListener;
import model.XmlTag;
import model.pdscTag.Package;
import view.components.FinalStepFormContainer;
import view.components.FormContainer;
import view.components.StepTwoFormContainer;
import view.components.TagListPanelComponent;

public class PdscWizardFrame extends JFrame {
	
	private static JScrollPane leftScrollPane;
	private static JScrollBar leftPanelScrollBar;
	private static JScrollPane tagListScrollPane;
	private static JScrollBar tagListPanelScrollBar;
	private static JButton continueBtn;
	private static JButton backBtn;
	private static JPanel contentPane;								/* contentPane */
	private static JPanel rightPanel;								/* rightPanel it's inside contentPane */
	private static JPanel rightPanel_center_lv1;					/* rightPanel_center_lv1 it's inside rightPanel */
	private static JPanel rightPanel_bottom_lv1;					/* rightPanel_bottom_lv1 it's inside rightPanel_center_lv1 */	
	private static JPanel leftPanel; 								/* contains actual step frame , it's inside contentPane*/						
	private static JPanel stepBarPanel;								/* stepBarPanel it's inside contentPane */								
	private static ArrayList<JPanel> steps; 						/* contains all steps panel */
	private static int step_number = 0;								/* current step number */						
	private static JTextPane descriptionPane;						/* description label */
	private static WizardFrameListener listener;
	private JPanel tagListPanel;
	private JList tagList;
	private Session session;
	private XmlTag pac; 
	


	//--------------------------------------------------------------------------constructor()
	public PdscWizardFrame() {
		
		session = Session.getInstance();
		
		steps = new ArrayList();
		pac = new Package();
		pac.setSelectedAttrArr(pac.getAttrArr());
		
		steps.add(new FormContainer(pac));
		steps.add(new StepTwoFormContainer());
		steps.add(new FinalStepFormContainer());
		
		/* frame initial setup */
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1026, 598);
		
			/* contentPane initial setup */
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
				BorderLayout b = new BorderLayout(0, 0);
				b.setHgap(0);
				b.setVgap(0);
			contentPane.setLayout(b);
			setContentPane(contentPane); 
		
			/* generate all component */
			generateStepBar();
			placeComponent();
			
			/* place all all component into contentPane */
			contentPane.add(leftScrollPane, BorderLayout.WEST);  
			contentPane.add(rightPanel, BorderLayout.CENTER);
			contentPane.add(stepBarPanel, BorderLayout.SOUTH);
			contentPane.revalidate();
			contentPane.repaint();
			
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		setListeners();
		session.setWizardFrame(this);
		
	}
	
	//--------------------------------------------------------------------------next()
	public void next() {
		if(step_number < steps.size()-1) { 
			step_number += 1;
		}
		UpdateComponent();

	}
	
	//--------------------------------------------------------------------------back()
	public void back() {
		if(step_number > 0) { 
			step_number -= 1;
		}
		UpdateComponent();
	}
	
	//--------------------------------------------------------------------------placeComponent()
	private void placeComponent() {
		
		/* leftPanel initial setup */
		leftPanel = new JPanel();			
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		leftPanel.add(steps.get(step_number));
		leftScrollPane = new JScrollPane(leftPanel);
		leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		leftScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		/* rightPanel initial setup */
		rightPanel = new JPanel();
		rightPanel.setBackground(Color.DARK_GRAY);
		rightPanel.setSize(new Dimension(420,590));
		rightPanel.setLayout(new BorderLayout(0, 0));
		
			/* rightPanel_center initial setup */
			rightPanel_center_lv1 = new JPanel();
			rightPanel_center_lv1.setBackground(Color.DARK_GRAY);
			
			/* setting up tag list Panel */
			tagListScrollPane = new JScrollPane(new TagListPanelComponent());
			
			/* hide scrollbar into leftScrollPane, tagListScrollPane */
			hideScrollBar();
			
		/* placement of stepBar, rightpanel_center, tagListPanel into rightPanel */
		rightPanel.add(tagListScrollPane, BorderLayout.EAST);
		rightPanel.add(rightPanel_center_lv1, BorderLayout.CENTER);
		rightPanel_center_lv1.setLayout(new BorderLayout(0, 0));
		
			/* setting up rightpanel_bottom_lv1 */
			rightPanel_bottom_lv1 = new JPanel();
			rightPanel_bottom_lv1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 255, 255)));
			rightPanel_bottom_lv1.setBackground(Color.DARK_GRAY);
			rightPanel_center_lv1.add(rightPanel_bottom_lv1, BorderLayout.SOUTH);
			rightPanel_bottom_lv1.setLayout(new GridLayout(0, 5));
		
				/* generating void panel */
				for(int i = 0; i < 3; i++) {
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.DARK_GRAY);
					panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
					rightPanel_bottom_lv1.add(panel_1);
				}
			
				/* setting up backBtn */
				backBtn = new JButton("back");
				backBtn.setForeground(Color.DARK_GRAY);
				backBtn.setBackground(Color.WHITE);
			
				/* setting up continueBtn */
				continueBtn = new JButton("Continue");
				continueBtn.setForeground(Color.DARK_GRAY);
				continueBtn.setBackground(Color.WHITE);
			
			rightPanel_bottom_lv1.add(backBtn);
			rightPanel_bottom_lv1.add(continueBtn);
		
		descriptionPane = new JTextPane();
		descriptionPane.setForeground(Color.WHITE);
		descriptionPane.setBackground(Color.DARK_GRAY);
		rightPanel_center_lv1.add(descriptionPane, BorderLayout.CENTER);
		
	}
	
	
	//--------------------------------------------------------------------------generateStepBar()
	private void generateStepBar() {
		stepBarPanel = new JPanel();
		stepBarPanel.setBackground(UIManager.getColor(Color.WHITE));
		stepBarPanel.setMinimumSize(new Dimension(10, 200));
		stepBarPanel.setLayout(new GridLayout(1,steps.size()));
		for (int i = 0 ; i < steps.size(); i++) {
			JPanel panel = new JPanel();
			if(i <= step_number) {
				panel.setBackground(new Color(30, 144, 255));
			}
			else {
				panel.setBackground(UIManager.getColor(Color.WHITE));
			}
			stepBarPanel.add(panel);
		}
	}
	
	
	//--------------------------------------------------------------------------UpdateComponent()
	private void UpdateComponent() {
		contentPane.removeAll();
			generateStepBar();
			placeComponent();
		contentPane.add(leftScrollPane, BorderLayout.WEST);  
		contentPane.add(rightPanel, BorderLayout.CENTER);
		contentPane.add(stepBarPanel, BorderLayout.SOUTH);
		contentPane.revalidate();
		contentPane.repaint();	
		session.setWizardFrame(this);
		setListeners();
	}
	
	
	//--------------------------------------------------------------------------printDescription()
	public void printDescription(String text) {
		descriptionPane.setText("");
		descriptionPane.setText(text);
	}
	
	//--------------------------------------------------------------------------showNewFileFrame()
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
	
	//--------------------------------------------------------------------------setListeners()
	private void setListeners() {
		listener = new WizardFrameListener();
		continueBtn.addActionListener(listener);
		continueBtn.setActionCommand("continue");
		backBtn.addActionListener(listener);
		backBtn.setActionCommand("back");
	}
	
	//--------------------------------------------------------------------------getSteps()
	public static ArrayList<JPanel> getSteps() {
		return steps;
	}
	
	
	//--------------------------------------------------------------------------getSteps()
	public void addStep(JPanel step) {
		int index = steps.size();
		this.steps.add(index - 1, step);
		this.UpdateComponent();
	}
	
	//--------------------------------------------------------------------------setListeners()
	private void hideScrollBar() {
		tagListPanelScrollBar = new JScrollBar(JScrollBar.VERTICAL) {

            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        tagListPanelScrollBar.setUnitIncrement(16);
        tagListScrollPane.setVerticalScrollBar(tagListPanelScrollBar);
        tagListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tagListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
        leftPanelScrollBar = new JScrollBar(JScrollBar.VERTICAL) {

            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        leftPanelScrollBar.setUnitIncrement(16);
        leftScrollPane.setVerticalScrollBar(leftPanelScrollBar);
        leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	}
	
	
}
