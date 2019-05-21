package listeners.wizardFrameListener.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.WizardBusiness;
import listeners.wizardFrameListener.WizardFrameListener;
import model.Response;
import model.XmlTag;
import view.comp.AttributeComboBox;
import view.comp.AttributeTextField;
import view.comp.TagTextField;
import view.wizardFrame.comp.wizardForm.FinalStepForm;
import view.wizardFrame.comp.wizardForm.Form;



public class FormListener extends WizardFrameListener implements FocusListener , ActionListener{
	
	private Form form;
	
	private FinalStepForm finalStepForm;
	
	private Session session = Session.getInstance();
	
	
	
	public FormListener(Form formElement){
		this.form = formElement;
	}
	
	
	
	
	public FormListener(FinalStepForm finalStepForm){
		this.finalStepForm = finalStepForm;
	}


	/* text field focus listener */
	@Override
	public void focusGained(FocusEvent e) {
		setValue(e);
		session.getWizardFrame().updateXmlPreview();
	} 

	@Override
	public void focusLost(FocusEvent e) {
		setValue(e);
		session.getWizardFrame().updateXmlPreview();
	}
	
	
	/**
	 * attributeComboBox listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(e.getSource().getClass() == AttributeComboBox.class) {
			AttributeComboBox comboBox = (AttributeComboBox) e.getSource();
			comboBox.setAttrValue();
			session.getWizardFrame().updateXmlPreview();
		}
		
		else if(command == "generatePdsc" ) {
			String ext = "";
			File destinationPath = session.getWizardFrame().showNewFileFrame();
			Response response;
			if (destinationPath != null) {
				
				ArrayList<XmlTag> tagArr = session.getWizardFrame().getTagArr();
				Document doc = WizardBusiness.writePdsc(tagArr);
				response = FileBusiness.createFile(destinationPath.toString() , "PDSC", doc);
				
				ext = "PDSC";
			}
			else { // Operation cancelled or some error occurred 
				response = new Response.ResponseBuilder().message("Operation cancelled or some error occurred").status(-1).build();
			}
			
			if (response.getStatus() == FileBusiness.FILE_CREATED_CORRECTLY) {
				File createdFile = new File(destinationPath.toString() + "." + ext);				
			}
			else if(response.getStatus() == FileBusiness.FILE_ALREADY_EXIST) {
				System.out.println("file already exists");
			}
			else {
				System.out.println(response.getMessage());
			}
		}
		
	}
	
	
	/**
	 * setting text field and attribute filed value
	 * 
	 * @param e 
	 */
	private void setValue(FocusEvent e) {
		if(e.getSource().getClass() == TagTextField.class) {
			TagTextField textField = (TagTextField) e.getSource();
			textField.setTagContent();
			
		}
		else if(e.getSource().getClass() == AttributeTextField.class) {
			AttributeTextField textField = (AttributeTextField) e.getSource();
			textField.setAttrValue();
		}
	}
	
	


}
