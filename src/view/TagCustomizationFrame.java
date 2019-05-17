package view;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import business.Session;
import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import view.Components.ModelComponents.CustomColor;
import view.Components.ModelComponents.TagBtn;
import view.Components.tagCustomizationFrameComponents.ChildrenListBar;
import view.Components.tagCustomizationFrameComponents.TagContainer;





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
	
	private void generateLeftPanel() {
		
		/** leftPanel initial setup */
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new BorderLayout(0, 0));
		leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		/** tagContainer initial setup */
		tagContainer = new TagContainer(parent, listener);
		
		/** setting up leftScrollPane */
		JScrollPane leftScrollPanel = new JScrollPane(tagContainer);
		
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
	 * GenerateRightPanel
	 * 
	 * @return void
	 */
	
	private void generateRightPanel(XmlTag tag){

		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBorder(new MatteBorder(0,1,0,0, CustomColor.LIGHT_GRAY));
		
		JScrollPane rightScrollPanel = new JScrollPane(new ChildrenListBar(tag, listener));
		
		/** creation of leftScrollPaneScrollBar. Scroll bar of LeftScrollPane */
		JScrollBar rightScrollPanelScrollBar = new JScrollBar(){
			
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
        rightScrollPanelScrollBar.setUnitIncrement(16);
        rightScrollPanel.setVerticalScrollBar(rightScrollPanelScrollBar);
        rightScrollPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        rightScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rightScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		/** adding leftPanel inside lefScrollPanel */
		rightPanel.add(rightScrollPanel);
		
	
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
	    
		/** generating leftPanel */
		generateLeftPanel();
		
		/** generating bottomPanel */
		generateBottomPanel();
		
		/** generating rightPanel */
		generateRightPanel(parent);
		
		/** adding bottomPanel, leftPanel inside contentPane */
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		contentPane.add(leftPanel, BorderLayout.CENTER);
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		/** adding contentPane inside Container */
		c.add(contentPane);
	}
	
	
	
	
	/**
	 * Generate centerPanel 
	 * 
	 * @return void
	 */
	
	public void generateCenterPanel(){
	}
	
	
	
	
	
	/** 
	 * creating bottomPanel 
	 * 
	 * @return void
	 */
	
	private void generateBottomPanel() {
		
		/** setting up bottomPanel */
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new BorderLayout());
		
		/** setting up addButton */
		TagBtn addButton = new TagBtn (parent, "Add");
		addButton.addActionListener(listener);
		addButton.setActionCommand("addInWizard");
		addButton.setBackground(Color.DARK_GRAY);
		addButton.setForeground(Color.WHITE);
		addButton.setPressedBackgroundColor(Color.GRAY);
		
		/** adding addButoon inside bottomPanel */
		bottomPanel.add(addButton , BorderLayout.CENTER);
	}
	
	
	
	
	/**
	 * Update right Panel removing and repainting children list bar
	 * 
	 * @param tag for children list bar
	 */
	
	public void updateRightPanel(XmlTag tag) {
	
		contentPane.remove(rightPanel);
		
		
		if(tag.getChildrenArr() != null) {
			generateRightPanel(tag);
			contentPane.add(rightPanel, BorderLayout.EAST);
			contentPane.repaint();
			contentPane.revalidate();
		}
		
		
	}
	
	
	
	
	/**
	 * add tagPanel inside tagContainer
	 * 
	 * @param tag	new tag to add
	 * @return void
	 */
	
	public void addTagPanel(XmlTag tag) {
		tagContainer.addTagPanel(tag);
	}
	
	
	
	
	/**
	 * Remove tagPanel from tagContainer
	 * 
	 * @param panel		panel to remove
	 * @return void
	 */
	
	public void removeTagPanel(JPanel panel) {
		tagContainer.removeTagPanel(panel);
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
		JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.OK_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 icon, options, options[0]); 
	
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
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 icon, options, options[0]); 
		if(value == 0) return true;
		else return false;
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public void okMessage(String message, String title) {
		ImageIcon icon = new ImageIcon (getClass().getClassLoader().getResource("icons/ok96.png"));
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
