package view.comp;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import model.xml.XmlTag;

public class TagComboBox extends JComboBox<String>{
	private XmlTag tag;
	
	public TagComboBox(XmlTag tag) { 
		this.tag = tag;
		ArrayList<String> values = (ArrayList<String>) tag.getPossibleValues();
		this.setModel(new DefaultComboBoxModel(values.toArray()));
	}
	
	public XmlTag getTag() {
		return this.tag;   
	}

}
