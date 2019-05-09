package view.Components.ModelComponents;

import javax.swing.JTextField;

import model.XmlTag;

public class TagTextField extends JTextField {
	private XmlTag tag;
	
	public TagTextField(XmlTag tag){
		super();
		this.tag = tag;
	}
	
	public void setTagContent(){
		tag.setContent(this.getText());
	}
	
	public XmlTag getTag() {
		return this.tag;
	}
	

}