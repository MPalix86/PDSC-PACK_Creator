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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.DefaultEditorKit;

import business.Session;
import listeners.WizardFrameListener;
import model.XmlTag;
import model.pdscTag.Package;
import view.Components.ModelComponents.xmlEditor.XmlTextPane;
import view.Components.wizardFrameComponents.FinalStepForm;
import view.Components.wizardFrameComponents.Form;
import view.Components.wizardFrameComponents.TagListBar;

public class WizardFrame extends JFrame {
	
	private static JScrollPane leftScrollPane;
	private static JScrollBar leftPanelScrollBar;
	private static JScrollPane tagListScrollPane;
	private static JScrollBar tagListPanelScrollBar;
	private static JButton continueBtn;
	private static JButton backBtn;
	private static JPanel contentPane;								/* contentPane */
	private static JPanel rightPanel;								/* rightPanel it's inside contentPane */
	private static JPanel rightPanel_center_lv1;					/* rightPanel_center_lv1 it's inside rightPanel */
	private static JPanel rightPanel_bottom_lv2;					/* rightPanel_bottom_lv1 it's inside rightPanel_center_lv1 */	
	private static JPanel leftPanel; 								/* contains actual step frame , it's inside contentPane*/						
	private static JPanel stepBarPanel;								/* stepBarPanel it's inside contentPane */								
	private static ArrayList<JPanel> steps; 						/* contains all steps panel */
	private static int step_number = 0;								/* current step number */						
	private static XmlTextPane previewPane;						/* description label */
	private static WizardFrameListener listener;
	private JPanel tagListPanel;
	private Session session;
	private XmlTag pac; 
	


	//--------------------------------------------------------------------------constructor()
	public WizardFrame() {
		listener = new WizardFrameListener(this);
		session = Session.getInstance();
		
		steps = new ArrayList();
		pac = new Package();      									// first step;
		pac.setSelectedAttrArr(pac.getAttrArr());
		
		steps.add(new Form(pac));
		//steps.add(new StepTwoFormContainer());
		steps.add(new FinalStepForm());
		
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
		contentPane.remove(stepBarPanel);
		contentPane.remove(leftScrollPane);
		generateStepBar();
		
		leftPanel = new JPanel();			
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		leftPanel.add(steps.get(step_number));
		leftScrollPane = new JScrollPane(leftPanel);
		leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		leftScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		contentPane.add(stepBarPanel, BorderLayout.SOUTH);
		contentPane.add(leftScrollPane, BorderLayout.WEST);
		contentPane.repaint();
		contentPane.revalidate();

	}
	
	//--------------------------------------------------------------------------back()
	public void back() {
		if(step_number > 0) { 
			step_number -= 1;
		}
		contentPane.remove(stepBarPanel);
		contentPane.remove(leftScrollPane);
		generateStepBar();
		
		leftPanel = new JPanel();			
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		leftPanel.add(steps.get(step_number));
		leftScrollPane = new JScrollPane(leftPanel);
		leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		leftScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		contentPane.add(stepBarPanel, BorderLayout.SOUTH);
		contentPane.add(leftScrollPane, BorderLayout.WEST);
		contentPane.repaint();
		contentPane.revalidate();
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
		rightPanel.setBackground(new Color(52, 73, 94));
		rightPanel.setSize(new Dimension(420,590));
		rightPanel.setLayout(new BorderLayout(0, 0));
		rightPanel.setBorder(new MatteBorder(0,0,0,0,Color.white));
		
			/* rightPanel_center_lv1 initial setup */
			rightPanel_center_lv1 = new JPanel();
			rightPanel_center_lv1.setBackground(Color.DARK_GRAY);
			
			/* setting up tag list Panel */
			tagListScrollPane = new JScrollPane(new TagListBar());
			
			/* hide scrollbar into leftScrollPane, tagListScrollPane */
			hideScrollBar();
			
		/* placement of stepBar, rightpanel_center, tagListPanel into rightPanel */
		rightPanel.add(tagListScrollPane, BorderLayout.EAST);
		rightPanel.add(rightPanel_center_lv1, BorderLayout.CENTER);
		rightPanel_center_lv1.setLayout(new BorderLayout(0, 0));
		
			/* setting up rightpanel_bottom_lv1, panel that contains bar with "continue" and "back" buttons*/
			rightPanel_bottom_lv2 = new JPanel();
			rightPanel_bottom_lv2.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 255, 255)));
			rightPanel_bottom_lv2.setBackground(Color.DARK_GRAY);
			rightPanel_bottom_lv2.setLayout(new GridLayout(0, 5));
			
			
		
				/* generating void panel for rightpanel_bottom_lv1 to force buttons on right side*/
				for(int i = 0; i < 3; i++) {
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.DARK_GRAY);
					panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
					rightPanel_bottom_lv2.add(panel_1);
				}
			
				/* setting up backBtn */
				backBtn = new JButton("back");
				backBtn.setForeground(Color.DARK_GRAY);
				backBtn.setBackground(Color.WHITE);
			
				/* setting up continueBtn */
				continueBtn = new JButton("Continue");
				continueBtn.setForeground(Color.DARK_GRAY);
				continueBtn.setBackground(Color.WHITE);
			
			rightPanel_bottom_lv2.add(backBtn);
			rightPanel_bottom_lv2.add(continueBtn);
			
	
				previewPane = new XmlTextPane();
				previewPane.setBackground(Color.WHITE);
				previewPane.setEditable(false);
				JPanel xmlEditoriPane = new JPanel();
				xmlEditoriPane.add(previewPane);
				JScrollPane XmEditorlScrollPane = new JScrollPane(xmlEditoriPane);
				xmlEditoriPane.setBackground(Color.white);
				xmlEditoriPane.setBorder(new MatteBorder(0,0,0,0, Color.white));
				previewPane.setBorder(new MatteBorder(0,0,0,0, Color.white));
				XmEditorlScrollPane.setBorder(new MatteBorder(0,0,0,0, Color.white));
		
		rightPanel_center_lv1.add(XmEditorlScrollPane, BorderLayout.CENTER);
		rightPanel_center_lv1.add(rightPanel_bottom_lv2, BorderLayout.SOUTH);
		
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
	public void setPreviewDocument(String preview) {
		previewPane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
		previewPane.setText(preview);
		previewPane.revalidate();
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
		steps.add(index - 1, step);
		
		contentPane.remove(stepBarPanel);
		contentPane.remove(leftPanel);
		generateStepBar();
		contentPane.add(stepBarPanel, BorderLayout.SOUTH);
		contentPane.add(leftScrollPane, BorderLayout.WEST);
		contentPane.repaint();
		contentPane.revalidate();
		
		//this.UpdateComponent();
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
	
	public ArrayList<XmlTag> getTagArr(){
		session.setWizardFrame(this);
		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
		for (int i = 0; i < steps.size() - 1; i++) {   					/* because last steps is final step without any tag */
			Form step = (Form) steps.get(i);
			tagArr.add(step.getTag());
		}
		return tagArr;
	}
	
	
}
