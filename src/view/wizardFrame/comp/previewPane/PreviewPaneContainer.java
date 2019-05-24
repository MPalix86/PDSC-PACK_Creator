package view.wizardFrame.comp.previewPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import listeners.wizardFrameListener.comp.previewPaneListener.PreviewPaneContainerListener;

public class PreviewPaneContainer extends JPanel{
	
	private PreviewPane previewPane;
	
	private PreviewPaneContainerListener listener;
	
	
	public PreviewPaneContainer() {
		
		this.listener = new PreviewPaneContainerListener(this);
		this.previewPane = new PreviewPane();
		placeComponents();
	}
	
	
	
	
	private void placeComponents() {
		
		this.setLayout(new BorderLayout());
		
		JScrollPane ScrollPane = new JScrollPane(previewPane);
		ScrollPane.setBackground(Color.WHITE);
		ScrollPane.setBorder(new EmptyBorder(10,0,0,0));
		ScrollPane.setRowHeaderView(previewPane.getTextLineNumber());
		this.add(ScrollPane, BorderLayout.CENTER);
	}


	/**
	 * @return the previewPane
	 */
	public PreviewPane getPreviewPane() {
		return previewPane;
	}

}
