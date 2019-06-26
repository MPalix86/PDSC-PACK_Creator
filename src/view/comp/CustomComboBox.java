package view.comp;

import java.awt.Color;

import javax.swing.JComboBox;

public class CustomComboBox<E> extends JComboBox<E> {

    public CustomComboBox(E[] list) {
        super(list);
        this.setForeground(Color.BLUE);
    }

    public boolean containsItem(Object o) {
    	int itemCount = getItemCount();
    	for (int i = 0 ; i < itemCount ; i ++) {
    		if(o.equals(this.getItemAt(i))) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public CustomComboBox() {
   
    }
    

    
    
  
}
