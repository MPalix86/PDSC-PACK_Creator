package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import business.XmlTagBusiness;
import listeners.wizardFrameListeners.WizardFrameListener;
import model.XmlTag;
import view.comp.TagMenuItem;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.comp.toolBar.ToolBar;

public class ToolBarListener extends WizardFrameListener implements ActionListener {
	
	private ToolBar toolBar;
	
	private Session session;
	
	public ToolBarListener(ToolBar toolBar) {
		this.toolBar = toolBar;
		this.session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command == "addRootChild") {
			TagMenuItem item = (TagMenuItem) e.getSource();
			XmlTag tag = item.getTag();
			tag = XmlTagBusiness.getCompleteTagFromTagInstance(tag);
			new TagCustomizationFrame(new XmlTag(tag));
		}
		
		
		else if(command.equals("showHideTagsListBar")) {
			session.getWizardFrame().ShowHideTagListBar();
		}
		
		
		
		
		

		
		
		
		
		
		
	}
}
