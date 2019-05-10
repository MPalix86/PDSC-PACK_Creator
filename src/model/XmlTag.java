package model;

import java.util.ArrayList;

import business.Utils;

public class XmlTag {
	private String name;
	private String content;
	private ArrayList<XmlAttribute> attrArr;									/* ------------------ DA SPOSTARE NELLE SINGOLE CLASSI--------------*/
	private ArrayList<XmlAttribute> selectedAttrArr;
	private String description;
	private boolean required;
	private  ArrayList<XmlTag> children;										/* ------------------ DA SPOSTARE NELLE SINGOLE CLASSI--------------*/
	private  ArrayList<XmlTag> selectedChildren;
	private XmlTag parent;
	private Integer max;
	private String defaultContent;
	public final static int MAX_OCCURENCE_NUMBER = 1000;
	private XmlNameSpace nameSpace;


	
	public XmlNameSpace getNameSpace() {
		return nameSpace;
	}


	public XmlTag setNameSpace(XmlNameSpace nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}


	private XmlTag (XmlTagBuilder builder) {
		this.name = builder.name;
		this.content = builder.content;
		this.attrArr = builder.attrArr;
		this.description = builder.description;
		this.required = builder.required;
	}
	
	
	public XmlTag setDefaultContent(String content) {
		this.defaultContent = content;
		return this;
	}
	
	public String getDefaultContent() {
		return this.defaultContent;
	}
	
	public XmlTag getParent() {
		return parent;
	}



	public XmlTag setParent(XmlTag parent) {
		this.parent = parent;
		return this;
	}

	
	public ArrayList<XmlTag> getSelectedChildren() {
		return selectedChildren;
	}


	public XmlTag setSelectedChildren(ArrayList<XmlTag> selectedChildren) {
		this.selectedChildren = selectedChildren;
		return this;
	}
	
	public void addSelecteChild(XmlTag tag) {
		if(selectedChildren == null) {
			selectedChildren = new ArrayList<XmlTag>();
		}
		if(!selectedChildren.contains(tag)) {
			this.selectedChildren.add(tag);
		}
		else {
			Utils.print("l'attributo è gia presente tra gli attributi selezionati");
		}
	}
	
	public void removeSelecteChild(XmlTag tag) {
		if(selectedChildren.contains(tag)) {
			this.selectedChildren.remove(tag);
		}
		else {
			Utils.print("l'attributo è gia presente tra gli attributi selezionati");
		}
	}
	
	
	
	public ArrayList<XmlAttribute> getSelectedAttrArr() {
		return selectedAttrArr;
	}

	public XmlTag setSelectedAttrArr(ArrayList<XmlAttribute> selectedAttrArr) {
		this.selectedAttrArr = selectedAttrArr;
		return this;
		
	}


	public void addSelectedAttr(XmlAttribute attr) {
		if(selectedAttrArr == null) {
			selectedAttrArr = new ArrayList<XmlAttribute>();
		}
		if(!selectedAttrArr.contains(attr)) {
			this.selectedAttrArr.add(attr);
		}
		
	}
	
	public void removeSelectedAttr(XmlAttribute attr) {
		if(selectedAttrArr.contains(attr)) {
			this.selectedAttrArr.remove(attr);
		}
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
		this.max = max;								  /*------------------CORRECTION--------------------*/
		return this;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getContent() {
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
	
	public XmlTag setName(String name) {
		this.name = name;
		return this;
	}
	
	public XmlTag setContent(String content) {
		this.content = content;
		return this;
	}
	
	public XmlTag setAttr(ArrayList<XmlAttribute> attrArr) {
		this.attrArr = attrArr;
		return this;
	}
	
	public void addAttr(XmlAttribute attr) {
		if(attrArr == null) {
			attrArr = new ArrayList<XmlAttribute>();
		}
		this.attrArr.add(attr);
	}
	
	public void removeAttr(XmlAttribute attr) {
		this.attrArr.remove(attr);
	}
	
	public boolean isRequired() {
		return this.required;
	}
	
	public XmlTag setRequired(boolean required) {
		this.required = required;
		return this;
	}
	
	public void freeMemory() {
		this.attrArr = null;
		this.children = null;
		this.parent = null;
		this.max = null;
	}
	
	
	
	
	//  builder pattern
	public static class XmlTagBuilder{

		// required parameters
		private String name;
		private boolean required;
		private String content;

		// optional parameters
		boolean isEmpty;
		private ArrayList<XmlAttribute> attrArr;
		private String description;
		private int max;
		
		
		public XmlTagBuilder(String name , boolean required ){
			this.name = name;
			this.required = required;
		}
		
		public XmlTagBuilder content(String content) {
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
