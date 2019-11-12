package view.wizardFrame.comp.xmlForm.comp.tagComp;

import javax.swing.JLabel;

import model.xml.XmlTag;
import view.comp.utils.ColorUtils;

public class TagLabel extends JLabel{
	
	private XmlTag tag;
	
	public TagLabel(String text, XmlTag tag) {
		super(text);
		this.tag = tag;
		customize();
	}
	
	
	private void customize() {
		this.setForeground(ColorUtils.TAG_COLOR);
	}


	/**
	 * @return the tag
	 */
	public XmlTag getTag() {
		return tag;
	}
}
