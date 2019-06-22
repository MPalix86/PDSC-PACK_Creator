package view.wizardFrame.comp.xmlForm.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import business.CustomUtils;
import listeners.wizardFrameListeners.comp.xmlForm.XmlFormListener;
import model.XmlAttribute;
import model.XmlNameSpace;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.CustomColor;
import view.wizardFrame.comp.xmlForm.XmlForm;

public class TagRow extends JPanel{
	
	XmlFormListener listener;
	
	private XmlTag tag;
	private AttributeLabel attrLabel0;
	private TagLabel tagLabel0;
	private TagLabel tagLabel1 ;
	private TagLabel tagLabel2;
	private TagFormTextArea tagTextArea ;
	private TagFormTextField tagTextField ;
	private int leftBorder;
	private XmlForm form;
	private int option;
	private int rowNumber;
	public boolean hasFocus = false;
	ArrayList<AttributeLabel> attrLabelArr ;
	
	private final static int OPEN_ROW = 0;
	private final static int CLOSE_ROW = 1;
	
	

	public TagRow(XmlTag tag, XmlForm form) {
		this.form = form;
		this.tag = tag;
		attrLabelArr = new ArrayList<AttributeLabel>();
		listener = new XmlFormListener(form);
		this.setLayout(new MigLayout(
					"nogrid", // Layout Constraints
					"",       // Column constraints
				 	"[]0[]")); // row constraints
		this.setBackground(Color.WHITE);
	}
	
	
	
	public TagRow open() {
		attrLabelArr.clear();
		
		option = OPEN_ROW;
		
		tagLabel0 = new TagLabel("<  " + tag.getName(), this.tag);
		
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
				
				attrLabelArr.add(attrLabel0);
				
				attrLabel0.addMouseListener(listener);
				this.add(attrLabel0);
				
				/** if attribute have no possible values */
			
				if(attr.getPossibleValues() == null) {
					AttributeFormTextField textField ;
					
					/** if attribute have value set */
					if(attr.getValue() != null) textField = new AttributeFormTextField(attr, attr.getValue());
					else textField = new AttributeFormTextField(attr);
					
					textField.addFocusListener(listener);
					this.add(textField);
				}
				/** if attribute have possible values */
				else {
					AttributeFormComboBox valuesComboBox = new AttributeFormComboBox(attr);  
					
					if (attr.getDefaultValue() != null && attr.getValue() == null ) {
						valuesComboBox.setSelectedItem(attr.getDefaultValue());
						attr.setValue(attr.getDefaultValue());
					}
					
					/** if attribute have value set */
					else if (attr.getValue() != null) valuesComboBox.setSelectedItem(attr.getValue());
					
					valuesComboBox.addFocusListener(listener);
					this.add(valuesComboBox);
				}
				
				/** close attribute value */
				this.add(new AttributeLabel(" \" ", attr));
				
				/** add space */
				this.add(new JLabel("  "));
			}
			
		}
		
		/** close tag */
		tagLabel1 = new TagLabel("> ",tag);
		tagLabel1.addMouseListener(listener);
		if(tag.getContent() != null && CustomUtils.thereAreMoreLinesInString(tag.getContent())) this.add(tagLabel1, "wrap");
		else  this.add(tagLabel1);
		
		
		
		/** if tag have no children */
		if(tag.getSelectedChildrenArr() == null) {

			/** if tag haven't possible values */
			if(tag.getPossibleValues() == null) {

				/** if tag have content set */
				
				if(tag.getContent() != null ) {
					
					/** if content have more than one line */
					if(CustomUtils.thereAreMoreLinesInString(tag.getContent())){
						
						/** add textArea with content set */
						JPanel panel = new JPanel(new BorderLayout());
						
						tagTextArea = new TagFormTextArea(tag,tag.getContent());
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
						tagTextField = new TagFormTextField(tag,tag.getContent());
						tagTextField.addFocusListener(listener);
						this.add(tagTextField);
					}
				}
				
				/** if tag have't content set but have default content */
				else if (tag.getDefaultContent() != null) {
					
					/** add textfield with default content set  */
					tagTextField = new TagFormTextField(tag , tag.getDefaultContent());
					tagTextField.addFocusListener(listener);
					tag.setContent(tag.getDefaultContent());
					this.add(tagTextField);
				}
				
				else {
					/** add textfield without content set  */
					tagTextField = new TagFormTextField(tag);
					tagTextField.addFocusListener(listener);
					this.add(tagTextField);
				}
				
				

				
			}
			
			/** if tag have possible value  */
			else {
				TagFormComboBox contentComboBox = new TagFormComboBox(tag);

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

			tagLabel2 = new TagLabel("</  " + tag.getName() + "  > ",tag);
			tagLabel2.addMouseListener(listener);
			this.add(tagLabel2);
		}
		return this;
	}
	

	/** generate close tag row */
	public TagRow close() {
		option = CLOSE_ROW;
		tagLabel2 = new TagLabel("</  " + tag.getName() + "  >",tag);
		tagLabel2.addMouseListener(listener);
		this.add(tagLabel2);
		return this;
	}

	public void setTagLabelBrighter() {
		if (tagLabel0 != null) tagLabel0.setForeground(CustomColor.TAG_COLOR_BRIGHTER);
		if (tagLabel1 != null) tagLabel1.setForeground(CustomColor.TAG_COLOR_BRIGHTER);
		if (tagLabel2 != null) tagLabel2.setForeground(CustomColor.TAG_COLOR_BRIGHTER);
	}
	
	

	public void unsetTagLabelBrighter() {
		if (tagLabel0 != null) tagLabel0.setForeground(CustomColor.TAG_COLOR);
		if (tagLabel1 != null) tagLabel1.setForeground(CustomColor.TAG_COLOR);
		if (tagLabel2 != null) tagLabel2.setForeground(CustomColor.TAG_COLOR);
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
	 * update row
	 */
	public void update(){
		this.removeAll();
		if(option == OPEN_ROW) open();
		else close();
		repaint();
		revalidate();
	}
	
	
	public void highlightBckGround() {
		this.setBackground(new Color(178,215,255));
		for (Component comp : this.getComponents()) {
			comp.setBackground(new Color(178,215,255));
		}
	}
	
	
	
	public void unsetHighlightBackGround() {
		this.setBackground(CustomColor.WHITE);
		for (Component comp : this.getComponents()) {
			comp.setBackground(CustomColor.WHITE);
		}
	}
	
	
	
	public void hideComp() {
		for (Component comp : this.getComponents()) {
			comp.setVisible(false);
		}
		repaint();
		revalidate();
	}
	
	
	
	public void showComp() {
		for (Component comp : this.getComponents()) {
			comp.setVisible(true);
		}
		repaint();
		revalidate();
	}
	
	
	
	
	/**
	 * @return the tagLabel0
	 */
	public TagLabel getTagLabel0() {
		return tagLabel0;
	}
	
	
	
	
	/**
	 * @return the tagTextArea
	 */
	public TagFormTextArea getTagTextArea() {
		return tagTextArea;
	}




	/**
	 * @return the tagLabel1
	 */
	public TagLabel getTagLabel1() {
		return tagLabel1;
	}



	/**
	 * @return the tagLabel2
	 */
	public TagLabel getTagLabel2() {
		return tagLabel2;
	}
	
	
	/**
	 * @return the leftBorder
	 */
	public int getLeftBorder() {
		return leftBorder;
	}



	/**
	 * @param leftBorder the leftBorder to set
	 */
	public void setLeftBorder(int leftBorder) {
		this.leftBorder = leftBorder;
	}
	
	
	
	public void setRowNumber(int i) {
		this.rowNumber = i;
	}
	
	
	public int getRowNumber() {
		return this.rowNumber;
	}
	
	public boolean hasFocus() {
		if(hasFocus) return true;
		return false;
	}
	

}
