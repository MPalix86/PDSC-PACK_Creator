package model.interfaces.events;

import java.util.EventObject;

import model.xml.XmlTag;

public class TagEvenet extends EventObject{
	
	 public TagEvenet(XmlTag source) {
	        super(source);
	   }

}
