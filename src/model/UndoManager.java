package model;

import java.util.ArrayList;

import model.interfaces.UndoAbleEditListener;

/**
 * Class that implements undo redo;
 * this class use a mix of memento pattern command patter and observable pattern
 * 
 * @author Mirco Palese
 */

public class UndoManager {
	
	
	/** 
	 * element with void tag which acts like a separator between new and old inserted status;
	 * EXAMPLE : vector with 11 entry (separator = S )
	 * 
	 *  	| 1 2 3 4 5 6 S 7 8 9 10 | 
	 *  
	 *  in this way vector became cyclic placing limit on undo and redo operations.
	 *  for example assume that we are in status 4. We can redo until status 6 and 
	 *  undo until status 8.
	 *  adding new status cause S to move on;
	 */
	private Memento separator  = new Memento(new XmlTag());
	
	/** current root tag (the current state of the undo/redo able object ) */
	private XmlTag root;
	
	/** 
	 * mementoArr contains all possible status of undo/redo able object.
	 * see memento pattern 
	 */
	private ArrayList<Memento> mementoArr ;
	
	/** index of current state in mementoArr */
	private int i;
	
	/** 
	 * array of UndoAbleEditListener to notify when undo/redo action was executed 
	 * see observable pattern
	 */
	private ArrayList<UndoAbleEditListener> listeners;
	
	/** maximum number of traceable states */
	public final int MAX_SAVED_STATEMENT = 21;
	
	
	
	public UndoManager(XmlTag root) {
		this.mementoArr = new ArrayList<Memento>();
		this.listeners = new ArrayList<UndoAbleEditListener>();
		
		
		this.root = root;
		this.i = 0;
		
		mementoArr.add(new Memento(root));
//		this.mementoArr.add(separator);
	}
	
	
	
	
	/**
	 * add new state saving it into a memento
	 */
	public void addState() {

//		if (i > 0 && i < MAX_SAVED_STATEMENT) {
//			XmlTag lastSavedRoot = mementoArr.get(mementoArr.size()).getStatus();
//			if(lastSavedRoot.equals(root))  return;
//		}
		
		if(mementoArr.size() == MAX_SAVED_STATEMENT) i = 0;
		
//		if( i < mementoArr.size()) {
//			
//			if(mementoArr.get(i) != null) {
//				for (int j = i; j < mementoArr.size(); j++) {
//					mementoArr.remove(j);
//				}
//			}
//			
//		}

		mementoArr.add(new Memento(root));
		i++;
		
		
		/** notify listeners that event happened */
		listeners.forEach((l) -> l.undoAbleEditHappened(new UndoAbleEditEvent(this)));
	}
	
	
	
	
	/**
	 * undo function: restore the state to the previous one
	 */
	public void undo() {
		if( i > 0) {
			i--;
			Memento m = mementoArr.get(i);
			root.replaceWith(m.getStatus() , m.getStatus().getParent());
			
			for (int h = 0; h < mementoArr.size(); h++) {
				System.out.println(mementoArr.get(h).getStatus().getName());
			}
			
			/** notify listeners that event happened */
			listeners.forEach((l) -> l.undoAbleEditHappened(new UndoAbleEditEvent(this)));
		}
	}
	
	
	
	
	/**
	 * redo function: restore the state to the next one
	 */
	public void redo() {
	
		if(i < mementoArr.size() - 1) {
			i++;
			Memento m = mementoArr.get(i);
			root.replaceWith(m.getStatus() , m.getStatus().getParent());
			
			System.out.println("redo");
			System.out.println("memntoArr size : " + mementoArr.size());
			System.out.println("i : " + i);
			
			/** notify listeners that event happened */
			listeners.forEach((l) -> l.undoAbleEditHappened(new UndoAbleEditEvent(this)));
		}
		
	}
	
	
	
	
	/**
	 * Return true if user can undo false otherwise
	 * 
	 * @return true if user can undo false otherwise
	 */
	public boolean canUndo() {
		if (i > 0) {
			if(mementoArr.get(i - 1) != null) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * Return true if user can redo false otherwise
	 * 
	 * @return true if user can redo false otherwise
	 */
	public boolean canRedo() {
		if ( i < MAX_SAVED_STATEMENT) {
			if( mementoArr.get(i) != null) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * addUndoAbleEditListener. See observable pattern
	 * 
	 * @param l listener to add
	 */
	public void addUndoAbleEditListener(UndoAbleEditListener l) {
		listeners.add(l);
	}
	
	
	
	
	
	/**
	 * removeUndoAbleEditListener. See observable pattern
	 * 
	 * @param l listener to remove
	 */
	public void removeUndoAbleEditListener(UndoAbleEditListener l) {
		listeners.remove(l);
	}
	
	
	/** 
	 * Defines memento object to store information
	 * 
	 * @author Mirco Palese
	 */
	private class Memento {
		
		private XmlTag tag;
		
		public Memento(XmlTag tag) {
			this.tag = new XmlTag(tag);
		}
		
		public XmlTag getStatus() {
			return this.tag;
		}
	
	}
	

}
