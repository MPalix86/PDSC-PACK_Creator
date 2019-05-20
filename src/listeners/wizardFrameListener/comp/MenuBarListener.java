package listeners.wizardFrameListener.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.XmlTag;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.munuBar.MenuBar;
import view.wizardFrame.comp.munuBar.comp.TagMenuItem;

public class MenuBarListener implements ActionListener {
	
	private WizardFrame wizardFrame;
	
	public MenuBarListener(MenuBar menuBar) {
		this.wizardFrame = wizardFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command == "addPackageChild") {
			TagMenuItem item = (TagMenuItem) e.getSource();
			Class tagClass = item.getTagClass();
			Constructor<?> c = null;
			try {
				c = tagClass.getConstructor(boolean.class, XmlTag.class, int.class);
			} catch (NoSuchMethodException e2) {
				 
				e2.printStackTrace();
			} catch (SecurityException e2) {
				 
				e2.printStackTrace();
			}
			try {
				try {
					TagCustomizationFrame f = new TagCustomizationFrame((XmlTag) c.newInstance(true,null,1) );
				} catch (InvocationTargetException e1) {
					 
					e1.printStackTrace();
				}
			} 
			catch (SecurityException e1) {
				 
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				 
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				 
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				 
				e1.printStackTrace();
			}
	
		}
		
		
		
		
	}

}
