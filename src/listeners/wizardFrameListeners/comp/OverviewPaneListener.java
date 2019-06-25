package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.XmlTagBusiness;
import model.XmlTag;
import view.tagCustomizationFrame.TagCustomizationFrame;

public class OverviewPaneListener implements ActionListener{

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
			new TagCustomizationFrame(root);
		}
		
	}

}
