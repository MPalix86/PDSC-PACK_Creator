package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import business.XmlTagBusiness;
import model.XmlTag;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;
import view.wizardFrame.comp.toolBar.ToolBar;

public class ToolBarListener implements ActionListener {
	
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
			if(session.getSelectedForm() != null) {
				XmlTagBusiness.addTagInParent(new XmlTag(tag) , tag, session.getSelectedPDSCDoc().getRoot(), true, false,null);
				session.getSelectedForm().UpdateView();
			}
			else DialogUtils.warningMessage("Select document before adding tag");
		}
		
		
		else if(command.equals("showHideTagsListBar")) {
			session.getWizardFrame().ShowHideTagListBar();
		}
		
		
		
		
		

		
		
		
		
		
		
	}
}
