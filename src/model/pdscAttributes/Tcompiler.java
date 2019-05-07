package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.CompilerEnumType;

public class Tcompiler extends XmlAttribute {
	public Tcompiler() {
		this.setName("Tcompiler");
		this.setPossibleValues(new CompilerEnumType());
	}
	
}
