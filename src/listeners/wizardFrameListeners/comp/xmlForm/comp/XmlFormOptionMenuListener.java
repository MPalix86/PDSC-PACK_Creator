package listeners.wizardFrameListeners.comp.xmlForm.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XmlFormOptionMenuListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command.equals("test")) {
			//STUFF
		}
		
	}
}
