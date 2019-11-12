package model.interfaces;

import model.interfaces.events.UndoAbleEditEvent;

public interface UndoAbleEditListener {
	public void undoAbleEditHappened(UndoAbleEditEvent e);
}
