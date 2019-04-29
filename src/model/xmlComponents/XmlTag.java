package model.xmlComponents;

import java.util.ArrayList;

import model.Response;
import model.Response.ResponseBuilder;

public class XmlTag {
	private String name;
	private XmlTagContents content;
	private ArrayList<XmlAttribute> attrArr;
	private String description;
	private boolean required;
	
	private XmlTag (XmlTagBuilder builder) {
		this.name = builder.name;
		this.content = builder.content;
		this.attrArr = builder.attrArr;
		this.description = builder.description;
		this.required = builder.required;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public XmlTagContents getContent() {
		return this.content;
	}
	
	public ArrayList<XmlAttribute> getAttrArr() {
		return this.attrArr;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setContent(XmlTagContents content) {
		this.content = content;
	}
	
	public void setAttr(ArrayList<XmlAttribute> attrArr) {
		this.attrArr = attrArr;
	}
	
	public void addAttr(XmlAttribute attr) {
		if(attrArr == null) {
			attrArr = new ArrayList<XmlAttribute>();
		}
		this.attrArr.add(attr);
	}
	public boolean isRequired() {
		return this.required;
	}
	
	
	
	
	
	//  builder pattern
	public static class XmlTagBuilder{

		// required parameters
		private String name;
		private boolean required;
		private XmlTagContents content;

		// optional parameters
		boolean isEmpty;
		private ArrayList<XmlAttribute> attrArr;
		private String description;
		
		
		public XmlTagBuilder(String name , boolean required , XmlTagContents content){
			this.name = name;
			this.required = required;
			this.content = content;
		}
		
		public XmlTagBuilder content(XmlTagContents content) {
			this.content = content;
			return this;
		}
		
		public XmlTagBuilder attr(ArrayList<XmlAttribute> attrArr) {
			this.attrArr = attrArr;
			return this;
		}
		
		
		public XmlTagBuilder descritpion(String description) {
			this.description = description;
			return this;
		}
		
		public XmlTag build(){
			return new XmlTag(this); 
		}

	}
}
