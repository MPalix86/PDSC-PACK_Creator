package listeners.wizardFrameListeners.comp;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.Session;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.XmlFormContainer;

public class TabContainerListener implements ChangeListener{
	private Session session;
	
	public TabContainerListener() {
		session = Session.getInstance();
	}
	 public void stateChanged(ChangeEvent changeEvent) {
	        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
	        int index = sourceTabbedPane.getSelectedIndex();
	        
	        /**
	         * updating selectedForm in Session based on tab focus
	         */
	        if(sourceTabbedPane.getSelectedComponent().getClass() == XmlFormContainer.class) {
	        	XmlFormContainer formContainer = (XmlFormContainer) sourceTabbedPane.getSelectedComponent();
	        	XmlForm form = formContainer.getFormPanel();
	        	session.setSelectedForm(form);
	        }

	      }

}
