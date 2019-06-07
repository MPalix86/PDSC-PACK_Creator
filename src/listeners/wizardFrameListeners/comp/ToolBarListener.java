package listeners.wizardFrameListeners.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import business.Session;
import listeners.wizardFrameListeners.WizardFrameListener;
import model.XmlTag;
import model.pdsc.Pack;
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
				TagCustomizationFrame f = new TagCustomizationFrame((XmlTag) c.newInstance(false,null,1) );
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		
		
		else if(command.equals("showHideTagsListBar")) {
			session.getWizardFrame().ShowHideTagListBar();
		}
		
		else if (command.equals("createPack")) {
			ArrayList<XmlTag> tagArr = session.getWizardFrame().getFormPanelContainer().getFormPanel().getTagArr();
			XmlTag root = tagArr.get(0);
			File path = session.getWizardFrame().showChooseDirectoryFrame();
			if (path != null) {
				try {
					int status = session.getWizardFrame().getPack().createPack(root , path);
					if(status == Pack.PACK_CREATED_CORRECTLY) {
						
					}
					else if (status == Pack.REQUIRED_FIELDS_MISSING) {
						session.getWizardFrame().warningMessage("Required Fields Missing");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
