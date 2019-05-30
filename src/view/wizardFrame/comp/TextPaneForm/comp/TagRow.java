package view.wizardFrame.comp.TextPaneForm.comp;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.XmlAttribute;
import model.XmlTag;

public class TagRow extends JPanel{
	private XmlTag tag;
	
	public TagRow(XmlTag tag) {
		this.tag = tag;
		
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
	}
	
	
	
	public TagRow open() {
		
		this.add(new TagLabel("< " + tag.getName(), this.tag));
		
		/** if tag have selected attributes */
		if(tag.getSelectedAttrArr() != null) {
			for(int i = 0; i < tag.getSelectedAttrArr().size(); i++) {
				
				XmlAttribute attr = tag.getSelectedAttrArr().get(i);
				
				/** open attribute value */
				this.add(new AttributeLabel(" " + attr.getName() + " = \" ", attr));
				
				/** if attribute have no default values */
				if(attr.getPossibleValues().getClass() == String.class) {
					AttributeFormTextField textField = new AttributeFormTextField(attr, this);
					this.add(textField);
				}
				/** if attribute have default values */
				else {
					AttributeFormComboBox valuesComboBox = new AttributeFormComboBox(attr,this);  
					valuesComboBox.setForeground(Color.DARK_GRAY);
					this.add(valuesComboBox);
				}
				
				/** close attribute value */
				this.add(new AttributeLabel(" \" ", attr));
			}
		}
		
		/** close tag */
		this.add(new TagLabel(" > ",tag));
		
		/** if tag have no children */
		if(tag.getSelectedChildrenArr() == null) {
			TagFormTextField tagTextField = new TagFormTextField(tag,this);
			this.add(tagTextField);
			this.add(new TagLabel("</" + tag.getName() + "> ",tag));
		}
		
		return this;
		
	}
	
	public TagRow close() {
		this.add(new TagLabel("< /" + tag.getName() + " >",tag));
		return this;
	}
	
	public TagRow getVoidRow() {
		return this;
	}
	
	
	
}
