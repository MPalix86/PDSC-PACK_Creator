//package view.wizardFrame.comp.wizardForm;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.GridLayout;
//import java.util.ArrayList;
//
//import javax.swing.JPanel;
//import javax.swing.JScrollBar;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.MatteBorder;
//
//import listeners.wizardFrameListeners.comp.wizardFormListeners.FormContainerListener;
//import model.XmlTag;
//import model.pdsc.tags.Description;
//import model.pdsc.tags.License;
//import model.pdsc.tags.Name;
//import model.pdsc.tags.Package;
//import model.pdsc.tags.Url;
//import model.pdsc.tags.Vendor;
//import view.comp.CustomColor;
//import view.comp.SquareButton;
//
//public class FormContainer extends JPanel{
//	
//	private SquareButton backButton;
//	
//	
//	private SquareButton nextButton;
//	
//	/** JPanel's array. Contains all panels which are part of the wizard */
//	private static ArrayList<JPanel> steps; 
//	
//	private int stepIndex = 0;
//	
//	private FormContainerListener listener;
//	
//	private JScrollPane scrollPane;
//	
//	
//	
//	public FormContainer() {
//		
//		/** creation of new FormContainerListener instance */
//		listener = new FormContainerListener(this);
//		
//		/** array step initialization */
//		steps = new ArrayList();
//		
//		/** new instance of root tag of the document */
//		Package pac = new Package(true, null, 1);
//		pac.setSelectedAttrArr(pac.getAttrArr());
//		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
//		tagArr.add(pac);
//		
//		/** other mandatory tag instances */
//		ArrayList<XmlTag> tagArr1 = new ArrayList<XmlTag>();
//		tagArr1.add(new Vendor(true, null, 1, "STMicroelectronics"));
//		tagArr1.add(new Name(true, null, 1, ""));
//		tagArr1.add(new Description(true, null, 1, ""));
//		tagArr1.add(new License(true, null, 1, ""));
//		tagArr1.add(new Url(true, null, 1,"http://sw-center.st.com/packs/x-cube/"));
//		
//		/** populating steps array */
//		steps.add(new Form(tagArr));
//		steps.add(new Form(tagArr1));
//		steps.add(new FinalStepForm());
//		
//		/** placing components */
//		placeComponents();
//
//		
//	}
//	
//	
//	
//	/**
//	 * place all components 
//	 * 
//	 * @return void
//	 */
//	private void placeComponents() {
//		
//		this.setBackground(Color.WHITE);
//		this.setBorder(new MatteBorder(0,1,0,0, CustomColor.LIGHT_GRAY ));
//		this.setLayout(new BorderLayout());
//		
//		/** BottomButtonBar contains button next and back */
//		JPanel bottomButtonBar = new JPanel();
//		
//		backButton = new SquareButton("< Back");
//		backButton.addActionListener(listener);
//		backButton.setActionCommand("back");
//		
//		nextButton = new SquareButton("Next >");
//		nextButton.addActionListener(listener);
//		nextButton.setActionCommand("next");
//		
//		/** placing backBUtton and nextButton into bottomButtonBar */
//		bottomButtonBar.add(backButton);
//		bottomButtonBar.add(nextButton);
//		
//		enableNextBackButton();
//		
//		
//		/** BottomButtonBar initial setup */
//		bottomButtonBar.setLayout(new GridLayout(0,2));
//		bottomButtonBar.setBorder(new MatteBorder(1,0,0,0, CustomColor.LIGHT_GRAY));
//		bottomButtonBar.setBackground(Color.WHITE);
//		
//		/** placing backBUtton and nextButton into BottomButtonBar */
//		bottomButtonBar.add(backButton);
//		bottomButtonBar.add(nextButton);
//		
//        JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
//        	
//        	/**
//        	 * override isVisible to keep the scroll bar working even if
//        	 * it is hidden
//        	 */
//        	
//            @Override
//            public boolean isVisible() {
//                return true;
//            }
//        }; 
//        scrollBar.setUnitIncrement(16);
//        
//        
//        /** ScrollPane contains current step */
//		scrollPane = new JScrollPane(steps.get(stepIndex));
//		
//		/** setting scrollPane */
//		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
//		scrollPane.setVerticalScrollBar(scrollBar);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//		
//		this.add(scrollPane, BorderLayout.CENTER);
//		this.add(bottomButtonBar, BorderLayout.SOUTH);
//		
//		this.repaint();
//		this.revalidate();
//		
//	}
//	
//	
//	
//
//	
//	
//	
//	/**
//	 * Go to the next step of the wizard.
//	 *
//	 * @return void
//	 */
//	public void next() {
//		if(stepIndex < steps.size()-1) { 
//			stepIndex += 1;
//			
//		}
//		
//		enableNextBackButton();
//		
//		this.removeAll();
//		placeComponents();
//	}
//	
//	
//	
//	
//	/**
//	 * Go to the previous step of the wizard.
//	 *
//	 * @return void
//	 */
//	public void back() {
//		if(stepIndex > 0) { 
//			stepIndex -= 1;
//		}
//		
//		enableNextBackButton();
//		
//		this.removeAll();
//		placeComponents();
//	}
//	
//	
//	
//	
//	/**
//	 * Enable and disable next and back button
//	 * 
//	 * @return void
//	 */
//	private void enableNextBackButton() {
//
//		if(stepIndex == 0) { 
//			this.backButton.setEnabled(false);
//		}
//		
//		else if(stepIndex == steps.size()-1) { 
//			this.nextButton.setEnabled(false);
//		}
//		
//		else { 
//			this.nextButton.setEnabled(true);
//			this.backButton.setEnabled(true);
//		}
//	}
//	
//	
//	
//	
//	/**
//	 * Add step into steps array.
//	 *
//	 * @param form	new form to insert in the wizard
//	 * @return void
//	 */
//	public void addStep(JPanel form) {
//		
//		int index = steps.size();
//		
//		/** adding steps as the penultimate element */
//		steps.add(index - 1, form);
//	}   
//	
//	
//	
//	
//
//	/**
//	 * Return array that contains all tag children of root tag inserted during wizard.
//	 * every root tag child contains relative children. 
//	 *
//	 * @return Array that contains all tag inserted during wizard
//	 */
//	public ArrayList<XmlTag> getTagArr(){
//		ArrayList<XmlTag> tagArr = new ArrayList<XmlTag>();
//		XmlTag tag = new XmlTag();
//		
//		for (int i = 0; i < steps.size() - 1; i++) {   					
//			Form form = (Form) steps.get(i);
//			for (int j = 0; j < form.getTagArr().size(); j++) {
//				tag = form.getTagArr().get(j);
//				tagArr.add(tag);
//			}
//		}
//		return tagArr;
//	}
//	
//	
//	
//	
//	/**
//	 * return steps array.
//	 *
//	 * @return return steps array.
//	 */
//	
//	public static ArrayList<JPanel> getSteps() {
//		return steps;
//	}  
//	
//	
//	
//	
//	
//	
//
//}
