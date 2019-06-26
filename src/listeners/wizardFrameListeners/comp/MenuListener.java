package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import model.Response;
import model.XmlTag;
import view.comp.DialogUtils;
import view.wizardFrame.comp.xmlForm.XmlForm;

public class MenuListener implements ActionListener {
	
	private Session session;
	
	public MenuListener() {
		session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command  = e.getActionCommand();
		
		if(command.contentEquals("showTagListBar")) {
			session.getWizardFrame().ShowTagListBar();
		}
		
		else if(command.equals("showValidator")) {
			session.getWizardFrame().addValidatorPane();
		}
		
		else if(command.equals("showDescriptionPane")) {
			session.getWizardFrame().addDescriptionPane();
		}
		
		
		else if(command.equals("openPDSCFile")) {
			File pdscFile = DialogUtils.showChooseFileFrame();
			if (pdscFile != null) {
				System.out.println(FilenameUtils.getExtension(pdscFile.getName()));
				if( FilenameUtils.getExtension(pdscFile.getName()).equals("PDSC") || FilenameUtils.getExtension(pdscFile.getName()).equals("pdsc")) {
					XmlTag root = FileBusiness.ReadPDSCFile(null,null,pdscFile);
					session.getWizardFrame().addXmlFormTab(new XmlForm(root));
				}
				else DialogUtils.warningMessage(" Select PDSC File ");
			}
			
			System.out.println("readPDSCFile");
		}
		
		else if(command.equals("savePDSCAs")) {
			File folder = DialogUtils.showSaveFileFrame();
			if(folder != null) {
				Document doc = FileBusiness.genratePDSCDocument(session.getSelectedForm().getRoot());
				Response response = FileBusiness.createFile(folder.toString(), "PDSC", doc);
				if (response != null) {
					if (response.getStatus() == FileBusiness.FILE_ALREADY_EXIST) {
						DialogUtils.warningMessage(" File Already Exists ");
					}
					else if(response.getStatus() == FileBusiness.FILE_CREATED_CORRECTLY) {
						DialogUtils.okMessage("File Created","");
					}
				}
				
			}
			
		}
		
		
	}

}
