package view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import view.comp.utils.ColorUtils;




public class SetUiManager {
	
	public SetUiManager() {

		UIManager.put("TabbedPane.contentBorderInsets", new Insets(5,0,0,0));
		UIManager.put("TabbedPane.background", new Color(235,235,235));
		UIManager.put("TabbedPane.foreground", Color.DARK_GRAY);
		UIManager.put("TabbedPane.selected", ColorUtils.WHITE);
		UIManager.put("TabbedPane.borderHightlightColor", Color.DARK_GRAY);
		
		
		

	  	
	  	/** setting JMenuItem */
		UIManager.put("MenuItem.selectionBackground",ColorUtils.DARK_GRAY);
		UIManager.put("MenuItem.selectionForeground", ColorUtils.WHITE);
		UIManager.put("MenuItem.borderPainted", false);
		UIManager.put("MenuItem.foreground", ColorUtils.DARK_GRAY);
		UIManager.put("MenuItem.background", Color.WHITE);
		
		UIManager.put("PopupMenu.border", new LineBorder(ColorUtils.DARK_GRAY , 2 , true));
		UIManager.put("PopupMenu.background", Color.WHITE);
		
		
	  	UIManager.put("Menu.selectionBackground", ColorUtils.DARK_GRAY);
		UIManager.put("Menu.selectionForeground", ColorUtils.WHITE);
		
		
		
	    UIManager.put("ComboBox.border", new MatteBorder(1,1,1,1, ColorUtils.LIGHT_GRAY));
	    UIManager.put("ComboBox.foreground", Color.BLACK);
	    UIManager.put("ComboBox.background", Color.WHITE);
	    UIManager.put("ComboBox.selectionForeground", Color.BLACK);
	    UIManager.put("ComboBox.selectionBackground", ColorUtils.LIGHT_GRAY);
	    
	    
	    UIManager.put("OptionPane.background",Color.WHITE);
	    UIManager.put("Panel.background",Color.WHITE);
	  

	    UIManager.put("Button.focus", ColorUtils.LIGHT_GRAY);
	    UIManager.put("Button.select", ColorUtils.LIGHT_GRAY);


		UIManager.put("MenuBar.selectionBackground", Color.WHITE);
		UIManager.put("MenuBar.selectionForeground", Color.BLUE);
	}
	

}
