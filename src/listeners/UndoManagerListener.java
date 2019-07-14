package listeners;

import business.Session;
import model.UndoAbleEditEvent;
import model.UndoManager;
import model.interfaces.UndoAbleEditListener;

public class UndoManagerListener implements UndoAbleEditListener{
	
	private Session session;
	
	public UndoManagerListener() {
		session = Session.getInstance();
	}

	@Override
	public void undoAbleEditHappened(UndoAbleEditEvent e) {
		UndoManager manager = (UndoManager) e.getSource();
		
	}
	
	

}
