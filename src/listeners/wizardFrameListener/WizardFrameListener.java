package listeners.wizardFrameListener;

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
		
		
		
		if(command.equals("next")) {
			session.getWizardFrame().next();
		}
		
		
		
		else if(command.equals("back")) {
			session.getWizardFrame().back();
		}
		
		
		
		else if(command.equals("updatePreviewPane")) {
			ArrayList<XmlTag> tagArr = session.getWizardFrame().getTagArr();
			Document doc = WizardBusiness.writePdsc(tagArr); 
			if(doc != null) {
				String preview = FileBusiness.getDocumentPreview(doc);
				session.getWizardFrame().setXmlPreview(preview);
			}
		}
		
		
	}
	
	
	

}
