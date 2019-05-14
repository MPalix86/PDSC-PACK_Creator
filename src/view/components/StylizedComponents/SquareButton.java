package view.Components.StylizedComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class SquareButton extends JButton{
	
	private static final Color stBlue = new Color (57,140,186);
	
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public SquareButton() {
        this(null);
    }

    public SquareButton(String text) {
        super(text);
        this.setPressedBackgroundColor(stBlue.brighter());
        this.setForeground(Color.WHITE);
        this.setBackground(stBlue);
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
