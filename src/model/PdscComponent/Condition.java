package model.PdscComponent;

import model.XmlAttribute;
import model.XmlTag;

public class Condition extends XmlTag{
	
	public Condition() {
		this.addAttr(new XmlAttribute ("Dvendor",false));
		this.addAttr(new XmlAttribute ("Dfamily",false));
		this.addAttr(new XmlAttribute ("DsubFamily",false));
	}
}
