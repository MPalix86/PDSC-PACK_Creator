package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.DmpuEnum;

public class Dmpu extends XmlAttribute{
	public Dmpu() {
		this.setName("Dmpu");
		this.setPossibleValues(new DmpuEnum());
	}
}
