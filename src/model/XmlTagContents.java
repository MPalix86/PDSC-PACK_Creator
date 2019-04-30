package model;

import java.util.ArrayList;

public class XmlTagContents {
	private String value;
	private ArrayList<XmlTag> tagArr;
	
	
	public XmlTagContents (String value , ArrayList<XmlTag> tagArr) {
		this.value = value;
		this.tagArr = tagArr;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public ArrayList<XmlTag> getTagArr() {
		return tagArr;
	}


	public void setTagArr(ArrayList<XmlTag> tagArr) {
		this.tagArr = tagArr;
	}
	
	public void addTag(XmlTag tag) {
		if(tagArr == null) {
			tagArr = new ArrayList<XmlTag>();
		}
		tagArr.add(tag);
	}
	

}
