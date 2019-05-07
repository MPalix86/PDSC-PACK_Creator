package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.DtzEnum;

public class Dtz extends XmlAttribute{
	public Dtz() {
		this.setName("Dtz");
		this.setPossibleValues(new DtzEnum());
	}
}
 