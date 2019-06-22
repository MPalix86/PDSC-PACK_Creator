package view.comp;

import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBar extends JScrollBar{
	
	public CustomScrollBar() {
		setUI(new BasicScrollBarUI());
	}
	

}
