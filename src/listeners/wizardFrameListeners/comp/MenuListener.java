package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;

public class MenuListener implements ActionListener {
	
	private Session session;
	
	public MenuListener() {
		session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command  = e.getActionCommand();
		
		if(command.contentEquals("showTagListBar")) {
			session.getWizardFrame().ShowTagListBar();
		}
		
		else if(command.equals("showValidator")) {
			session.getWizardFrame().addValidatorPane();
		}
		
		else if(command.equals("showDescriptionPane")) {
			session.getWizardFrame().addDescriptionPane();
		}
		
		
	}

}
