package view.comp;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import model.XmlTag;

public class TagComboBox extends JComboBox<String>{
	private XmlTag tag;
	
	public TagComboBox(XmlTag tag) { 
		this.tag = tag;
		ArrayList<String> values = (ArrayList<String>) tag.getPossibleValues();
		this.setModel(new DefaultComboBoxModel(values.toArray()));
	}
	
	public void setTagContent() {
		tag.setContent((String)this.getSelectedItem());
	}
	
	public XmlTag getTag() {
		return this.tag;   
	}

}
