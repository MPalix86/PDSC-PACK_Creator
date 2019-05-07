package model.pdscTag;

import model.XmlTag;
import model.pdscAttributes.Dcore;
import model.pdscAttributes.Dfamily;
import model.pdscAttributes.Dfpu;
import model.pdscAttributes.Dmpu;
import model.pdscAttributes.Dname;
import model.pdscAttributes.DsubFamily;
import model.pdscAttributes.Dvariant;
import model.pdscAttributes.Dvendor;
import model.pdscAttributes.Pname;

/* 
 * this class represnt pdsc tag <accept> that have same structure of pdsc tag <require>ß
 */
public class Accept extends XmlTag{
	public Accept() {
		this.setName("accept");
		
		this.addAttr(new Dvendor().setRequired(false));
		this.addAttr(new Dfamily().setRequired(false));
		this.addAttr(new DsubFamily().setRequired(false));
		this.addAttr(new Dname().setRequired(false));
		this.addAttr(new Dvariant().setRequired(false));
		this.addAttr(new Pname().setRequired(false));
		this.addAttr(new Dcore().setRequired(false));
		this.addAttr(new Dfpu().setRequired(false));
		this.addAttr(new Dmpu().setRequired(false));
	}
}
