package model;

public class XmlAttribute {
	private String name;
	private String value;
	private boolean required;
	private Class valueType;
	
	public XmlAttribute (String name , String value , Class valueType , boolean required ) {
		this.name = name;
		this.value = value;
		this.required = required;
		this.valueType = valueType;
		if(this.valueType != value.getClass()) {
			System.out.println("value mismatch");
		}
	}
	
	public XmlAttribute (String name  , boolean required) {
		this.name = name;
		this.required = required;
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

	public void setValue(String value) {
		if(this.valueType != null) {
			System.out.println("valuetype diverso da null");
			if(this.valueType != value.getClass()) {
				System.out.println("value mismatch");
			}
		}
		
		this.value = value;
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
