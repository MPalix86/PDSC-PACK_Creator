package packCreationFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PackCreationFrame extends JFrame{
	
	private JLabel updateLabel;
	
	public PackCreationFrame() {
		
		JPanel contentPane = new JPanel (new BorderLayout());
		this.setContentPane(contentPane);
		updateLabel = new JLabel();
		contentPane.add(updateLabel, BorderLayout.CENTER);
		
		
		this.setVisible(true);
		this.setMinimumSize(new Dimension(350,180));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	public void setText(String text) {
		this.updateLabel.setText("");
		this.updateLabel.setText(text);
		this.updateLabel.repaint();
		this.updateLabel.revalidate();
	}
	

}
