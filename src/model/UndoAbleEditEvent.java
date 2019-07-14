package model;

import java.util.EventObject;

public class UndoAbleEditEvent  extends EventObject{
	
    public UndoAbleEditEvent(Object source) {
        super(source);
    }
}
