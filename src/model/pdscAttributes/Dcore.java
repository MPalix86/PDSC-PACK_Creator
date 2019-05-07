package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.DcoreEnum;

public class Dcore extends XmlAttribute {
	public Dcore() {
		this.setName("Dcore");
		this.setPossibleValues(new DcoreEnum());
	}
}
