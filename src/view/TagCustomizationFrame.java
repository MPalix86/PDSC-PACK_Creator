package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Session;
import javafx.scene.control.ScrollBar;
import model.XmlTag;
import view.components.TagContainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class TagCustomizationFrame extends JFrame {

	private JPanel contentPane;
	private TagContainer tagContainer;
	private ArrayList<XmlTag> selectedTag; 

	/**
	 * Create the frame.
	 */
	public TagCustomizationFrame(XmlTag parent) {
		selectedTag = new ArrayList();
		selectedTag.add(parent);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 528);
			Container c = this.getContentPane();
			contentPane = new JPanel(new BorderLayout());
				contentPane.setBackground(Color.WHITE);
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
					
					JPanel leftPanel = new JPanel();
					leftPanel.setBackground(Color.WHITE);
					leftPanel.setLayout(new BorderLayout(0, 0));
					leftPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
					leftPanel.add(new JSeparator(SwingConstants.VERTICAL));
						tagContainer = new TagContainer(parent);
						
						JScrollPane scrollPane = new JScrollPane(tagContainer);
						JScrollBar scrollBar = new JScrollBar(){
				            @Override
				            public boolean isVisible() {
				                return true;
				            }
				        }; 
						scrollBar.setUnitIncrement(16);
						scrollPane.setVerticalScrollBar(scrollBar);
						scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
						scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
					leftPanel.add(scrollPane);
					
				contentPane.add(leftPanel, BorderLayout.WEST);
			
			c.add(contentPane);
			c.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		this.setContentPane(c);//(contentPane);
		this.setVisible(true);
		this.setSize(360, 500);       
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Session.setTagCustomizationFrame(this);
	}

	
	public void addTag(XmlTag tag) {
		selectedTag.add(tag);
	}
	
	public void addTagPanel(XmlTag tag) {
		tagContainer.addTagPanel(tag);
	}
	
	public void removeTagPanel(JPanel panel) {
		tagContainer.removeTagPanel(panel);
	}
	
	public void removeTag(XmlTag tag) {
		int index = selectedTag.indexOf(tag);
		selectedTag.remove(index);
	}
	
	public XmlTag getTagParent() {
		return this.selectedTag.get(0);
	}
	
	public ArrayList<XmlTag> getSelectedTag() {
		return selectedTag;
	}
	
	public void warningMessage(String message) {
		JOptionPane.showMessageDialog(this,message,"Alert",JOptionPane.INFORMATION_MESSAGE);
	
	}
}
