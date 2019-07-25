package view.wizardFrame.comp.xmlForm.comp.addAttributeFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import listeners.wizardFrameListeners.comp.xmlForm.comp.AddAttributeFrameListener;
import model.XmlAttribute;
import model.XmlTag;
import view.comp.SquareButton;
import view.comp.utils.ColorUtils;
import view.wizardFrame.comp.xmlForm.comp.addAttributeFrame.comp.attributeListBar.AttributesListBarContainer;

public class AddAttributeFrame extends JFrame{
	
	private JPanel contentPane;
	private AttributesListBarContainer bar;
	private JTextArea descriptionTextArea;
	private JPanel buttonsPanel;
	private AddAttributeFrameListener listener;
	private XmlAttribute selectedAttr;
	private XmlTag tag;
	private JLabel selectedAttrlabel;
	private ArrayList<XmlAttribute> addedAttr;
	private XmlTag originalTagCopy;
	
	
	/**
	 * @return the selectedAttr
	 */
	public XmlAttribute getSelectedAttr() {
		return selectedAttr;
	}

	public AddAttributeFrame(XmlTag tag) {
		this.originalTagCopy = new XmlTag(tag , tag.getParent());
		this.tag = tag;
		addedAttr = new ArrayList<XmlAttribute>();
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(600,450));
		
		listener = new AddAttributeFrameListener(this);
		bar = new AttributesListBarContainer(tag,listener);
		
		placeComponents();
		centreWindow(this);
		this.setVisible(true);
	}
	
	private void placeComponents() {
		
	
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setBackground(Color.WHITE );
		descriptionTextArea.setEditable(false);
		
		this.setBackground(Color.WHITE);
		

		contentPane = new JPanel();
		buttonsPanel = new JPanel();
		
		JPanel contentPane1 = new JPanel(new BorderLayout());
		contentPane1.setBackground(Color.WHITE);
		
			JPanel panel1 = new JPanel(new BorderLayout());
			panel1.setBackground(Color.WHITE);
			
				JLabel tagLabel = new JLabel("<html> Selected Tag : " + tag.getName() + " <br> </html>");
				Font labelFont = tagLabel.getFont();
				tagLabel.setFont(new Font(labelFont.getName(),Font.PLAIN,16));
				tagLabel.setBorder(new EmptyBorder(0,10,0,0));
				tagLabel.setForeground(ColorUtils.TAG_COLOR);

				
			panel1.add(tagLabel,BorderLayout.WEST);
			panel1.setBorder(new MatteBorder(0,0,0,0, ColorUtils.LIGHT_GRAY));
			
			JPanel contentPane2 = new JPanel(new BorderLayout());
			contentPane2.setBackground(Color.WHITE);
			
				JPanel panel2 = new JPanel(new BorderLayout());
				panel2.setBackground(Color.WHITE);
				panel2.setBorder(new MatteBorder(0,0,1,0, ColorUtils.LIGHT_GRAY));
					selectedAttrlabel = new JLabel("<html> <font size = 3> Add desired attrbutes for tag " + tag.getName() + 
													". Click info button for attribute description </font> <br></html>"
													);
					selectedAttrlabel.setBorder(new EmptyBorder(5,10,10,10));
				panel2.add(selectedAttrlabel, BorderLayout.CENTER);
			
			contentPane2.add(panel2,BorderLayout.NORTH);
			contentPane2.add(descriptionTextArea, BorderLayout.CENTER);
			
			buttonsPanel.setBackground(Color.WHITE);
			buttonsPanel.setLayout(new GridLayout(1,2));
			buttonsPanel.setBorder(new MatteBorder(1,0,0,0, ColorUtils.LIGHT_GRAY));

			
			SquareButton addBtn = new SquareButton("Confirm");
			addBtn.addActionListener(listener);
			addBtn.setActionCommand("addAttributes");
			SquareButton cancelBtn = new SquareButton("Cancel");
			cancelBtn.addActionListener(listener);
			cancelBtn.setActionCommand("cancel");
			
			buttonsPanel.add(cancelBtn);
			buttonsPanel.add(addBtn);
			
		contentPane1.add(panel1, BorderLayout.NORTH);
		contentPane1.add(contentPane2 , BorderLayout.CENTER);
		contentPane1.add(buttonsPanel,BorderLayout.SOUTH);
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(bar,BorderLayout.WEST);
		contentPane.add(contentPane1,BorderLayout.CENTER);
		
		

		this.add(contentPane , BorderLayout.CENTER);
		
	
	}
	
	
	public void updateDescription(String atrtName , String text){
		this.selectedAttrlabel.setText(atrtName);
		this.descriptionTextArea.setText("Description : \n\n" + text);
		Font selectedAttrlabelFont = selectedAttrlabel.getFont();
		selectedAttrlabel.setFont(new Font(selectedAttrlabelFont.getName(),Font.PLAIN,14));
		selectedAttrlabel.setForeground(ColorUtils.ATTR_COLOR);
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public XmlTag geTtag() {
		return this.tag;
	}
	
	public ArrayList<XmlAttribute> getAddeAttrArr(){
		return this.addedAttr;
	}
	
	public XmlTag getOriginalTagCopy() {
		return this.originalTagCopy;
	}
}


