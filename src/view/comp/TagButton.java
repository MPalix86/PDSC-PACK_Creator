package view.comp;

import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.XmlTag;
import view.comp.utils.ColorUtils;

/**
 * This button contains an XmlTag instance
 * 
 * @author mircopalese
 */
public class TagButton extends JButton{
	
	private XmlTag	tag;
	

    
	/**
	 * initialize tag instance and setting text and color
	 * 
	 * @param tag button's associated tag
	 */
	
	public TagButton(XmlTag tag ) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setForeground(ColorUtils.TAG_COLOR);
		this.tag = tag;
		
	}
	
	
	
	
	public TagButton(XmlTag tag , String text ) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setText(text);
		this.tag = tag;
		
	}
	
	
	
	
	/**
	 * return button's associated tag
	 * 
	 * @return button's associated tag
	 */
	
	public XmlTag getTag() {
		return tag;
	}
	
	
	
	
	/**
	 * set button's associated tag
	 * 
	 * @param tag  button's associated tag
	 */
	
	public void setTag(XmlTag tag) {
		this.tag = tag;
	}
	
	
	
	
   public TagButton toIconButton(ImageIcon icon) {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setMargin(new Insets(0,0,0,0));
		setIcon(icon);
		return this;
    }
   
   
   public TagButton toIconButton(Icon icon) {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setMargin(new Insets(0,0,0,0));
		setIcon(icon);
		return this;
    }
}
