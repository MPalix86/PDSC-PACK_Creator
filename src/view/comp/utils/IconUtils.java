package view.comp.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import business.Scalr;
import business.Scalr.Method;
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
	
	/** MA */
//	public static Icon MAgetPencilSquareOIcon(int size , Color c) {
//		if (c == null) c = ICON_COLOR;
//		IconFontSwing.register(GoogleMaterialDesignIcons.);
//		return IconFontSwing.buildIcon(FontAwesome.PENCIL_SQUARE_O, size, c );
//	}
	
	
	
	/** FA */
	
	public static Icon FAgetPencilSquareOIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.PENCIL_SQUARE_O, size, c );
	}
	
	public static Icon FAgetFileCodeOIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FILE_CODE_O, size, c );
	}
	
	public static Icon FAgetExclamationIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.EXCLAMATION, size, c );
	}
	
	public static Icon FAgetCheckIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.CHECK, size, c );
	}
	
	public static Icon FAgetFileIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FILE, size, c );
	}
	
	public static Icon FAgetTrashIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.TRASH, size, c );
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
	
	public static Icon FAgetPencilIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.PENCIL, size, c);
	}
	
	
	public static Icon FAgetFolderOpenIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.FOLDER_OPEN, size, c);
	}
	
	public static Icon FAgetClipboardIcon(int size , Color c) {
		if (c == null) c = ICON_COLOR;
		IconFontSwing.register(FontAwesome.getIconFont());
		return IconFontSwing.buildIcon(FontAwesome.CLIPBOARD, size, c);
	}
	
	
	
	
	
	/** OTHERS */
	
	public static ImageIcon getPackIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "pack.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	
	public static ImageIcon getGearIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "pack.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	
	public static ImageIcon removeFileIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "removeFile.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getFileOkIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "fileOk.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getOkIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "ok.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getPriorityIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "priority.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getFileXIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "fileX.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getStLogoIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "STLogoOnly.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getYoututbeIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "youtube.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}

	
	public static ImageIcon getTwitterIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "twitter.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getFacebookIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "facebook.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getUndoIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "undo.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}

	
	public static ImageIcon getRedoIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "redo.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm,Method.AUTOMATIC,  size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getWarningIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "warning48.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;	 
	}
	
	public static ImageIcon getPlayIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "play.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getHideChildrenListIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "hideList20.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getShowChildrenListIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "showList20.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getChildrenListIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "taglist20.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getChildrenListIArrowIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "tagListArrow.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getSaveIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "save.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getSaveAsIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "saveAs.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getScreeIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "screen.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getCopyIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "copy.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getCutIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "cut.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getNewDocumentIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "newDocument.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	
	public static ImageIcon getCloneIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "clone.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getTreeStructureIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "treeStructure.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getTrashIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "trash.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getHierarchyIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "hierarchy.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getDiagramIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "diagram.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	public static ImageIcon getStructureIcon(int size) {
		ImageIcon icon = null;
		try {
			BufferedImage buffIm = ImageIO.read(IconUtils.class.getResource(iconPath() + "structure.png"));
			BufferedImage scaledImg = Scalr.resize(buffIm, Method.AUTOMATIC, size);
			icon = new ImageIcon(scaledImg);
		}catch(Exception e) {
			icon = new ImageIcon();
		}
		return icon;
	}
	
	private static String iconPath() {
		return  "/icons/";
	}
	

}
