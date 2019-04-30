package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.XmlTag;

import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;

public class TagCustomizationFrame extends JFrame {

	private JPanel contentPane;


	
	/**
	 * Create the frame.
	 */
	public TagCustomizationFrame(XmlTag tag) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		contentPane.add(leftPanel, BorderLayout.CENTER);
		
	}

}
