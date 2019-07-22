package model;

import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.util.HashMap;

import listeners.ClipboardListener;
import view.wizardFrame.comp.xmlForm.XmlForm;

public class PDSCDocument {
	
	private XmlForm form;
	
	private File sourcePath;
	
	private HashMap <XmlAttribute,String> attrPathFilesHashMap;
	
	private HashMap <XmlTag, String> tagPathFilesHashMap;
	
	private XmlTag root;
	
	private UndoManager undoManager;
		
	private Clipboard clipboard = new Clipboard("PDSCCReator ClipBoard");
	
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root) {
		
		this.attrPathFilesHashMap = new HashMap <XmlAttribute,String>();
		this.tagPathFilesHashMap = new HashMap <XmlTag,String>();
		clipboard.addFlavorListener(new ClipboardListener());
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root) {
		
		this.attrPathFilesHashMap = new HashMap <XmlAttribute,String>();
		this.tagPathFilesHashMap = new HashMap <XmlTag,String>();
		
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
	}
	
	public PDSCDocument(XmlForm form, File sourcePath, XmlTag root, UndoManager m) {
		
		this.attrPathFilesHashMap = new HashMap <XmlAttribute,String>();
		this.tagPathFilesHashMap = new HashMap <XmlTag,String>();
		
		this.undoManager = m;
		this.form = form;
		this.sourcePath = sourcePath;
		this.root = root;
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath, XmlTag root, UndoManager m) {
		this.attrPathFilesHashMap = new HashMap <XmlAttribute,String>();
		
		this.undoManager = m;
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.root = root;
	}
	
	
	
	public void addAttrPath(XmlAttribute attr, String sourcePath) {
		if(attrPathFilesHashMap.containsKey(attr)) attrPathFilesHashMap.replace(attr, sourcePath);
		else {
			this.attrPathFilesHashMap.put(attr , sourcePath);
		}
	}
	
	
	
	public void addTagPath(XmlTag tag, String sourcePath) {
		if(tagPathFilesHashMap.containsKey(tag)) tagPathFilesHashMap.replace(tag, sourcePath);
		else {
			this.tagPathFilesHashMap.put(tag , sourcePath);
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
	
	public HashMap <XmlAttribute,String> getAttrPathFilesHashMap(){
		return this.attrPathFilesHashMap;
	}
	
	public HashMap <XmlTag,String> getTagPathFilesHashMap(){
		return this.tagPathFilesHashMap;
	}
	
	public void setUndoManager(UndoManager m) {
		this.undoManager = m;
	}
	
	public UndoManager getUndoManager() {
		return this.undoManager;
	}
	
	public Clipboard getClipboard() {
		return this.clipboard;
	}

	

}
