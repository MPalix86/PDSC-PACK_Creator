package view.components;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;

import listeners.StepOneFormFocusListener;
import model.xmlComponents.XmlAttribute;
import model.xmlComponents.XmlTag;
import view.PdscWizardFrame;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

public class StepOneFormContainer extends JPanel {
	private static JTextField schemaVersion;
	private static JTextField xmlns;
	private static JTextField noNamespaceSchemaLocation;
	private static JTextField dcore;
	private static JTextField dvendor;
	private static JTextField dname;
	private static JTextField tcompiler; 
	private static JLabel titleLabel;
	private static JLabel titleLabel_1;

	public StepOneFormContainer() {
		
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(new Dimension(420,590));
		JLabel label2 = new JLabel("schemaVersion *"); 
		label2.setForeground(new Color(255, 99, 71));
		label2.setBackground(Color.WHITE);
		label2.setBounds(57, 85, 296, 16);
		add(label2);
		
		schemaVersion = new JTextField();
		schemaVersion.setBorder(new LineBorder(Color.LIGHT_GRAY));
		schemaVersion.setForeground(Color.DARK_GRAY);
		schemaVersion.setBounds(57, 113, 296, 33);
		schemaVersion.addFocusListener(new StepOneFormFocusListener(this));
		add(schemaVersion);
		schemaVersion.setColumns(10);
		
		JLabel lblXmlnsxs = new JLabel("xmlns:xs *");
		lblXmlnsxs.setForeground(new Color(255, 99, 71));
		lblXmlnsxs.setBounds(57, 158, 296, 16);
		add(lblXmlnsxs);
		
		xmlns = new JTextField();
		xmlns.setBorder(new LineBorder(Color.LIGHT_GRAY));
		xmlns.setForeground(Color.DARK_GRAY);
		xmlns.setText("http://www.w3.org/2001/XMLSchema-instance\"");
		xmlns.setColumns(10);
		xmlns.setBounds(57, 186, 296, 33);
		xmlns.addFocusListener(new StepOneFormFocusListener(this));
		add(xmlns);
		
		JLabel lblXsnonamespaceschemalocation = new JLabel("xs:noNamespaceSchemaLocation *");
		lblXsnonamespaceschemalocation.setForeground(new Color(255, 99, 71));
		lblXsnonamespaceschemalocation.setBounds(57, 231, 296, 16);
		add(lblXsnonamespaceschemalocation);
		
		noNamespaceSchemaLocation = new JTextField();
		noNamespaceSchemaLocation.setBorder(new LineBorder(Color.LIGHT_GRAY));
		noNamespaceSchemaLocation.setForeground(Color.DARK_GRAY);
		noNamespaceSchemaLocation.setColumns(10);
		noNamespaceSchemaLocation.setBounds(57, 259, 296, 33);
		noNamespaceSchemaLocation.addFocusListener(new StepOneFormFocusListener(this));
		add(noNamespaceSchemaLocation);
		
		JLabel lblDcore = new JLabel("Dcore");
		lblDcore.setForeground(new Color(255, 99, 71));
		lblDcore.setBounds(57, 304, 296, 16);
		add(lblDcore);
		
		dcore = new JTextField();
		dcore.setBorder(new LineBorder(Color.LIGHT_GRAY));
		dcore.setForeground(Color.DARK_GRAY);
		dcore.setColumns(10);
		dcore.setBounds(57, 332, 296, 33);
		dcore.addFocusListener(new StepOneFormFocusListener(this));
		add(dcore);
		
		JLabel lblDvendor = new JLabel("Dvendor");
		lblDvendor.setForeground(new Color(255, 99, 71));
		lblDvendor.setBounds(57, 377, 296, 16);
		add(lblDvendor);
		
		dvendor = new JTextField();
		dvendor.setBorder(new LineBorder(Color.LIGHT_GRAY));
		dvendor.setForeground(Color.DARK_GRAY);
		dvendor.setColumns(10);
		dvendor.setBounds(57, 405, 296, 33);
		dvendor.addFocusListener(new StepOneFormFocusListener(this));
		add(dvendor);
		
		JLabel lblDname = new JLabel("Dname");
		lblDname.setForeground(new Color(255, 99, 71));
		lblDname.setBounds(57, 450, 296, 16);
		add(lblDname);
		
		dname = new JTextField();
		dname.setBorder(new LineBorder(Color.LIGHT_GRAY));
		dname.setForeground(Color.DARK_GRAY);
		dname.setColumns(10);
		dname.setBounds(57, 478, 296, 33);
		dname.addFocusListener(new StepOneFormFocusListener(this));
		add(dname);
		
		JLabel lblTcompiler = new JLabel("Tcompiler");
		lblTcompiler.setForeground(new Color(255, 99, 71));
		lblTcompiler.setBounds(57, 523, 296, 16);
		add(lblTcompiler);
		
		tcompiler = new JTextField();
		tcompiler.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tcompiler.setForeground(Color.DARK_GRAY);
		tcompiler.setColumns(10);
		tcompiler.setBounds(57, 551, 296, 33);
		tcompiler.addFocusListener(new StepOneFormFocusListener(this));
		add(tcompiler);
		
		titleLabel = new JLabel("PDSC creator");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(142, 0, 135, 44);
		add(titleLabel);
		
		titleLabel_1 = new JLabel("<package> *");
		titleLabel_1.setForeground(new Color(0, 0, 128));
		titleLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		titleLabel_1.setBounds(30, 49, 123, 24);
		add(titleLabel_1);
	}



	public ArrayList<JTextField> getTexFieldsArr() {
		ArrayList<JTextField>texFieldsArr = new ArrayList();
		texFieldsArr.add(schemaVersion);
		texFieldsArr.add(xmlns);
		texFieldsArr.add(noNamespaceSchemaLocation);
		texFieldsArr.add(dcore);
		texFieldsArr.add(dvendor);
		texFieldsArr.add(dname);
		texFieldsArr.add(tcompiler);
		return texFieldsArr;
	}
	
	public static ArrayList<XmlTag> getTagArr() { 
		ArrayList<XmlTag> tagArr = new ArrayList();
		XmlTag pack = new XmlTag.XmlTagBuilder("package", true , null).build();
		pack.addAttr(new XmlAttribute("schemaVersion", schemaVersion.getText(),  true));
		pack.addAttr(new XmlAttribute("xmlnsxs", xmlns.getText(), true));
		pack.addAttr(new XmlAttribute("xsnoNamespaceSchemaLocation", noNamespaceSchemaLocation.getText(),true));
		pack.addAttr(new XmlAttribute("Dcore",dcore.getText(), false));
		pack.addAttr(new XmlAttribute("Dvendor", dvendor.getText(), false));
		pack.addAttr(new XmlAttribute("Dname", dname.getText(), false));
		pack.addAttr(new XmlAttribute("Tcompiler", tcompiler.getText(), false));
		tagArr.add(pack);
		return tagArr;
	}
	
	public ArrayList<String> getTexFieldsValueArr(){
		ArrayList<String> texFieldsValueArr = new ArrayList();
		texFieldsValueArr.add(schemaVersion.getText());
		texFieldsValueArr.add(xmlns.getText());
		texFieldsValueArr.add(noNamespaceSchemaLocation.getText());
		texFieldsValueArr.add(dcore.getText());
		texFieldsValueArr.add(dvendor.getText());
		texFieldsValueArr.add(dname.getText());
		texFieldsValueArr.add(tcompiler.getText());
		return texFieldsValueArr;
	}



	public String getSchemaVersion() {
		return schemaVersion.getText();
	}



	public String getXmlns() {
		return xmlns.getText();
	}



	public String getXsNoNamespaceSchemaLocation() {
		return noNamespaceSchemaLocation.getText();
	}



	public String getDcore() {
		return dcore.getText();
	}



	public String getDvendor() {
		return dvendor.getText();
	}



	public String getDname() {
		return dname.getText();
	}



	public String getTcompiler() {
		return tcompiler.getText();
	}


}
