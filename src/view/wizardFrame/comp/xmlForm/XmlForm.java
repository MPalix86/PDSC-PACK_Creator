package view.wizardFrame.comp.xmlForm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.OSValidator;
import business.XmlTagBusiness;
import listeners.wizardFrameListeners.comp.xmlForm.XmlFormListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class XmlForm extends JPanel{


	private HashMap<XmlTag,TagRow> tagOpenRowHashMap;
	private HashMap<XmlTag,TagRow> tagCloseRowHashMap;
	private HashMap<XmlTag,Line2D> OpenCloseTagsLinesHashMap;
	private XmlFormListener listener;
	private XmlTag root;
	protected Integer rowCounter;
	private final static int LEFT_PADDING = 25;
	
	
	private static int INNER_ROW_PADDIND;

	public XmlForm() {
		
		if(OSValidator.isWindows()) INNER_ROW_PADDIND = -8;
		else INNER_ROW_PADDIND = -13;
		
		root = XmlTagBusiness.getRoot();
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("vendor", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("name", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("description", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("license", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("url", root));
		this.OpenCloseTagsLinesHashMap = new HashMap<XmlTag,Line2D>();
		this.tagOpenRowHashMap = new HashMap<XmlTag, TagRow>();
		this.tagCloseRowHashMap = new HashMap<XmlTag, TagRow>();
		root.setSelectedAttrArr(root.getAttrArr());

		listener = new XmlFormListener(this);
		
		placeComponents();
	}
	
	
	
	
	public XmlForm(XmlTag root) {
		
		if(OSValidator.isWindows()) INNER_ROW_PADDIND = -8;
		else INNER_ROW_PADDIND = -13;
		this.root = root;
		this.OpenCloseTagsLinesHashMap = new HashMap<XmlTag,Line2D>();
		this.tagOpenRowHashMap = new HashMap<XmlTag, TagRow>();
		this.tagCloseRowHashMap = new HashMap<XmlTag, TagRow>();
		listener = new XmlFormListener(this);
		
		placeComponents();

	}



	private void placeComponents() {
		this.removeAll();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
		this.setLayout(new MigLayout("wrap 1" , "" , "[] 0 []"));
		rowCounter = 0;
		tagOpenRowHashMap.clear();
		tagCloseRowHashMap.clear();
		if(this.root != null) paintTag(root,0);
		this.repaint();
		this.revalidate();
	}
	


	/**
	 * create tag open and close row for every tag and set indentation
	 * 
	 * @param tag
	 * @param level starting indentation level (usually 0)
	 */
	private void paintTag(XmlTag tag, int level) {
		

		XmlTag parent =  tag;

		/** calculating left border based on levelCounter */
		int leftBorder = level * LEFT_PADDING;

		TagRow openRow = new TagRow(parent,this);

		tagOpenRowHashMap.put(parent, openRow);
		
		rowCounter++;
		
		openRow.setRowNumber(rowCounter);

		/** setting calculated border */
		openRow.setBorder(new EmptyBorder( INNER_ROW_PADDIND, leftBorder, INNER_ROW_PADDIND, 0));
		
		/** saving left border that represent indentation inside row */
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
			leftBorder = parentLevel * LEFT_PADDING;

			TagRow closeRow = new TagRow(parent,this);

			tagCloseRowHashMap.put(parent,closeRow);
			
			rowCounter++;
			
			closeRow.setRowNumber(rowCounter);

			/** setting calculated border */
			closeRow.setBorder(new EmptyBorder( INNER_ROW_PADDIND, leftBorder, INNER_ROW_PADDIND, 0));
			
			/** saving left border that represent indentation inside row */
			closeRow.setLeftBorder(leftBorder);
			
			this.add(closeRow.close());
		}
	}

	
	
	/**
	 * adding tag inside XmlForm
	 * @param tag
	 */
	public void addRootChild(XmlTag tag) {
		if (tag.getParent() == null) {
			this.root.addSelectedChild(tag);
			tag.setParent(root);
			tag.setRequired(false);
		}
		else {
			tag.getParent().addSelectedChild(tag);
		}
		placeComponents();
	}
	
	
	
	public void UpdateView() {
		placeComponents();
	}




	/**
	 * used For generating line that matches open and close row of tag 
	 * 
	 * @param tag 
	 */
	public void drawOpenCloseTagLine(XmlTag tag) {
		TagRow closeRow = getTagCloseRow(tag);
		if (closeRow != null) {
			Graphics g = this.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(CustomColor.LIGHT_GRAY);
			TagRow openRow = getTagOpenRow(tag);
			Line2D line = new Line2D.Double(
					openRow.getX() + openRow.getLeftBorder() + 15, 
					openRow.getY()  + openRow.getHeight(), 
					closeRow.getX() + closeRow.getLeftBorder() + 15,
					closeRow.getY()
			);
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





	public TagRow getTagOpenRow(XmlTag tag) {
		return this.tagOpenRowHashMap.get(tag);
	}





	public TagRow getTagCloseRow(XmlTag tag) {
		if(tagCloseRowHashMap.containsKey(tag)) {
			return tagCloseRowHashMap.get(tag);

		}
		else return null;
	}


	/**
	 * @return the root
	 */
	public XmlTag getRoot() {
		return root;
	}
	
	public int getRowCounter() {
		return this.rowCounter;
	}
	
	

}
