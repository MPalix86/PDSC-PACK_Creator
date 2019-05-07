package model;

public class XmlAttribute {
	private String name;
	private String value;
	private boolean required;
	private Object possibleValues;
	
	public XmlAttribute (String name , String value ,  Object possibleValues, boolean required) {
		this.name = name;
		this.value = value;
		this.required = required;
		this.possibleValues = possibleValues;
	}
	
	
	
	public Object getPossibleValues() {
		return possibleValues;
	}



	public XmlAttribute setPossibleValues(Object possibleValues) {
		this.possibleValues = possibleValues;
		return this;
	}



	public XmlAttribute () {
	}

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(String value) {
		 this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	//--------------------------------------------------------------------------
	public XmlAttribute setRequired(boolean required) {
		this.required = required;
		return this;
	}
	
}
