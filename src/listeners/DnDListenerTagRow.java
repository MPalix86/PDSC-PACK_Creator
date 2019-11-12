package listeners;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import business.Session;
import business.TagManager;
import business.utils.XmlTagUtils;
import model.UndoManager;
import model.xml.XmlTag;
import model.xml.XmlTagSelection;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

/**
 * Every DropTarget must have its own listener. So this listener 
 * refers to TagRow drop target
 * 
 * In particular this listener is used only on drop, dragOver , dragExit
 * 
 * @author Mirco Palese
 */
public class DnDListenerTagRow implements DragGestureListener , DropTargetListener  {
	
	private Session session;
	
	
	
	
	public DnDListenerTagRow() {
		this.session = Session.getInstance();
	}
	
	
	
	
	
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		
	}
	
	
	
	
	
	@Override
	public void drop(DropTargetDropEvent dtde) {
		DropTarget dt = (DropTarget) dtde.getSource();
	
		TagRow row = (TagRow) dt.getComponent();
		XmlTag tag = row.getTag();
		if(row != null) {
			XmlTag parent = tag.getParent();
			Transferable  tr = dtde.getTransferable();
			XmlTag tagDropped = null;
			int index = -1 ;
			try {
				boolean tagIsDropped = false;
				/** 
				 * moving in new parent 
				 * If tagRow is openRow tagDropeed must be inserted inside parent
				 */
				if(row.isOpen()) {
					/** recovering and removing tag dropped */
					tagDropped = (XmlTag) tr.getTransferData(XmlTagSelection.xmlTagFlavor);
					TagManager.removeSelectedChildFromParent(tagDropped, tagDropped.getParent(), false, false);
					index = parent.getSelectedChildrenArr().indexOf(tag);
					TagManager.dropTagInParent(tagDropped, XmlTagUtils.findModelChildFromSelectedChildName(parent, tagDropped.getName()), parent, false, false, index);
					tagIsDropped = true;
				}
				/** 
				 * moving in new parent 
				 * If tagRow is closeRow tagDropeed must be inserted inside tag and
				 * not inside parent
				 */
				else {
					/** recovering and removing tag dropped */
					tagDropped = (XmlTag) tr.getTransferData(XmlTagSelection.xmlTagFlavor);
					TagManager.removeSelectedChildFromParent(tagDropped, tagDropped.getParent(), false, false);
					index = tag.getSelectedChildrenArr().size();
					TagManager.dropTagInParent(tagDropped, XmlTagUtils.findModelChildFromSelectedChildName(tag, tagDropped.getName()), tag, false, false, index);
					tagIsDropped = true;
				}
				session.getSelectedForm().repaintView();
				if(tagDropped != null) session.getSelectedForm().highlightComponetBckGround(tagDropped, null);
				if(tagIsDropped) UndoManager.registerOperation();
			} 
			catch (UnsupportedFlavorException | IOException e) {e.printStackTrace();}
		}
	}
	
	
	
	
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}
	
	
	
	
	
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		DropTarget dt = (DropTarget) dtde.getSource();
		TagRow row = (TagRow) dt.getComponent();
		row.drawHorizontalLine(null, 10, 4, row.getWidth(), 4 , 5);
		
	}
	
	
	
	
	
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		
	}
	
	
	
	
	
	@Override
	public void dragExit(DropTargetEvent dte) {
		session.getSelectedForm().repaint();
	}
	
	
	
	

}
