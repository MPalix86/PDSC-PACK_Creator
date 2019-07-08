package view.wizardFrame.comp.overviewPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import listeners.IndependentFrameListener.FileOptionListener;
import net.miginfocom.swing.MigLayout;
import view.comp.IconUtils;
import view.comp.TextButton;

public class OverviewPane extends JPanel{
	
	private FileOptionListener listener;
	
	public OverviewPane() {
		this.setLayout(new BorderLayout());
		
		listener = new FileOptionListener();
		
		JPanel contentPane = new JPanel(new BorderLayout());
		
		JPanel centerPanel = new JPanel(new MigLayout());
		
		JPanel northPanel = new JPanel();
		
		TextButton openFileButton = new TextButton ("Open PDSC file from file system ...");
		openFileButton.addActionListener(listener);
		openFileButton.setActionCommand("openPDSCFile");
		openFileButton.setIcon(IconUtils.FAgetFolderOpenIcon(20, null));
		TextButton newFileButton = new TextButton ("Create new PDSC file");
		newFileButton.setIcon(IconUtils.FAgetPlusIcon(20, null));
		newFileButton.addActionListener(listener);
		newFileButton.setActionCommand("createNewPDSC");
		
		northPanel.add(new JLabel("logo here"));
		northPanel.add(new JLabel("social"));
		northPanel.add(new JLabel(" social"));
		northPanel.add(new JLabel(" social"));
		northPanel.add(new JLabel(" social"));
		northPanel.setBorder(new MatteBorder(0,0,1,0,Color.DARK_GRAY));
		
        centerPanel.add(openFileButton , "wrap");
        centerPanel.add(newFileButton, "wrap");
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(northPanel, BorderLayout.NORTH);
        

		this.add(contentPane, BorderLayout.CENTER);
	}

}
