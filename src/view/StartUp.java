package view;

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




public class StartUp extends JFrame {
	

	public static void main(String[] args) throws Exception {
		
//		JDomParser parser = new JDomParser();
//		parser.writeXmlFile("./test.xml");
//		EditFileFrame mf = new EditFileFrame();
//		mf.placeComponents();
		PdscWizardFrame frame = new PdscWizardFrame();
	}
	

}
	

