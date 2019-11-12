package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import business.Session;
import business.XmlAttributeManager;
import business.TagManager;
import model.xml.XmlAttribute;
import model.xml.XmlEnum;
import model.xml.XmlTag;
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
			TagManager.removeSelectedAttributeFromParent(attr, tag, true, true);
			
		}
		
		else if (command.equals("addPath")) {
			File file = DialogUtils.showChooseFileFrame();
			if(file != null) {
				if(attr.getValue()!= null) {
					String value = attr.getValue().replace(FilenameUtils.getName(attr.getValue()), "") + file.getName() ;
					XmlAttributeManager.setAttributeValue(attr, value, true);
				}
				else XmlAttributeManager.setAttributeValue(attr, file.getName(), true);
				attr.setFile(file);
			}
				
		}
		
		
		else if (command.equals("removePath")) {
			attr.setValue(attr.getValue().replace(attr.getFile().getName(), ""));
			attr.setFile(null);
			session.getSelectedForm().getTagOpenRow(attr.getTag()).update();
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
		
		TagRow row = session.getSelectedForm().getTagOpenRow(tag);
		if(row != null) row.update();
	}
}


