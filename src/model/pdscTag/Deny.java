package model.pdscTag;

import model.XmlAttribute;
import model.XmlTag;
import model.pdscType.DcoreEnum;
import model.pdscType.DeviceVendorEnum;
import model.pdscType.DfpuEnum;
import model.pdscType.DmpuEnum;

/* 
 * this class represnt pdsc tag <deny> that have same structure of pdsc tag <deny>
 */
public class Deny extends XmlTag{
	public Deny(){
		this.setName("deny");
		this.addAttr(new XmlAttribute ("Dvendor", false, DeviceVendorEnum.class));
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
