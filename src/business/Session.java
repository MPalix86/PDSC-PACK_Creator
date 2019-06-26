package business;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.xmlForm.XmlForm;;

public class Session {

	/** last path open */
	private static File lastDirectoryOpenPath;
	
	
	private static Session instance;	
	
	/** wizardFrame instance */
	private static WizardFrame wizardFrame;
	
	/** Array that contains all current form on which user is working */
	private static ArrayList<XmlForm> formArray;
	
	/**
	 * contains always current selected form on wich user is working.
	 * On This variable are base many function like "save file" in Menu ,
	 * "add root children in XmlForm class", ecc ecc.
	 * 
	 * IMPORTANT :
	 * set to null before creation of new xmlForm, and after creation update it;
	 * This is the only way the tagCustomizationFrameListener has to distinguish creation of new xmlForm
	 * from the update of selected form;
	 * 
	 * selectedForm change based on current selected tab in wizardFrame
	 */
	private static XmlForm selectedForm;
	
	
	
	
	
	/**
	 * contains association between xmlForm and file on which save work;
	 * The association is created when user save for the first time his work;
	 */
	private static HashMap<XmlForm, File> formFileHashMap;
	
	
	
	
	
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
	
	
	
	
	/**
	 * return wizardFrame
	 * @return wizardFrame;
	 */
	public WizardFrame getWizardFrame() {
		return this.wizardFrame;
	}
	
	

	public void setWizardFrame(WizardFrame wizardFrame) {
		this.wizardFrame = wizardFrame;
	}
	
	public void addXmlFormInFormArr(XmlForm form) {
		if(formArray == null) formArray = new ArrayList<XmlForm>();
		formArray.add(form);
	}
	
	
	public XmlForm getSelectedForm() {
		return this.selectedForm;
	}
	
	
	public void setSelectedForm(XmlForm form) {
		this.selectedForm = form;
	}
																												/* OTHERS */
	
 
	public static synchronized Session getInstance(){
		if(instance==null)
			if(formFileHashMap == null) {
				formFileHashMap = new HashMap<XmlForm,File>();
			}
			instance = new Session();
		return instance;
	}
	
}
