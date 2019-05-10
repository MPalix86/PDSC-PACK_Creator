package model;

public class XmlAttribute {
	private String name;
	private String value;
	private String defaultValue;
	private boolean required;
	private Object possibleValues;
	private XmlNameSpace nameSpace ;
	
	
	public XmlNameSpace getNameSpace() {
		return nameSpace;
	}


	public XmlAttribute setNameSpace(XmlNameSpace nameSpace) {
		this.nameSpace = nameSpace;
		return this;
	}


	public XmlAttribute (String name , String value ,  Object possibleValues, boolean required) {
		this.name = name;
		this.value = value;
		this.required = required;
		this.possibleValues = possibleValues;
	}
	
	
	public void setDefaultContent(String value) {
		this.defaultValue = value;
	}
	
	public String getDefaultContent() {
		return this.defaultValue;
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
