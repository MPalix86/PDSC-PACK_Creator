package view.wizardFrame.comp.previewPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import listeners.wizardFrameListeners.comp.previewPaneListeners.PreviewPaneContainerListener;

public class PreviewPaneContainer extends JFrame{
	
	private PreviewPane previewPane;
	
	private PreviewPaneContainerListener listener;
	
	
	public PreviewPaneContainer() {
		
		this.listener = new PreviewPaneContainerListener(this);
		this.previewPane = new PreviewPane();
		placeComponents();
	}
	
	
	
	
	private void placeComponents() {
		JPanel contentPane = new JPanel(new BorderLayout());
		this.setLayout(new BorderLayout());
		this.add(contentPane , BorderLayout.CENTER);
		
		JScrollPane ScrollPane = new JScrollPane(previewPane);
		ScrollPane.setBackground(Color.WHITE);
		ScrollPane.setBorder(new EmptyBorder(10,0,0,0));
		ScrollPane.setRowHeaderView(previewPane.getTextLineNumber());
		contentPane.add(ScrollPane, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setMinimumSize(new Dimension(500,600));
		this.setPreferredSize(new Dimension(600,800));
	}


	/**
	 * @return the previewPane
	 */
	public PreviewPane getPreviewPane() {
		return previewPane;
	}

}
