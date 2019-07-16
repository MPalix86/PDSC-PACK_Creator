package model;

import java.io.File;
import java.util.HashMap;

import view.wizardFrame.comp.xmlForm.XmlForm;

public class PDSCDocument {
	
	private XmlForm form;
	
	private File sourcePath;
	
	private HashMap <XmlAttribute,String> pathFilesHashMap;
	
	private XmlTag root;
	
	private UndoManager undoManager;
	
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root) {
		
		this.pathFilesHashMap = new HashMap <XmlAttribute,String>();
		
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root) {
		this.pathFilesHashMap = new HashMap <XmlAttribute,String>();
		
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
	}
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root, UndoManager m) {
		
		this.pathFilesHashMap = new HashMap <XmlAttribute,String>();
		
		this.undoManager = m;
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root, UndoManager m) {
		this.pathFilesHashMap = new HashMap <XmlAttribute,String>();
		
		this.undoManager = m;
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
	}
	
	
	
	public void addPath(XmlAttribute attr, String sourcePath) {
		if(pathFilesHashMap.containsKey(attr)) pathFilesHashMap.replace(attr, sourcePath);
		else {
			this.pathFilesHashMap.put(attr , sourcePath);
		}
	}
	

	/**
	 * @return the form
	 */
	public XmlForm getForm() {
		return form;
	}

	/**
	 * @param form the form to set
	 */
	public void setForm(XmlForm form) {
		this.form = form;
	}

	/**
	 * @return the sourcePath
	 */
	public File getSourcePath() {
		return sourcePath;
	}

	/**
	 * @param sourcePath the sourcePath to set
	 */
	public void setSourcePath(File sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	public XmlTag getRoot() {
		return this.root;
	}
	
	public HashMap <XmlAttribute,String> getPathFilesHashMap(){
		return this.pathFilesHashMap;
	}
	
	public void setUndoManager(UndoManager m) {
		this.undoManager = m;
	}
	
	public UndoManager getUndoManager() {
		return this.undoManager;
	}

	

}
