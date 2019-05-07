package business;

import java.io.File;
import java.util.ArrayList;

import view.PdscWizardFrame;
import view.TagCustomizationFrame;

public class Session {
	private static File currentWorkingFile = null;
	private static ArrayList<File> arrWorkingFile = new ArrayList<File>();
	private static String currentWorkingFileName;
	private static Session instance;																			
	private  PdscWizardFrame wizardFrame;
	private static TagCustomizationFrame tagCustomizationFrame;
																												/* GETTTERS */
	
	
	public  TagCustomizationFrame getTagCustomizationFrame() {
		return tagCustomizationFrame;
	}

	public  void setTagCustomizationFrame(TagCustomizationFrame tagCustomizationFrame) {
		Session.tagCustomizationFrame = tagCustomizationFrame;
	}

	//--------------------------------------------------------------------------getCurrentWorkingFile()
	public File getCurrentWorkingFile() {
		return Session.currentWorkingFile;
	}
	
	//--------------------------------------------------------------------------getWizardFrame()
	public PdscWizardFrame getWizardFrame() {
		return this.wizardFrame;
	}
	
	//--------------------------------------------------------------------------getCurrentWorkingFileName()
	public String getCurrentWorkingFileName() {
		return Session.currentWorkingFileName;
	}
	
	//--------------------------------------------------------------------------getArrWorkingFile()
	public ArrayList<File> getArrWorkingFile() {
		return Session.arrWorkingFile;
	}
																												/* SETTERS */
	
	
	//--------------------------------------------------------------------------setCurrentWorkingFileName()
	public void setCurrentWorkingFileName(String name) {
		Session.currentWorkingFileName = name;
	}	
	
	//--------------------------------------------------------------------------setWizardFrame()
	public void setWizardFrame(PdscWizardFrame wizardFrame) {
		this.wizardFrame = wizardFrame;
	}
																												/* OTHERS */
	
	//--------------------------------------------------------------------------addFileArrWorkingFile()
	public void addFileArrWorkingFile(File file) {
		if(!Session.arrWorkingFile.contains(file)){
			Session.arrWorkingFile.add(file);
		}
	}
	
	//--------------------------------------------------------------------------removeFileArrWorkingFile()
	public void removeFileArrWorkingFile(File file) {
		int index;
		if(Session.arrWorkingFile.contains(file)){
			index = Session.arrWorkingFile.indexOf(file);
			Session.arrWorkingFile.remove(index);
		}
		
	}
	
	//--------------------------------------------------------------------------getInstance() 
	public static synchronized Session getInstance(){
		if(instance==null)
			instance = new Session();
		return instance;
	}
	
}
