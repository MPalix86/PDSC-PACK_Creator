package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.DeviceVendorEnum;

public class Dvendor extends XmlAttribute{
	public Dvendor() {
		this.setName("Dvendor" );
		this.setPossibleValues(new DeviceVendorEnum());
	}
}
