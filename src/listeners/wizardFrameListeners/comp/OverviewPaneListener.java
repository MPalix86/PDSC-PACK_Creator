package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import business.XmlTagBusiness;
import model.XmlTag;
import view.tagCustomizationFrame.TagCustomizationFrame;

public class OverviewPaneListener implements ActionListener{
	
	private Session session;
	
	public OverviewPaneListener() {
		this.session = Session.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("createNewPDSC")) {
			XmlTag root = XmlTagBusiness.getRoot();
			root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("vendor", root));
			root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("name", root));
			root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("description", root));
			root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("license", root));
			root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("url", root));
			
			/**
			 * IMPORTANT set to null before creation of new xmlForm
			 */
			session.setSelectedForm(null);
			
			new TagCustomizationFrame(root);
		}
		
	}

}
