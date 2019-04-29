package listeners;

import java.awt.GraphicsDevice;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import business.FileBusiness;
import business.Session;
import model.Response;
import view.EditFileFrame;

public class TopMenuBarListener implements ActionListener{
	private EditFileFrame mainFrame;
	
//	//--------------------------------------------------------------------------MainFrameListener()
//	public TopMenuBarListener(EditFileFrame mainFrame) {
//		this.mainFrame = mainFrame;
//		Session session = Session.getInstance();
//	}
//
//	//--------------------------------------------------------------------------actionPerformed()
//	public void actionPerformed(ActionEvent e) {
//		String ext = "";
//		String command = e.getActionCommand();
//		Response response = null;
//		
//		if(command == "createXmlFile" || command == "createPdscFile"){
//			
//			File destinationPath = mainFrame.showNewFileFrame();
//			if (command == "createXmlFile" && destinationPath != null) {
//				response = FileBusiness.createFile(destinationPath.toString() , "xml");
//				ext = "xml";
//			}
//			else if (command == "createPdscFile" && destinationPath != null) {
//				response = FileBusiness.createFile(destinationPath.toString() , "PDSC");
//				ext = "PDSC";
//			}
//			else if(destinationPath == null) { // Operation cancelled || some error occurred 
//				response = new Response.ResponseBuilder().message("Operation cancelled or some error occurred").status(-1).build();
//			}
//			
//			
//			if (response.getStatus() == FileBusiness.FILE_CREATED_CORRECTLY) {
//				File createdFile = new File(destinationPath.toString() + "." + ext);
//				Session.setCurrentWorkingFile(createdFile);		 								/*saving current path in session */
//				Session.addFileArrWorkingFile(createdFile);
//				mainFrame.showMessageDialog(response.getMessage(), "", JOptionPane.DEFAULT_OPTION);
//				mainFrame.addTab(Session.getCurrentWorkingFile(), null);
//			}
//			else if(response.getStatus() == FileBusiness.FILE_ALREADY_EXIST) {
//				mainFrame.showMessageDialog(response.getMessage(), "", JOptionPane.WARNING_MESSAGE);
//			}
//			else {
//				System.out.println(response.getMessage());
//			}
//		}
//		
//		if (command == "openFile") {
//			File file = mainFrame.showOpenFileFrame();
//			response = FileBusiness.openFile(file);
////			if(response.getStatus() == FileBusiness.FILE_ALREADY_OPEN ) {
////				mainFrame.showMessageDialog(response.getMessage(),"", JOptionPane.INFORMATION_MESSAGE);
////				return;
////			}
////			Session.setCurrentWorkingFile(file);		 								/*saving current path in session */
////			Session.addFileArrWorkingFile(file);
//			mainFrame.addTab(file, (String) response.getObject());
//		}
//		
//		
//	}
	

	
}