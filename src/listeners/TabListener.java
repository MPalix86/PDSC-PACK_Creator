package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.EditFileFrame;

public class TabListener implements ActionListener{
	private EditFileFrame mf;

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == "close") {
			System.out.println("close");
		}
		
	}

}
