package view.Components.ModelComponents;

import javax.swing.JButton;

import model.XmlTag;

public class TagBtn extends JButton{
	private String tagName;
	private XmlTag	tag;
	
	public TagBtn(XmlTag tag ) {
		super();
		this.tagName = tag.getName();
		this.tag = tag;
		super.setName(tagName);
		super.setText(tag.getName());
	}
	
	public TagBtn(XmlTag tag , String text) {
		super();
		this.tag = tag;
		super.setText(text);
	}

	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public XmlTag getTag() {
		return tag;
	}
	public void setTag(XmlTag tag) {
		this.tag = tag;
	}
}
