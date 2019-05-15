package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import business.Utils;
import model.XmlTag;
import view.TagCustomizationFrame;

public class TagListPanelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		/* 
		 * for description see tagCustomizationFrameListener 
		 */
		Class<XmlTag> tagClass = null;
		try {
			tagClass = (Class<XmlTag>) Class.forName("model.pdsc.tags." + Utils.firstLetterCaps(e.getActionCommand()));
			Constructor<?> cons = tagClass.getConstructor(boolean.class, XmlTag.class, int.class);
			TagCustomizationFrame f = new TagCustomizationFrame((XmlTag) cons.newInstance(true, null, 1));
		} 
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		System.out.println(e.getActionCommand());
	}

}
