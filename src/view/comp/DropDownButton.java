package view.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class DropDownButton extends AbstractButton
{
	
   JToggleButton menuButton;
   JPopupMenu popupMenu;
  
   public DropDownButton(JPopupMenu _popupMenu, ImageIcon icon) {
	   
      this.popupMenu = _popupMenu;
  
      menuButton = new JToggleButton(icon);
      add(menuButton);
      menuButton.setContentAreaFilled(false);
      menuButton.setBorder(new EmptyBorder(9,9,9,9));
      
  
      menuButton.addActionListener(new ActionListener() {
    	  
    	 @Override
         public void actionPerformed(ActionEvent ae) {
            popupMenu.show(menuButton, 0, menuButton.getSize().height);
         }
      });
   } 
}
