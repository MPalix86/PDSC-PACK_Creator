package view.wizardFrame.comp.TextPaneForm.comp;

import javax.swing.JLabel;

import model.XmlTag;
import view.comp.CustomColor;

public class TagLabel extends JLabel{
	
	private XmlTag tag;
	
	public TagLabel(String text, XmlTag tag) {
		super(text);
		this.tag = tag;
		customize();
	}
	
	
	private void customize() {
		this.setForeground(CustomColor.TAG_COLOR);
	}
}
