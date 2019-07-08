package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.XmlTagBusiness;
import listeners.wizardFrameListeners.WizardFrameListener;
import model.Pack;
import model.Response;
import model.XmlTag;
import view.comp.CustomColor;
import view.comp.DialogUtils;
import view.comp.TagMenuItem;
import view.comp.TextButton;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.comp.toolBar.ToolBar;

public class ToolBarListener extends WizardFrameListener implements ActionListener {
	
	private ToolBar toolBar;
	
	private Session session;
	
	public ToolBarListener(ToolBar toolBar) {
		this.toolBar = toolBar;
		this.session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command == "addRootChild") {
			TagMenuItem item = (TagMenuItem) e.getSource();
			XmlTag tag = item.getTag();
			tag = XmlTagBusiness.getCompleteTagFromTagInstance(tag);
			new TagCustomizationFrame(new XmlTag(tag));
		}
		
		
		else if(command.equals("showHideTagsListBar")) {
			session.getWizardFrame().ShowHideTagListBar();
		}
		
		else if(command.equals("validateXSD")) {
			if(session.getSelectedPDSCDoc() != null) {
				Document doc = FileBusiness.genratePDSCDocument(session.getSelectedForm().getRoot());
				Response response = FileBusiness.validateXMLSchema(doc);
				
				
				/** response.getObject() contains line number erro */
				if(response.getObject() != null) {
					
					/** recovering line number */
					int lineNumber = (int) response.getObject();
					
					/** button creation for error showing */
					TextButton lineButton  = new TextButton("Show error line", CustomColor.SYSTEM_RED_COLOR_DARK , CustomColor.SYSTEM_RED_COLOR_LIGHT.brighter());
					
					/** button listener */
					lineButton.addActionListener(new ActionListener() { 
						  public void actionPerformed(ActionEvent e) {
							  
							/** making row blink */
							System.out.println(lineNumber);
						    session.getSelectedForm().lineFocusBlink(lineNumber, CustomColor.LIGHT_GRAY, 5);
						  } 
						} );
					
					/** insert text and button in validator */
					session.getWizardFrame().setValidatorText(response.getMessage());
					session.getWizardFrame().insertValidatorComnponent(lineButton);
				}
				else session.getWizardFrame().setValidatorText(response.getMessage());
			}
			else DialogUtils.warningMessage("No document selected");
		}
		
		else if (command.equals("createPack")) {
			XmlTag root = session.getSelectedForm().getRoot();
			File path = DialogUtils.showChooseDirectoryFrame();
			if (path != null) {
				try {
					int status = session.getSelectedPDSCDoc().getPack().createPack(root , path);
					if(status == Pack.PACK_CREATED_CORRECTLY) {
						
					}
					else if (status == Pack.REQUIRED_FIELDS_MISSING) {
						DialogUtils.warningMessage("Required Fields Missing");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
