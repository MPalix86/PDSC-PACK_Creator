package view.comp;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class SquareButton extends JButton{
    
    private Color background = Color.WHITE;
    
    private Color foreground = Color.DARK_GRAY;
    
    private Color pressedBackgroundColor = new Color(224,224,224); 
    
    private Color hoverBackgroundColor;

    public SquareButton() {
        this(null);
    }

    
    
    
    public SquareButton(String text) {
        super(text);
        this.setBackground(background);
    	this.setForeground(foreground);
    	this.setPressedBackgroundColor(pressedBackgroundColor);
        super.setBorderPainted(false);
        
    }
    
    
    
    
    public SquareButton(String text, Color background, Color foreground) {
    	super(text);
    	this.setBackground(background);
    	this.setForeground(foreground);
    	this.setPressedBackgroundColor(this.getBackground().brighter());
        super.setBorderPainted(false);
        
    }
    
    
    
    
    public SquareButton(String text, Color background, Color foreground, Color pressedBackgroundColor) {
    	super(text);
    	this.setBackground(background);
    	this.setForeground(foreground);
    	this.setPressedBackgroundColor(pressedBackgroundColor);
        super.setBorderPainted(false);
        
    }
    
    
    
    


    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth() + 8 , getHeight() + 8);
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}
