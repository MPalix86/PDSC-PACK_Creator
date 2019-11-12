package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import org.apache.commons.io.FilenameUtils;

import business.Session;
import business.TagManager;
import business.utils.CustomUtils;
import mao.XmlTagMao;
import model.Response;
import model.xml.XmlTag;
import view.comp.TagMenuItem;
import view.comp.utils.DialogUtils;
import view.wizardFrame.comp.xmlForm.comp.TagRow;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.AddAttributeFrame;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagOptionMenu;

public class TagOptionMenuListener implements ActionListener , MouseListener{
	
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
			long a = System.nanoTime();
			TagManager.removeSelectedChildFromParent(tag, tag.getParent(), true, true);
			session.getSelectedForm().repaintView();
			long b = System.nanoTime();
			long c = (b - a)/1000000;
			System.out.println("deleteTag " + c);
		}
		
		
		
		else if(command.equals("cloneTag")) {
			int copiesNumber = DialogUtils.cloneDialog();
			TagManager.cloneTag(tag, copiesNumber, true, true);
			session.getSelectedForm().repaintView();

		}
		
		else if(command.equals("addTag")) {			
			TagMenuItem tagMenuItem = (TagMenuItem) e.getSource();
			XmlTag child = tagMenuItem.getTag();
			XmlTag newTag = new XmlTag(child, child.getParent());
			TagManager.addTagInParent(newTag, child, child.getParent(), true, true, true, null);
			session.getSelectedForm().repaintView();
			session.getSelectedForm().highlightComponetBckGround(newTag,null);
			AddAttributeFrame frame = null;
			if(newTag.getAttrArr() != null) frame = new AddAttributeFrame(newTag);
			if (frame != null) frame.requestFocusInWindow();
		}

		
		
		
		
		else if(command.equals("addAttribute")) {
			new AddAttributeFrame(tag);
		}
		
		
		else if (command.equals("addPath")) {
			File file = DialogUtils.showChooseFileFrame();
			if(file != null) {
				if(tag.getContent()!= null) {
					String value =  tag.getContent().replace(FilenameUtils.getName(tag.getContent()), "") + file.getName();
					TagManager.setTagContent(tag, value, true);
				}
				else TagManager.setTagContent(tag, file.getName(), true);
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
			TagManager.addRequiredChildren(tag, true);
			session.getSelectedForm().repaintView();
			session.getSelectedForm().highlightComponetBckGround(tag, null);
		}

		
		
		else if(command.equals("addCustomAttribute")) {
			
			String attrNames = DialogUtils.showInputDialog("Add Custom Attribute", "Add one or more attributes separated by space \n attr1 attr2 ...");
			if (attrNames != null){
//				boolean val = DialogUtils.CustomButtonTrueFalsePane("Update DB", "Do you want to add this attribute in DB", "Add", "Cancel", null);
//				
				String[] names = CustomUtils.separateText(attrNames, " ");	
				
//				if(val) {
//					for(String name : names) {
//						XmlAttribute attr = XmlAttributeDao.getInstance().getAttributeFromName(name);
//						if(attr != null) {
//							System.out.println(attr.getAttrId());
//							XmlAttributeDao.getInstance().InsertAttributeInTag(attr.getAttrId(), tag.getTagId());
//						}
//						else {
//							int attrId = XmlAttributeDao.getInstance().InsertNewAttribute(name);
//							if(attrId != 0) {
//								XmlAttributeDao.getInstance().InsertAttributeInTag(attrId, tag.getTagId());
//							}
//						}
//						tag.addAttr(attr);
//					}
//				}
				
				
				TagManager.addCustomAttributes(tag, names, true);
				TagRow row = session.getSelectedForm().getTagOpenRow(tag);
				row.update();
				row.requestFocus();
			}
		}
		

		
		else if(command.equals("addCustomTag")) {
			
			String tagNames = DialogUtils.showInputDialog("Add Custom Element", "Add one or more custom elements separated by space \n el1 el2 ...");
			if(tagNames != null && tagNames.trim() != "") {
				String[] names = CustomUtils.separateText(tagNames, " ");
				TagManager.addCustomTags(tag, names, true, true);
				session.getSelectedForm().repaintView();
				
			}
			
		}
		
		
		
		
		else if(command.equals("addFiles")) {
			if(tag.getValueType().equals("FilesContainer")) {
				XmlTag tagFile = XmlTagMao.getCompleteTagFromNameAndParent("file", tag);
				Response r = DialogUtils.multipleFilesTagSettingsDialog(tagFile, "Customize <file>", "message", null);
				int status = r.getStatus();
				if(status == 0) {
					String path = r.getMessage();
					File[] listOfFiles = DialogUtils.showChooseMultipleFilesFrame();
					if(listOfFiles != null) {
						System.out.println("Non funziona");
						TagManager.addMultipleFiles(tag, tagFile, listOfFiles, path, true);
						session.getSelectedForm().repaintView();
					}
				}
			}	

		}
		

			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		TagMenuItem item = (TagMenuItem) e.getSource();
		XmlTag tag = item.getTag();
		String description = " ELEMENT : <" + tag.getName() + ">\n" + XmlTagMao.getTagDescription(tag);
		session.getWizardFrame().addDescriptionPane();
		session.getWizardFrame().setDescriptionText(description);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
