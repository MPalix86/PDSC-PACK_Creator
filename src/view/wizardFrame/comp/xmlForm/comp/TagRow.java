package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.wizardFrameListeners.comp.xmlForm.XmlFormListener;
import model.XmlAttribute;
import model.XmlNameSpace;
import model.XmlTag;
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.XmlForm;

public class TagRow extends JPanel{
	
	XmlFormListener listener;
	
	private XmlForm form;
	private XmlTag tag;
	private AttributeLabel attrLabel0;
	private TagLabel tagLabel0;
	private TagLabel tagLabel1 ;
	private TagLabel tagLabel2;
	private int leftBorder;
	




	public TagRow(XmlTag tag, XmlForm form) {
		this.tag = tag;
		this.form = form;
		
		listener = new XmlFormListener(this.form);
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
	}
	
	
	
	public TagRow open() {
		
		if(tag.getSelectedAttrArr() == null)  tagLabel0 = new TagLabel("< " + tag.getName()+ " >", this.tag );
		else  tagLabel0 = new TagLabel("< " + tag.getName(), this.tag);
		
		tagLabel0.addMouseListener(listener);
		this.add(tagLabel0);
		
		/** if tag have nameSpace */
		if(tag.getNameSpace() != null) {
			XmlNameSpace n = tag.getNameSpace();
			NameSpaceLabel nameSPaceLabel = new NameSpaceLabel(n, " xmlns:" + n.getPrefix()+ " =  \" "); 
			NameSpaceLabel nameSPaceLabel1 = new NameSpaceLabel(n, " \" " ); 
			NameSpaceFormTextField nameSpaceTextField;
			if(n.getUrl() != null)  nameSpaceTextField = new NameSpaceFormTextField(n, n.getUrl(), this);
			else nameSpaceTextField = new NameSpaceFormTextField(n, this);
			this.add(nameSPaceLabel);
			this.add(nameSpaceTextField);
			this.add(nameSPaceLabel1);
		}
		
		/** if tag have selected attributes */
		if(tag.getSelectedAttrArr() != null) {
			for(int i = 0; i < tag.getSelectedAttrArr().size(); i++) {
				XmlAttribute attr = tag.getSelectedAttrArr().get(i);  
				
//				/** if attribute have nameSpace */
//				if (attr.getNameSpace() != null) attrLabel0 = new AttributeLabel(attr.getNameSpace().getPrefix()+":"+ attr.getName() + " = \" ", attr);
//				else 
					attrLabel0 = new AttributeLabel(" " + attr.getName() + " = \" ", attr);
				
				
				attrLabel0.addMouseListener(listener);
				this.add(attrLabel0);
				
				/** if attribute have no possible values */
				if(attr.getPossibleValues().getClass() == String.class) {
					AttributeFormTextField textField ;
					
					/** if attribute have value set */
					if(attr.getValue() != null) textField = new AttributeFormTextField(attr, attr.getValue(),this);
					else textField = new AttributeFormTextField(attr,this);
					
					textField.addFocusListener(listener);
					this.add(textField);
				}
				/** if attribute have possible values */
				else {
					AttributeFormComboBox valuesComboBox = new AttributeFormComboBox(attr,this);  
					
					if (attr.getDefaultValue() != null && attr.getValue() == null ) {
						valuesComboBox.setSelectedItem(attr.getDefaultValue());
					}
					
					/** if attribute have value set */
					else if (attr.getValue() != null) valuesComboBox.setSelectedItem(attr.getValue());
					
					valuesComboBox.setForeground(Color.DARK_GRAY);
					valuesComboBox.addFocusListener(listener);
					
					this.add(valuesComboBox);
				}
				
				/** close attribute value */
				this.add(new AttributeLabel(" \" ", attr));
				
				/** add space */
				this.add(new JLabel("  "));
			}
			
			/** close tag */
			tagLabel1 = new TagLabel(" > ",tag);
			tagLabel1.addMouseListener(listener);
			this.add(tagLabel1);
		}
		

		
		/** if tag have no children */
		if(tag.getSelectedChildrenArr() == null) {
			TagFormTextField tagTextField ;
			
			/** if tag have content set */
			if(tag.getContent() != null) {
				tagTextField = new TagFormTextField(tag,this,tag.getContent());
			}
			else  tagTextField = new TagFormTextField(tag,this);
			
			tagTextField.addFocusListener(listener);
			this.add(tagTextField);
			
			tagLabel2 = new TagLabel("</" + tag.getName() + "> ",tag);
			tagLabel2.addMouseListener(listener);
			this.add(tagLabel2);
		}
		
		return this;
		
	}
	
	public TagRow close() {
		tagLabel2 = new TagLabel("< /" + tag.getName() + " >",tag);
		tagLabel2.addMouseListener(listener);
		this.add(tagLabel2);
		return this;
	}
	

	public void setTagLabelBrighter() {
		if (tagLabel0 != null) tagLabel0.setForeground(CustomColor.TAG_COLOR_BRIGHTER);
		if (tagLabel2 != null) tagLabel2.setForeground(CustomColor.TAG_COLOR_BRIGHTER);
	}
	
	

	public void unsetTagLabelBrighter() {
		if (tagLabel0 != null) tagLabel0.setForeground(CustomColor.TAG_COLOR);
		if (tagLabel2 != null) tagLabel2.setForeground(CustomColor.TAG_COLOR);
	}
	
	
	
	
	
	
	/**
	 * @return the tagLabel0
	 */
	public TagLabel getTagLabel0() {
		return tagLabel0;
	}



	/**
	 * @return the tagLabel1
	 */
	public TagLabel getTagLabel1() {
		return tagLabel1;
	}



	/**
	 * @return the tagLabel2
	 */
	public TagLabel getTagLabel2() {
		return tagLabel2;
	}
	
	
	/**
	 * @return the leftBorder
	 */
	public int getLeftBorder() {
		return leftBorder;
	}



	/**
	 * @param leftBorder the leftBorder to set
	 */
	public void setLeftBorder(int leftBorder) {
		this.leftBorder = leftBorder;
	}
	

}
