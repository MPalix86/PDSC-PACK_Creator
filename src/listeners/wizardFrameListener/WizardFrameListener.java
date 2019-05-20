package listeners.wizardFrameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.WizardBusiness;
import model.XmlTag;
import view.wizardFrame.WizardFrame;

public class WizardFrameListener implements ActionListener {

	
	private WizardFrame wizardFrame;
	
	public WizardFrameListener(WizardFrame wizardFrame) {
		this.wizardFrame = wizardFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		
		
		if(command.equals("next")) {
			wizardFrame.next();
		}
		
		
		
		else if(command.equals("back")) {
			wizardFrame.back();
		}
		
		
		
		else if(command.equals("updatePreviewPane")) {
			ArrayList<XmlTag> tagArr = wizardFrame.getTagArr();
			Document doc = WizardBusiness.writePdsc(tagArr); 
			if(doc != null) {
				String preview = FileBusiness.getDocumentPreview(doc);
				wizardFrame.setXmlPreview(preview);
			}
		}
		
		
	}
	
	
	

}
