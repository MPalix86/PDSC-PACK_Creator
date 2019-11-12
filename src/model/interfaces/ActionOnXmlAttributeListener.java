package model.interfaces;

import model.interfaces.events.AttributeEvent;

public interface ActionOnXmlAttributeListener {
	public void attributeAdded(AttributeEvent e);
	public void valueChanged(AttributeEvent e);
	public void attributeRemoved(AttributeEvent e);
}
