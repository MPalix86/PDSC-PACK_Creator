package view.comp;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox{
	
	public CustomCheckBox() {
		// Set default icon for checkbox
	    this.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("icons/uncheckedCheckBox.png")));
	    // Set selected icon when checkbox state is selected
	    this.setSelectedIcon(new ImageIcon(this.getClass().getClassLoader().getResource("icons/checkedCheckBox.png")));
	    // Set disabled icon for checkbox
	    this.setDisabledIcon(new ImageIcon(this.getClass().getClassLoader().getResource("icons/uncheckedDisabledCheckBox.png")));
	    // Set disabled-selected icon for checkbox
	    this.setDisabledSelectedIcon(new ImageIcon(this.getClass().getClassLoader().getResource("icons/checkedDisabledCheckBox.png")));
	    // Set checkbox icon when checkbox is pressed
	    this.setPressedIcon(this.getIcon());
	    // Set icon when a mouse is over the checkbox
	    this.setRolloverIcon(this.getIcon());
	    // Set icon when a mouse is over a selected checkbox
	    this.setRolloverSelectedIcon(this.getIcon());
	}
    

}
