package view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import view.comp.CustomColor;




public class SetUiManager {
	
	public SetUiManager() {

		UIManager.put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
		UIManager.put("TabbedPane.background", new Color(235,235,235));
		UIManager.put("TabbedPane.foreground", Color.DARK_GRAY);
		UIManager.put("TabbedPane.selected", CustomColor.WHITE);
		
		

	  	
	  	/** setting JMenuItem */
		UIManager.put("MenuItem.selectionBackground",CustomColor.DARK_GRAY);
		UIManager.put("MenuItem.selectionForeground", CustomColor.WHITE);
		UIManager.put("MenuItem.borderPainted", false);
		UIManager.put("MenuItem.foreground", CustomColor.DARK_GRAY);
		UIManager.put("MenuItem.background", Color.WHITE);
		
		UIManager.put("PopupMenu.border", new MatteBorder(1,1,1,1,CustomColor.SYSTEM_GRAY_COLOR_DARK));
		UIManager.put("PopupMenu.background", Color.WHITE);
		
		
	  	UIManager.put("Menu.selectionBackground", CustomColor.DARK_GRAY);
		UIManager.put("Menu.selectionForeground", CustomColor.WHITE);
		
		
		
	    UIManager.put("ComboBox.border", new MatteBorder(1,1,1,1, CustomColor.LIGHT_GRAY));
	    UIManager.put("ComboBox.foreground", Color.BLACK);
	    UIManager.put("ComboBox.background", Color.WHITE);
	    UIManager.put("ComboBox.selectionForeground", Color.BLACK);
	    UIManager.put("ComboBox.selectionBackground", CustomColor.LIGHT_GRAY);
	    
	    

	  

	    UIManager.put("Button.focus", CustomColor.LIGHT_GRAY);
	    UIManager.put("Button.select", CustomColor.LIGHT_GRAY);


		UIManager.put("MenuBar.selectionBackground", Color.GREEN);
		UIManager.put("MenuBar.selectionForeground", Color.BLUE);
	}
	

}
