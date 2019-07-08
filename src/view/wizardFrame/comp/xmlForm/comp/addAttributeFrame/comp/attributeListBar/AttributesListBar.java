package view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.comp.attributeListBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.XmlAttributeBusiness;
import listeners.wizardFrameListeners.comp.xmlForm.comp.AddAttributeFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import net.miginfocom.swing.MigLayout;
import view.comp.AttributeButton;
import view.comp.AttributeCheckBox;
import view.comp.CustomColor;
import view.comp.IconUtils;


public class AttributesListBar extends JPanel{
	
	
	private XmlTag tag;
	private int option;
	private AddAttributeFrameListener listener;
	

	public AttributesListBar(XmlTag tag, AddAttributeFrameListener listener ) {
		this.tag = tag;
		this.listener = listener;
		placeComponents();
	}
	
	
	

	private void placeComponents() {

		this.setBorder(new EmptyBorder(0,0,0,0));
		this.setLayout(new BorderLayout());
		
		ArrayList<XmlAttribute> attrArr = null;
		if (tag.getAttrArr() != null) attrArr = XmlAttributeBusiness.getNotSelectedAttributes(tag);
		
		
		JPanel panel = new JPanel(new BorderLayout());
		
  		JPanel panel1 = new JPanel();
  		panel1.setLayout(new MigLayout("wrap 2"));
  		
  		panel1.setBackground(Color.WHITE);
  		
  		panel1.setMaximumSize(new Dimension(300,40));
		
  		if(attrArr != null) {

  		  	for(int i = 0; i < attrArr.size(); i++) {
  		  		XmlAttribute attr = attrArr.get(i);
  		    	
  		    	AttributeCheckBox checkBox = new AttributeCheckBox(attr);
  		    	checkBox.addItemListener(listener);
  		    	checkBox.setForeground(CustomColor.ATTR_COLOR);
  		    	if(attr.isRequired()) {
  		    		checkBox.setText(attr.getName() + " *");
  		    		checkBox.setSelected(true);
  		    	}
  		    	 
  		    	
  		    	panel1.add(checkBox);
  		        
  		        AttributeButton showDescriptionButton = (AttributeButton) new AttributeButton(attr).toIconButton(IconUtils.FAgetInfoCircleIcon(20,null));;
  		        showDescriptionButton.addActionListener(listener);
  		        showDescriptionButton.setActionCommand("showDescription");
  		        
  		        panel1.add(showDescriptionButton);
  		        
  		        
//  		        btn.addActionListener(listener);
//  				btn.setActionCommand("addRootChild");
  		        	
  		    }
  		}

    	panel.add(panel1,BorderLayout.CENTER);
    	
    	this.add(panel,BorderLayout.CENTER);
  
	}
		

	
}