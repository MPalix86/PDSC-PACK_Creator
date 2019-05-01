package model.pdscComponent;

import model.XmlAttribute;
import model.XmlTag;
import model.pdscType.DcoreEnum;
import model.pdscType.DeviceVendorEnum;
import model.pdscType.DfpuEnum;
import model.pdscType.DmpuEnum;

/* 
 * this class represnt pdsc tag <accept> that have same structure of pdsc tag <require>ß
 */
public class Accept extends XmlTag{
	public Accept() {
		this.setName("accept");
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
