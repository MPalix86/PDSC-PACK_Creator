package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;
import business.TagManager;
import business.utils.XmlTagUtils;
import mao.XmlTagMao;
import model.xml.XmlTag;
import view.comp.TagButton;
import view.comp.utils.DialogUtils;
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
		String command = e.getActionCommand();
		
		if (command.equals("addRootChild")) {
			TagListBarButton btn = (TagListBarButton) e.getSource();
			
			XmlTag tag = btn.getTag();
			tag = XmlTagMao.getCompleteTagFromTagInstance(tag);
			if(session.getSelectedForm() != null) {
				XmlTag newTag = new XmlTag(tag, session.getSelectedForm().getRoot());
				XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(session.getSelectedForm().getRoot(), newTag.getName());
				TagManager.addTagInParent(newTag, modelTag, session.getSelectedForm().getRoot(), true, true, true, null);
				session.getSelectedForm().repaintView();
				session.getSelectedForm().highlightComponetBckGround(newTag, null);
			}
			else DialogUtils.warningMessage("Select document before adding tag");
			
		}
		
		if(command.equals("showDescription")) {
			
			TagButton b = (TagButton) e.getSource();
			XmlTag tag = b.getTag();
			String d = "ELEMENT : " + "<" + tag.getName() + ">\n";
			String d1 = XmlTagMao.getTagDescription(tag);
			if (d1 != null) session.getWizardFrame().setDescriptionText(d + d1);
			else session.getWizardFrame().setDescriptionText(d + "NO DESCRIPTION AVAILABLE");
			session.getWizardFrame().addDescriptionPane();
		}
	}

}
