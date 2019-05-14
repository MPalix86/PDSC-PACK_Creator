package model.pdsc.attributes.values;

import model.XmlEnum;

public class DsecureEnum extends XmlEnum{
	public DsecureEnum() {
		this.add("");
		
		this.add("Secure ");
		this.add("Non-secure");
	}
}
