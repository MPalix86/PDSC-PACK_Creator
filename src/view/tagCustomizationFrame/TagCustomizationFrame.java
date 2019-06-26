package view.tagCustomizationFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import listeners.tagCustomizationFrameListeners.TagCustomizationFrameListener;
import model.XmlTag;
import view.comp.CustomColor;
import view.comp.SquareButton;
import view.comp.TagButton;
import view.tagCustomizationFrame.comp.TagContainer;
import view.wizardFrame.comp.xmlForm.XmlForm;





/**
 * Tag customization frame.
 * 
 * @author Mirco Palese
 */

public class TagCustomizationFrame extends JFrame {
	
	/** Frame content pane */
	private JPanel contentPane;
	
	private JPanel bottomPanel;
	
	private JPanel centerPanel;
	
	/** tag container */
	private TagContainer tagContainer;
	
	/** tag customization frame listener */
	private TagCustomizationFrameListener listener;
	
	/** frmae's parent tag */
	private XmlTag parent;
	
	private ArrayList<JPanel> childrenPanelArr;
	
	private XmlForm form;
	
	
	
	
	/**
	 * Constructor of TagCustomizationFrame. Create a new instance of tag customization frame that
	 * placing all components, initializing frame with the chosen tag.
	 * 
	 * @see  #generateLeftPanel()
	 */
	
	public TagCustomizationFrame(XmlTag parent) {
		
		/** recovenring parent tag */
		this.parent = parent;
		
		/** recovenring parent tag */
		this.parent = parent;
		
		/** setting up listener */
		listener = new TagCustomizationFrameListener(this);
		
		/** setting up default close operation */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		placeComponents();
	}
	
	
	
	
	/** 
	 * generate center panel with all components 
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
		
		/** adding bottomPanel, leftPanel inside contentPane */
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
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
		TagButton addButton = new TagButton (parent, "Add New");
		addButton.addActionListener(listener);
		addButton.setActionCommand("addInWizard");
		
		JPanel addButtonPanel = new JPanel(new BorderLayout());
		addButtonPanel.setBorder(new MatteBorder(1,1,1,1,CustomColor.LIGHT_GRAY));
		addButtonPanel.add(addButton, BorderLayout.CENTER);
		
		
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
	 * @return the tagContainer
	 */
	public TagContainer getTagContainer() {
		return tagContainer;
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
	 * return children panel of this tag
	 * 
	 * @return children Panel
	 */
	
	public ArrayList<JPanel> getChildrenPanelArr() {
		return this.childrenPanelArr;
	}
	
	

}
