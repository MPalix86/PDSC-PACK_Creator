package view.comp;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import model.XmlAttribute;

public class AttributeComboBox extends JComboBox<String>{
	private XmlAttribute attr;
	
	public AttributeComboBox(XmlAttribute attr) { 
		this.attr = attr;
		ArrayList<String> values = (ArrayList<String>) attr.getPossibleValues();
		this.setModel(new DefaultComboBoxModel(values.toArray()));
		this.attr.setValue("");
	}
	
	public void setAttrValue() {
		attr.setValue((String)this.getSelectedItem());
	}
	
	public XmlAttribute  getAttr() {
		return this.attr;
	}

}
