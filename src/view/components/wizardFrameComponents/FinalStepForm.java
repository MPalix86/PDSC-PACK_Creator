package view.Components.wizardFrameComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import listeners.WizardFrameListener;

public class FinalStepForm extends JPanel{
	private WizardFrameListener listener;
	
	public FinalStepForm(WizardFrameListener listenenr) {
		placeComponent();
		this.listener = listenenr;
	}
	
	public FinalStepForm() {
		this.listener = new WizardFrameListener();  
		placeComponent();
	
	}
	
	private void placeComponent() {
		setForeground(Color.BLACK);
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(new Dimension(420,590));
		
		JLabel titleLabel = new JLabel("Wizard Complete");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(6, 0, 408, 44);
		add(titleLabel);
		
		JButton generatePdscBtn = new JButton("Generate PDSC");
		generatePdscBtn.setForeground(Color.BLACK);
		generatePdscBtn.setBackground(Color.WHITE);
		generatePdscBtn.setBounds(116, 145, 185, 44);
		generatePdscBtn.addActionListener(listener);
		generatePdscBtn.setActionCommand("generatePdsc");
		
		add(generatePdscBtn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(65, 297, 254, 175);
		Dimension lblDim = new Dimension (lblNewLabel.getWidth(),lblNewLabel.getHeight());
		Dimension lblDim1 = new Dimension (1024,655);
		Image img = getScaledImage(lblDim1,lblDim ,"/icons/st1.png");
		ImageIcon icon = new ImageIcon();
		icon.setImage(img);
		lblNewLabel.setIcon(icon);
		add(lblNewLabel);
	}
	
	
	
	public Image getScaledImage(Dimension imgSize, Dimension boundary, String path) {
		BufferedImage img = null; 
		try {
		    img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }
	    Image dimg = img.getScaledInstance(new_width, new_height,Image.SCALE_SMOOTH);
	    return dimg;
	}
}
