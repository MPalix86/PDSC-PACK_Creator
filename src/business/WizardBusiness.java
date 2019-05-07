package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.Response;
import model.XmlAttribute;
import model.XmlTag;
import model.XmlTagContent;
import view.components.StepOneFormContainer;

public class WizardBusiness {
	private static Document doc;
	
	
	
	/* CAUTION 
	 * this function use XmlTag XmlAttribute XmlTagContent defined 
	 * in model/xmlComponents. To write file with JDOM library need to
	 * to convert xmlTag into JDOM Element;
	 */
	//--------------------------------------------------------------------------writePdsc()
	public static Document writePdsc(ArrayList<XmlTag> tagArr) {
		doc = new Document();		
		for(int i = 0 ; i < tagArr.size(); i++) { 
			XmlTag xmlTag = tagArr.get(i);
			ArrayList<XmlAttribute> xmlAttrArr = null;
			try { xmlAttrArr = tagArr.get(i).getAttrArr();} 					// if attrArr != null
			catch(Exception e) {}
			Element el = new Element(xmlTag.getName());							// conversion of XmlTag into JDOM Element
			XmlTagContent xmlTagContents = xmlTag.getContent();
			if(xmlTagContents != null) {													
				if (xmlTagContents.getValue() != null) {						// if element contains String
					el.setText(xmlTagContents.getValue());
				}
				else if (xmlTagContents.getTagArr() != null) {					// if element contains other tag
					el = addChild(xmlTag);
				}
			}
			if(xmlAttrArr != null) {											// if tag contains attributes
				for(int j = 0 ; j < xmlAttrArr.size(); j++) {
					XmlAttribute xmlAttr = xmlAttrArr.get(j);
					if(xmlAttr.isRequired() && xmlAttr.getValue() == "") {		// if attribute is required and attribute is empty
						System.out.println("Attribute :" + xmlAttr.getName() + " is required");
						return null;
					}
					el.setAttribute(xmlAttr.getName(), (String)xmlAttr.getValue());
				}
			}
			if( i == 0) { 														// element root
				doc.setRootElement(el);
			}
			else {																// other element
				doc.getRootElement().addContent(el);
			}
			
		}
		
		return doc;
		
	}
	
	
	/* CAUTION 
	 * this function use XmlTag XmlAttribute XmlTagContent defined 
	 * in model/xmlComponents. To write file with JDOM library need to
	 * to convert xmlTag into JDOM Element;
	 */
	//--------------------------------------------------------------------------addChild();
	private static Element addChild(XmlTag tag) {
		XmlTagContent parentContents = tag.getContent();
		Element parent = new Element(tag.getName());
		if( parentContents.getTagArr() != null) {								// if parent tag contains other tag
			ArrayList<XmlTag> xmlChildTagArr = parentContents.getTagArr();
			for(int i = 0; i < xmlChildTagArr.size(); i++) {					// for each child
				Element childEl = new Element(xmlChildTagArr.get(i).getName());
				if (xmlChildTagArr.get(i).getContent().getTagArr() != null) {	// if child tag contains children tag
					parent.addContent( addChild(xmlChildTagArr.get(i)));		// recursion
				}
				else {															// if child tag does not contains children tag
					childEl.setText(xmlChildTagArr.get(i).getContent().getValue());
					parent.addContent(childEl);
				}
			}
		}
		return parent;
	}
	
	
	//--------------------------------------------------------------------------readPdsc()
	private static void readPdsc() throws IOException {
		File file = new File("/Users/mircopalese/Desktop/pdsctest.pdsc");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder stringBuilder = new StringBuilder();
		String inputLine;
		while ((inputLine = reader.readLine()) != null) {
		    stringBuilder.append(inputLine);
		}
		String pageContent = stringBuilder.toString();
		Pattern pattern = Pattern.compile("<(?!!)(?!/)\\s*([a-zA-Z0-9]+)(.*?)>");
		Matcher matcher = pattern.matcher(pageContent);
		while (matcher.find()) {
		    String tagName = matcher.group(1);
		    String attributes = matcher.group(2);
		    System.out.println("tag name: " + tagName);
		    System.out.println("     rest of the tag: " + attributes);
		    Pattern attributePattern = Pattern.compile("(\\S+)=['\"]{1}([^>]*?)['\"]{1}");
		    Matcher attributeMatcher = attributePattern.matcher(attributes);
		    while(attributeMatcher.find()) {
		        String attributeName = attributeMatcher.group(1);
		        String attributeValue = attributeMatcher.group(2);
		        System.out.println("         attribute name: " + attributeName + "    value: " + attributeValue);
		    }
		}
	}
	
	
	
	
	
	
	
	

}
