package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JPopupMenu;

import listeners.wizardFrameListeners.comp.xmlForm.comp.TagOptionMenuListener;
import model.XmlTag;
import view.comp.TagMenuItem;

public class TagOptionMenu extends JPopupMenu {
	
	TagOptionMenuListener listener =  new TagOptionMenuListener();
	
	private XmlTag tag;

	
	public TagOptionMenu(XmlTag tag) {
		this.tag = tag;
		
		TagMenuItem deleteTagItem = new TagMenuItem("Delete",tag);
		deleteTagItem.addActionListener(listener);
		deleteTagItem.setActionCommand("deleteTag");
		if (tag.isRequired()) {
			deleteTagItem.setEnabled(false);
		}
		
		add(deleteTagItem);

	}
	


}
