package view.tagCustomizationFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import business.Session;
import listeners.tagCustomizationFrameListener.TagCustomizationFrameListener;
import model.XmlTag;
import view.comp.CustomColor;
import view.comp.SquareButton;
import view.comp.TagButton;
import view.tagCustomizationFrame.comp.ChildrenListBar;
import view.tagCustomizationFrame.comp.TagContainer;





/**
 * Tag customization frame.
 * 
 * @author Mirco Palese
 */

public class TagCustomizationFrame extends JFrame {
	
	/** Frame content pane */
	private JPanel contentPane;
	
	/** bottom panel */
	private JPanel bottomPanel;
	
	/** left panel */
	private JPanel leftPanel;
	
	private JPanel centerPanel;
	
	/** right panel */
	private JPanel rightPanel;
	
	/** tag container */
	private TagContainer tagContainer;
	
	/** session */
	private Session session;
	
	/** tag customization frame listener */
	private TagCustomizationFrameListener listener;
	
	/** frmae's parent tag */
	private XmlTag parent;
	
	private ArrayList<JPanel> childrenPanelArr;
	
	
	
	
	/**
	 * Constructor of TagCustomizationFrame. Create a new instance of tag customization frame that
	 * placing all components, initializing frame with the chosen tag.
	 * 
	 * @see  #generateLeftPanel()
	 */
	
	public TagCustomizationFrame(XmlTag parent) {
		
		/** setting up JOptionPane color */
		UIManager UI=new UIManager();
		UI.put("OptionPane.background",new ColorUIResource(255,255,255));   
		UI.put("Panel.background",new ColorUIResource(255,255,255));
		
		/** recovenring parent tag */
		this.parent = parent;
		
		/** setting up listener */
		listener = new TagCustomizationFrameListener(this);
		
		/** recovering session instance */
		session = Session.getInstance();
		
		/** setting up default close operation */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		placeComponents();
		//generateCenterPanel();
	}
	
	
	
	
	/** 
	 * generate left panel with all components 
	 * 
	 * @return void
	 */
	
	private void generateCenterPanel() {
		
		/** centerPanel initial setup */
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		/** tagContainer initial setup */
		tagContainer = new TagContainer(this);
		
		/** setting up centerScrollPane */
		JScrollPane centerScrollPanel = new JScrollPane(tagContainer);
		
		/** creation of centerScrollPaneScrollBar. Scroll bar of centerScrollPane */
		JScrollBar centerScrollPanelScrollBar = new JScrollBar(){
			
	    	/**
        	 * override isVisible to keep the scroll bar working even if
        	 * it is hidden
        	 */
            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        
        /** setting up cebterScrollPane */
        centerScrollPanelScrollBar.setUnitIncrement(16);
        centerScrollPanel.setVerticalScrollBar(centerScrollPanelScrollBar);
        centerScrollPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        centerScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		/** adding centerPanel inside centerScrollPanel */
		centerPanel.add(centerScrollPanel);
		
	}
	
	
	
	
	
	/**
	 * GenerateRightPanel
	 * 
	 * @return void
	 */
	
	private void generateLeftPanel(XmlTag tag){

		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(new MatteBorder(0,0,0,1, CustomColor.LIGHT_GRAY));
		
		JScrollPane leftScrollPanel = new JScrollPane(new ChildrenListBar(tag, listener));
		
		/** creation of leftScrollPaneScrollBar. Scroll bar of LeftScrollPane */
		JScrollBar leftScrollPanelScrollBar = new JScrollBar(){
			
	    	/**
        	 * override isVisible to keep the scroll bar working even if
        	 * it is hidden
        	 */
            @Override
            public boolean isVisible() {
                return true;
            }
        }; 
        
        /** setting up leftScrollPane */
        leftScrollPanelScrollBar.setUnitIncrement(16);
        leftScrollPanel.setVerticalScrollBar(leftScrollPanelScrollBar);
        leftScrollPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        leftScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		/** adding leftPanel inside lefScrollPanel */
        leftPanel.add(leftScrollPanel);
		
	
	}
	
	
	
	
	/** 
	 * recovering container, setting up frame, contentPane and placing all components 
	 * 
	 * @return void
	 */
	
	private void placeComponents() {
		
		/** recovering container and setting up contentPane */
		Container c = this.getContentPane();
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		/** frame initial setup */
		c.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setContentPane(c);//(contentPane);
		setVisible(true);
		setSize(900, 600);       
	    setLocation(200, 100);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    
		/** generating centerPanel */
	    generateCenterPanel();
		
		/** generating bottomPanel */
		generateBottomPanel();
		
		/** generating rightPanel */
		generateLeftPanel(parent);
		
		/** adding bottomPanel, leftPanel inside contentPane */
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(leftPanel, BorderLayout.WEST);
		
		/** adding contentPane inside Container */
		c.add(contentPane);
	}
	
	
	
	

	
	
	
	
	/** 
	 * creating bottomPanel 
	 * 
	 * @return void
	 */
	
	private void generateBottomPanel() {
		
		JPanel panel = new JPanel (new BorderLayout());
		
		/** setting up bottomPanel */
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new BorderLayout());
		
		/** setting up addButton */
		TagButton addButton = new TagButton (parent, "Add");
		addButton.addActionListener(listener);
		addButton.setActionCommand("addInWizard");
		
		JPanel addButtonPanel = new JPanel(new BorderLayout());
		addButtonPanel.setBorder(new MatteBorder(1,1,1,1,CustomColor.LIGHT_GRAY));
		addButtonPanel.add(addButton, BorderLayout.CENTER);
		
		
		
		/** setting up addButton */
		SquareButton cancelButton = new SquareButton("Cancel");
		cancelButton.addActionListener(listener);
		cancelButton.setActionCommand("cancel");
		JPanel cancelButtonPanel = new JPanel(new BorderLayout());
		cancelButtonPanel.setBorder(new MatteBorder(1,1,1,1,CustomColor.LIGHT_GRAY));
		cancelButtonPanel.add(cancelButton, BorderLayout.CENTER);

		panel.add(addButtonPanel , BorderLayout.CENTER);
		panel.add(cancelButtonPanel ,BorderLayout.EAST);
		
		/** adding addButoon inside bottomPanel */
		bottomPanel.add(panel , BorderLayout.CENTER);
	}
	
	
	
	
	/**
	 * Update right Panel removing and repainting children list bar
	 * 
	 * @param tag for children list bar
	 */
	
	public void updateLeftPanel(XmlTag tag) {
	
		contentPane.remove(leftPanel);
		
		
		if(tag.getChildrenArr() != null) {
			generateLeftPanel(tag);
			contentPane.add(leftPanel, BorderLayout.WEST);
			contentPane.repaint();
			contentPane.revalidate();
		}
		
		
	}
	
	
	

	
	/**
	 * @return the tagContainer
	 */
	public TagContainer getTagContainer() {
		return tagContainer;
	}




	/**
	 * @param tagContainer the tagContainer to set
	 */
	public void setTagContainer(TagContainer tagContainer) {
		this.tagContainer = tagContainer;
	}




	/**
	 * @return the listener
	 */
	public TagCustomizationFrameListener getListener() {
		return listener;
	}




	/**
	 * @param listener the listener to set
	 */
	public void setListener(TagCustomizationFrameListener listener) {
		this.listener = listener;
	}
	
	
	
	
	/**
	 * return parent tag associated with this frame
	 * 
	 * @return return parent tag associated with this frame
	 */
	
	public XmlTag getTagParent() {
		return this.parent;
	}
	
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public void warningMessage(String message) {
		ImageIcon icon = new ImageIcon (getClass().getClassLoader().getResource("icons/warning48.png"));
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
	}
	
	
	
	
	/**
	 * Show option pane warning message with "yes-no" option
	 * 
	 * @param message	message to show inside option pane
	 * @return true if yes was selected, false otherwise
	 */
	
	public boolean yesNoWarningMessage(String message) {
		ImageIcon icon = new ImageIcon (getClass().getClassLoader().getResource("icons/warning48.png"));
		Object[] options = { "YES", "NO" };
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
		if(value == 0) return true;
		else return false;
	}
	
	
	
	
	/**
	 * Clone tag option pane. Show number spinner to select number of copy
	 * 
	 * @return selected number of copy
	 */
	
	public int cloneDialog() {
		SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
		ImageIcon cloneIcon = new ImageIcon(getClass().getClassLoader().getResource("icons/clone40.png"));
		JSpinner spinner = new JSpinner(sModel);
		int option = JOptionPane.showOptionDialog(null, spinner, "Clone Tag", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, cloneIcon, null, null);
		if (option == JOptionPane.CANCEL_OPTION) {}
		else if (option == JOptionPane.OK_OPTION){ return (int)spinner.getValue();}
		return -1;
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public void okMessage(String message, String title) {
		ImageIcon icon = new ImageIcon (getClass().getClassLoader().getResource("icons/ok40.png"));
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, title, JOptionPane.OK_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 icon, options, options[0]); 
	
	}
	
	
	
	
	/**
	 * return children panel of this tag
	 * 
	 * @return children Panel
	 */
	
	public ArrayList<JPanel> getChildrenPanelArr() {
		return this.childrenPanelArr;
	}
	
	

}
