package listeners;

import business.Session;
import model.UndoManager;
import model.interfaces.UndoAbleEditListener;
import model.interfaces.events.UndoAbleEditEvent;

public class UndoManagerListener implements UndoAbleEditListener{
	
	private Session session;
	
	public UndoManagerListener() {
		session = Session.getInstance();
	}

	@Override
	public void undoAbleEditHappened(UndoAbleEditEvent e) {
		UndoManager manager = (UndoManager) e.getSource();
		if(session.getSelectedPDSCDoc().getUndoManager().canUndo()) session.getWizardFrame().getToolBarContainer().getToolBar().enableUndo();
		else  session.getWizardFrame().getToolBarContainer().getToolBar().disableUndo();
		
		if(session.getSelectedPDSCDoc().getUndoManager().canRedo()) session.getWizardFrame().getToolBarContainer().getToolBar().enableRedo();
		else  session.getWizardFrame().getToolBarContainer().getToolBar().disableRedo();
	}
	
	

}
