package view.comp.utils;

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
	
	public static Icon FAgetExclamationTriangleIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.EXCLAMATION_TRIANGLE, size, c );
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
	
	public static Icon FAgetTimesIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.TIMES, size, c);
	}
	
	
	public static Icon FAgetPlusIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.PLUS, size, c);
	}
	
	
	public static Icon FAgetFolderOpenIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, size, c);
	}
	
	
	
	
	
	/** OTHERS */
	
	public static ImageIcon getPackIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "pack20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getUndoIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "undo.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getRedoIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "redo.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getOkIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "ok40.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getWarningIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "warning48.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;	 
	}
	
	public static ImageIcon getPlayIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "play20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getHideChildrenListIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "hideList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getShowChildrenListIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "showList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getChildrenListIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "tagList20.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getChildrenListIArrowIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "tagListArrow.png")).getImage().getScaledInstance(33, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getSaveIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "save.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getSaveAsIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "saveAs.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getScreeIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "screen.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getPcIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "pc.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getFolderIcon(int size) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new ImageIcon(IconUtils.class.getResource(iconPath() + "folder.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	private static String iconPath() {
		return  "/icons/";
	}
	

}
