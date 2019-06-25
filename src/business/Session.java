package business;

import java.io.File;
import java.util.ArrayList;

import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.xmlForm.XmlForm;;

public class Session {

	private static File lastDirectoryOpenPath;
	private static Session instance;																			
	private static WizardFrame wizardFrame;
	private static ArrayList<XmlForm> formArray;
	private static XmlForm selectedForm;
	

	
	
	
	/**
	 * @return the lastDirectoryOpenPath
	 */
	public static File getLastDirectoryOpenPath() {
		return lastDirectoryOpenPath;
	}

	/**
	 * @param lastDirectoryOpenPath the lastDirectoryOpenPath to set
	 */
	public static void setLastDirectoryOpenPath(File lastDirectoryOpenPath) {
		Session.lastDirectoryOpenPath = lastDirectoryOpenPath;
	}


																												/* GETTTERS */

	public WizardFrame getWizardFrame() {
		return this.wizardFrame;
	}
	
	

	public void setWizardFrame(WizardFrame wizardFrame) {
		this.wizardFrame = wizardFrame;
	}
	
	public void addXmlForm(XmlForm form) {
		if(formArray == null) formArray = new ArrayList<XmlForm>();
		formArray.add(form);
	}
	
	
	public XmlForm getSelectedForm() {
		return this.selectedForm;
	}
																												/* OTHERS */
	
 
	public static synchronized Session getInstance(){
		if(instance==null)
			instance = new Session();
		return instance;
	}
	
}
