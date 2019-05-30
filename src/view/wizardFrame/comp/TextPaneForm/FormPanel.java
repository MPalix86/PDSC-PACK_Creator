package view.wizardFrame.comp.TextPaneForm;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.XmlTag;
import model.pdsc.tags.Description;
import model.pdsc.tags.License;
import model.pdsc.tags.Name;
import model.pdsc.tags.Package;
import model.pdsc.tags.Url;
import model.pdsc.tags.Vendor;
import net.miginfocom.swing.MigLayout;
import view.wizardFrame.comp.TextPaneForm.comp.TagRow;

public class FormPanel extends JPanel{
	
	private ArrayList<XmlTag> tagArr;
	
	private XmlTag root = new Package(true, null, 1);
	private final static int PADDING = 25;
	
	public FormPanel() {
		
		this.tagArr = new ArrayList<XmlTag>();
		root.setSelectedAttrArr(root.getAttrArr());
		root.addSelectedChild(new Vendor(true, null, 1, "STMicroelectronics"));
		root.addSelectedChild(new Name(true, null, 1, ""));
		root.addSelectedChild(new Description(true, null, 1, ""));
		root.addSelectedChild(new License(true, null, 1, ""));
		root.addSelectedChild(new Url(true, null, 1,"http://sw-center.st.com/packs/x-cube/"));
		tagArr.add(root);

		placeComponents() ;
		
	}
	
	
	
	private void placeComponents() {
		this.removeAll();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
		this.setLayout(new MigLayout("wrap 1") );
		this.tagArr.forEach((t)->paintTag(t,0));
		this.repaint();
		this.revalidate();
	}
	

	
	private void paintTag(XmlTag tag, int level) {
		
		XmlTag parent =  tag;
		
		/** calculating left border based on levelCounter */
		int leftBorder = level * PADDING;
		
		TagRow row = new TagRow(parent);
		
		/** setting calculated border */
		row.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
		
		this.add(row.open());
		
		
		int parentLevel = level;
		
		if( parent.getSelectedChildrenArr() != null) {
		
			/** increases indentation level */
			level++;
			
			ArrayList<XmlTag> xmlChildren = parent.getSelectedChildrenArr();

			/** iterating trough selected children */
			for(int i = 0; i < xmlChildren.size(); i++) {		
				XmlTag child = xmlChildren.get(i);	
				
				/** recursion */
				paintTag(child,level);
			}
			
			/** calculating left border based on parentLevelCounter for closing tag*/
			leftBorder = parentLevel * PADDING;
			
			row = new TagRow(parent);
			
			/** setting calculated border */
			row.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
			
			this.add(row.close());
		}
		
		
	}
	
	
	
	
	public void addTag(XmlTag tag) {
		
		this.root.addSelectedChild(tag);
		paintTag(tag,1);
		this.repaint();
		this.revalidate();
	}
	
	public ArrayList<XmlTag> getTagArr() {
		return this.tagArr;
	}
	
	
	
	
}
