package model.pdscTag;

import model.XmlAttribute;
import model.XmlTag;

public class Conditions extends XmlTag{
	
	
	public Conditions() {
		this.setName("conditions");
		this.addAttr(new XmlAttribute ("id",true,String.class));
		this.addChild(new Accept().setMax(1));
		//this.addChild(new Deny().setMax(MAX_OCCURENCE_NUMBER));
		this.addChild(new Require().setMax(1));

	}
}  
