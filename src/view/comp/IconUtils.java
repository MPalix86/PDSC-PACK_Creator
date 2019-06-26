package view.comp;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class IconUtils {
	
	private static final Color ICON_COLOR = new Color(86,175,255);
	
	/**
	 * FA = Font Awesome icon
	 * 
	 * @param size icon size
	 * @return
	 */
	
	
	/** FA */
	
	public static Icon FAgetDesktopIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.DESKTOP, size, c );
	}
	
	public static Icon FAgetAlignLeftIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.ALIGN_LEFT, size, c);
	}
	
	public static Icon FAgetFileCodeIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FILE_CODE_O, size, c );
	}
	
	public static Icon FAgetInfoCircleIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.INFO_CIRCLE, size, c );
	}
	
	public static Icon FAgetPlusCircleIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, size, c);
	}
	
	public static Icon FAgetFloppyIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FLOPPY_O, size, c);
	}
	
	
	
	
	
	/** OTHERS */
	
	public static ImageIcon getPackIcon(int size) {
		return new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "pack20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getPlayIcon(int size) {
		return new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "play20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getHideChildrenListIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "hideList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getShowChildrenListIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "showList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getChildrenListIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "tagList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getChildrenListIArrowIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "tagListArrow.png")).getImage().getScaledInstance(33, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getSaveIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource( iconPath() + "save.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getSaveAsIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource( iconPath() + "saveAs.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getScreeIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource( iconPath() + "screen.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	public static ImageIcon getPcIcon(int size) {
		return  new ImageIcon(new ImageIcon(IconUtils.class.getResource( iconPath() + "pc.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	private static String iconPath() {
		return  "/icons/";
	}
	

}
