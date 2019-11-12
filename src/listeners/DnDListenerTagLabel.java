package listeners;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import business.Session;
import model.xml.XmlTag;
import model.xml.XmlTagSelection;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagLabel;

/**
 * Every DropTarget must have its own listener. So this listener 
 * refers to Taglabel drop target
 * 
 * In particular this listener is only used to trigger dragGestureRecognized
 * 
 * @author Mirco Palese
 */
public class DnDListenerTagLabel implements DragGestureListener , DropTargetListener  {

	private Session session;	
	
	
	
	
	
	
	public DnDListenerTagLabel() {
		session = Session.getInstance();
	}
	
	
	
	
	
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		Cursor cursor = null;
		
		if (dge.getDragAction() == DnDConstants.ACTION_MOVE) {
			TagLabel label = (TagLabel) dge.getComponent();
			XmlTag tag = label.getTag();
			cursor = DragSource.DefaultCopyDrop;
			dge.startDrag(cursor, new XmlTagSelection(label.getTag()));
			session.getSelectedForm().restoreComponentsBackGround();
			session.getSelectedForm().highlightComponetBckGround(tag, null);
		}
		
	}
	
	
	
	
	
	@Override
	public void drop(DropTargetDropEvent dtde) {
	}
	
	
	
	
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		//System.out.println("drag entered " + dtde.getLocation());
	}
	
	
	
	
	
	@Override
	public void dragOver(DropTargetDragEvent dtde) {

	}
	
	
	
	
	
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	@Override
	public void dragExit(DropTargetEvent dte) {
		

	}
	
	

}
