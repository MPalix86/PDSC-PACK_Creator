package listeners.wizardFrameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import org.jdom2.Document;

import business.FileBusiness;
import business.Session;
import business.WizardBusiness;
import model.XmlTag;

public class WizardFrameListener implements ActionListener {

	
	private Session session = Session.getInstance();
	
	public WizardFrameListener() {	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("updatePreview")) {
			ArrayList<XmlTag> tagArr = session.getWizardFrame().getFormContainer().getTagArr();
			Document doc = WizardBusiness.writePdsc(tagArr); 
			if(doc != null) {
				String preview = FileBusiness.getDocumentPreview(doc);
				session.getWizardFrame().getPreviewPaneContainer().getPreviewPane().setPreview(preview);
			}
		}
		
		else if(command.equals("prova")) {
			
			ArrayList<XmlTag> tagArr = session.getWizardFrame().getFormContainer().getTagArr();
			Document doc = WizardBusiness.writePdsc(tagArr); 
			if(doc != null) {
				String preview = FileBusiness.getDocumentPreview(doc);
				session.getWizardFrame().getPreviewPaneContainer().getPreviewPane().setPreview(preview);
			}
			
			String text = session.getWizardFrame().getPreviewPaneContainer().getPreviewPane().getText();
			JTextPane p  = session.getWizardFrame().getTextPane();
			try {
				WizardBusiness.readPdscString(text,p);
				p.repaint();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
	

}
