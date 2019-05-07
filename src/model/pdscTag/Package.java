package model.pdscTag;

import model.XmlTag;
import model.pdscAttributes.Dcore;
import model.pdscAttributes.Dname;
import model.pdscAttributes.Dvendor;
import model.pdscAttributes.SchemaVersion;
import model.pdscAttributes.Tcompiler;
import model.pdscAttributes.Xmlnsxs;
import model.pdscAttributes.XsnoNamespaceSchemaLocation;

public class Package extends XmlTag {
	
	public Package(){
		this.setName("package");
		this.addAttr(new SchemaVersion().setRequired(true));
		this.addAttr(new Xmlnsxs().setRequired(true));
		this.addAttr(new XsnoNamespaceSchemaLocation().setRequired(true));
		this.addAttr(new Dcore().setRequired(false));
		this.addAttr(new Dvendor().setRequired(false));
		this.addAttr(new Dname().setRequired(false));
		this.addAttr(new Tcompiler().setRequired(false));
	}
}
