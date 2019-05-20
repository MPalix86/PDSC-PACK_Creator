package view.wizardFrame.comp.wizardForm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import business.Session;
import listeners.wizardFrameListener.comp.FormListener;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeComboBox;
import view.comp.AttributeTextField;
import view.comp.CustomColor;
import view.comp.TagTextField;


/**
 * Create new Form of wizard 
 * 
 * @author Mirco Palese
 */

public class Form extends JPanel{
	
	/** dimension of text field and label  */
	private final static int LABEL_HEIGHT = 16;
	private final static int TEXT_HEIGHT = 33;
	private final static int LABEL_WIDTH = 296;
	private final static int TEXT_WIDTH = 296;
	private final static int TEXT_X = 57;
	private final static int LABEL_X = 57;
	
	/** array that contains all tags to be inserted inside form */
	private ArrayList<XmlTag> tagArr;
	
	/** value to add y position between label and text */
	private final static int DELTA_LABEL_TEXT = 28;
	
	/** value to add at y position between text and label */
	private final static int DELTA_TEXT_LABEL = 45;
	
	/** value to add y position between title and other elements */
	private final static int DELTA_TITLE = 36;
	
	
	private FormListener listener;
	
	
	private int positionY = 49;
	
	private Session session;
	
	
	
	
	
	/**
	 * Form constructor. No listener is needed
	 * 
	 * @param tagArr	array list containing tags to be added inside form
	 */
	
	public Form(ArrayList<XmlTag> tagArr ) {
		
		/** setting up listener */
		this.listener = new FormListener(this);
		
		session = Session.getInstance();
		
		/** setting up tagArr */
		this.tagArr = tagArr;
		
		/** Form initial setup */
		setLayout(null);
		setBackground(Color.WHITE);
		
		placeComponents();
	}
	
	
	
	
	
	/**
	 * Form constructor. Listener is needed
	 * 
	 * @param tagArr 	array list containing tags to be added inside form
	 * @param listener	listener for Form
	 */
	
	public Form(ArrayList<XmlTag> tagArr, FormListener listener) {
		
		/** setting up listener */
		this.listener = listener;
		
		/** stting up tagArr */
		this.tagArr = tagArr;
		
		/** Form initial setup */
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		placeComponents();
	}
	
	
	
	
	
	/**
	 * Form constructor. No listener is needed
	 * 
	 * @param tag	tag to added inside form
	 */
	
	public Form(XmlTag tag) {
		
		/** setting up listener */
		this.listener = new FormListener(this);
		
		/** stting up tagArr */
		this.tagArr = new ArrayList<XmlTag>();
		tagArr.add(tag);
		
		/** Form initial setup */
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		placeComponents();
	}
	
	
	
	
	
	/**
	 * Add tag's attribute placing all necessary components.
	 * <p>
	 * NOTE: this frame use null layout. All components are put into position 
	 * 		 manually, managing x and y coordinates.
	 * 
	 * @param attr	attribute to add inside tag
	 * @return void
	 */
	
	private void placeAttr(XmlAttribute attr) {
			
		/** setting up attrLabel */
		JLabel attrLabel = new JLabel();
		attrLabel.setForeground(CustomColor.ATTR_COLOR);
		
		/** setting up position of attrLabel */
		attrLabel.setBounds(LABEL_X, positionY ,LABEL_WIDTH, LABEL_HEIGHT);
		
		/** calculation of the new position */
		positionY += DELTA_LABEL_TEXT;
		
		/** setting up the require "*" symbol if attribute is required */
		if(attr.isRequired()) attrLabel.setText(attr.getName() + " *");
		else attrLabel.setText(attr.getName());
		
		/** adding attrLabel into Form */
		this.add(attrLabel);
		
		/** if attribute has no default value  */
		if(attr.getPossibleValues().getClass() == String.class) {
			
			/** setting up text filed valueText */
			AttributeTextField valueText = new AttributeTextField(attr);
			valueText.setBorder(new LineBorder(Color.LIGHT_GRAY));
			valueText.setForeground(Color.DARK_GRAY);
			valueText.setColumns(10);
			
			/** adding focus listener */
			valueText.addFocusListener(listener);
			
			/** setting up position of valueText text field */
			valueText.setBounds(TEXT_X, positionY, TEXT_WIDTH, TEXT_HEIGHT);
			
			/** calculation of the new position */
			positionY += DELTA_TEXT_LABEL;
			
			/** adding text field valòueText to the form */
			this.add(valueText);
		}
		
		/** if attribute has default value  */
		else {
			
			/** setting up  AttributeComboBox valuesComboBox with all possible values*/	
			AttributeComboBox valuesComboBox = new AttributeComboBox(attr);  
			valuesComboBox.addActionListener(listener);
			valuesComboBox.setForeground(Color.DARK_GRAY);
			
			/** setting up valuesComboBox position */
			valuesComboBox.setBounds(TEXT_X, positionY, TEXT_WIDTH, TEXT_HEIGHT);
			
			/** calculation of the new position */
			positionY += DELTA_TEXT_LABEL;
			
			/** adding valuesCombobox to the form */
			this.add(valuesComboBox);
		}
		
		/** setting up size of form panel */
		this.setPreferredSize(new Dimension(420, positionY +10));
		
	}
	
	
	
	
	/**
	 * Add tag inside form placing all necessary components.
	 * <p>
	 * NOTE: this frame use null layout. All components are put into position 
	 * 		 manually, managing x and y coordinates.
	 * 
	 * @return void
	 */
	
	public void addTag() {
		
		XmlTag tag = new XmlTag();
		
		/** setting up title label */
		JLabel titleLabel = new JLabel("PDSC creator");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(142, 0, 135, 44);  
		add(titleLabel);
		
		/** for each tag in tagArr */
		for(int i = 0; i < tagArr.size(); i++) {
			
			/** getting current element */
			tag = tagArr.get(i);
			
			/** scroll trough the tag using Breadth First Search Algorithm */
			ArrayList <XmlTag> children = new ArrayList<XmlTag>();
			children.add(tag);
			while(!children.isEmpty()) {
				XmlTag element = children.get(0);
				JLabel tagNameLabel;
				
				/** setting up the require "*" symbol if element is required */
				if(element.isRequired()) tagNameLabel = new JLabel("<"+element.getName()+"> *");
				else tagNameLabel = new JLabel("<"+element.getName()+">");
				
				/** setting up tagNameLabel */
				tagNameLabel.setForeground(CustomColor.TAG_COLOR);
				tagNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				tagNameLabel.setBounds(30, positionY , 123, 24);
				this.add(tagNameLabel);
				
				/** calculating new position */
				positionY += DELTA_TITLE;
				
				children.remove(element);
				 
				/** if element has selected children */
				if( element.getSelectedChildrenArr() != null )	element.getSelectedChildrenArr().forEach((c)-> children.add(c));
				
				/** if element has no selected children */
				else {	
					
					/** if tag can have content */
					if(element.getDefaultContent() != null) {
						
						/** setting up TagTextField valueText */
						TagTextField valueText = new TagTextField(element);
						valueText.setBorder(new LineBorder(Color.LIGHT_GRAY));
						valueText.setForeground(Color.DARK_GRAY);
						valueText.setColumns(10);
						
						/** adding focus listener */
						valueText.addFocusListener(listener);
						
						/** setting up position of valueText */
						valueText.setBounds(37, positionY, TEXT_WIDTH, TEXT_HEIGHT);
						
						/** calculation of new position */
						positionY += DELTA_TEXT_LABEL;
						
						this.add(valueText);
					}
				}
				
				/** if element has selected attributes */
				if(element.getSelectedAttrArr()!=  null) {
					ArrayList<XmlAttribute> attrArr = element.getSelectedAttrArr();
					attrArr.forEach((a)->placeAttr(a));
				}
			}
			
			/** setting up size of form panel */
			this.setPreferredSize(new Dimension(420, positionY +10));
		}
		

	}
	
	
	
	
	/**
	 * Place all components
	 * 
	 * @return void
	 */
	
	private void placeComponents() {
		addTag();
	}
	
	
	
	
	
	/**
	 * Return array list with all tag contained in the form
	 * 
	 * @return array list with all tag contained in the form
	 */
	
	public ArrayList<XmlTag>getTagArr() {
		return tagArr;
	}
	
}
