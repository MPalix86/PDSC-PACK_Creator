package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Utils;
import model.XmlTag;
import model.pdscTag.Conditions;
import model.pdscType.TagTypeEnum;
import view.TagCustomizationFrame;

public class TagListPanelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		/* 
		 * for description see tagCustomizationFrameListener 
		 */
		Class<XmlTag> tagClass = null;
		try {
			tagClass = (Class<XmlTag>) Class.forName("model.pdscTag." + Utils.firstLetterCaps(e.getActionCommand()));
		} 
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
		System.out.println(e.getActionCommand());
		try {
			TagCustomizationFrame f = new TagCustomizationFrame(tagClass.newInstance());
		} 
		catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

}
