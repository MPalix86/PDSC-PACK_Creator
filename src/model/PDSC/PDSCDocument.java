package model.PDSC;

import java.io.File;

import model.UndoManager;
import model.xml.XmlTag;
import view.wizardFrame.comp.xmlForm.XmlForm;

/**
 * PDSCDocument abstraction
 * 
 * @author mircopalese
 */
public class PDSCDocument {
	
	/** dynamic interface that contains the editable document */
	private XmlForm form;
	
	/** 
	 * path of PDSC file 
	 *  when new document is created sourcePath is null.
	 *  when sourcePath is null, PDSC document is not saved on file system
	 */
	private File sourcePath;
	
	/**
	 * Root tag containing all document
	 */
	private XmlTag root;
	
	/** Undo Manager to manage undo redo system */
	private UndoManager undoManager;
	
	/** xsd file associated for check of pdsc document */
	private File xsd;
	
	/** chekcer for checking conditions based on "cbundle" tag */
	private PDSCConditionsChecker checker ;
	
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root,PDSCConditionsChecker checker) {
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
		this.checker = checker;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root,PDSCConditionsChecker checker) {
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
		this.checker = checker;
		
	}
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root, UndoManager m,PDSCConditionsChecker checker) {
		this.undoManager = m;
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
		this.checker = checker;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root, UndoManager m,PDSCConditionsChecker checker) {
		this.undoManager = m;
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
		this.checker = checker;
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
	
	public File getXsdFile() {
		return this.xsd;
	}
	
	public void setXsdFile(File f) {
		this.xsd = f;
	}
	
	public PDSCConditionsChecker getPDSCConditionsChecker() {
		return this.checker;
	}

	

}
