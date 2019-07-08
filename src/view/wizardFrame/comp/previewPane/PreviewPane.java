package view.wizardFrame.comp.previewPane;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

import model.PDSCDocument;
import view.wizardFrame.comp.previewPane.xmlEditor.TextLineNumber;
import view.wizardFrame.comp.previewPane.xmlEditor.XmlTextPane;

public class PreviewPane extends XmlTextPane{
	private PDSCDocument doc;
	
	private TextLineNumber textLineNumber;
	
	public PreviewPane() {
		placeComponents();
	}
	
	
	
	private void placeComponents() {
		
		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setBackground(Color.WHITE);
		this.setEditable(false);
		
		textLineNumber = new TextLineNumber(this);
		textLineNumber.setBackground(Color.WHITE);
		textLineNumber.setCurrentLineForeground(Color.GRAY);
		textLineNumber.setForeground(Color.GRAY);
	}
	
	
	
	
	/**
	 * set text inside text pane.
	 *
	 * @param preview String with new content of updateXmlPreviewPane
	 */
	
	public void setPreview(String preview) {
		this.setText(preview);
		this.revalidate();
	}




	/**
	 * @return the textLineNumber
	 */
	public TextLineNumber getTextLineNumber() {
		return textLineNumber;
	}



}
