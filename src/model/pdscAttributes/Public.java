package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.BooleanType;

public class Public extends XmlAttribute {
	public Public() {
		this.setName("public");
		this.setPossibleValues(new BooleanType());
	}
}
