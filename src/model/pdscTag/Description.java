package model.pdscTag;

import model.XmlTag;
import model.pdscAttributes.Cclass;
import model.pdscAttributes.Cgroup;
import model.pdscAttributes.Doc;
import model.pdscAttributes.Generator;
import model.pdscAttributes.Public;

public class Description extends XmlTag{
	public Description() {
		this.setName("description");
		this.setContent(new String());
		
		this.addAttr(new Cclass().setRequired(true));
		this.addAttr(new Cgroup().setRequired(false));
		this.addAttr(new Doc().setRequired(false));
		this.addAttr(new Generator().setRequired(false));
		this.addAttr(new Public().setRequired(false));
	}
}
