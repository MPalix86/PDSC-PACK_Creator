package listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AttributesListener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.err.println(e.getStateChange());
		
	}

}
