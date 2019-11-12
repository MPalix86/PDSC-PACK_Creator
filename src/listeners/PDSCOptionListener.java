package listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.jdom2.Document;

import business.Session;
import business.utils.PDSCDocumentUtils;
import business.utils.XmlTagUtils;
import model.Log;
import model.Response;
import model.PDSC.PDSCConstants;
import model.PDSC.Pack;
import view.comp.TextButton;
import view.comp.utils.ColorUtils;
import view.comp.utils.DialogUtils;
import view.comp.utils.IconsUtils;

public class PDSCOptionListener implements ActionListener{
	
	private Session session;
	
	public PDSCOptionListener(){
		session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String command = e.getActionCommand();
	
		
		if(command.equals("validateXSD")) {
			if(session.getSelectedPDSCDoc() != null) {
				
				Document doc = XmlTagUtils.getJDomDocumentFromXmlTag(session.getSelectedRoot());
				
				File xsd = session.getSelectedPDSCDoc().getXsdFile();
				if(xsd == null)
					try {
						String docName = "Untitled";
						if(session.getSelectedPDSCDoc().getSourcePath() != null) docName = session.getSelectedPDSCDoc().getSourcePath().toString();
						DialogUtils.okMessage("There isn't xsd file associated with this document.\n Click OK to select and associate an xsd file to \"" + docName + "\"", "XSD assciation" , IconsUtils.FAgetFileCodeIcon(40, ColorUtils.SYSTEM_GRAY_COLOR_DARK));
						xsd = DialogUtils.showChooseFileFrame(new File(System.getProperty("user.home")) , true);
					} catch (Exception e1) {							
						e1.printStackTrace();
					}
				if(xsd != null) {
					if(FilenameUtils.getExtension(xsd.getAbsolutePath()).equals("xsd")) {
						
						session.getSelectedPDSCDoc().setXsdFile(xsd);
						Response response = PDSCDocumentUtils.validatePDSCDocumentWithXMLSchema(doc, session.getSelectedPDSCDoc().getXsdFile());
						
						/** response.getObject() contains line number error */
						if(response.getObject() != null) {
							
							/** recovering line number */
							int lineNumber = (int) response.getObject();
							
							session.getWizardFrame().setConsoleText(response.getMessage() , false);
							session.getWizardFrame().addConsolePane();
							if(lineNumber > 0) {
								/** button creation for error showing */
								TextButton lineButton  = new TextButton("Show error line", ColorUtils.SYSTEM_RED_COLOR_DARK , ColorUtils.SYSTEM_RED_COLOR_LIGHT.brighter());
								
								/** button listener */
								lineButton.addActionListener(new ActionListener() { 
									  public void actionPerformed(ActionEvent e) {
										  
										/** making row blink */
										System.out.println(lineNumber);
									    session.getSelectedForm().lineFocusBlink(lineNumber, ColorUtils.LIGHT_GRAY, 5);
									  } 
									} );
								
								/** insert text and button in validator */
								session.getWizardFrame().insertConsoleComnponent(lineButton);
							}
						}
						
						else session.getWizardFrame().setConsoleText(response.getMessage(), false);
					}
					
					else DialogUtils.warningMessage("Select xsd document");
	
				}

			}
			else DialogUtils.warningMessage("No document selected");
		}
		
		
		
		else if (command.equals("createPack")) {
			if (session.getSelectedPDSCDoc() != null) {
				File path = DialogUtils.showChooseDirectoryFrame();
				if(path != null) {
					Pack pack = new Pack(session.getSelectedPDSCDoc(), path);
					
					try {
						Response r = pack.createPack();
						int status = r.getStatus();
						if(status == Pack.PACK_CREATED_CORRECTLY) { 
							ArrayList<Log> logArr = ( ArrayList<Log>) r.getObject();
							session.getWizardFrame().addConsolePane();
							session.getWizardFrame().setConsoleText("", false);
							for(int i = 0; i < logArr.size(); i++) {
								Log l = logArr.get(i);
								if(l.getType() == Log.WARNING) session.getWizardFrame().setConsoleText("Warning " + l.getText(), true);
								else if (l.getType() == Log.ERROR)session.getWizardFrame().setConsoleText("Error " + l.getText(), true);
							}
							session.getWizardFrame().setConsoleText("PACK CREATED CORRECTLY IN : " + path.getAbsolutePath(), true);
							boolean choice = DialogUtils.CustomButtonsTrueFalsePane(pack.getName() , "Pack Created Correctly", "Open folder", "Continue", IconsUtils.getOkIcon(48));
							if(choice) Desktop.getDesktop().open(pack.getMainPathFile());
						}
						else if(status == PDSCConstants.REQUIRED_FIELDS_MISSING) {
							DialogUtils.warningMessage("Required Fields missing");
						}
						else DialogUtils.warningMessage("Some Error Occurred");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			else DialogUtils.warningMessage("Cannot create pack ");
		}
		
		
		
		
		
		
		else if (command.equals("undo")) {
			if(session.getSelectedPDSCDoc() == null) return;
			session.getSelectedPDSCDoc().getUndoManager().undo();
			session.getSelectedForm().repaintView();
		}
		
		else if (command.equals("redo")) {
			if(session.getSelectedPDSCDoc() == null) return;
			session.getSelectedPDSCDoc().getUndoManager().redo();
			session.getSelectedForm().repaintView();
		}
		
	}

}
