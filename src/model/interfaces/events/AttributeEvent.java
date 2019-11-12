package model.interfaces.events;

import java.util.EventObject;

import model.xml.XmlAttribute;

public class AttributeEvent extends EventObject{
	
	 public AttributeEvent(XmlAttribute source) {
	        super(source);
	   }

}
