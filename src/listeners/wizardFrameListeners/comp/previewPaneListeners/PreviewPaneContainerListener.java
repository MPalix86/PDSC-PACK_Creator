package listeners.wizardFrameListeners.comp.previewPaneListeners;

import java.awt.event.ActionEvent;

import business.Session;
import listeners.wizardFrameListeners.WizardFrameListener;
import view.wizardFrame.comp.previewPane.PreviewPaneContainer;

public class PreviewPaneContainerListener extends WizardFrameListener{
	
	private Session session;
	
	private PreviewPaneContainer previewPaneContainer;
	
	
	public PreviewPaneContainerListener(PreviewPaneContainer previewPaneContainer) {
		this.previewPaneContainer = previewPaneContainer;
		session = Session.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
	}
}
