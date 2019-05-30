//package listeners.wizardFrameListeners.comp.wizardFormListeners;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import business.Session;
//import listeners.wizardFrameListeners.WizardFrameListener;
//import view.wizardFrame.comp.wizardForm.FormContainer;
//
//public class FormContainerListener extends WizardFrameListener implements ActionListener{
//	
//	private Session session;
//	
//	private FormContainer formContainer;
//	
//	public FormContainerListener(FormContainer formContainer) {
//		this.formContainer = formContainer;
//		this.session = Session.getInstance();
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String command = e.getActionCommand();
//		
//		
//		
//		if(command.equals("next")) {
//			formContainer.next();
//		}
//		
//		
//		
//		else if(command.equals("back")) {
//			formContainer.back();
//		}
//	
//	}
//	
//	
//	
//	
//}
