package model.pdscAttributes;

import model.XmlAttribute;
import model.pdscType.DfpuEnum;

public class Dfpu extends XmlAttribute{
	public Dfpu() {
		this.setName("Dfpu");
		this.setPossibleValues(new DfpuEnum());
	}
}
  