package view.wizardFrame.comp.xmlForm.comp;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;

import business.utils.CustomUtils;
import listeners.DnDListenerTagLabel;
import listeners.DnDListenerTagRow;
import listeners.wizardFrameListeners.comp.xmlForm.comp.TagRowComponentListener;
import model.xml.XmlAttribute;
import model.xml.XmlNameSpace;
import model.xml.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconsUtils;
import view.wizardFrame.comp.xmlForm.XmlForm;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeFormTextField;
import view.wizardFrame.comp.xmlForm.comp.attributeComp.AttributeLabel;
import view.wizardFrame.comp.xmlForm.comp.namespaceComp.NameSpaceFormTextField;
import view.wizardFrame.comp.xmlForm.comp.namespaceComp.NameSpaceLabel;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagFormComboBox;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagFormTextArea;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagFormTextField;
import view.wizardFrame.comp.xmlForm.comp.tagComp.TagLabel;

public class TagRow extends JPanel{
	
	private TagRowComponentListener listener;
	private XmlTag tag;
	private AttributeLabel attrLabel0;
	private TagLabel tagLabel0;
	private TagLabel tagLabel1 ;
	private TagLabel tagLabel2;
	private TagFormTextArea tagTextArea ;
	private TagFormTextField tagTextField ;
	private XmlForm form;
	private boolean backGrounIsHighlighted = false;
	private int option;
	private int rowNumber;
	
	public final static int OPEN_ROW = 0;
	public final static int CLOSE_ROW = 1;
	
	

	public TagRow(XmlTag tag, XmlForm form, int option) {
		this.option = option;
		this.form = form;
		this.tag = tag;
		listener = new TagRowComponentListener(form);
		this.setLayout(new MigLayout(
					"nogrid", // Layout Constraints
					"",       // Column constraints
				 	"[]0[]")); // row constraints
		this.setBackground(Color.WHITE);
		DropTarget dt = new DropTarget(this, DnDConstants.ACTION_COPY, new DnDListenerTagRow(),
				true, null);
		
		if(option == OPEN_ROW) open();
		else close();
	}
	
	
	
	private TagRow open() {
		
		tagLabel0 = new TagLabel("<  " + tag.getName(), this.tag);
		tagLabel0.setTransferHandler(new TransferHandler("ciao"));
		
		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(tagLabel0,
				DnDConstants.ACTION_MOVE, new DnDListenerTagLabel());
		
		
		if(tag.getFile() != null && tag.getValueType().equals("File")) {
			tagLabel0.setToolTipText("Source file associated: " + tag.getFile().getAbsolutePath());
			tagLabel0.setIcon(IconsUtils.FAgetFileIcon(14, ColorUtils.SYSTEM_GREEN_COLOR_DARK));
		}		
		else if(tag.getFile() == null && tag.getValueType().equals("File")) tagLabel0.setIcon(IconsUtils.FAgetFileIcon(14, ColorUtils.SYSTEM_GRAY_COLOR_DARK));
//		else if(tag.getValueType().equals("FilesContainer")) {
//			tagLabel0.setToolTipText("Select multiple files ");
//			tagLabel0.setIcon(IconsUtils.getMultipleFilesIcon(14));
//		}
		tagLabel0.addMouseListener(listener);
		
		this.add(tagLabel0);
		
		/** if tag have nameSpace */
		if(tag.getNameSpace() != null) {
			XmlNameSpace n = tag.getNameSpace();
			NameSpaceLabel nameSPaceLabel = new NameSpaceLabel(n, " xmlns:" + n.getPrefix()+ " =  \" "); 
			NameSpaceLabel nameSPaceLabel1 = new NameSpaceLabel(n, " \" " ); 
			NameSpaceFormTextField nameSpaceTextField;
			if(n.getUrl() != null)  nameSpaceTextField = new NameSpaceFormTextField(n, n.getUrl(), this);
			else nameSpaceTextField = new NameSpaceFormTextField(n, this);
			this.add(nameSPaceLabel);
			this.add(nameSpaceTextField);
			this.add(nameSPaceLabel1);
		}
		
		/** if tag have selected attributes */
		if(tag.getSelectedAttrArr() != null) {
			for(int i = 0; i < tag.getSelectedAttrArr().size(); i++) {
				XmlAttribute attr = tag.getSelectedAttrArr().get(i);  
			
				
//				/** if attribute have nameSpace */
//				if (attr.getNameSpace() != null) attrLabel0 = new AttributeLabel(attr.getNameSpace().getPrefix()+":"+ attr.getName() + " = \" ", attr);
//				else 
				
				attrLabel0 = new AttributeLabel(" " + attr.getName() + " = \" ", attr);
				
				if(attr.getPossibleValuesType().equals("File") && attr.getFile() != null) {
					attrLabel0.setToolTipText("Source file associated :" + attr.getFile().getAbsolutePath());
					attrLabel0.setIcon(IconsUtils.FAgetFileIcon(14, ColorUtils.SYSTEM_GREEN_COLOR_DARK));
				}
				else if(attr.getPossibleValuesType().contentEquals("File") && attr.getFile() == null) attrLabel0.setIcon(IconsUtils.FAgetFileIcon(14, ColorUtils.SYSTEM_GRAY_COLOR_DARK));
				
				attrLabel0.addMouseListener(listener);
				this.add(attrLabel0);
				
				/** if attribute have no possible values */
			
				if(attr.getPossibleValues() == null) {
					AttributeFormTextField textField ;
					
					/** if attribute have value set */
					if(attr.getValue() != null) textField = new AttributeFormTextField(attr, attr.getValue(),this);
					else textField = new AttributeFormTextField(attr,this);
					
					textField.addFocusListener(listener);
					this.add(textField);
				}
				
				/** if attribute have possible values */
				else {
					AttributeFormComboBox valuesComboBox = new AttributeFormComboBox(attr,this);	
					
					/** if attribute have possible values and attributes have no value set */
					if (attr.getDefaultValue() != null && attr.getValue() == null ) {
						valuesComboBox.setSelectedItem(attr.getDefaultValue());
						attr.setValue(attr.getDefaultValue());
					}
					
					/** if attribute have value set */
					if (attr.getValue() != null) {
			
						/** if combobox contains attrValue in list */
						if(valuesComboBox.containsItem(attr.getValue())) {
							valuesComboBox.setSelectedItem(attr.getValue());
						}
						else {
							valuesComboBox.addItem(attr.getValue());
							valuesComboBox.setSelectedItem(attr.getValue());
						}
					}
					
					
					valuesComboBox.addFocusListener(listener);
					valuesComboBox.addActionListener(listener);
					this.add(valuesComboBox);
				}
				
				/** close attribute value */
				this.add(new AttributeLabel(" \" ", attr));
				
				/** add space */
				this.add(new JLabel("  "));
			}
			
		}
		
		/** close tag */
		if(tag.getValueType().equals("Void") && tag.getSelectedChildrenArr() == null) tagLabel1 = new TagLabel("/> ",tag);
		else tagLabel1 = new TagLabel("> ",tag);
		tagLabel1.addMouseListener(listener);
		if(tag.getContent() != null && CustomUtils.thereAreMoreLinesInString(tag.getContent())) this.add(tagLabel1, "wrap");
		else  this.add(tagLabel1);
		
		/** if tag have no children */
		if(tag.getSelectedChildrenArr() == null || tag.getSelectedChildrenArr().size() <= 0) {

			/** if tag haven't possible values */
			if(tag.getPossibleValues() == null && !tag.getValueType().equals("Void")) {

				/** if tag have content set */
				
				if(tag.getContent() != null ) {
					
					/** if content have more than one line */
					if(CustomUtils.thereAreMoreLinesInString(tag.getContent())){
						
						/** add textArea with content set */
						JPanel panel = new JPanel(new BorderLayout());
						
						tagTextArea = new TagFormTextArea(tag,tag.getContent(),this);
						tagTextArea.addFocusListener(listener);
						
						/** set border to get indentation */
						panel.setBorder(new EmptyBorder(0,40,0,0));
						panel.setBackground(Color.WHITE);
						panel.add(tagTextArea, BorderLayout.CENTER);
						
						this.add(panel ,"wrap");
					}
					
					/** if content haven't more than one line */
					else {
						/** add texfield with content set */
						tagTextField = new TagFormTextField(tag,tag.getContent(),this);
						tagTextField.addFocusListener(listener);
						this.add(tagTextField);
					}
				}
				
				/** if tag have't content set but have default content */
				else if (tag.getDefaultContent() != null) {
					
					/** add textfield with default content set  */
					tagTextField = new TagFormTextField(tag , tag.getDefaultContent(),this);
					tagTextField.addFocusListener(listener);
					tag.setContent(tag.getDefaultContent());
					this.add(tagTextField);
				}
				
				else {
					/** add textfield without content set  */
					tagTextField = new TagFormTextField(tag,this);
					tagTextField.addFocusListener(listener);
					this.add(tagTextField);
				}
				
				

				
			}
			
			/** if tag have possible value  */
			else if(tag.getPossibleValues() != null && !tag.getValueType().equals("Void")){
				TagFormComboBox contentComboBox = new TagFormComboBox(tag,this);

				if (tag.getDefaultContent() != null && tag.getContent() == null ) {
					contentComboBox.setSelectedItem(tag.getDefaultContent());
					tag.setContent(tag.getDefaultContent());
				}

				/** if tag have value set */
				else if (tag.getContent() != null) contentComboBox.setSelectedItem(tag.getContent());

				contentComboBox.setForeground(Color.DARK_GRAY);
				contentComboBox.addFocusListener(listener);

				this.add(contentComboBox);
			}
			
			if(!tag.getValueType().equals("Void")) {
				tagLabel2 = new TagLabel("</  " + tag.getName() + "  > ",tag);
				DragSource ds1 = new DragSource();
				ds1.createDefaultDragGestureRecognizer(tagLabel2,
						DnDConstants.ACTION_MOVE, new DnDListenerTagLabel());
				tagLabel2.addMouseListener(listener);
				this.add(tagLabel2);
			}
			
		}
		return this;
	}
	
	
	
	
	
	/** 
	 * generate close tag row 
	 */
	private TagRow close() {
		tagLabel2 = new TagLabel("</  " + tag.getName() + "  >",tag);
		tagLabel2.addMouseListener(listener);
		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(tagLabel2,
				DnDConstants.ACTION_MOVE, new DnDListenerTagLabel());
		this.add(tagLabel2);
		return this;
	}
	
	
	
	
	/**
	 * set tag labels Brighter
	 */
	public void setTagLabelBrighter() {
		if (tagLabel0 != null) tagLabel0.setForeground(ColorUtils.TAG_COLOR_BRIGHTER);
		if (tagLabel1 != null) tagLabel1.setForeground(ColorUtils.TAG_COLOR_BRIGHTER);
		if (tagLabel2 != null) tagLabel2.setForeground(ColorUtils.TAG_COLOR_BRIGHTER);
	}
	
	
	
	
	/**
	 * Unset tagLabel brighter
	 */
	public void unsetTagLabelBrighter() {
		if (tagLabel0 != null) tagLabel0.setForeground(ColorUtils.TAG_COLOR);
		if (tagLabel1 != null) tagLabel1.setForeground(ColorUtils.TAG_COLOR);
		if (tagLabel2 != null) tagLabel2.setForeground(ColorUtils.TAG_COLOR);
	}
	
	
	
	
	
	/** 
	 * used when switching from TagTextField and tagTextArea and vice versa
	 * to avoid the focus lost
	 */
	public void setFocusOnTagContentField() {
		if(tagTextArea != null) tagTextArea.requestFocusInWindow();
		if(tagTextField != null) tagTextField.requestFocusInWindow();
	}
	
	
	
	
	
	/** 
	 * used when switching from TagTextField and tagTextArea and vice versa
	 * to avoid the change on caret position
	 */
	public void setTagContentFieldCaretPosition(int caretPosition) {
		if(tagTextArea != null) tagTextArea.setCaretPosition(caretPosition);
		if(tagTextField != null) {
			SwingUtilities.invokeLater(new Runnable() {
		        @Override
		        public void run() {
		        	
		        	try {
		        		tagTextField.setCaretPosition(caretPosition);
		        		} catch (Exception ignore) { }
		        }
		    });
		}
	}
	
	
	
	
	
	/**
	 * Remove all elements and repaint the interface.
	 */
	public void update(){
		this.removeAll();
		if(option == OPEN_ROW) open();
		else close();
		repaint();
		revalidate();
	}
	
	
	
	
	/**
	 * Highlight background of this row
	 * 
	 * @param bg = color background, set to null for default color
	 */
	public void highlightBckGround(Color bg) {
		if (bg == null) bg = new Color(200,221,242);
		this.setBackground(bg);
		for (Component comp : this.getComponents()) {
			comp.setBackground(bg);
		}
		/** tagtextArea is not recognized like Component*/
		if(tagTextArea != null) tagTextArea.setBackground(bg);
		
		this.backGrounIsHighlighted = true;
	}
	
	
	
	
	/**
	 * remove Bacground color for tag row and its components
	 */
	public void unsetHighlightBackGround() {
		this.setBackground(ColorUtils.WHITE);
		for (Component comp : this.getComponents()) {
			comp.setBackground(ColorUtils.WHITE);
		}
		/** tagtextArea is not recognized like Component*/
		if(tagTextArea != null) tagTextArea.setBackground(Color.WHITE);
		
		this.backGrounIsHighlighted = false;
	}
	
	
	
	
	/**
	 * hide all components in row
	 */
	public void hideComp() {
		for (Component comp : this.getComponents()) {
			comp.setVisible(false);
		}
		repaint();
		revalidate();
	}
	
	
	
	
	public void adjustTagLabel() {
		if(tag.getSelectedChildrenArr() != null) {
			if(this.tagLabel2 != null) {
			this.tagLabel2.setVisible(false);
			}
				
			if(this.tagTextField != null) {
				this.tagTextField.setVisible(false);
			}
			if(this.tagTextArea != null) {
				this.tagTextArea.setVisible(false);
			}
		}
		else {
			if(this.tagLabel2 != null) {
			this.tagLabel2.setVisible(true);
			}
				
			if(this.tagTextField != null) {
				this.tagTextField.setVisible(true);
			}
			if(this.tagTextArea != null) {
				this.tagTextArea.setVisible(true);
			}
		}

	}
	
	
	
	/**
	 * show all components in row
	 */
	public void showComp() {
		for (Component comp : this.getComponents()) {
			comp.setVisible(true);
		}
		repaint();
		revalidate();
	}
	
	
	
	
	/** 
	 * Change foreground color for all components in row
	 * 
	 * @param c color to set
	 */
	public void setForeground(Color c) {
		for (Component comp : this.getComponents()) {
			comp.setForeground(c);
		}
		repaint();
		revalidate();
	}
	
	
	/**
	 * reset default foreground color for all components
	 */
	public void setDefaultForeground() {
		for (Component comp : this.getComponents()) {
			if(comp.getClass().equals(AttributeFormTextField.class)) 		comp.setForeground(ColorUtils.ATTR_VALUE_COLOR);
			else if(comp.getClass().equals(AttributeFormComboBox.class)) 	comp.setForeground(ColorUtils.ATTR_VALUE_COLOR);
			else if(comp.getClass().equals(TagFormTextField.class)) 		comp.setForeground(Color.DARK_GRAY);
			else if(comp.getClass().equals(TagFormTextArea.class)) 			comp.setForeground(Color.DARK_GRAY);
			else if(comp.getClass().equals(TagFormComboBox.class)) 			comp.setForeground(Color.DARK_GRAY);
			else if(comp.getClass().equals(AttributeLabel.class)) 			comp.setForeground(ColorUtils.ATTR_COLOR);
			else if(comp.getClass().equals(TagLabel.class))					comp.setForeground(ColorUtils.TAG_COLOR);
			else if(comp.getClass().equals(NameSpaceLabel.class))			comp.setForeground(ColorUtils.ATTR_COLOR);
			else if(comp.getClass().equals(NameSpaceFormTextField.class))	comp.setForeground(ColorUtils.ATTR_VALUE_COLOR);
		}
		repaint();
		revalidate();
	}
	
	
	
	
	
	/**
	 * request focus on row
	 */
	public void requestFocus() {
		boolean focusSetted = false;
		for (Component comp : this.getComponents()) {
			if(comp.getClass().equals(AttributeFormTextField.class)) 		{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(AttributeFormComboBox.class)) 	{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(TagFormTextField.class)) 		{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(TagFormComboBox.class)) 			{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(AttributeLabel.class)) 			{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(TagLabel.class))					{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(NameSpaceLabel.class))			{comp.requestFocusInWindow(); focusSetted = true; break; }
			else if(comp.getClass().equals(NameSpaceFormTextField.class))	{comp.requestFocusInWindow(); focusSetted = true; break; }
		}
		if(!focusSetted) {
			if(tagTextArea != null) tagTextArea.requestFocusInWindow();
		}
		repaint();
		revalidate();
	}
	
	
	
	
	
	
	
	/**
	 * Draw Horizontal line 
	 */
	public void drawHorizontalLine(Color c , int x1, int y1, int x2, int y2, int stroke) {
		if(c == null) c = ColorUtils.SYSTEM_GREEN_COLOR_DARK;
		  Graphics g = this.getGraphics();
		  Graphics2D g2 = (Graphics2D) g;
		  g2.setColor(c);
		  g2.setStroke(new BasicStroke(stroke));
		  //draw a line (starting x,y; ending x,y)
		  g2.drawLine(x1, y1, x2, y2);
	}

	
	/**
	 * @return the leftBorder
	 */
	public TagFormTextArea getTagTextArea() {
		return this.tagTextArea;
	}
	
	public XmlTag getTag() {
		return this.tag;
	}
	
	
	public TagLabel getTagLabel0() {
		return this.tagLabel0;
	}
	
	
	public boolean isBackGroundHiglighted() {
		return this.backGrounIsHighlighted;
	}
	
	
	
	
	public void setRowNumber(int i) {
		this.rowNumber = i;
	}
	
	
	
	public int getRowNumber() {
		return this.rowNumber;
	}
	
	public boolean isOpen() {
		if(option == this.OPEN_ROW) return true;
		return false;
	}
	
	

}