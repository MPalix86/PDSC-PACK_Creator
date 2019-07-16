package view.tagCustomizationFrame.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import listeners.tagCustomizationFrameListeners.TagCustomizationFrameListener;
import model.XmlTag;
import view.comp.DropDownButton;
import view.comp.TagMenuItem;
import view.comp.utils.ColorUtils;
import view.comp.utils.IconUtils;


public class CollapsablePanel extends JPanel {
	 
    private boolean selected;
    JPanel contentPanel_;
    HeaderPanel headerPanel_;
    private XmlTag tag;
    private TagCustomizationFrameListener listener ;
    

 
    private class HeaderPanel extends JPanel implements MouseListener {
        String text_;
        Font font;
        BufferedImage open, closed, remove;
        final int OFFSET = 50, PAD = 30;
        int textWidth, textHeight;
        private XmlTag tag;
        private TagCustomizationFrameListener listener;
        JPopupMenu editMenu;
        TagMenuItem copyItem;
        TagMenuItem deleteItem;
        TagMenuItem addRequiredTagsItem;
        JMenu childrenMenu;
        DropDownButton dropDownButton;
 
        public HeaderPanel(String text,XmlTag tag ,TagCustomizationFrameListener listener ) {
        	this.tag = tag;
        	this.listener = listener;
        	this.setLayout(null);
        	this.setBackground(Color.WHITE);
        	this.setSize(300, 20);
            addMouseListener(this);
            text_ = text;
            font = new Font("sans-serif", Font.PLAIN, 15);
            setPreferredSize(new Dimension(200, 20));
            int w = getWidth();
            int h = getHeight();
            
            editMenu = new JPopupMenu();
    		copyItem = new TagMenuItem("Copy Tag",this.tag);
    		addRequiredTagsItem = new TagMenuItem("Add Required Tags",this.tag);
    		deleteItem = new TagMenuItem("Delete",this.tag);
    		
    		/** if tag has children add showChildrenButton */
    		childrenMenu = new JMenu("Children Tag");
    		
    		if(tag.getChildrenArr() != null) {
    			for (int i =0;  i < tag.getChildrenArr().size(); i++){
    				XmlTag child = tag.getChildrenArr().get(i);
    				
    				TagMenuItem childMenuItem;
    				if(child.isRequired()) childMenuItem = new TagMenuItem("< "+ child.getName() + " > *",child);
    				else  childMenuItem = new TagMenuItem("< "+ child.getName() + " >",child);
    				
    				childMenuItem.addActionListener(listener);
    				childMenuItem.setActionCommand("addTagPanel");
    				childrenMenu.add(childMenuItem);
    			}
    			editMenu.add(childrenMenu);
    		}
    		else {
    			childrenMenu.setEnabled(false);
    			editMenu.add(childrenMenu);
    		}
    		
   
            dropDownButton = new DropDownButton(editMenu,IconUtils.FAgetPencilSquareOIcon(18, ColorUtils.DARK_GRAY));
            dropDownButton.setBounds(5, 0, 20, 20);
        	
            this.add(this.dropDownButton);
        	
    		if(tag.getParent() != null) {

    			/** setting button's listener */
    			deleteItem.addActionListener(listener);
    			deleteItem.setActionCommand("removeTagPanel");
    			
    			copyItem.addActionListener(listener);
    			copyItem.setActionCommand("cloneTag");
    			
    		}
    		else {
    			deleteItem.setEnabled(false);
    			copyItem.setEnabled(false);
    		}
    		addRequiredTagsItem.addActionListener(listener);
    		addRequiredTagsItem.setActionCommand("addRequiredTags");
    		
    		
     		editMenu.add(copyItem);
    		editMenu.add(deleteItem);
    		editMenu.add(addRequiredTagsItem);
        	
 
            try {
                open = ImageIO.read(getClass().getResourceAsStream("/icons/downArrow.png"));
                closed = ImageIO.read(getClass().getResourceAsStream("/icons/rightArrow.png"));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
 
        
        
        
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            int h = getHeight();
            
            if (selected) {
            	g2.drawImage(open, PAD, 0, h, h, this);
            }
            else {

            	g2.drawImage(closed, PAD, 0, h, h, this);
            }
            
          
            g2.setFont(font);
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = font.getLineMetrics(text_, frc);
            float height = lm.getAscent() + lm.getDescent();
            float x = OFFSET;
            float y = (h + height) / 2 - lm.getDescent();
            g2.drawString(text_, x, y);
        }
        
        
      
        
        public void mouseClicked(MouseEvent e) {
            toggleSelection();
        }
 
        public void mouseEntered(MouseEvent e) {
        	
        }
 
        public void mouseExited(MouseEvent e) {
        	
        }
 
        public void mousePressed(MouseEvent e) {
        }
 
        public void mouseReleased(MouseEvent e) {
        }
 
    }

    public CollapsablePanel(String text, JPanel panel, XmlTag tag, TagCustomizationFrameListener listener ) {
        super(new GridBagLayout());
    	this.listener = listener;
    	this.tag = tag;
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 3, 0, 3);
        gbc.weightx = 1.0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridwidth = gbc.REMAINDER;
        selected = false;
        headerPanel_ = new HeaderPanel(text, this.tag,listener);
        panel.setBorder(new EmptyBorder(0,headerPanel_.PAD,0,0));
       
        setBackground(Color.WHITE);
        contentPanel_ = panel;
 
        add(headerPanel_, gbc);
        add(contentPanel_, gbc);
        contentPanel_.setVisible(false);
 
        JLabel padding = new JLabel();
        gbc.weighty = 1.0;
        add(padding, gbc);
        
        headerPanel_.setPreferredSize(new Dimension(900,headerPanel_.getHeight()));
 
    }
 
    public void toggleSelection() {
        selected = !selected;
 
        if (contentPanel_.isShowing())
            contentPanel_.setVisible(false);
        else
            contentPanel_.setVisible(true);
 
        validate();
 
        headerPanel_.repaint();
    }
    
    public JPanel getContentPanel() {
    	return this.contentPanel_;
    }
 
}