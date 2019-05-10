package view.Components.wizardFrameComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import listeners.TagListPanelListener;
import view.Components.StylizedComponents.WizardMoveButton;

public class TagListBar extends JPanel{
	public TagListBar() {
		
		
		setBorder(null);
		setPreferredSize(new Dimension(130, 878));
		setMinimumSize(new Dimension(130, 10));
		setBackground(Color.GRAY);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setMinimumSize(new Dimension(10, 70));
		panel.setMaximumSize(new Dimension(32767, 70));
		panel.setBackground(Color.GRAY);
		add(panel);
		panel.setLayout(null);
		
		WizardMoveButton dominate = new WizardMoveButton("<dominate>");
		dominate.setForeground(Color.WHITE);
		dominate.addActionListener(new TagListPanelListener());
		dominate.setBackground(Color.GRAY);
		dominate.setActionCommand("dominate");
		dominate.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		dominate.setBounds(-11, -11, 151, 91);
		dominate.setMinimumSize(new Dimension(117, 70));
		dominate.setMaximumSize(new Dimension(117, 70));
		//dominate.setForeground(new Color(0, 0, 128));
		panel.add(dominate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setMinimumSize(new Dimension(10, 70));
		panel_1.setMaximumSize(new Dimension(32767, 70));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		
		WizardMoveButton requirements = new WizardMoveButton("<requirements>");
		requirements.addActionListener(new TagListPanelListener());
		requirements.setBackground(Color.GRAY);
		requirements.setForeground(Color.WHITE);
		requirements.setActionCommand("requirements");
		requirements.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		requirements.setMinimumSize(new Dimension(117, 70));
		requirements.setMaximumSize(new Dimension(117, 70));
		//requirements.setForeground(new Color(0, 0, 128));
		requirements.setBounds(-11, -11, 151, 91);
		panel_1.add(requirements);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setMinimumSize(new Dimension(10, 70));
		panel_2.setMaximumSize(new Dimension(32767, 70));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		add(panel_2);
		
		WizardMoveButton create = new WizardMoveButton("<create>");
		create.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		create.setBackground(Color.GRAY);
		create.setForeground(Color.WHITE);
		create.addActionListener(new TagListPanelListener());
		create.setActionCommand("create");
		create.setMinimumSize(new Dimension(117, 70));
		create.setMaximumSize(new Dimension(117, 70));
		//create.setForeground(new Color(0, 0, 128));
		create.setBounds(-11, -11, 151, 91);
		panel_2.add(create);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setMinimumSize(new Dimension(10, 70));
		panel_3.setMaximumSize(new Dimension(32767, 70));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		add(panel_3);
		
		WizardMoveButton repository = new WizardMoveButton("<repository>");
		repository.addActionListener(new TagListPanelListener());
		repository.setBackground(Color.GRAY);
		repository.setForeground(Color.WHITE);
		repository.setActionCommand("repository");
		repository.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		repository.setMinimumSize(new Dimension(117, 70));
		repository.setMaximumSize(new Dimension(117, 70));
		//repository.setForeground(new Color(0, 0, 128));
		repository.setBounds(-11, -11, 151, 91);
		panel_3.add(repository);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setMinimumSize(new Dimension(10, 70));
		panel_4.setMaximumSize(new Dimension(32767, 70));
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		add(panel_4);
		
		WizardMoveButton releases = new WizardMoveButton("<releases>");
		releases.addActionListener(new TagListPanelListener());
		releases.setBackground(Color.GRAY);
		releases.setForeground(Color.WHITE);
		releases.setActionCommand("releases");
		releases.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		releases.setMinimumSize(new Dimension(117, 70));
		releases.setMaximumSize(new Dimension(117, 70));
		//releases.setForeground(new Color(0, 0, 128));
		releases.setBounds(-11, -11, 151, 91);
		panel_4.add(releases);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setMinimumSize(new Dimension(10, 70));
		panel_5.setMaximumSize(new Dimension(32767, 70));
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		add(panel_5);
		
		WizardMoveButton keywords = new WizardMoveButton("<keywords>");
		keywords.addActionListener(new TagListPanelListener());
		keywords.setActionCommand("keywords");
		keywords.setBackground(Color.GRAY);
		keywords.setForeground(Color.WHITE);
		keywords.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		keywords.setMinimumSize(new Dimension(117, 70));
		keywords.setMaximumSize(new Dimension(117, 70));
		//keywords.setForeground(new Color(0, 0, 128));
		keywords.setBounds(-11, -11, 151, 91);
		panel_5.add(keywords);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setMinimumSize(new Dimension(10, 70));
		panel_6.setMaximumSize(new Dimension(32767, 70));
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.WHITE);
		add(panel_6);
		
		WizardMoveButton generators = new WizardMoveButton("<generators>");
		generators.addActionListener(new TagListPanelListener());
		generators.setBackground(Color.GRAY);
		generators.setForeground(Color.WHITE);
		generators.setActionCommand("generators");
		generators.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		generators.setMinimumSize(new Dimension(117, 70));
		generators.setMaximumSize(new Dimension(117, 70));
		//generators.setForeground(new Color(0, 0, 128));
		generators.setBounds(-11, -11, 151, 91);
		panel_6.add(generators);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setMinimumSize(new Dimension(10, 70));
		panel_8.setMaximumSize(new Dimension(32767, 70));
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		add(panel_8);
		
		WizardMoveButton devices = new WizardMoveButton("<devices>");
		devices.addActionListener(new TagListPanelListener());
		devices.setBackground(Color.GRAY);
		devices.setForeground(Color.WHITE);
		devices.setActionCommand("devices");
		devices.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		devices.setMinimumSize(new Dimension(117, 70));
		devices.setMaximumSize(new Dimension(117, 70));
	//	devices.setForeground(new Color(0, 0, 128));
		devices.setBounds(-11, -11, 151, 91);
		panel_8.add(devices);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setMinimumSize(new Dimension(10, 70));
		panel_9.setMaximumSize(new Dimension(32767, 70));
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBackground(Color.WHITE);
		add(panel_9);
		
		WizardMoveButton boards = new WizardMoveButton("<boards>");
		boards.addActionListener(new TagListPanelListener());
		boards.setBackground(Color.GRAY);
		boards.setForeground(Color.WHITE);
		boards.setActionCommand("boards");
		boards.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		boards.setMinimumSize(new Dimension(117, 70));
		boards.setMaximumSize(new Dimension(117, 70));
		//boards.setForeground(new Color(0, 0, 128));
		boards.setBounds(-11, -11, 151, 91);
		panel_9.add(boards);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setMinimumSize(new Dimension(10, 70));
		panel_7.setMaximumSize(new Dimension(32767, 70));
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.WHITE);
		add(panel_7);
		
		JButton taxonomy = new JButton("<taxonomy>");
		taxonomy.addActionListener(new TagListPanelListener());
		taxonomy.setActionCommand("taxonomy");
		taxonomy.setBackground(Color.WHITE);
		taxonomy.setMinimumSize(new Dimension(117, 70));
		taxonomy.setMaximumSize(new Dimension(117, 70));
		taxonomy.setForeground(new Color(0, 0, 128));
		taxonomy.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		taxonomy.setBounds(-11, -11, 151, 91);
		panel_7.add(taxonomy);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setMinimumSize(new Dimension(10, 70));
		panel_10.setMaximumSize(new Dimension(32767, 70));
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		add(panel_10);
		
		WizardMoveButton apis = new WizardMoveButton("<apis>");
		apis.addActionListener(new TagListPanelListener());
		apis.setBackground(Color.GRAY);
		apis.setForeground(Color.WHITE);
		apis.setActionCommand("apis");
		apis.setMinimumSize(new Dimension(117, 70));
		apis.setMaximumSize(new Dimension(117, 70));
		//apis.setForeground(new Color(0, 0, 128));
		apis.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		apis.setBounds(-11, -11, 151, 91);
		panel_10.add(apis);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setMinimumSize(new Dimension(10, 70));
		panel_11.setMaximumSize(new Dimension(32767, 70));
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBackground(Color.WHITE);
		add(panel_11);
		
		JButton conditions = new JButton("<conditions>");
		conditions.addActionListener(new TagListPanelListener());
		conditions.setActionCommand("conditions");
		conditions.setBackground(Color.WHITE);
		conditions.setMinimumSize(new Dimension(117, 70));
		conditions.setMaximumSize(new Dimension(117, 70));
		conditions.setForeground(new Color(0, 0, 128));
		conditions.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		conditions.setBounds(-11, -11, 151, 91);
		panel_11.add(conditions);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setMinimumSize(new Dimension(10, 70));
		panel_12.setMaximumSize(new Dimension(32767, 70));
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBackground(Color.WHITE);
		add(panel_12);
		
		WizardMoveButton examples = new WizardMoveButton("<examples>");
		examples.addActionListener(new TagListPanelListener());
		examples.setBackground(Color.GRAY);
		examples.setForeground(Color.WHITE);
		examples.setActionCommand("examples");
		examples.setMinimumSize(new Dimension(117, 70));
		examples.setMaximumSize(new Dimension(117, 70));
		//examples.setForeground(new Color(0, 0, 128));
		examples.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		examples.setBounds(-11, -11, 151, 91);
		panel_12.add(examples);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setMinimumSize(new Dimension(10, 70));
		panel_13.setMaximumSize(new Dimension(32767, 70));
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBackground(Color.WHITE);
		panel_13.setBackground(Color.GRAY);
		add(panel_13);
		
		WizardMoveButton components = new WizardMoveButton("<components>");
		components.addActionListener(new TagListPanelListener());
		components.setForeground(Color.WHITE);
		components.setBackground(Color.GRAY);
		components.setActionCommand("components");
		components.setMinimumSize(new Dimension(117, 70));
		components.setMaximumSize(new Dimension(117, 70));
		//components.setForeground(new Color(0, 0, 128));
		components.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		components.setBounds(-11, -11, 151, 91);
		panel_13.add(components);
	}
}
