package listeners;

import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import business.Session;

public class ClipboardListener implements FlavorListener , ActionListener{
	
	private Session session;
	
	public ClipboardListener() {
		this.session = Session.getInstance();
	}
	

	@Override
	public void flavorsChanged(FlavorEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("copy")) {
			Object o = session.getSelectedPDSCDoc().getClipboard().getContents(null);
		
		}
		
	}

}
