package view.comp;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class SquareButton extends JButton{
    
    private Color pressedBackGroundColor = CustomColor.LIGHT_GRAY;
    
    private Color hoverBackGroundColor = Color.GRAY;
    
    public final static Color BACKGROUND_COLOR = Color.WHITE;
    
    public final static Color FOREGROUND_COLOR = Color.DARK_GRAY;
    
    public final static Color PRESSED_BACKGROUND_COLOR = Color.GRAY;
    
    public final static Color HOVER_BACKGROUND_COLOR = CustomColor.LIGHT_GRAY;

    public SquareButton() {
        setup();
    }
    
    
    
    public SquareButton(String text) {
        super(text);
        setup();

    }
    
    
    
    
    public SquareButton(String text, Color background, Color foreground) {
    	super(text);
    	setup();
    	this.setBackground(background);
        this.setForeground(foreground);
    }
    
    
    
    
    public SquareButton(String text, Color background, Color foreground, Color pressedBackgroundColor) {
    	super(text);
    	setup();
      	this.setBackground(background);
        this.setForeground(foreground);
    }
    
    
    
    private void setup() {
    	setBorder(new EmptyBorder(9,9,9,9));
        setBackground(this.BACKGROUND_COLOR);
        setForeground(this.FOREGROUND_COLOR);
    }

    
    public SquareButton toIconButton(ImageIcon icon) {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setMargin(new Insets(0,0,0,0));
		setIcon(icon);
		return this;
    }
    
    
    public SquareButton toIconButton(Icon icon) {
    	
		setMargin(new Insets(0,0,0,0));
		setIcon(icon);
		return this;
    }
    

}
