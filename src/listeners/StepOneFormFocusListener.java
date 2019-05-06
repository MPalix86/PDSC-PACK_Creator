package listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import business.Session;
import view.PdscWizardFrame;
import view.components.StepOneFormContainer;

public class StepOneFormFocusListener implements FocusListener {
	private PdscWizardFrame frame ;
	private StepOneFormContainer stepOneContainer;
	private ArrayList<JTextField> textFieldArr;
	private Session session;
	
	public StepOneFormFocusListener(StepOneFormContainer stepOneContainer){
		session = Session.getInstance();
		this.stepOneContainer = stepOneContainer;
		textFieldArr = stepOneContainer.getTexFieldsArr();
	}

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

}
