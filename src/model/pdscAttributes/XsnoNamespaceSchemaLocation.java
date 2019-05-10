package model.pdscAttributes;

import model.XmlAttribute;
import model.XmlNameSpace;

public class XsnoNamespaceSchemaLocation extends XmlAttribute{
	public XsnoNamespaceSchemaLocation() {
		XmlNameSpace xs = new XmlNameSpace("xs", "http://www.w3.org/2001/XMLSchema-instance");
		
		this.setNameSpace(xs);
		this.setName("noNamespaceSchemaLocation");
		this.setPossibleValues(new String());
	}
}
