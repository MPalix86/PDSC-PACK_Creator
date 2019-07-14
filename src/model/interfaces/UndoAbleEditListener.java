package model.interfaces;

import model.UndoAbleEditEvent;

public interface UndoAbleEditListener {
	public void undoAbleEditHappened(UndoAbleEditEvent e);
}
