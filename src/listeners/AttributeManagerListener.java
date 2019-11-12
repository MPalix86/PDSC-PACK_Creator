package listeners;

import business.Session;
import model.interfaces.ActionOnXmlAttributeListener;
import model.interfaces.events.AttributeEvent;

public class AttributeManagerListener implements ActionOnXmlAttributeListener{
	private static Session session = Session.getInstance();

	@Override
	public void attributeAdded(AttributeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(AttributeEvent e) {

	}

	@Override
	public void attributeRemoved(AttributeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
