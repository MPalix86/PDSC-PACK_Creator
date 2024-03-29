package view.wizardFrame.comp.xmlForm.comp.namespaceComp;

import javax.swing.JLabel;

import model.xml.XmlNameSpace;
import view.comp.utils.ColorUtils;

public class NameSpaceLabel extends JLabel{
	
	private XmlNameSpace nameSpace;
	
	public NameSpaceLabel(XmlNameSpace nameSpace, String text) {
		super(text);
		this.nameSpace = nameSpace;
		this.setForeground(ColorUtils.ATTR_COLOR);
		
	}
	

	/**
	 * @return the attr
	 */
	public XmlNameSpace getnameSpace() {
		return nameSpace;
	}
}
