package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import model.XmlTag;
import view.comp.TagMenuItem;

public class TagOptionMenuListener implements ActionListener{
	
	private Session session = Session.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("deleteTag")) {
			TagMenuItem item = (TagMenuItem) e.getSource();
			XmlTag tag = item.getTag();
			XmlTag parent = tag.getParent();
			parent.removeSelectedChild(tag);
			session.getWizardFrame().getFormPanelContainer().getFormPanel().removeTag();
			session.getWizardFrame().updatePreview();
		}
		
	}

}
