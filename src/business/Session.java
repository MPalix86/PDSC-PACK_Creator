package business;

import java.io.File;
import java.util.ArrayList;

import view.PdscWizardFrame;

public class Session {
	private static File currentWorkingFile = null;
	private static ArrayList<File> arrWorkingFile = new ArrayList<File>();
	private static String currentWorkingFileName;
	private static Session instance;																			
	private static PdscWizardFrame wizardFrame;
																												/* GETTTERS */
	
	
	//--------------------------------------------------------------------------getCurrentWorkingFile()
	public static File getCurrentWorkingFile() {
		return Session.currentWorkingFile;
	}
	
	//--------------------------------------------------------------------------getWizardFrame()
	public static PdscWizardFrame getWizardFrame() {
		return Session.wizardFrame;
	}
	
	//--------------------------------------------------------------------------getCurrentWorkingFileName()
	public static String getCurrentWorkingFileName() {
		return Session.currentWorkingFileName;
	}
	
	//--------------------------------------------------------------------------getArrWorkingFile()
	public static ArrayList<File> getArrWorkingFile() {
		return Session.arrWorkingFile;
	}
																												/* SETTERS */
	
	//--------------------------------------------------------------------------setCurrentWorkingFile()
	public static void setCurrentWorkingFile(File currentWorkingFile) {
		Session.currentWorkingFile = currentWorkingFile;
		Session.setCurrentWorkingFileName(currentWorkingFile.getName());
	}
	
	//--------------------------------------------------------------------------setCurrentWorkingFileName()
	public static void setCurrentWorkingFileName(String name) {
		Session.currentWorkingFileName = name;
	}	
	
	//--------------------------------------------------------------------------setWizardFrame()
	public static void setWizardFrame(PdscWizardFrame wizardFrame) {
		Session.wizardFrame = wizardFrame;
	}
																												/* OTHERS */
	
	//--------------------------------------------------------------------------addFileArrWorkingFile()
	public static void addFileArrWorkingFile(File file) {
		if(!Session.arrWorkingFile.contains(file)){
			Session.arrWorkingFile.add(file);
		}
	}
	
	//--------------------------------------------------------------------------removeFileArrWorkingFile()
	public static void removeFileArrWorkingFile(File file) {
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
