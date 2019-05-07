package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.DsecureEnum;

public class Dsecure extends XmlAttribute{
	public Dsecure() {
		this.setName("DsecureEnum");
		this.setPossibleValues(new DsecureEnum());
	}
}
