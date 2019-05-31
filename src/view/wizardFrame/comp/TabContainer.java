package view.wizardFrame.comp;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class TabContainer extends JTabbedPane{
	
	public void TabContainer() {
		this.setBackground(Color.white);
		this.setBorder(new EmptyBorder(0,0,0,0));
	}

}
