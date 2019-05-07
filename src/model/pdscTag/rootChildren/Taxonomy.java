package model.pdscTag.rootChildren;

import model.XmlTag;
import model.pdscTag.Description;

public class Taxonomy extends XmlTag{
	public Taxonomy() {
		this.setName("taxonomy");
		
		this.addChild(new Description().setRequired(true).setMax(MAX_OCCURENCE_NUMBER).setParent(this));
	}
}
