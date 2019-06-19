package view.comp;

import javax.swing.JTextField;

import model.XmlTag;

public class TagTextField extends JTextField {
	protected XmlTag tag;
	
	public TagTextField(XmlTag tag){
		super();
		this.tag = tag;

	}
	
	
	
	public TagTextField(XmlTag tag,String text){
		super(text);
		this.tag = tag;

	}
	
	
	public void setTagContent(){
		tag.setContent(this.getText());
	}
	
	public XmlTag getTag() {
		return this.tag;
	}
	

}
