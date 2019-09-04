package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import business.CustomUtils;
import business.Session;
import business.XmlTagBusiness;
import model.XmlTag;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;
import view.wizardFrame.comp.xmlForm.comp.TagRow;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagOptionMenu;

public class TagOptionMenuListener implements ActionListener{
	
	private Session session = Session.getInstance();
	private TagOptionMenu menu;
	
	public TagOptionMenuListener(TagOptionMenu menu) {
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		TagMenuItem item = (TagMenuItem) e.getSource();
		XmlTag tag = item.getTag();
		
		if(command.equals("deleteTag")) {
			XmlTagBusiness.removeSelectedChildFromParent(tag, tag.getParent(), true, true);
			session.getSelectedForm().UpdateView();
		}
		
		
		
		else if(command.equals("cloneTag")) {
			int copiesNumber = DialogUtils.cloneDialog();
			XmlTagBusiness.cloneTag(tag, copiesNumber, true, true);
			session.getSelectedForm().UpdateView();

		}
		
		else if(command.equals("addTag")) {			
			
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag child = tagMenuItem.getTag();
			XmlTag newTag = new XmlTag(child, child.getParent());
			XmlTagBusiness.addTagInParent(newTag, child, child.getParent(), true, true, null);
			session.getSelectedForm().UpdateView();
			if(child.getAttrArr() != null) new AddAttributeFrame(newTag);
		}

		
		
		
		
		else if(command.equals("addAttribute")) {
			new AddAttributeFrame(tag);
		}
		
		
		else if (command.equals("addPath")) {
			File file = DialogUtils.showChooseFileFrame();
			if(file != null) {
				if(tag.getContent()!= null) {
					String value =  tag.getContent().replace(FilenameUtils.getName(tag.getContent()), "") + file.getName();
					XmlTagBusiness.setTagContent(tag, value, true);
				}
				else XmlTagBusiness.setTagContent(tag, file.getName(), true);
				tag.setFile(file);
				session.getSelectedForm().getTagOpenRow(tag).update();
				
			}		
		}
		
		else if(command.equals("removePath")) {
			tag.setContent(tag.getContent().replace(tag.getFile().getName(), ""));
			tag.setFile(null);
			session.getSelectedForm().getTagOpenRow(tag).update();
		}
		
		else if(command.equals("addRequiredChildren")) {
			XmlTagBusiness.addRequiredChildren(tag.getParent(), true);
			session.getSelectedForm().UpdateView();
		}

		
		
		else if(command.equals("addCustomAttribute")) {
			
			String attrNames = DialogUtils.showInputDialog("Add Custom Attribute", "Add one or more attributes separated by space \n attr1 attr2 ...");
			if (attrNames != null){
				String[] names = CustomUtils.separateText(attrNames, " ");	
				XmlTagBusiness.addCustomAttributes(tag, names, true);
				TagRow row = session.getSelectedForm().getTagOpenRow(tag);
				row.update();
				row.requestFocus();
			}
		}
		

		
		else if(command.equals("addCustomTag")) {
			String tagNames = DialogUtils.showInputDialog("Add Custom Element", "Add one or more custom elements separated by space \n el1 el2 ...");
			if(tagNames != null && tagNames.trim() != "") {
				String[] names = CustomUtils.separateText(tagNames, " ");
				XmlTagBusiness.addCustomTags(tag, names, true, true);
				session.getSelectedForm().UpdateView();
				
			}
			
		}
		

			
		}
	}
