package view.wizardFrame.comp.xmlForm.comp;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.wizardFrameListeners.comp.xmlForm.XmlFormListener;
import model.XmlAttribute;
import model.XmlTag;

public class TagRow extends JPanel{
	
	XmlFormListener listener = new XmlFormListener(this);
	
	private XmlTag tag;
	
	public TagRow(XmlTag tag) {
		this.tag = tag;
		
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
	}
	
	
	
	public TagRow open() {
		
		TagLabel tagLabel0 = new TagLabel("< " + tag.getName(), this.tag);
		tagLabel0.addMouseListener(listener);
		this.add(tagLabel0);
		
		/** if tag have selected attributes */
		if(tag.getSelectedAttrArr() != null) {
			for(int i = 0; i < tag.getSelectedAttrArr().size(); i++) {
				
				XmlAttribute attr = tag.getSelectedAttrArr().get(i);
				
				AttributeLabel attrLabel0 = new AttributeLabel(" " + attr.getName() + " = \" ", attr,tag);
				attrLabel0.addMouseListener(listener);
				this.add(attrLabel0);
	
				
				/** if attribute have no possible values */
				if(attr.getPossibleValues().getClass() == String.class) {
					AttributeFormTextField textField ;
					
					/** if attribute have value set */
					if(attr.getValue() != null) textField = new AttributeFormTextField(attr, this, attr.getValue());
					else textField = new AttributeFormTextField(attr, this);
					
					textField.addFocusListener(listener);
					this.add(textField);
				}
				/** if attribute have possible values */
				else {
					AttributeFormComboBox valuesComboBox = new AttributeFormComboBox(attr,this);  
					
					/** if attribute have value set */
					if (attr.getValue() != null) valuesComboBox.setSelectedItem(attr.getValue());
					
					valuesComboBox.setForeground(Color.DARK_GRAY);
					valuesComboBox.addFocusListener(listener);
					this.add(valuesComboBox);
				}
				
				/** close attribute value */
				this.add(new AttributeLabel(" \" ", attr,tag));
			}
		}
		
		/** close tag */
		TagLabel tagLabel1 = new TagLabel(" > ",tag);
		tagLabel1.addMouseListener(listener);
		this.add(tagLabel1);
		
		/** if tag have no children */
		if(tag.getSelectedChildrenArr() == null) {
			TagFormTextField tagTextField ;
			
			/** if tag have content set */
			if(tag.getContent() != null) {
				tagTextField = new TagFormTextField(tag,this,tag.getContent());
				System.out.println("diverso da null " + tag.getName());
			}
			else  tagTextField = new TagFormTextField(tag,this);
			
			tagTextField.addFocusListener(listener);
			this.add(tagTextField);
			this.add(new TagLabel("</" + tag.getName() + "> ",tag));
		}
		
		return this;
		
	}
	
	public TagRow close() {
		TagLabel tagLabel1 = new TagLabel("< /" + tag.getName() + " >",tag);
		tagLabel1.addMouseListener(listener);
		this.add(tagLabel1);
		return this;
	}
	
	public TagRow getVoidRow() {
		return this;
	}

}
