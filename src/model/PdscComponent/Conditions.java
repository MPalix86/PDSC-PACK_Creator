package model.pdscComponent;

import model.XmlAttribute;
import model.XmlTag;

public class Conditions extends XmlTag{
	
	public Conditions() {
		this.setName("conditions");
		this.addAttr(new XmlAttribute ("id",true,String.class));
		Deny d = new Deny();
		d.setMax(1);
		Require r = new Require();
		r.setMax(1);
		Accept a = new Accept();
		a.setMax(1);
		this.addChild(d);
		this.addChild(r);
		this.addChild(a);
	}
}
