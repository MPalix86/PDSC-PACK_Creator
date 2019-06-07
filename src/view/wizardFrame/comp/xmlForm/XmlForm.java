package view.wizardFrame.comp.xmlForm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
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
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class XmlForm extends JPanel{
	
	private ArrayList<XmlTag> tagArr;
	
	private HashMap<XmlTag,TagRow> tagOpenRowHashMap;
	private HashMap<XmlTag,TagRow> tagCloseRowHashMap;
	private HashMap<XmlTag,Line2D> OpenCloseTagsLinesHashMap; 

	private XmlFormListener listener;
	private XmlTag root = new Package(true, null, 1);
	
	private final static int PADDING = 25;
	
	public XmlForm() {
		this.OpenCloseTagsLinesHashMap = new HashMap<XmlTag,Line2D>();
		this.tagOpenRowHashMap = new HashMap<XmlTag, TagRow>();
		this.tagCloseRowHashMap = new HashMap<XmlTag, TagRow>();
		this.tagArr = new ArrayList<XmlTag>();
		root.setSelectedAttrArr(root.getAttrArr());
		root.addSelectedChild(new Vendor(true, null, 1, "STMicroelectronics"));
		root.addSelectedChild(new Name(true, null, 1));
		root.addSelectedChild(new Description(true, null, 1));
		root.addSelectedChild(new License(true, null, 1));
		root.addSelectedChild(new Url(true, null, 1,"http://sw-center.st.com/packs/x-cube/"));
		tagArr.add(root);

		listener = new XmlFormListener(this);
		
		placeComponents() ;
		
	}
	
	
	
	private void placeComponents() {
		this.removeAll();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
		this.setLayout(new MigLayout("wrap 1") );
		
		tagOpenRowHashMap.clear();
		tagCloseRowHashMap.clear();
		
		this.tagArr.forEach((t)->paintTag(t,0));
		
		this.repaint();
		this.revalidate();
	}
	

	
	private void paintTag(XmlTag tag, int level) {
		
		XmlTag parent =  tag;
		
		/** calculating left border based on levelCounter */
		int leftBorder = level * PADDING;
		
		TagRow openRow = new TagRow(parent,this);
		
		tagOpenRowHashMap.put(parent, openRow);
		
		/** setting calculated border */
		openRow.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
		
		openRow.setLeftBorder(leftBorder);
		
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
			
			TagRow closeRow = new TagRow(parent,this);
			
			tagCloseRowHashMap.put(parent, closeRow);
			
			/** setting calculated border */
			closeRow.setBorder(new EmptyBorder( 0, leftBorder, 0, 0));
			
			closeRow.setLeftBorder(leftBorder);
			
			this.add(closeRow.close());
		}
		
		
	}


	public void addTag(XmlTag tag) {
		
		if (tag.getParent() == null) {
			this.root.addSelectedChild(tag);
			tag.setParent(root);
			tag.setRequired(false);
			System.out.println("parent == null");
		}
		else {
			tag.getParent().addSelectedChild(tag);
		}
		
	
		placeComponents();
	}
	
	
	public void UpdateView() {
		placeComponents();
	}
	
	
	
	public ArrayList<XmlTag> getTagArr() {
		return this.tagArr;
	}
	
	
	
	public void drawOpenCloseTagLine(XmlTag tag) {
		TagRow closeRow = getTagCloseRow(tag);
		if (closeRow != null) {
			Graphics g = this.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(CustomColor.LIGHT_GRAY);
			TagRow openRow = getTagOpenRow(tag);
			Line2D line = new Line2D.Double(openRow.getX() + openRow.getLeftBorder(),openRow.getY()+openRow.getHeight()  , closeRow.getX()+ closeRow.getLeftBorder(),closeRow.getY());
			g2.draw(line);
			OpenCloseTagsLinesHashMap.put(tag, line);
		}
	}
	
	
	
	public void removeLine(XmlTag tag) {
		if(OpenCloseTagsLinesHashMap.containsKey(tag)) {
			OpenCloseTagsLinesHashMap.remove(tag);
			repaint();
		}
	}
	
	
	
	public void setTagLabeBrighter(XmlTag tag) {
		/** recovering open row */
		TagRow openRow = getTagOpenRow(tag);
		
		if(getTagCloseRow(tag) != null) {
			TagRow closeRow = getTagCloseRow(tag) ;
			
			openRow.setTagLabelBrighter();
			closeRow.setTagLabelBrighter();
		}
		else openRow.setTagLabelBrighter();
	}
	
	
	
	public void unsetTagLabeBrighter(XmlTag tag) {
		/** recovering open row */
		TagRow openRow = getTagOpenRow(tag);
		
		if(getTagCloseRow(tag) != null) {
			TagRow closeRow = getTagCloseRow(tag) ;
			
			openRow.unsetTagLabelBrighter();
			closeRow.unsetTagLabelBrighter();
		}
		else openRow.unsetTagLabelBrighter();
	}

	
	
	
	
	private TagRow getTagOpenRow(XmlTag tag) {
		return getTagOpenRowHashMap().get(tag);
	}
	
	
	
	
	
	private TagRow getTagCloseRow(XmlTag tag) {
		if(tagCloseRowHashMap.containsKey(tag)) {
			return tagCloseRowHashMap.get(tag);

		}
		else return null;
	}
	
	public void repaintTagRow(XmlTag tag) {
		tagOpenRowHashMap.get(tag).repaint();
		tagOpenRowHashMap.get(tag).revalidate();
	}



	/**
	 * @return the tagOpenRowHashMap
	 */
	public HashMap<XmlTag, TagRow> getTagOpenRowHashMap() {
		return tagOpenRowHashMap;
	}



	/**
	 * @return the tagCloseRowHashMap
	 */
	public HashMap<XmlTag, TagRow> getTagCloseRowHashMap() {
		return tagCloseRowHashMap;
	}

	
	
}
