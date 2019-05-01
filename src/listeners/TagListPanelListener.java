package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.pdscComponent.Conditions;
import model.pdscType.TagTypeEnum;
import view.TagCustomizationFrame;

public class TagListPanelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TagTypeEnum tagType = TagTypeEnum.valueOf(e.getActionCommand());
		System.out.println(e.getActionCommand());
		switch(tagType) {
			case dominate : 
			case requirements :
			case create  :
			case repository :
			case releases  :
			case keywords  :
			case generators  :
			case devices  :
			case boards  :
			case taxonomy  :
			case apis  :
			case conditions  : TagCustomizationFrame f = new TagCustomizationFrame(new Conditions());
			case examples  :
			case components  :
		}
	}

}
