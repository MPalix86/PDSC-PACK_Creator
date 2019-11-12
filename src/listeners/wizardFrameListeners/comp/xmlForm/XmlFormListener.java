package listeners.wizardFrameListeners.comp.xmlForm;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import business.Session;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.comp.XmlFormOptionMenu;

public class XmlFormListener implements MouseListener{
	
	
	private XmlForm xmlForm;
	private Session session;
	
	
	public XmlFormListener (XmlForm xmlForm) {
		this.xmlForm = xmlForm;
		session = Session.getInstance();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3) {
			new XmlFormOptionMenu().show(e.getComponent(), e.getX(), e.getY());
		}
		xmlForm.restoreComponentsBackGround();
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	



}
