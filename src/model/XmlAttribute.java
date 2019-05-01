package model;

import model.Exception.TypeMismatchException;

public class XmlAttribute {
	private String name;
	private String value;
	private boolean required;
	private Class valueType;
	
	public XmlAttribute (String name , String value , Class valueType , boolean required ) throws TypeMismatchException {
		this.name = name;
		this.value = value;
		this.required = required;
		this.valueType = valueType;
		if(this.valueType != value.getClass()) {
			throw new TypeMismatchException("Type mismatch"); 
		}
	}
	
	public XmlAttribute (String name  , boolean required , Class valueType) {
		this.name = name;
		this.required = required;
		this.valueType = valueType;
	}
	
	
	public XmlAttribute () {
	}

	public String getName() {
		return name;
	}
	
	public Object getValueType() {
		return valueType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) throws TypeMismatchException {
		if(this.valueType != null) {
			if(this.valueType != value.getClass()) {
				throw new TypeMismatchException("Type mismatch"); 
			}
		}
		else {
			this.value = value;
		}
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public void setValueType(Class c) {
		this.valueType = c;
	}
	
	
	
}
