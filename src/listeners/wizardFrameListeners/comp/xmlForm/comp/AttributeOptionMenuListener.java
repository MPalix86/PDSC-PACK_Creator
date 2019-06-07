package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import business.FileBusiness;
import business.Session;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeMenuItem;

public class AttributeOptionMenuListener implements ActionListener{
	
	private Session session = Session.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("deleteAttribute")) {
			AttributeMenuItem item = (AttributeMenuItem) e.getSource();
			XmlAttribute attr = item.getAttr();
			XmlTag tag = attr.getTag();
			tag.removeSelectedAttr(attr);
			session.getWizardFrame().getFormPanelContainer().getFormPanel().UpdateView();
			session.getWizardFrame().updatePreview();
		}
		
		else if (command.equals("addPath")) {
			AttributeMenuItem item = (AttributeMenuItem) e.getSource();
			XmlAttribute attr = item.getAttr();
			
			if(!FileBusiness.isFilePath(attr.getValue()) ) {
				session.getWizardFrame().warningMessage("Enter valid destination path before selecting file");
			}
			else {
				
				File file = session.getWizardFrame().showChooseFileFrame();
				if(file != null) {
					attr.setValue(attr.getValue() + file.getName()) ;
					session.getWizardFrame().getPack().getDestinationSourcePathFilesHashMap().put(attr.getValue(), file.getAbsolutePath());
				}
			}
			session.getWizardFrame().getFormPanelContainer().getFormPanel().UpdateView();
			
		}
	}
}


