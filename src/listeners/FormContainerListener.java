package listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import business.Session;
import view.PdscWizardFrame;
import view.components.FormContainer;


public class FormContainerListener  implements FocusListener , DocumentListener{
	private PdscWizardFrame frame ;
	private FormContainer formContainer;
	private ArrayList<JTextField> textFieldArr;
	private Session session;
	
	public FormContainerListener(){
		session = Session.getInstance();
		this.formContainer = formContainer;
	}
	
	
	/* FOCUS LISTENER */
	@Override
	public void focusGained(FocusEvent e) {
		boolean found  = textFieldArr.contains(e.getComponent());
		if(found) {
			session.getWizardFrame().printDescription("FOCUS LISTENER");
		}
	} 

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("focus perso: " + e.getComponent().getClass().getName());
	}
	
	
	/* DOCUMENT LISTENER */
	@Override
	public void insertUpdate(DocumentEvent e) {
		System.out.println("insert update");
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		System.out.println("remove update");
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("change update");
		
	}

}
