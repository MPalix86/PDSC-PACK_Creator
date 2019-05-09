package view.Components.StylizedComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;

public class CustomButton extends JButton{

    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public CustomButton() {
        this(null);
    }

    public CustomButton(String text) {
        super(text);
        this.setPressedBackgroundColor(new Color(52, 73, 94).brighter());
        this.setBackground(new Color(52, 73, 94));
        super.setBorderPainted(false);
        this.setForeground(Color.WHITE);
        this.setMargin(new Insets(5, 5, 5, 5));
       
        //this.setBorder(new LineBorder(Color.BLACK));
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
