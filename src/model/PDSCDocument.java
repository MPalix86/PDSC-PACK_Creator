package model;

import java.io.File;

import view.wizardFrame.comp.xmlForm.XmlForm;

public class PDSCDocument {
	
	private XmlForm form;
	
	private File sourcePath;
	
	private Pack pack;
	
	public PDSCDocument(XmlForm form, File sourcePath) {
		this.form = form;
		this.sourcePath = sourcePath;
		this.pack = new Pack();
	}
	
	
	public PDSCDocument(XmlForm form, String sourcePath) {
		this.form = form;
		this.sourcePath = new File(sourcePath);
		this.pack = new Pack();
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

	/**
	 * @return the pack
	 */
	public Pack getPack() {
		return pack;
	}

	/**
	 * @param pack the pack to set
	 */
	public void setPack(Pack pack) {
		this.pack = pack;
	}
	
	

}
