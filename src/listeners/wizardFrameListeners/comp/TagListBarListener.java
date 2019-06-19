package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.XmlTagBusiness;
import model.XmlTag;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.comp.tagsListBar.TagsListBar;
import view.wizardFrame.comp.tagsListBar.comp.TagListBarButton;

public class TagListBarListener implements ActionListener{
	
	private TagsListBar childrenListBar;

	
	public TagListBarListener(TagsListBar childrenListBar) {
		this.childrenListBar = childrenListBar;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TagListBarButton btn = (TagListBarButton) e.getSource();
		String command = e.getActionCommand();
		if (command.equals("addRootChild")) {
			XmlTag tag = btn.getTag();
			tag = XmlTagBusiness.getCompleteTag(tag);
			new TagCustomizationFrame(new XmlTag(tag));
		}
	}

}
