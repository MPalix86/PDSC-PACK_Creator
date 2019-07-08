package view.comp;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

 

public final class TextButton extends JLabel implements MouseListener{
	
	private ArrayList<ActionListener> listeners;
	
	private Color foreground;
	private Color mouseEnteredReleasedForeground;
	
	private ActionEvent actionEvenet ;
	
	public TextButton(String text) {
		super(text);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		listeners = new ArrayList<ActionListener>();
		addMouseListener(this);
		foreground = Color.DARK_GRAY;
		mouseEnteredReleasedForeground = CustomColor.SYSTEM_BLUE_COLOR_DARK;
		this.setForeground(foreground);
	}
	
	
	public TextButton(String text , Color foreground  , Color mouseEnteredReleasedForeground) {
		super(text);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		listeners = new ArrayList<ActionListener>();
		addMouseListener(this);
		this.foreground = foreground;
		this.mouseEnteredReleasedForeground = mouseEnteredReleasedForeground;
		this.setForeground(foreground);
	}
	
	
	public void addActionListener(ActionListener l) {
		listeners.add(l);
	}
	
	public void setActionCommand(String command) {
		actionEvenet = new ActionEvent(this , 0 , command);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listeners.forEach((l) -> l.actionPerformed(actionEvenet));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.setForeground(this.getForeground().brighter());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.setForeground(mouseEnteredReleasedForeground);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(mouseEnteredReleasedForeground);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(foreground);
		
	}

}