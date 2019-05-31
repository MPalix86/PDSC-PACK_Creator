package view.comp;

import javax.swing.JTextField;

import model.XmlTag;

public class TagTextField extends JTextField {
	private XmlTag tag;
	
	public TagTextField(XmlTag tag){
		super();
		this.tag = tag;
		if(tag.getDefaultContent() != null) {
			this.setText(tag.getDefaultContent());
			tag.setContent(tag.getDefaultContent());
		}
	}
	
	
	
	public TagTextField(XmlTag tag,String text){
		super(text);
		this.tag = tag;
		if(tag.getDefaultContent() != null) {
			this.setText(tag.getDefaultContent());
			tag.setContent(tag.getDefaultContent());
		}
	}
	
	
	public void setTagContent(){
		tag.setContent(this.getText());
	}
	
	public XmlTag getTag() {
		return this.tag;
	}
	

}
