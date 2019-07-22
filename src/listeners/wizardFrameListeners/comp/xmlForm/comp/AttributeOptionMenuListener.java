package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import business.Session;
import model.XmlAttribute;
import model.XmlEnum;
import model.XmlTag;
import view.comp.AttributeMenuItem;
import view.comp.utils.DialogUtils;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class AttributeOptionMenuListener implements ActionListener{
	
	private Session session = Session.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		AttributeMenuItem item = (AttributeMenuItem) e.getSource();
		XmlAttribute attr = item.getAttr();
		XmlTag tag = attr.getTag();
		
		
		if(command.equals("deleteAttribute")) {
			boolean response = true;
			if(attr.isRequired()) response = DialogUtils.yesNoWarningMessage("Following PDSC Standard ' " + attr.getName() + " ' is required for tag <" + tag.getName() + "> \n do you want to delete it ?");
			if(response) {
				tag.removeSelectedAttr(attr);
			}

			/**
			 * IMPORTANT : saving state of root tag for undo redo action
			 */
			Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		}
		
		else if (command.equals("addPath")) {
			File file = DialogUtils.showChooseFileFrame();
			if(file != null) {
				if(attr.getValue()!= null) attr.setValue(attr.getValue().replace(FilenameUtils.getName(attr.getValue()), "") + file.getName()) ;
				else attr.setValue(file.getName()) ;
				session.getSelectedPDSCDoc().addAttrPath(attr, file.getAbsolutePath());
			}
				
		}
		
		
		else if (command.contentEquals("addCustomValue")) {
			String value = DialogUtils.showInputDialog("Add Value", "Insert Value");
			
			if( value != null && value != "") {
				XmlEnum possibleValues = (XmlEnum) attr.getPossibleValues();
				if(!possibleValues.contains(value)) {
					possibleValues.add(1, value);
				}
				attr.setValue(value);
		
			}
		}
		
		/**
		 * IMPORTANT : saving state of root tag for undo redo action
		 */
		Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		
		TagRow row = session.getSelectedForm().getTagOpenRow(tag);
		row.update();
	}
}


