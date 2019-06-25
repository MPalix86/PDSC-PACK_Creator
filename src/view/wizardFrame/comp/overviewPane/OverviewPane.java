package view.wizardFrame.comp.overviewPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import listeners.wizardFrameListeners.comp.OverviewPaneListener;
import view.comp.SquareButton;

public class OverviewPane extends JPanel{
	
	private OverviewPaneListener listener = new OverviewPaneListener();
	
	public OverviewPane() {
		setLayout(new BorderLayout());
		SquareButton btn = new SquareButton("Create new PDSC");
		btn.addActionListener(listener);
		btn.setActionCommand("createNewPDSC");
		this.add(btn);
	}

}
