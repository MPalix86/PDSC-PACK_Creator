package view.wizardFrame.comp.overviewPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import listeners.ContactListener;
import listeners.FileOptionListener;
import net.miginfocom.swing.MigLayout;
import view.comp.TextButton;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconUtils;

public class OverviewPane extends JPanel{
	
	private FileOptionListener filelistener;
	
	private ContactListener contactListener;
	
	public OverviewPane() {
		this.setLayout(new BorderLayout());
		
		filelistener = new FileOptionListener();
		contactListener = new ContactListener();
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new MigLayout());
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		
		TextButton openFileButton = new TextButton ("Open PDSC file from file system ...");
		openFileButton.addActionListener(filelistener);
		openFileButton.setActionCommand("openPDSCFile");
		openFileButton.setIcon(IconUtils.FAgetFolderOpenIcon(20, ColorUtils.FOLDER_BROWN));
		
		TextButton newFileButton = new TextButton ("Create new PDSC file");
		newFileButton.setIcon(IconUtils.getNewDocumentIcon(20));
		newFileButton.addActionListener(filelistener);
		newFileButton.setActionCommand("createNewPDSC");
		
		
		
		TextButton stLabel = new TextButton(IconUtils.getStLogoIcon(60));
		stLabel.addActionListener(contactListener);
		stLabel.setActionCommand("stSite");
		stLabel.setToolTipText("Go to ST web site");
		
		TextButton facebook = new TextButton(IconUtils.getFacebookIcon(30));
		facebook.addActionListener(contactListener);
		facebook.setActionCommand("facebookSite");
		facebook.setToolTipText("Go to ST Facebook Profile");
		
		TextButton twitter = new TextButton(IconUtils.getTwitterIcon(35));
		twitter.addActionListener(contactListener);
		twitter.setActionCommand("twitterSite");
		twitter.setToolTipText("Go to ST Twitter Profile");
		
		TextButton youtube = new TextButton(IconUtils.getYoututbeIcon(45));
		youtube.addActionListener(contactListener);
		youtube.setActionCommand("youtubeSite");
		youtube.setToolTipText("Go to ST Youtube Channel");
		
		
		
		northPanel.add(stLabel);
		northPanel.add(facebook);
		northPanel.add(twitter);
		northPanel.add(youtube);
		
		
		northPanel.setBorder(new MatteBorder(0,0,1,0,Color.DARK_GRAY));
		northPanel.setBackground(new Color(200,221,242));
		
        centerPanel.add(openFileButton , "wrap");
        centerPanel.add(newFileButton, "wrap");
        
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(northPanel, BorderLayout.NORTH);
        

		this.add(contentPane, BorderLayout.CENTER);
	}

}
