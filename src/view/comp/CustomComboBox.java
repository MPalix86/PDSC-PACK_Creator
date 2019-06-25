package view.comp;

import java.awt.Color;

import javax.swing.JComboBox;

public class CustomComboBox<E> extends JComboBox<E> {

    public CustomComboBox(E[] list) {
        super(list);
        this.setForeground(Color.BLUE);
    }
    
    public CustomComboBox() {
   
    }
    

    
    
  
}
