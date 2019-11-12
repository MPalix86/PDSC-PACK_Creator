package model;

import java.util.ArrayList;

import business.Session;
import business.utils.XmlTagUtils;
import model.interfaces.UndoAbleEditListener;
import model.interfaces.events.UndoAbleEditEvent;
import model.xml.XmlTag;

/**
 * Class that implements undo redo;
 * this class use a mix of memento pattern command patter and observable pattern
 * 
 * @author Mirco Palese
 */

public class UndoManager {
	
	private static Session session;
	
	
	/** 
	 * S:
	 * element with void tag which acts like a separator between new and old inserted status;
	 * EXAMPLE : vector with 11 entry (separator = S )
	 * 
	 *  	| 1 2 3 4 5 6 S 7 8 9 10 | 
	 *  
	 *  in this way vector became cyclic, placing limit on undo and redo operations.
	 *  for example assume that we are in status 4. We can redo until status 6 and 
	 *  undo until status 7.
	 *  adding new status cause S to move on;
	 */
	private Memento separator  = new Memento(new XmlTag());
	
	/** AarayList initial value to avoid indexOutOfBound exception */
	private Memento filler = new Memento(new XmlTag());
	
	/** current root tag (the current state of the undo/redo able object ) */
	private XmlTag root;
	
	/** last saved state */
	private Memento lastSavedMemento;
	
	/** 
	 * mementoArr contains all possible status of undo/redo able object.
	 * see memento pattern 
	 */
	private ArrayList<Memento> mementoArr ;
	
	/** index of current state in mementoArr */
	private int i;
	
	/** index of separator in mementoArr */
	private int s;
	
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
		
		for ( int j = 0; j <= MAX_SAVED_STATEMENT; j++) {
			mementoArr.add(filler);
		}
		
		this.root = root;
		i = 0;
		s = i + 1;
	}
	
	
	
	
	/**
	 * add new state saving it into a memento.
	 * For more info uncomment all system.out.println()
	 * 
	 * @return void
	 */
	private void addState() {
		
		/** 
		 * verifying that status has really changed before saving it .
		 * For more info on comparison see XmlTagUtils.compareText function.
		 * uncomment all System.out.println for more info.
		 * 
		 * if lastSavedMemento == null => first status is being entered
		 */
		if(lastSavedMemento == null || !XmlTagUtils.compareTagText(root, this.lastSavedMemento.getStatus())){		
			
			/** adding filler between i and s only if is not the first status **/
			if(lastSavedMemento != null) addFillers(i);
			
			//System.out.println("before increment : s = " + s);
			//System.out.println("before increment : i = " + i);
			
			/** incrementing variable only if lastsaved memento != null => is not first insert */
			if(lastSavedMemento != null) i++;
			if(i > MAX_SAVED_STATEMENT) i = 0;
			s = i + 1;
			
			//System.out.println("after increment : s = " + s);
			//System.out.println("after increment : i = " + i);
			
			//System.out.print("adding memento status at index " + i + " ");
			
			/** setting memento stauts */
			mementoArr.set(i, new Memento(root));
			
			lastSavedMemento = new Memento (root);
			
			/** adding separator */
			if(s > MAX_SAVED_STATEMENT) {
				mementoArr.set(0 , separator);
				//System.out.print("adding separator status at index 0 ");
				 s = 0;
			}
			else {
				mementoArr.set(s , separator);
				//System.out.print("adding separator status at index " + s + " ");
			}
			
			//System.out.println("");
			
			/** notify listeners that event happened */
			listeners.forEach((l) -> l.undoAbleEditHappened(new UndoAbleEditEvent(this)));
		}
		//printUndoManager();
	}
	
	
	
	
	
	/**
	 * if undo or redo happens, rempove all status setted between i and s 
	 * example : undo happens and i is at position 2 and s at position 6
	 * 
	 * | 0 1 i 3 4 5 s | 
	 * 
	 * in this case state 0, 1 are "undo" statement.
	 * state 3, 4, 5 are "redo" statement.
	 * 
	 * so if "addState" occurs when mementoArr is in state described above, 
	 * new statement will be added at position 4 and s will moved at position 5
	 * 
	 * | 0 1 2 i s 5 6 |
	 * 
	 * so in this situation we can undo until position 5, but the "undo statement"
	 * are only 0 and 1;
	 * 
	 * to avoid this we must had "fillers" between i and s (s included) before adding new status
	 * 
	 * @param x current state position ( i )
	 */
	private void addFillers(int x) {
		//System.out.println("filler , s is at position: " + s);
		x++;
		if(x > MAX_SAVED_STATEMENT) x = 0;
		if(mementoArr.get(x) != separator) {
			//System.out.println("Setting filler at position :" + x);
			mementoArr.set(x, filler);
			addFillers(x);
		}
		else {
			//System.out.println("found separator, returning");
			
			mementoArr.set(x, filler);
			return;
		}
		
	}
	
	
	
	
	/**
	 * undo function: restore the state to the previous one
	 * For more info uncomment all system.out.println()
	 * 
	 * @return void;
	 */
	public void undo() {
		int j = i;
		i--;
		if( i < 0 ) {
			i = MAX_SAVED_STATEMENT;
		}
			
		if(!mementoArr.get(i).equals(filler) && !mementoArr.get(i).equals(separator)) {
			//System.out.print("undoing at status " + i + " ");
			Memento m = mementoArr.get(i);
			root.replaceWith(m.getStatus() , m.getStatus().getParent());
		}
		
		else {
			i = j;
			//System.out.println("separator or filler found, cannot undo");
		}
		
		//System.out.println("now index is " + i);
	
		
		/** notify listeners that event happened */
		listeners.forEach((l) -> l.undoAbleEditHappened(new UndoAbleEditEvent(this)));
	}
	
	
	
	
	/**
	 * redo function: restore the state to the next one.
	 * For more info uncomment all system.out.println()
	 * 
	 * @return void
	 */
	public void redo() {
		int  j = i;
		i++;
		if( i > MAX_SAVED_STATEMENT) i = 0;
				
		if(!mementoArr.get(i).equals(filler) && !mementoArr.get(i).equals(separator)) {
			//System.out.print("redoing at status " + i + " ");
			Memento m = mementoArr.get(i);
			root.replaceWith(m.getStatus() , m.getStatus().getParent());
		}
			
		else {
			i = j;
			//System.out.println("separator or filler found, cannot redo");
		}
			
			
		//System.out.println("now index is " + i);
			
		/** notify listeners that event happened */
		listeners.forEach((l) -> l.undoAbleEditHappened(new UndoAbleEditEvent(this)));

		
	}
	
	
	
	
	/**
	 * Return true if user can undo false otherwise
	 * 
	 * @return true if user can undo false otherwise
	 */
	public boolean canUndo() {
		int j = i;
		i--;
		
		if( i < 0 ) i = MAX_SAVED_STATEMENT;
			
		if(!mementoArr.get(i).equals(filler) && !mementoArr.get(i).equals(separator)) {
			i = j;
			return true;
		}
		
		else {
			i = j;
			return false;
		}
		
	}
	
	
	
	
	/**
	 * Return true if user can redo false otherwise
	 * 
	 * @return true if user can redo false otherwise
	 */
	public boolean canRedo() {
		int  j = i;
		i++;
		if( i > MAX_SAVED_STATEMENT) i = 0;
				
		if(!mementoArr.get(i).equals(filler) && !mementoArr.get(i).equals(separator)) {
			i = j;
			return true;
		}
			
		else {
			i = j;
			return false;
		}
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
			this.tag = new XmlTag(tag, tag.getParent());
		}
		
		public XmlTag getStatus() {
			return this.tag;
		}
		
		public void setStatus(XmlTag tag) {
			this.tag.replaceWith(tag, tag.getParent());
		}
	
	}
	
	
	
	
	
	/** call addState (see above) on selected pdsc document */
	public static void registerOperation() {
		session = Session.getInstance();
		if(session != null && session.getSelectedPDSCDoc() != null)
			session.getSelectedPDSCDoc().getUndoManager().addState();
	}
	
	
	
	
	private void printUndoManager() {
		String arr = "";
		for (int i = 0; i < this.mementoArr.size(); i++) {
			Memento status  = mementoArr.get(i);
			
			if (status.equals(separator)) arr += " s |";
			else if(status.getStatus().getName() == null) arr += " f |";
			else arr += " r |";
		}
		System.out.println(arr);
	}
	

}