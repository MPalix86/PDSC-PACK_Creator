package model;

import model.Exceptions.TypeMismatchException;

public class XmlAttribute {
	private String name;
	private Object value;
	private boolean required;
	private Class valueType;
	
	public XmlAttribute (String name , Object value , Class valueType , boolean required ) throws TypeMismatchException {
		this.name = name;
		this.value = value;
		this.required = required;
		this.valueType = valueType;
		if(this.valueType != value.getClass()) {
			throw new TypeMismatchException("Type mismatch"); 
		}
	}
	
	private XmlAttribute (XmlAttributeBuilder builder ) throws TypeMismatchException {
		this.name = builder.name;
		this.value = builder.value;
		this.required = builder.required;
		this.valueType = builder.valueType;
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

	public Object getValue() {
		return value;
	}

	public XmlAttribute setValue(Object value) throws TypeMismatchException {
		if(this.valueType != null) {
			if(this.valueType != value.getClass()) {
				throw new TypeMismatchException("Type mismatch"); 
			}
		}
		else {
			this.value = value;
		}
		return this;
	}

	public boolean isRequired() {
		return required;
	}

	//--------------------------------------------------------------------------
	public XmlAttribute setRequired(boolean required) {
		this.required = required;
		return this;
	}
	
//	public void setRequired(boolean required) {
//		this.required = required;
//	}
	
	public void setValueType(Class c) {
		this.valueType = c;
	} 

	public static class XmlAttributeBuilder{
		
		//required parementers
		private String name;
		private Class valueType;
		
		//optional paramenters
		private Object value;
		private boolean required;
		
		public XmlAttributeBuilder(String name, Class valueType ) {
			
			this.name = name;
			this.valueType = valueType;
		}
		
		public XmlAttributeBuilder value(Object value)throws TypeMismatchException{
			if (value.getClass() != this.valueType) {
				throw new TypeMismatchException("Mysmatch exception");
			}
			this.value = value;
			return this;
		}
		
		public XmlAttributeBuilder required(boolean required){
			this.required = required;
			return this;
		}
		
		public XmlAttribute build() {
			return new XmlAttribute();
		}
	
	}
}
