package view.wizardFrame.comp.tagsListBar.comp;

import model.xml.XmlTag;
import view.comp.SquareButton;

public class TagListBarButton extends SquareButton{
	
	private XmlTag tag;
	
	public TagListBarButton(String text, XmlTag tag) {
		super(text);
		this.tag = tag;
	}

	/**
	 * @return the tagClass
	 */
	public XmlTag getTag() {
		return this.tag;
	}

	
}
