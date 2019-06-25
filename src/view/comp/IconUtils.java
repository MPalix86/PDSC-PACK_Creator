package view.comp;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class IconUtils {
	
	
	/**
	 * FA = Font Awesome icon
	 * 
	 * @param size icon size
	 * @return
	 */
	
	
	/** FA */
	
	public static Icon FAgetDesktopIcon(int size) {
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.DESKTOP, size, new Color(30,127,226) );
	}
	
	public static Icon FAgetAlignLeftIcon(int size) {
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.ALIGN_LEFT, size, new Color(30,127,226) );
	}
	
	public static Icon FAgetFileCodeIcon(int size) {
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FILE_CODE_O, size, new Color(30,127,226) );
	}
	
	public static Icon FAgetInfoCircleIcon(int size) {
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.INFO_CIRCLE, size, new Color(30,127,226) );
	}
	
	
	
	
	/** OTHERS */
	
	public static ImageIcon getPackIcon(int size) {
		return new ImageIcon(new ImageIcon(IconUtils.class.getResource("/icons/pack20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getPlayIcon(int size) {
		return new ImageIcon(new ImageIcon(IconUtils.class.getResource("/icons/play20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getHideChildrenListIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource("/icons/hideList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getShowChildrenListIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource("/icons/showList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getChildrenListIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource("/icons/tagList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	
	public static ImageIcon getChildrenListIArrowIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource("/icons/tagListArrow.png")).getImage().getScaledInstance(33, size, Image.SCALE_DEFAULT));
	}
	

}
