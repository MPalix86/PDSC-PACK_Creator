package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.WizardBusiness;
import model.Response;
import model.XmlTag;
import view.WizardFrame;

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
		
	}
	
	
	private static ArrayList<XmlTag> test() {
		ArrayList<XmlTag> tagArr = new ArrayList();
		ArrayList<XmlTag> tagArr1 = new ArrayList();
		ArrayList<XmlTag> childOfchildArr = new ArrayList();
		
//		XmlTag childOfChild  = new XmlTag.XmlTagBuilder("childOfChild", true , new XmlTagContents("childOfchild",null)).build();
//		childOfchildArr.add(childOfChild);
//		
//		
//		XmlTag root = new XmlTag.XmlTagBuilder("root", true , null).build();
//		XmlTag childTag1 = new XmlTag.XmlTagBuilder("child1", true , new XmlTagContents("child1" ,null)).build();
//		tagArr.add(childTag1);
//		XmlTag childTag2 = new XmlTag.XmlTagBuilder("child2", true , new XmlTagContents(null ,childOfchildArr )).build();
//		tagArr.add(childTag2);
//		XmlTag tag = new XmlTag.XmlTagBuilder("parent", true , new XmlTagContents(null ,tagArr )).build();
//		tagArr1.add(root);
//		tagArr1.add(tag);
		
		
		return tagArr1;

	}
	
	
	

}
