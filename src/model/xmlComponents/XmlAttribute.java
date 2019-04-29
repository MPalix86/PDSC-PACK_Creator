package model.xmlComponents;

import model.Response;


public class XmlAttribute {
	private String name;
	private String value;
	private boolean required;
	
	public XmlAttribute (String name , String value , boolean required) {
		this.name = name;
		this.value = value;
		this.required = required;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	
}
