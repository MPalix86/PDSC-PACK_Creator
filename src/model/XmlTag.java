package model;

import java.util.ArrayList;

public class XmlTag {
	private String name;
	private XmlTagContents content;
	private ArrayList<XmlAttribute> attrArr;
	private String description;
	private boolean required;
	private  ArrayList<XmlTag> children;
	private Integer max;
	public final static int MAX_OCCURENCE_NUMBER = 1000;



	private XmlTag (XmlTagBuilder builder) {
		this.name = builder.name;
		this.content = builder.content;
		this.attrArr = builder.attrArr;
		this.description = builder.description;
		this.required = builder.required;
	}
	
	public XmlTag () {

	}
	
	
	public Integer getMax() {
		return max;
	}

//	public void setMax(int max) {
//		this.max = max;
//	}
	
	public XmlTag setMax(Integer max) {
		this.max = max;
		return this;
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
	
	public ArrayList<XmlTag> getChildren() {
		return this.children;
	}
	
	
	public void addChild(XmlTag child) {
		if( children == null) {
			children = new ArrayList<XmlTag>();
		}
		this.children.add(child);
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
		private int max;
		
		
		public XmlTagBuilder(String name , boolean required ){
			this.name = name;
			this.required = required;
		}
		
		public XmlTagBuilder content(XmlTagContents content) {
			this.content = content;
			return this;
		}
		
		
		public XmlTagBuilder max(int max) {
			this.max = max;
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
