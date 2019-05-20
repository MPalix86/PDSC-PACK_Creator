package view.comp;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton{
	
	public IconButton(ImageIcon icon) {
		super();
		setBorderPainted(false);
		setBorder(null);
		//button.setFocusable(false);
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setIcon(icon);
	}
	
	
	
	
	public IconButton(Image image) {
		super();
		setBorderPainted(false);
		setBorder(null);
		//button.setFocusable(false);
		setMargin(new Insets(0, 0, 0, 0));
		ImageIcon icon = new ImageIcon(image);
		setContentAreaFilled(false);
		setIcon(icon);
	}


}
