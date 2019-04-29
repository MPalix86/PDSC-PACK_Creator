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
	
	public StepOneFormFocusListener(StepOneFormContainer stepOneContainer){
		this.stepOneContainer = stepOneContainer;
		textFieldArr = stepOneContainer.getTexFieldsArr();
	}

	@Override
	public void focusGained(FocusEvent e) {
		boolean found  = textFieldArr.contains(e.getComponent());
		if(found) {
			Session.getWizardFrame().printDescription("FOCUS LISTENER");
		}
	} 

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("focus perso: " + e.getComponent().getClass().getName());
	}

}
