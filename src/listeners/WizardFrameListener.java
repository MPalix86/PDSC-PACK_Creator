package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdom2.Attribute;
import org.jdom2.Document;

import business.FileBusiness;
import business.WizardBusiness;
import business.Session;
import model.Response;
import model.xmlComponents.XmlTagContents;
import model.xmlComponents.XmlTag;
import view.PdscWizardFrame;
import view.components.StepOneFormContainer;
import view.components.StepTwoFormContainer;

public class WizardFrameListener implements ActionListener {
	private Response response;
	private String ext;
	
	public WizardFrameListener() {
		Session.getWizardFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command == "continue") {
			Session.getWizardFrame().next();
		}
		
		else if(command == "back") {
			Session.getWizardFrame().back();
		}
		
		else if(command == "generatePdsc" ) {
			File destinationPath = Session.getWizardFrame().showNewFileFrame();
			if (destinationPath != null) {
				
				
				ArrayList<XmlTag> tagArr = StepOneFormContainer.getTagArr();
				tagArr.addAll(StepTwoFormContainer.getTagArr());
				
				//Document doc = WizardBusiness.writePdsc(tagArr);
				Document doc = WizardBusiness.writePdsc(test());
				response = FileBusiness.createFile(destinationPath.toString() , "PDSC", doc);
				ext = "PDSC";
			}
			else { // Operation cancelled || some error occurred 
				response = new Response.ResponseBuilder().message("Operation cancelled or some error occurred").status(-1).build();
			}
			
			
			if (response.getStatus() == FileBusiness.FILE_CREATED_CORRECTLY) {
				File createdFile = new File(destinationPath.toString() + "." + ext);
				Session.setCurrentWorkingFile(createdFile);		 								/*saving current path in session */
				Session.addFileArrWorkingFile(createdFile);
				
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
		
		XmlTag childOfChild  = new XmlTag.XmlTagBuilder("childOfChild", true , new XmlTagContents("childOfchild",null)).build();
		childOfchildArr.add(childOfChild);
		
		
		XmlTag root = new XmlTag.XmlTagBuilder("root", true , null).build();
		XmlTag childTag1 = new XmlTag.XmlTagBuilder("child1", true , new XmlTagContents("child1" ,null)).build();
		tagArr.add(childTag1);
		XmlTag childTag2 = new XmlTag.XmlTagBuilder("child2", true , new XmlTagContents(null ,childOfchildArr )).build();
		tagArr.add(childTag2);
		XmlTag tag = new XmlTag.XmlTagBuilder("parent", true , new XmlTagContents(null ,tagArr )).build();
		tagArr1.add(root);
		tagArr1.add(tag);
		
		
		return tagArr1;

	}
	
	
	

}
