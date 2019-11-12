package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import business.TagManager;
import mao.XmlTagMao;
import model.xml.XmlTag;
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
			tag = XmlTagMao.getCompleteTagFromTagInstance(tag);
			if(session.getSelectedForm() != null) {
				TagManager.addTagInParent(new XmlTag(tag) , tag, session.getSelectedPDSCDoc().getRoot(), true, false, true, null);
				session.getSelectedForm().repaintView();
			}
			else DialogUtils.warningMessage("Select document before adding tag");
		}
		
		
		else if(command.equals("showHideTagsListBar")) {
			session.getWizardFrame().ShowHideTagListBar();
		}
		
		
		
		
		

		
		
		
		
		
		
	}
}
