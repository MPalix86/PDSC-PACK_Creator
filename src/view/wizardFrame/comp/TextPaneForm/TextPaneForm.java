package view.wizardFrame.comp.TextPaneForm;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.XmlAttribute;
import model.XmlTag;
import view.comp.AttributeTextField;

public class TextPaneForm extends JTextPane{
	
	private StyledDocument doc = this.getStyledDocument();
	private Style style;
	private ArrayList<XmlTag> tagArr;
	
	
	public TextPaneForm(XmlTag tag, ArrayList<XmlTag> tagArr) {
		style = doc.addStyle("style", null);
		this.tagArr = tagArr;
		
		tagArr.forEach((t)->{
			try {
				paintTag(t);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	
	
	private void paintTag(XmlTag tag) throws BadLocationException {
		
		XmlTag parent =  tag;
		
		setCaretPosition(getDocument().getLength());
		
		StyleConstants.setForeground(style, Color.blue);
        
        doc.insertString(doc.getLength(), "<" +  parent.getName() + " " , style);
        setCaretPosition(getDocument().getLength());
		
        if(parent.getSelectedAttrArr() != null) {
        	ArrayList<XmlAttribute> attrArr = parent.getSelectedAttrArr();
        	for(int i = 0; i < attrArr.size(); i++) {
        		XmlAttribute attr = attrArr.get(i);
        		
        		StyleConstants.setForeground(style, Color.red);
        		
 		        JButton AttrButton = new JButton(attr.getName());
        		this.insertComponent(AttrButton);
        		
        		doc.insertString(doc.getLength(), " \" ", style);
		        setCaretPosition(getDocument().getLength());
		        
		        AttributeTextField attrTextField = new AttributeTextField (attr);
		        attrTextField.setMinimumSize(new Dimension(40,20));
		        attrTextField.setForeground(Color.GRAY);
	            insertComponent(attrTextField);
	            doc.insertString(doc.getLength(), " \" ", style);
	            
        	}
        }
        
        StyleConstants.setForeground(style, Color.blue);
	    doc.insertString(doc.getLength(), "> ", style);
	    doc.insertString(doc.getLength(), "< /" + parent.getName() + "> \n", style);

	
		
		if( parent.getSelectedChildrenArr() != null) {
			ArrayList<XmlTag> xmlChildren = parent.getSelectedChildrenArr();

			/** iterating trough selected children */
			for(int i = 0; i < xmlChildren.size(); i++) {		
				XmlTag child = xmlChildren.get(i);	
				
				/** recursion */
				paintTag(child);
			}
		}
	}
	
	
}
