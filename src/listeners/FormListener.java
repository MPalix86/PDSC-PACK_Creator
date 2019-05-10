package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.WizardBusiness;
import model.XmlTag;
import view.Components.ModelComponents.AttributeComboBox;
import view.Components.ModelComponents.AttributeTextField;
import view.Components.ModelComponents.TagTextField;
import view.Components.wizardFrameComponents.Form;


public class FormListener  implements FocusListener , ActionListener{
	private Form form;
	private Session session;
	
	public FormListener(Form formElement){
		session = Session.getInstance();
		this.form = formElement;
	}
	
	public FormListener(){
		session = Session.getInstance();				/* CANCELLARE QUESTO COSTRUTTORE */
	}
	
	
	/* FOCUS LISTENER */
	@Override
	public void focusGained(FocusEvent e) {
		setValue(e);
		ArrayList<XmlTag> tagArr = session.getWizardFrame().getTagArr();
		Document doc = WizardBusiness.writePdsc(tagArr); 
		if(doc != null) {
			String preview = FileBusiness.getDocumentPreview(doc);
			session.getWizardFrame().setPreviewDocument(preview);
		}
		
		
	} 

	@Override
	public void focusLost(FocusEvent e) {
		setValue(e);
		ArrayList<XmlTag> tagArr = session.getWizardFrame().getTagArr();
		Document doc = WizardBusiness.writePdsc(tagArr);
		if(doc != null) {
			String preview = FileBusiness.getDocumentPreview(doc);
			session.getWizardFrame().setPreviewDocument(preview);
		}
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass() == AttributeComboBox.class) {
			AttributeComboBox comboBox = (AttributeComboBox) e.getSource();
			comboBox.setAttrValue();
			
		}
		
	}
	
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
