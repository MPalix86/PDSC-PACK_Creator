package view.Components.StylizedComponents;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class WizardMoveButton extends JButton{

    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public WizardMoveButton() {
        this(null);
    }

    public WizardMoveButton(String text) {
        super(text);
        this.setPressedBackgroundColor(Color.DARK_GRAY);
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(52, 73, 94).brighter());
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
