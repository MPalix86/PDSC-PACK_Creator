package view.wizardFrame.comp.xmlForm.comp;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import listeners.wizardFrameListeners.comp.xmlForm.comp.XmlFormOptionMenuListener;

public class XmlFormOptionMenu extends JPopupMenu {

	
	public XmlFormOptionMenu() {
		XmlFormOptionMenuListener listener = new XmlFormOptionMenuListener();
		
		JMenuItem testItem = new JMenuItem("TEST BUTTON");
		testItem.addActionListener(listener);
		testItem.setActionCommand("test");
		
		
		
		
		this.add(testItem);
		this.add(new JSeparator());
		
	}
	
	

}
