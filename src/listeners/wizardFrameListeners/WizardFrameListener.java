package listeners.wizardFrameListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.WizardBusiness;
import model.XmlTag;

public class WizardFrameListener implements ActionListener {

	
	private Session session = Session.getInstance();
	
	public WizardFrameListener() {	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("updatePreview")) {
			ArrayList<XmlTag> tagArr = session.getWizardFrame().getFormPanelContainer().getFormPanel().getTagArr();
			Document doc = WizardBusiness.writePdsc(tagArr); 
			if(doc != null) {
				FileBusiness.validateXMLSchema(null, doc);
				String preview = FileBusiness.getDocumentPreview(doc);
				session.getWizardFrame().getPreviewPaneContainer().getPreviewPane().setPreview(preview);
			}
		}
		
	}
	
	
	

}
