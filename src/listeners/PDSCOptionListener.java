package listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import model.Log;
import model.Pack;
import model.Response;
import view.comp.TextButton;
import view.comp.utils.ColorUtils;
import view.comp.utils.DialogUtils;
import view.comp.utils.IconUtils;

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
				Document doc = FileBusiness.genratePDSCDocument(session.getSelectedForm().getRoot());
				Response response = FileBusiness.validateXMLSchema(doc);
				
				
				/** response.getObject() contains line number error */
				if(response.getObject() != null) {
					
					/** recovering line number */
					int lineNumber = (int) response.getObject();
					
					session.getWizardFrame().setConsoleText(response.getMessage() , false);
					session.getWizardFrame().addConsolePane();
					if(lineNumber > 0) {
						System.out.println("insert line button");
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
							boolean choice = DialogUtils.CustomButtonTrueFalsePane(pack.getName() , "Pack Created Correctly", "Open folder", "Continue", IconUtils.getOkIcon(48));
							if(choice) Desktop.getDesktop().open(pack.getMainPathFile());
						}
						else if(status == Pack.REQUIRED_FIELDS_MISSING) {
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
			session.getSelectedForm().UpdateView();
		}
		
		else if (command.equals("redo")) {
			if(session.getSelectedPDSCDoc() == null) return;
			session.getSelectedPDSCDoc().getUndoManager().redo();
			session.getSelectedForm().UpdateView();
		}
		
	}

}
