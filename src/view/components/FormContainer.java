package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.XmlTag;
import net.miginfocom.swing.MigLayout;



public class FormContainer extends JPanel{
	private XmlTag tag;
	
	public FormContainer(XmlTag tag) {
		this.tag = tag;
		setLayout(new MigLayout());
		setPreferredSize(new Dimension(900,900)); 
		
			JLabel titleLabel_1 = new JLabel("<package> *");
			titleLabel_1.setForeground(new Color(0, 0, 128));
			titleLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			titleLabel_1.setBounds(30, 49, 123, 24);
			
		this.add(titleLabel_1,"wrap");
		
			JPanel labelPanel = new JPanel();
		
				JLabel label2 = new JLabel("schemaVersion *"); 
				label2.setForeground(new Color(255, 99, 71));
				label2.setBackground(Color.WHITE);
				
			labelPanel.add(new SpacePanel());
			labelPanel.add(label2);
			
		this.add(labelPanel,"wrap");
		
			JPanel textPanel = new JPanel();
			textPanel.add(new SpacePanel());
			textPanel.add(new JTextField(20));
	
		this.add(textPanel,"wrap");

		
	}
	
	public class SpacePanel extends JPanel{
		public SpacePanel() {
			this.setPreferredSize(new Dimension(20,10));
//			this.setBackground(Color.RED);
			this.setVisible(true);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = (JPanel) f.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new FormContainer(), BorderLayout.CENTER);
		f.pack();
		f.setSize(900, 900);
		f.setVisible(true);
		
	}
	
}
