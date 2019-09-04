package model;

import java.io.File;

import view.wizardFrame.comp.xmlForm.XmlForm;

public class PDSCDocument {
	
	private XmlForm form;
	
	private File sourcePath;
	
	private XmlTag root;
	
	private UndoManager undoManager;
	
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root) {
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root) {
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
	}
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root, UndoManager m) {
		this.undoManager = m;
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root, UndoManager m) {
		this.undoManager = m;
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
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
	
	public void setUndoManager(UndoManager m) {
		this.undoManager = m;
	}
	
	public UndoManager getUndoManager() {
		return this.undoManager;
	}

	

}
