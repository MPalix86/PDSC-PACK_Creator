package model.pdscTag;

import model.XmlAttribute;
import model.XmlTag;
import model.pdscAttributes.Dvendor;
import model.pdscType.DcoreEnum;
import model.pdscType.DeviceVendorEnum;
import model.pdscType.DfpuEnum;
import model.pdscType.DmpuEnum;

public class Require extends XmlTag {
	
	public void setValue() {
		
	}
	
	public Require() {
		this.addChild(new Deny().setMax(2).setParent(this));
		this.setName("require");
		this.addAttr(new Dvendor().setRequired(false));
		this.addAttr(new XmlAttribute ("Dfamily", false, String.class));
		this.addAttr(new XmlAttribute ("DsubFamily", false, String.class));
		this.addAttr(new XmlAttribute ("Dname", false, String.class));
		this.addAttr(new XmlAttribute ("Dvariant", false , String.class));
		this.addAttr(new XmlAttribute ("Pname", false , String.class));
		this.addAttr(new XmlAttribute ("Dcore", false , DcoreEnum.class));
		this.addAttr(new XmlAttribute ("Dfpu ", false , DfpuEnum.class));
		this.addAttr(new XmlAttribute ("Dmpu ", false , DmpuEnum.class));
	}
}
