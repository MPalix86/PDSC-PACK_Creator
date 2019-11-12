package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.jdom2.Document;

import business.PDSCDocumentManager;
import business.Session;
import business.utils.PDSCDocumentUtils;
import business.utils.XmlTagUtils;
import model.Response;
import model.UndoManager;
import model.PDSC.PDSCConditionsChecker;
import model.PDSC.PDSCDocument;
import model.PDSC.PDSCFileReader;
import model.xml.XmlTag;
import view.comp.utils.DialogUtils;
import view.comp.utils.IconsUtils;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;

public class FileOptionListener implements ActionListener{
	private Session session;
	
	public  FileOptionListener() {
		session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("openPDSCFile")) {
			File pdscFile = DialogUtils.showChooseFileFrame();
			if (pdscFile != null) {
				
				/** if file is pdsc file */
				if( FilenameUtils.getExtension(pdscFile.getName()).equals("PDSC") || FilenameUtils.getExtension(pdscFile.getName()).equals("pdsc")) {
					
					/** if file is already open */
					if(session.getPdscDocFromFilePath(pdscFile) != null) {
						DialogUtils.warningMessage("File already open");
					}
					else {
						
						PDSCFileReader reader = new PDSCFileReader(pdscFile);
						
						/** reading file */
						XmlTag root = reader.read();
						
						/** creating new XmlForm */
						XmlForm form = new XmlForm(root);
						
						/** creating unndoManager to manage operation of undo/redo on PDSCDocument*/
						UndoManager manager = new UndoManager(root);
						
						manager.addUndoAbleEditListener(new UndoManagerListener());
						
						PDSCConditionsChecker checker = new PDSCConditionsChecker(root);
						
						/** creating new PDSCDocument instance */
						PDSCDocument doc = new PDSCDocument(form , pdscFile, root, manager, checker);
						
						/* adding document in  addInCurrentWorkingPdscDoc */
						session.addInCurrentWorkingPdscDoc(doc);
						
						/** adding tab in wizardFrame */
						session.getWizardFrame().addXmlFormTab(doc);
						
						session.setSelectedPDSCDoc(doc);

					}
				}
				else DialogUtils.warningMessage(" Select PDSC File ");
			}
		}
		
		else if(command.equals("savePDSC")) {
			if(Session.getInstance().getSelectedPDSCDoc() != null && Session.getInstance().getSelectedPDSCDoc().getSourcePath() != null) {
				File file = new File(session.getSelectedPDSCDoc().getSourcePath().toString());
				file.delete();
				Document doc = XmlTagUtils.getJDomDocumentFromXmlTag(session.getSelectedRoot());
				Response response = PDSCDocumentManager.savePDSCDDocumentOnFileSystem(file.toString(), doc);
				
				/** handling response */
				if (response != null) {
					if(response.getStatus() == PDSCDocumentManager.FILE_CREATED_CORRECTLY) {
						DialogUtils.noButtonsTemporaryMessage(" File Saved" , IconsUtils.getOkIcon(40), 700 , session.getWizardFrame());
					}
					else {
						DialogUtils.warningMessage("Some error occurred\n Error Code : " + response.getStatus() );
					}
				}
			}
			
			else {
				if(Session.getInstance().getSelectedPDSCDoc() != null) command = "savePDSCAs";
				else DialogUtils.noButtonsTemporaryMessage("No document selected" , IconsUtils.getWarningIcon(48), 1300, session.getWizardFrame());
			}
		}
		
		/**
		 * TEMPORARY CORRECTION DO NOT PUT ELSE IF SEE savePDSC above
		 */
		if(command.equals("savePDSCAs")) {
			
			if(session.getSelectedPDSCDoc() != null) {
				/** asking user to select destination folder */
				File folder = DialogUtils.showSaveFileFrame();
				/** verify if document is selected */
				if(folder != null) {
					
					/** generate pdsc document */
					Document doc = XmlTagUtils.getJDomDocumentFromXmlTag(session.getSelectedRoot());
					Response response = PDSCDocumentManager.createPDSCFileFromJDomDocument(folder.toString(), "PDSC", doc, false , false);
					
					/** handling response */
					if (response != null) {
						if (response.getStatus() == PDSCDocumentManager.FILE_ALREADY_EXIST) {
							DialogUtils.warningMessage(" File Already Exists ");
						}
						else if(response.getStatus() == PDSCDocumentManager.FILE_CREATED_CORRECTLY) {
							PDSCDocument pdscDoc = session.getSelectedPDSCDoc();
							pdscDoc.setSourcePath((File)response.getObject());
							session.getWizardFrame().updateTabTitle(pdscDoc);
							DialogUtils.okMessage("File Created","File Created " , null);
						}
					}
				}
			}
			else DialogUtils.warningMessage("No document selected");
			
		}
		
		
		
		else if(command.equals("showPDSCPreview")) {
			if(session.getSelectedPDSCDoc() != null) {
				Document doc = XmlTagUtils.getJDomDocumentFromXmlTag(session.getSelectedRoot());
				String text = PDSCDocumentUtils.getStringFromJDomDocument(doc);
				session.getWizardFrame().showPreview(text);
			}
			else DialogUtils.noButtonsTemporaryMessage("No document selected", IconsUtils.getWarningIcon(48), 1000, session.getWizardFrame());
		}
		
		
		else if(command.equals("createNewPDSC")) {
			
			/** 
			 * PDSCDocument during creation is set to null to avoid issues.
			 * Indeed during creation of new PDSCDocument, the selected PDSCDocument
			 * is ambiguous .
			 */
			if (session.getSelectedPDSCDoc() != null) session.setSelectedPDSCDoc((PDSCDocument) null);
			
			PDSCDocument doc = PDSCDocumentManager.createNewPDSCDocument();
			
			/* adding document in  addInCurrentWorkingPdscDoc */
			session.addInCurrentWorkingPdscDoc(doc);
			
			/** adding tab in wizardFrame */
			session.getWizardFrame().addXmlFormTab(doc);
			
			/** setting SelectedPDSCDcument */
			session.setSelectedPDSCDoc(doc);
			
			new AddAttributeFrame(doc.getRoot());

		}
		
	}

}
