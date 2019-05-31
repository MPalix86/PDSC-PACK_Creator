package view.wizardFrame.comp.xmlForm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.wizardFrameListeners.comp.xmlForm.XmlFormListener;
import model.XmlTag;
import model.pdsc.tags.Description;
import model.pdsc.tags.License;
import model.pdsc.tags.Name;
import model.pdsc.tags.Package;
import model.pdsc.tags.Url;
import model.pdsc.tags.Vendor;
import net.miginfocom.swing.MigLayout;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class XmlForm extends JPanel{
	
	private ArrayList<XmlTag> tagArr;
	
	private HashMap<JPanel,JPanel> openCloseTagRowHashMap;
	private HashMap<XmlTag,JPanel> tagRowHashMap;

	private XmlFormListener listener = new XmlFormListener(this);
	private XmlTag root = new Package(true, null, 1);
	
	private final static int PADDING = 25;
	
	public XmlForm() {
		
		this.tagArr = new ArrayList<XmlTag>();
		root.setSelectedAttrArr(root.getAttrArr());
		root.addSelectedChild(new Vendor(true, null, 1, "STMicroelectronics"));
		root.addSelectedChild(new Name(true, null, 1));
		root.addSelectedChild(new Description(true, null, 1));
		root.addSelectedChild(new License(true, null, 1));
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
		
		tagRowHashMap.clear();
		openCloseTagRowHashMap.clear();
		
		XmlTag parent =  tag;
		
		/** calculating left border based on levelCounter */
		int leftBorder = level * PADDING;
		
		TagRow openRow = new TagRow(parent);
		
		tagRowHashMap.put(parent, openRow);
		
		/** setting calculated border */
		openRow.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
		
		this.add(openRow.open());
		
		
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
			
			TagRow closeRow = new TagRow(parent);
			
			openCloseTagRowHashMap.put(openRow, closeRow);
			
			/** setting calculated border */
			closeRow.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
			
			this.add(closeRow.close());
		}
		
		
	}


	public void addTag(XmlTag tag) {
		this.root.addSelectedChild(tag);
		tag.setParent(root);
		tag.setRequired(false);
		placeComponents();
	}
	
	
	public void removeTag() {
		placeComponents();
	}
	
	
	public ArrayList<XmlTag> getTagArr() {
		return this.tagArr;
	}
	
	
	
	
	
	/**
	 * @return the openCloseTagRowHashMap
	 */
	public HashMap<JPanel, JPanel> getOpenCloseTagRowHashMap() {
		return openCloseTagRowHashMap;
	}



	/**
	 * @return the tagRowHashMap
	 */
	public HashMap<XmlTag, JPanel> getTagRowHashMap() {
		return tagRowHashMap;
	}

	
	
}
