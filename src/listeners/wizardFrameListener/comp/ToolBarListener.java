package listeners.wizardFrameListener.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import business.Session;
import listeners.wizardFrameListener.WizardFrameListener;
import model.XmlTag;
import view.comp.TagMenuItem;
import view.tagCustomizationFrame.TagCustomizationFrame;
import view.wizardFrame.comp.toolBar.ToolBar;

public class ToolBarListener extends WizardFrameListener implements ActionListener {
	
	private ToolBar toolBar;
	
	private Session session;
	
	public ToolBarListener(ToolBar toolBar) {
		this.toolBar = toolBar;
		this.session = Session.getInstance();
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
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
			try {
				TagCustomizationFrame f = new TagCustomizationFrame((XmlTag) c.newInstance(true,null,1) );
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		
		
		if(command.equals("showHideTagsListBar")) {
			session.getWizardFrame().ShowHideTagListBar();
		}
		
		
		
		
	}

}
