package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import business.Session;
import view.comp.AttributeComboBox;
import view.comp.AttributeTextField;
import view.comp.TagTextField;
import view.wizardFrame.comp.Form;


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
	 * recovering value from attribute comboBox 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().getClass() == AttributeComboBox.class) {
			AttributeComboBox comboBox = (AttributeComboBox) e.getSource();
			comboBox.setAttrValue();
			session.getWizardFrame().updateXmlPreview();
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
