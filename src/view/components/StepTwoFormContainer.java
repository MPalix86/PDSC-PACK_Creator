package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import listeners.StepOneFormFocusListener;
import model.XmlAttribute;
import model.XmlTag;
import model.XmlTagContents;
import view.PdscWizardFrame;
import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

public class StepTwoFormContainer extends JPanel{
	private static JTextField name;
	private static JTextField vendor;
	private static JTextField description;
	private static JTextField url;
	private static JTextField supportContact;
	private static JTextField license;

	public StepTwoFormContainer() {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(new Dimension(420,590));
		JLabel label2 = new JLabel("<name> *"); 
		label2.setForeground(new Color(0, 0, 128));
		label2.setBackground(Color.WHITE);
		label2.setBounds(57, 85, 296, 16);
		add(label2);
		
		name = new JTextField();
		name.setBorder(new LineBorder(Color.LIGHT_GRAY));
		name.setForeground(Color.DARK_GRAY);
		name.setBounds(57, 113, 296, 33);
		//name.addFocusListener(new PackageFormFocusListener());
		add(name);
		name.setColumns(10);
		
		JLabel lblXmlnsxs = new JLabel("<vendor> *");
		lblXmlnsxs.setForeground(new Color(0, 0, 128));
		lblXmlnsxs.setBounds(57, 158, 296, 16);
		add(lblXmlnsxs);
		
		vendor = new JTextField();
		vendor.setBorder(new LineBorder(Color.LIGHT_GRAY));
		vendor.setForeground(Color.DARK_GRAY);
		vendor.setColumns(10);
		vendor.setBounds(57, 186, 296, 33);
		//vendor.addFocusListener(new PackageFormFocusListener());
		add(vendor);
		
		JLabel lblXsnonamespaceschemalocation = new JLabel("<description> *");
		lblXsnonamespaceschemalocation.setForeground(new Color(0, 0, 128));
		lblXsnonamespaceschemalocation.setBounds(57, 231, 296, 16);
		add(lblXsnonamespaceschemalocation);
		
		description = new JTextField();
		description.setBorder(new LineBorder(Color.LIGHT_GRAY));
		description.setForeground(Color.DARK_GRAY);
		description.setColumns(10);
		description.setBounds(57, 259, 296, 33);
		//description.addFocusListener(new PackageFormFocusListener());
		add(description);
		
		JLabel lblDcore = new JLabel("<url> *");
		lblDcore.setForeground(new Color(0, 0, 128));
		lblDcore.setBounds(57, 304, 296, 16);
		add(lblDcore);
		
		url = new JTextField();
		url.setBorder(new LineBorder(Color.LIGHT_GRAY));
		url.setForeground(Color.DARK_GRAY);
		url.setColumns(10);
		url.setBounds(57, 332, 296, 33);
		//url.addFocusListener(new PackageFormFocusListener());
		add(url);
		
		JLabel lblDvendor = new JLabel("<supportContact>");
		lblDvendor.setForeground(new Color(0, 0, 128));
		lblDvendor.setBounds(57, 377, 296, 16);
		add(lblDvendor);
		
		supportContact = new JTextField();
		supportContact.setBorder(new LineBorder(Color.LIGHT_GRAY));
		supportContact.setForeground(Color.DARK_GRAY);
		supportContact.setColumns(10);
		supportContact.setBounds(57, 405, 296, 33);
		//supportContact.addFocusListener(new PackageFormFocusListener());
		add(supportContact);
		
		JLabel lblDname = new JLabel("<license>");
		lblDname.setForeground(new Color(0, 0, 128));
		lblDname.setBounds(57, 450, 296, 16);
		add(lblDname);
		
		license = new JTextField();
		license.setBorder(new LineBorder(Color.LIGHT_GRAY));
		license.setForeground(Color.DARK_GRAY);
		license.setColumns(10);
		license.setBounds(57, 478, 296, 33);
		//license.addFocusListener(new PackageFormFocusListener());
		add(license);

		JLabel titleLabel = new JLabel("PDSC creator");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(142, 0, 135, 44);
		add(titleLabel);

	}
	
	
	public ArrayList<JTextField> getTexFieldsArr() {
		ArrayList <JTextField> texFieldsArr = new ArrayList();
		texFieldsArr.add(name);
		texFieldsArr.add(vendor);
		texFieldsArr.add(description);
		texFieldsArr.add(url);
		texFieldsArr.add(supportContact);
		texFieldsArr.add(license);
		return texFieldsArr;
	}
	 
	public static ArrayList<XmlTag> getTagArr() {
		ArrayList<XmlTag> tagArr = new ArrayList();
//		tagArr.add(new XmlTag.XmlTagBuilder("name", true , new XmlTagContents(name.getText(),null)).build());
//		tagArr.add(new XmlTag.XmlTagBuilder("vendor", true , new XmlTagContents(vendor.getText() , null)).build());
//		tagArr.add(new XmlTag.XmlTagBuilder("description", true , new XmlTagContents(description.getText() , null)).build());
//		tagArr.add(new XmlTag.XmlTagBuilder("url", true , new XmlTagContents(url.getText() , null)).build());
//		tagArr.add(new XmlTag.XmlTagBuilder("supportContact", true , new XmlTagContents(supportContact.getText() , null)).build());
//		tagArr.add(new XmlTag.XmlTagBuilder("license", true , new XmlTagContents(license.getText() , null)).build());
		return tagArr;
		
	}
	
	public ArrayList<String> getTexFieldsValueArr() {
		ArrayList<String> texFieldsValueArr = new ArrayList();
		texFieldsValueArr.add(name.getText());
		texFieldsValueArr.add(vendor.getText());
		texFieldsValueArr.add(description.getText());
		texFieldsValueArr.add(url.getText());
		texFieldsValueArr.add(supportContact.getText());
		texFieldsValueArr.add(license.getText());
		return texFieldsValueArr;
	}


	public String getName() {
		return name.getText();
	}


	public String getVendor() {
		return vendor.getText();
	}


	public String getDescription() {
		return description.getText();
	}


	public String getUrl() {
		return url.getText();
	}


	public String getSupportContact() {
		return supportContact.getText();
	}


	public String getLicense() {
		return license.getText();
	}

}
