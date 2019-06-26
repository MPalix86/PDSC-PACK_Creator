package listeners.wizardFrameListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;

public class WizardFrameListener implements ActionListener {

	
	private Session session = Session.getInstance();
	
	public WizardFrameListener() {	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("updatePreview")) {
			
			Document doc = FileBusiness.genratePDSCDocument(session.getSelectedForm().getRoot());
			if(doc != null) {
				FileBusiness.validateXMLSchema(doc);
				String preview = FileBusiness.getDocumentPreview(doc);
			}
		}
	}
	
	
	

}
