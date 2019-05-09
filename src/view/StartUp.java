package view;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Node;

import business.FileBusiness;
import model.XmlAttribute;
import model.XmlTag;
import model.XmlTagContent;
import model.pdscTag.rootChildren.Conditions;
import view.Components.tagCustomizationFrameComponents.TagContainer;
import view.Components.tagCustomizationFrameComponents.CollapsableTagPanel;




public class StartUp extends JFrame {
	

	public static void main(String[] args) throws Exception {
		
//		JDomParser parser = new JDomParser();
//		parser.writeXmlFile("./test.xml");
//		EditFileFrame mf = new EditFileFrame();
//		mf.placeComponents();
		WizardFrame frame = new WizardFrame();
	
		
//		XmlTag tag = new XmlTag.XmlTagBuilder("test", true, new XmlTagContents("ciao" , null)).build();
//		XmlTag child = new XmlTag.XmlTagBuilder("child", true, new XmlTagContents("child" , null)).build();
//		child.addAttr(new XmlAttribute("child1" , "1" , true));
//		child.addAttr(new XmlAttribute("child1" , "2" , true));
//		child.addAttr(new XmlAttribute("child1" , "2" , true));
//		tag.addAttr(new XmlAttribute("nome" , "valore" , true));
//		tag.addAttr(new XmlAttribute("nome1" , "valore1" , false));
//		tag.addAttr(new XmlAttribute("nome2" , "valore2" , true));
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
//		tag.addChild(child);
		
		//TagPanelComponent p = new TagPanelComponent(tag);
		//CollapsablePanel cp = new CollapsablePanel("tag", p);
		//TagContainer c = new TagContainer(new Conditions());
//			JFrame f = new JFrame();
//	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        f.getContentPane().setSize(360, 500);
//	        f.getContentPane().add(new JScrollPane(c));
//	        f.setSize(360, 500);
//	        
//	        f.setLocation(200, 100);
//	        f.setVisible(true);
	}
	
	
}
	

    