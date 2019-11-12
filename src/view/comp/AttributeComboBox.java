package view.comp;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import model.xml.XmlAttribute;

public class AttributeComboBox extends JComboBox<String> {
	protected XmlAttribute attr;
	
	public AttributeComboBox(XmlAttribute attr) {
		this.attr = attr;
		ArrayList<String> values = (ArrayList<String>) attr.getPossibleValues();
		this.setModel(new DefaultComboBoxModel(values.toArray()));
	}
	
  public boolean containsItem(Object o) {
	int itemCount = getItemCount();
	for (int i = 0 ; i < itemCount ; i ++) {
		if(o.equals(this.getItemAt(i))) {
			return true;
		}
	}
	return false;
}
	
	public XmlAttribute  getAttr() {
		return this.attr;
	}
	
	

}
