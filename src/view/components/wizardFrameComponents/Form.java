package view.Components.wizardFrameComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import listeners.FormListener;
import model.XmlAttribute;
import model.XmlTag;
import view.Components.ModelComponents.AttributeComboBox;
import view.Components.ModelComponents.AttributeTextField;
import view.Components.ModelComponents.TagTextField;



public class Form extends JPanel{
	private ArrayList<XmlTag> tagArr;
	private JScrollPane s ;
	
	
	/* bounds */
	private final static int LABEL_HEIGHT = 16;
	private final static int TEXT_HEIGHT = 33;
	private final static int LABEL_WIDTH = 296;
	private final static int TEXT_WIDTH = 296;
	private final static int TEXT_X = 57;
	private final static int LABEL_X = 57;
	private final static int DELTA_LABEL_TEXT = 28;
	private final static int DELTA_TEXT_LABEL = 45;
	private final static int DELTA_TITLE = 36;
	
	private FormListener listener;
	
	private static int positionY ;
	
	public Form(XmlTag tag) {
		this.listener = new FormListener(this);
		positionY = 49;
		this.tag = tag;
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		placeComponents();
	}
	
	public Form(XmlTag tag, FormListener listener) {
		this.listener = listener;
		positionY = 49;
		this.tag = tag;
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		placeComponents();
	}
	
	private void placeAttr(XmlAttribute attr) {
			JLabel attrLabel = new JLabel();
				attrLabel.setForeground(new Color(255, 99, 71));
				attrLabel.setBounds(LABEL_X, positionY ,LABEL_WIDTH, LABEL_HEIGHT);
				positionY += DELTA_LABEL_TEXT;
				if(attr.isRequired()) attrLabel.setText(attr.getName() + " *");
				else attrLabel.setText(attr.getName());
			
			this.add(attrLabel);
			
			if(attr.getPossibleValues().getClass() == String.class) {
				AttributeTextField valueText = new AttributeTextField(attr);
				valueText.setBorder(new LineBorder(Color.LIGHT_GRAY));
				valueText.setForeground(Color.DARK_GRAY);
				valueText.setColumns(10);
				valueText.addFocusListener(listener);
				valueText.setBounds(TEXT_X, positionY, TEXT_WIDTH, TEXT_HEIGHT);
				positionY += DELTA_TEXT_LABEL;
				this.add(valueText);
			}
			else if(attr.getPossibleValues() != null) {
					ArrayList<String> values =  (ArrayList<String>)attr.getPossibleValues();
					AttributeComboBox valuesComboBox = new AttributeComboBox(attr);  
					valuesComboBox.addActionListener(listener);
					valuesComboBox.setPreferredSize(new Dimension(296, 33));
					valuesComboBox.setForeground(Color.DARK_GRAY);
					valuesComboBox.setBounds(TEXT_X, positionY, TEXT_WIDTH, TEXT_HEIGHT);
					
					valuesComboBox.setPreferredSize(new Dimension(296, 33));
					positionY += DELTA_TEXT_LABEL;
					this.add(valuesComboBox);
			}
			
		this.setPreferredSize(new Dimension(420, positionY +10));
		
	}
	


	public void placeComponents() {
		XmlTag tag = new XmlTag();
		for(int i = 0; i < tagArr.size(); i++) {
			tag = tagArr.get(i);
			JLabel titleLabel = new JLabel("PDSC creator");
			titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setBounds(142, 0, 135, 44);  
			add(titleLabel);
			ArrayList <XmlTag> children = new ArrayList();
			children.add(tag);
			while(!children.isEmpty()) {
				XmlTag element = children.get(0);
				JLabel tagNameLabel;
				if(element.isRequired()) {
					tagNameLabel = new JLabel("<"+element.getName()+"> *");
				}
				else {
					tagNameLabel = new JLabel("<"+element.getName()+">");
				}
		
				tagNameLabel.setForeground(new Color(0, 0, 128));
				tagNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				tagNameLabel.setBounds(30, positionY , 123, 24);
				positionY += DELTA_TITLE;
				
				this.add(tagNameLabel);
					
				children.remove(element);
				
				if( element.getSelectedChildren() != null ) {
					element.getSelectedChildren().forEach((c)-> children.add(c));
				}
				else {
					if(element.getContent() != null) {
						TagTextField valueText = new TagTextField(element);
						valueText.setBorder(new LineBorder(Color.LIGHT_GRAY));
						valueText.addFocusListener(listener);
						valueText.setForeground(Color.DARK_GRAY);
						valueText.setColumns(10);
						valueText.setBounds(37, positionY, TEXT_WIDTH, TEXT_HEIGHT);
						positionY += DELTA_TEXT_LABEL;
						this.add(valueText);
					}
				}
		
				if(element.getSelectedAttrArr()!=  null) {
					ArrayList<XmlAttribute> attrArr = element.getSelectedAttrArr();
					attrArr.forEach((a)->placeAttr(a));
				}

				
			}
			this.setPreferredSize(new Dimension(420, positionY +10));
		}
		

	}
	
	
	public XmlTag getTag() {
		return tag;
	}

	public void setTag(XmlTag tag) {
		this.tag = tag;
	}
	
}
