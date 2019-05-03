package model.pdscAttributes;

import model.XmlAttribute;
import model.Exceptions.TypeMismatchException;
import model.pdscType.DeviceVendorEnum;

public class Dvendor extends XmlAttribute {
	public Dvendor() {
		super.setName("Dvendor");
		super.setValueType(DeviceVendorEnum.class);
	}
	
}
