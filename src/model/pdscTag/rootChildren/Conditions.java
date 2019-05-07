package model.pdscTag.rootChildren;

import model.XmlTag;
import model.pdscAttributes.Id;
import model.pdscTag.Accept;
import model.pdscTag.Deny;
import model.pdscTag.Require;

public class Conditions extends XmlTag{
	
	
	public Conditions() {
		this.setName("conditions");
		this.addAttr(new Id().setRequired(true));
		this.addChild(new Accept().setMax(1).setParent(this));
		this.addChild(new Deny().setMax(MAX_OCCURENCE_NUMBER).setParent(this));
		this.addChild(new Require().setMax(1).setParent(this));
	}
}  
