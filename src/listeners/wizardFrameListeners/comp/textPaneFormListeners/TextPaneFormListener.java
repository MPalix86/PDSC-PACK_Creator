package listeners.wizardFrameListeners.comp.textPaneFormListeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextPaneFormListener implements DocumentListener {

	@Override
	public void insertUpdate(DocumentEvent e) {
		
		System.out.println("ok");
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		System.out.println("ok");
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		//System.out.println("ok");
		
	}

}
