package model.PdscComponent;

import model.XmlAttribute;
import model.XmlTag;

public class Require extends XmlTag {
	
	public void setValue() {
		
	}
	
	public Require() {
		this.setName("require");
		this.addAttr(new XmlAttribute ("Dvendor",false));
		this.addAttr(new XmlAttribute ("Dfamily",false));
		this.addAttr(new XmlAttribute ("DsubFamily",false));
		this.addAttr(new XmlAttribute ("Dname",false));
		this.addAttr(new XmlAttribute ("Dvariant",false));
		this.addAttr(new XmlAttribute ("Pname",false));
		this.addAttr(new XmlAttribute ("Dcore",false));
		this.addAttr(new XmlAttribute ("Dfpu ",false));
		this.addAttr(new XmlAttribute ("Dmpu ",false));
	}
}
