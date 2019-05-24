package view.wizardFrame.comp.toolBar;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ToolBarContainer extends JPanel {
	
	private ToolBar toolBar;


	public ToolBarContainer() {
		
		this.toolBar = new ToolBar();
		
		placeComponents();
	}
	
	
	
	private void placeComponents() {
		
		this.setLayout(new BorderLayout());
		
		this.add(toolBar, BorderLayout.CENTER);
	}
	
	
	
	/**
	 * @return the toolBar
	 */
	public ToolBar getToolBar() {
		return toolBar;
	}

	
}
