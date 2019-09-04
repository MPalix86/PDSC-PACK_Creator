package listeners.wizardFrameListeners.comp;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.Session;
import model.PDSCDocument;
import view.comp.customTabbedPane.ClosableTabbedPane;
import view.comp.customTabbedPane.ClosableTabbedPane.TabCloseListener;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.XmlFormContainer;

public class ClosableTabbedPaneListener implements TabCloseListener, ChangeListener{
	
	private Session session = Session.getInstance();

	@Override
	public void onTabClose(ClosableTabbedPane tabbedPane) {
		
		
		/** 
		 * handle before removing tab from ClosableTabbedPane
		 */
		
		if(tabbedPane.getSelectedComponent().getClass() == XmlFormContainer.class) {
			
			/** recovering xmlForm panel */
			XmlFormContainer c = (XmlFormContainer) tabbedPane.getSelectedComponent();
			XmlForm form = c.getFormPanel();
			
			/** recovering pdsc document from form */
			PDSCDocument doc = session.getPdscDocFromXmlForm(form);
			
			/** removing PDSCDoc from CurrentWorkingPdscDoc */
			if(doc != null) session.removeFromCurrentWorkingPdscDoc(doc);
			
			/** unsetting slectedPDSCDocument */
			if(session.getSelectedPDSCDoc().equals(doc)) session.setSelectedPDSCDoc((PDSCDocument) null);
			
			
			
		}
		
		
		/**
		 * 	tabbedPane.getTabCount() <= 1 (not <= 0) because this handler is called 
		 *  before removing tab, see ClosableTabbedPane class
		 */
		
		if(tabbedPane.getParent().getClass() == JSplitPane.class && tabbedPane.getTabCount() <= 1){
			session.getWizardFrame().addOverviewPane();
		}
		
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
        /**
         * updating selectedPDSCDoc in Session based on tab focus
         */

        if(sourceTabbedPane.getSelectedComponent() != null && sourceTabbedPane.getSelectedComponent().getClass() == XmlFormContainer.class) {
        	XmlFormContainer formContainer = (XmlFormContainer) sourceTabbedPane.getSelectedComponent();
        	XmlForm form = formContainer.getFormPanel();
        	session.getWizardFrame().setConsoleText(" ", false);
        	session.getWizardFrame().setDescriptionText(" ");
        	session.setSelectedPDSCDoc(form);
        	
        	/** enabling / disabling undo redo when document change*/
        	if(session.getSelectedPDSCDoc().getUndoManager().canRedo()) {
        		session.getWizardFrame().getToolBarContainer().getToolBar().enableRedo();
        	}else session.getWizardFrame().getToolBarContainer().getToolBar().disableRedo();
        	if(session.getSelectedPDSCDoc().getUndoManager().canRedo()) {
        		session.getWizardFrame().getToolBarContainer().getToolBar().enableUndo();
        	}else session.getWizardFrame().getToolBarContainer().getToolBar().disableUndo();
        	
        }
	
	}

}
