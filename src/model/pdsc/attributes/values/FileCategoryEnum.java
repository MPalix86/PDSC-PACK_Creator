package model.pdsc.attributes.values;

import model.XmlEnum;

public class FileCategoryEnum extends XmlEnum{
	public FileCategoryEnum() {
		this.add("");
		
		this.add("doc");
		this.add("header");
		this.add("include");
		this.add("library");
		this.add("object");
		this.add("source");
		this.add("sourceC");
		this.add("sourceCpp");
		this.add("sourceAsm");
		this.add("linkerScript");
		this.add("utility");
		this.add("image");
		this.add("preIncludeGlobal");
		this.add("preIncludeLocal");
		this.add("other");
	}

}
