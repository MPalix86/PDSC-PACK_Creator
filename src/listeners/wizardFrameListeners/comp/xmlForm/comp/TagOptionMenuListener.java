package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import model.XmlTag;
import view.comp.TagMenuItem;
import view.tagCustomizationFrame.TagCustomizationFrame;

public class TagOptionMenuListener implements ActionListener{
	
	private Session session = Session.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		TagMenuItem item = (TagMenuItem) e.getSource();
		XmlTag tag = item.getTag();
		
		if(command.equals("deleteTag")) {
			
			XmlTag parent = tag.getParent();
			parent.removeSelectedChild(tag);
			session.getWizardFrame().getFormPanelContainer().getFormPanel().UpdateView();
			session.getWizardFrame().updatePreview();
		}
		
		else if(command.equals("customize")) {
			new TagCustomizationFrame(tag);
		}
		
	}

}
