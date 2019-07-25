package business;

import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.util.ArrayList;

import model.PDSCDocument;
import view.wizardFrame.WizardFrame;
import view.wizardFrame.comp.xmlForm.XmlForm;;

public class Session {

	/** last path open */
	private static File lastDirectoryOpenPath;
	
	
	private static Session instance;	
	
	/** wizardFrame instance */
	private static WizardFrame wizardFrame;

	
	
	/**
	 * contains always current selected form with and all necessary information 
	 * on wich user is working.
	 * On This variable are based many function like "save file" in Menu ,
	 * "add root children in XmlForm class", ecc ecc.
	 * 
	 * IMPORTANT :
	 * set to null before creation of new xmlForm, and after creation update it;
	 * This is the only way the tagCustomizationFrameListener has to distinguish creation of new xmlForm
	 * from the update of selected form;
	 * 
	 * selectedForm change based on current selected tab in wizardFrame
	 */
	private static PDSCDocument selectedPdscDoc;
	
	/**
	 * represent open all open document on which user is working
	 */
	private static ArrayList<PDSCDocument> currentWorkinPdscDoc ;
	
	private Clipboard clipboard;
	
	
	private Session() {
		 clipboard = new Clipboard("PDSCCReator ClipBoard");
	}
	
	
	
	
	
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
	
	public PDSCDocument getSelectedPDSCDoc() {
		return this.selectedPdscDoc;
	}
	
	
	public void setSelectedPDSCDoc(PDSCDocument doc) {
		this.selectedPdscDoc = doc;
	}
	
	public void setSelectedPDSCDoc(XmlForm form) {
		for (int i = 0; i < currentWorkinPdscDoc.size(); i++) {
			PDSCDocument doc = currentWorkinPdscDoc.get(i);
			if(doc.getForm().equals(form)) {
				setSelectedPDSCDoc(doc);
			}
		}
	}
	
	
	public XmlForm getSelectedForm() {
		if (selectedPdscDoc == null) return null;
		return selectedPdscDoc.getForm();
	}
	
	
	public void addInCurrentWorkingPdscDoc(PDSCDocument doc) {
		if(currentWorkinPdscDoc == null) currentWorkinPdscDoc = new ArrayList<PDSCDocument>();
		currentWorkinPdscDoc.add(doc);
	}
	
	
	public PDSCDocument getPdscDocFromXmlForm(XmlForm form) {
		if(currentWorkinPdscDoc != null) {
			for (int i = 0; i < currentWorkinPdscDoc.size(); i++) {
				PDSCDocument doc = currentWorkinPdscDoc.get(i);
				if(doc.getForm().equals(form)) {
					return doc;
				}
			}
		}
		return null;
	}
	
	public PDSCDocument getPdscDocFromFilePath(File file) {
		if(currentWorkinPdscDoc != null) {
			for (int i = 0; i < currentWorkinPdscDoc.size(); i++) {
				PDSCDocument doc = currentWorkinPdscDoc.get(i);
				if(doc.getSourcePath() != null) {
					if(doc.getSourcePath().equals(file)) {
						return doc;
					}
				}
				
			}
		}

		return null;
	}
	

	
	public void removeFromCurrentWorkingPdscDoc(PDSCDocument doc) {
		if(this.currentWorkinPdscDoc.contains(doc)) {
			currentWorkinPdscDoc.remove(doc);
		}
	}
																										
	
 
	public static synchronized Session getInstance(){
		if(instance==null)
			instance = new Session();
		return instance;
		
	}
	
	public Clipboard getClipboard() {
		return this.clipboard;
	}
	
	
}
