package model.interfaces.events;

import java.util.EventObject;

public class UndoAbleEditEvent  extends EventObject{
	
    public UndoAbleEditEvent(Object source) {
        super(source);
    }
}
