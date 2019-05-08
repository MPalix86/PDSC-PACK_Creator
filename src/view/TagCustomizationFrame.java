package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import business.Session;
import listeners.TagCustomizationFrameListener;
import model.XmlTag;
import view.components.TagBtn;
import view.components.TagContainer;

public class TagCustomizationFrame extends JFrame {

	private JPanel contentPane;
	private TagContainer tagContainer;
	private Session session;
	private TagCustomizationFrameListener listener;
	private XmlTag parent;

	/**
	 * Create the frame.
	 */
	public TagCustomizationFrame(XmlTag parent) {
		this.parent = parent;
		listener = new TagCustomizationFrameListener(this);
		session = Session.getInstance();
		
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
						tagContainer = new TagContainer(parent, listener);
						
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
					
					TagBtn addBtn = new TagBtn (parent, "Add");
					addBtn.addActionListener(listener);
					addBtn.setActionCommand("add");
				contentPane.add(addBtn, BorderLayout.SOUTH);
				contentPane.add(leftPanel, BorderLayout.WEST);
			
			c.add(contentPane);
			c.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		this.setContentPane(c);//(contentPane);
		this.setVisible(true);
		this.setSize(360, 500);       
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        session.setTagCustomizationFrame(this);
	}

	
	
	public void addTagPanel(XmlTag tag) {
		tagContainer.addTagPanel(tag);
	}
	
	public void removeTagPanel(JPanel panel) {
		tagContainer.removeTagPanel(panel);
	}
	
	
	public XmlTag getTagParent() {
		return this.parent;
	}
	
	public void warningMessage(String message) {
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.OK_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 new ImageIcon ("icons/warning.png"), options, options[0]); 
	
	}
	
	public boolean yesNoWarningMessage(String message) {
		Object[] options = { "YES", "NO" };
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 new ImageIcon ("icons/warning.png"), options, options[0]); 
		if(value == 0) return true;
		else return false;
	}
	
}
