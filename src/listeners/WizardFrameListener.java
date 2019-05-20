package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.WizardBusiness;
import model.Response;
import model.XmlTag;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.munuBar.comp.TagMenuItem;

public class WizardFrameListener implements ActionListener {
	private Response response;
	private Session session;
	private WizardFrame wizardFrame;
	
	public WizardFrameListener(WizardFrame wizardFrame) {
		this.wizardFrame = wizardFrame;
		session = Session.getInstance();
	}
	
	public WizardFrameListener() {
		session = Session.getInstance();
		this.wizardFrame = session.getWizardFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command == "continue") {
			session.getWizardFrame().next();
		}
		
		
		
		else if(command == "back") {
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
		
		
		
		else if(command == "generatePdsc" ) {
			String ext = "";
			File destinationPath = session.getWizardFrame().showNewFileFrame();
			if (destinationPath != null) {
				
				session = Session.getInstance();
				this.wizardFrame = session.getWizardFrame();
				ArrayList<XmlTag> tagArr = wizardFrame.getTagArr();
				Document doc = WizardBusiness.writePdsc(tagArr);
				response = FileBusiness.createFile(destinationPath.toString() , "PDSC", doc);
				
				ext = "PDSC";
			}
			else { // Operation cancelled || some error occurred 
				response = new Response.ResponseBuilder().message("Operation cancelled or some error occurred").status(-1).build();
			}
			
			
			if (response.getStatus() == FileBusiness.FILE_CREATED_CORRECTLY) {
				File createdFile = new File(destinationPath.toString() + "." + ext);
				//session.setCurrentWorkingFile(createdFile);		 								/*saving current path in session */
				session.addFileArrWorkingFile(createdFile);
				
			}
			else if(response.getStatus() == FileBusiness.FILE_ALREADY_EXIST) {
				System.out.println("file already exists");
			}
			else {
				System.out.println(response.getMessage());
			}
		}
		
		
	
		else if(command == "addPackageChild") {
			TagMenuItem item = (TagMenuItem) e.getSource();
			Class tagClass = item.getTagClass();
			Constructor<?> c = null;
			try {
				c = tagClass.getConstructor(boolean.class, XmlTag.class, int.class);
			} catch (NoSuchMethodException e2) {
				 
				e2.printStackTrace();
			} catch (SecurityException e2) {
				 
				e2.printStackTrace();
			}
			try {
				try {
					TagCustomizationFrame f = new TagCustomizationFrame((XmlTag) c.newInstance(true,null,1) );
				} catch (InvocationTargetException e1) {
					 
					e1.printStackTrace();
				}
			} 
			catch (SecurityException e1) {
				 
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				 
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				 
				e1.printStackTrace();
			}
	
		}
		
	}
	
	
	

}
