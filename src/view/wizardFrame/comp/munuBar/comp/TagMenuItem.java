package view.wizardFrame.comp.munuBar.comp;

import javax.swing.JMenuItem;

public class TagMenuItem extends JMenuItem {
	
	private Class tagClass;
	
	public TagMenuItem(String text, Class tagClass) {
		super(text);
		this.tagClass = tagClass;
	}
	
	public TagMenuItem(String text) {
		super(text);
	}
	
	
	
	/**
	 * @return the tagClass
	 */
	public Class getTagClass() {
		return tagClass;
	}

	/**
	 * @param tagClass the tagClass to set
	 */
	public void setTagClass(Class tagClass) {
		this.tagClass = tagClass;
	}

}
