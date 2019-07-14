package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import business.XmlTagBusiness;
import model.XmlTag;
import view.comp.utils.DialogUtils;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.comp.tagsListBar.TagsListBar;
import view.wizardFrame.comp.tagsListBar.comp.TagListBarButton;

public class TagListBarListener implements ActionListener{
	
	private TagsListBar childrenListBar;
	private Session session;

	
	public TagListBarListener(TagsListBar childrenListBar) {
		this.childrenListBar = childrenListBar;
		session = Session.getInstance();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TagListBarButton btn = (TagListBarButton) e.getSource();
		String command = e.getActionCommand();
		if (command.equals("addRootChild")) {
			XmlTag tag = btn.getTag();
			tag = XmlTagBusiness.getCompleteTagFromTagInstance(tag);
			if(session.getSelectedForm() != null) {
				new TagCustomizationFrame(new XmlTag(tag));
			}
			else DialogUtils.warningMessage("Select document before adding tag");
			
		}
	}

}
