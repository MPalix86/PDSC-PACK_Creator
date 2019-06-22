package view.comp;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class SquareButton extends JButton{
    
    private Color backGround = Color.WHITE;
    
    private Color foreGround = Color.DARK_GRAY;
    
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
        
    }
    
    
    
    
    public SquareButton(String text, Color background, Color foreground, Color pressedBackgroundColor) {
    	super(text);
    	setup();
    }
    
    
    
    private void setup() {
    	setBorder(new EmptyBorder(9,9,9,9));
        setBackground(this.BACKGROUND_COLOR);
        setHoverBackGroundColor(this.HOVER_BACKGROUND_COLOR);
        setForeground(this.FOREGROUND_COLOR);
        setPressedBackGroundColor(Color.PINK);
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackGroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackGroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    public Color getHoverBackGroundColor() {
        return hoverBackGroundColor;
    }

    public void setHoverBackGroundColor(Color hoverBackGroundColor) {
        this.hoverBackGroundColor = hoverBackGroundColor;
    }

    public Color getPressedBackGroundColor() {
        return pressedBackGroundColor;
    }

    public void setPressedBackGroundColor(Color pressedBackGroundColor) {
        this.pressedBackGroundColor = pressedBackGroundColor;
    }

    
    public SquareButton toIconButton(ImageIcon icon) {
    	setBorder(new EmptyBorder(9,9,9,9));
        setBackground(this.BACKGROUND_COLOR);
        setHoverBackGroundColor(this.HOVER_BACKGROUND_COLOR);
        setForeground(this.FOREGROUND_COLOR);
        setPressedBackGroundColor(Color.PINK);
		setContentAreaFilled(false);
		setIcon(icon);
		return (SquareButton) this;
    }
    

}
