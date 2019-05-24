package view.wizardFrame.comp.tagsListBar.comp;

import view.comp.SquareButton;

public class ListBarButton extends SquareButton{
	
	private Class tagClass;
	
	public ListBarButton(String text, Class tagClass) {
		super(text);
		this.tagClass = tagClass;
	}

	/**
	 * @return the tagClass
	 */
	public Class getTagClass() {
		return tagClass;
	}

	
}
