package view.components;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class TagListPanelComponent extends JPanel{
	public TagListPanelComponent() {
		setBorder(null);
		setPreferredSize(new Dimension(130, 600));
		setMinimumSize(new Dimension(130, 10));
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setMinimumSize(new Dimension(10, 70));
		panel.setMaximumSize(new Dimension(32767, 70));
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JButton button_14 = new JButton("New button");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_14.setBounds(-11, -11, 151, 91);
		button_14.setMinimumSize(new Dimension(117, 70));
		button_14.setMaximumSize(new Dimension(117, 70));
		button_14.setForeground(Color.DARK_GRAY);
		button_14.setBackground(Color.DARK_GRAY);
		panel.add(button_14);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setMinimumSize(new Dimension(10, 70));
		panel_1.setMaximumSize(new Dimension(32767, 70));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		
		JButton button = new JButton("New button");
		button.setMinimumSize(new Dimension(117, 70));
		button.setMaximumSize(new Dimension(117, 70));
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(-11, -11, 151, 91);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setMinimumSize(new Dimension(10, 70));
		panel_2.setMaximumSize(new Dimension(32767, 70));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		add(panel_2);
		
		JButton button_1 = new JButton("New button");
		button_1.setMinimumSize(new Dimension(117, 70));
		button_1.setMaximumSize(new Dimension(117, 70));
		button_1.setForeground(Color.DARK_GRAY);
		button_1.setBackground(Color.DARK_GRAY);
		button_1.setBounds(-11, -11, 151, 91);
		panel_2.add(button_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setMinimumSize(new Dimension(10, 70));
		panel_3.setMaximumSize(new Dimension(32767, 70));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		add(panel_3);
		
		JButton button_2 = new JButton("New button");
		button_2.setMinimumSize(new Dimension(117, 70));
		button_2.setMaximumSize(new Dimension(117, 70));
		button_2.setForeground(Color.DARK_GRAY);
		button_2.setBackground(Color.DARK_GRAY);
		button_2.setBounds(-11, -11, 151, 91);
		panel_3.add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setMinimumSize(new Dimension(10, 70));
		panel_4.setMaximumSize(new Dimension(32767, 70));
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		add(panel_4);
		
		JButton button_3 = new JButton("New button");
		button_3.setMinimumSize(new Dimension(117, 70));
		button_3.setMaximumSize(new Dimension(117, 70));
		button_3.setForeground(Color.DARK_GRAY);
		button_3.setBackground(Color.DARK_GRAY);
		button_3.setBounds(-11, -11, 151, 91);
		panel_4.add(button_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setMinimumSize(new Dimension(10, 70));
		panel_5.setMaximumSize(new Dimension(32767, 70));
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		add(panel_5);
		
		JButton button_4 = new JButton("New button");
		button_4.setMinimumSize(new Dimension(117, 70));
		button_4.setMaximumSize(new Dimension(117, 70));
		button_4.setForeground(Color.DARK_GRAY);
		button_4.setBackground(Color.DARK_GRAY);
		button_4.setBounds(-11, -11, 151, 91);
		panel_5.add(button_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setMinimumSize(new Dimension(10, 70));
		panel_6.setMaximumSize(new Dimension(32767, 70));
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.WHITE);
		add(panel_6);
		
		JButton button_5 = new JButton("New button");
		button_5.setMinimumSize(new Dimension(117, 70));
		button_5.setMaximumSize(new Dimension(117, 70));
		button_5.setForeground(Color.DARK_GRAY);
		button_5.setBackground(Color.DARK_GRAY);
		button_5.setBounds(-11, -11, 151, 91);
		panel_6.add(button_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setMinimumSize(new Dimension(10, 70));
		panel_7.setMaximumSize(new Dimension(32767, 70));
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.WHITE);
		add(panel_7);
		
		JButton button_6 = new JButton("New button");
		button_6.setMinimumSize(new Dimension(117, 70));
		button_6.setMaximumSize(new Dimension(117, 70));
		button_6.setForeground(Color.DARK_GRAY);
		button_6.setBackground(Color.DARK_GRAY);
		button_6.setBounds(-11, -11, 151, 91);
		panel_7.add(button_6);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setMinimumSize(new Dimension(10, 70));
		panel_8.setMaximumSize(new Dimension(32767, 70));
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		add(panel_8);
		
		JButton button_7 = new JButton("New button");
		button_7.setMinimumSize(new Dimension(117, 70));
		button_7.setMaximumSize(new Dimension(117, 70));
		button_7.setForeground(Color.DARK_GRAY);
		button_7.setBackground(Color.DARK_GRAY);
		button_7.setBounds(-11, -11, 151, 91);
		panel_8.add(button_7);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setMinimumSize(new Dimension(10, 70));
		panel_9.setMaximumSize(new Dimension(32767, 70));
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBackground(Color.WHITE);
		add(panel_9);
		
		JButton button_8 = new JButton("New button");
		button_8.setMinimumSize(new Dimension(117, 70));
		button_8.setMaximumSize(new Dimension(117, 70));
		button_8.setForeground(Color.DARK_GRAY);
		button_8.setBackground(Color.DARK_GRAY);
		button_8.setBounds(-11, -11, 151, 91);
		panel_9.add(button_8);
	}
}
