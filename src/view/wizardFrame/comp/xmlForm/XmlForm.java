package view.wizardFrame.comp.xmlForm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.XmlTagBusiness;
import listeners.wizardFrameListeners.comp.xmlForm.XmlFormListener;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.comp.TagRow;

public class XmlForm extends JPanel{

	private ArrayList<XmlTag> tagArr;

	private HashMap<XmlTag,TagRow> tagOpenRowHashMap;
	private HashMap<XmlTag,TagRow> tagCloseRowHashMap;
	private HashMap<XmlTag,Line2D> OpenCloseTagsLinesHashMap;
	private XmlFormListener listener;
	private XmlTag root;


	private final static int PADDING = 25;

	public XmlForm() {
		root = XmlTagBusiness.getRoot();
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("vendor", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("name", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("description", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("license", root));
		root.addSelectedChild(XmlTagBusiness.getCompleteTagFromNameAndParent("url", root));
		this.OpenCloseTagsLinesHashMap = new HashMap<XmlTag,Line2D>();
		this.tagOpenRowHashMap = new HashMap<XmlTag, TagRow>();
		this.tagCloseRowHashMap = new HashMap<XmlTag, TagRow>();
		this.tagArr = new ArrayList<XmlTag>();
		root.setSelectedAttrArr(root.getAttrArr());
		tagArr.add(root);

		listener = new XmlFormListener(this);

		placeComponents() ;

	}



	private void placeComponents() {
		this.removeAll();
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
		this.setLayout(new MigLayout("wrap 1" , "" , "[] 0 []"));

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
		openRow.setBorder(new EmptyBorder( -15, leftBorder, -15, 0));
		
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
			leftBorder = parentLevel * PADDING;

			TagRow closeRow = new TagRow(parent,this);

			tagCloseRowHashMap.put(parent,closeRow);

			/** setting calculated border */
			closeRow.setBorder(new EmptyBorder( -10, leftBorder, -10, 0));
			
			/** saving left border that represent indentation inside row */
			closeRow.setLeftBorder(leftBorder);

			this.add(closeRow.close());
		}
	}


	public void addTag(XmlTag tag) {

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



	public ArrayList<XmlTag> getTagArr() {
		return this.tagArr;
	}



	public void drawOpenCloseTagLine(XmlTag tag) {
		int empiricalCorrection = 15;
		TagRow closeRow = getTagCloseRow(tag);
		if (closeRow != null) {
			Graphics g = this.getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(CustomColor.LIGHT_GRAY);
			TagRow openRow = getTagOpenRow(tag);
			Line2D line = new Line2D.Double(
					openRow.getX() + openRow.getLeftBorder() + empiricalCorrection, 
					openRow.getY() + openRow.getHeight()/2  + 4 , 
					closeRow.getX() + closeRow.getLeftBorder() + empiricalCorrection ,
					closeRow.getY() + closeRow.getHeight() - empiricalCorrection);
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


	/**
	 * @return the root
	 */
	public XmlTag getRoot() {
		return root;
	}

}
