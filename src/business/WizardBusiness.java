package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import model.XmlAttribute;
import model.XmlNameSpace;
import model.XmlTag;

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
			try { xmlAttrArr = tagArr.get(i).getSelectedAttrArr();} 			// if attrArr != null
			catch(Exception e) {}
			Element el = new Element(xmlTag.getName());							// conversion of XmlTag into JDOM Element
			if(xmlTag.getNameSpace() != null) {
				XmlNameSpace xmlNs= xmlTag.getNameSpace();
				Namespace ns = Namespace.getNamespace(xmlNs.getPrefix(), xmlNs.getUrl());
				el.addNamespaceDeclaration(ns);
			}
	
			if (xmlTag.getSelectedChildrenArr() != null) {							// if element contains other tag
				if( !xmlTag.getSelectedChildrenArr().isEmpty() ) {
					el = addChild(xmlTag);
				}
			}
			else if (xmlTag.getContent() != null) {								// if element contains String
				el.setText(xmlTag.getContent());
			}
			
			if(xmlAttrArr != null) {											// if tag contains attributes
				for(int j = 0 ; j < xmlAttrArr.size(); j++) {
					Attribute attribute = addAttribute(xmlAttrArr.get(j));
					if (attribute != null) el.setAttribute(attribute);				
				}
			}
			
			
			if( i == 0) { 														// element root
				doc.setRootElement(el);
			}
			else {		
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
	//--------------------------------------------------------------------------addChildren();
	private static Element addChild(XmlTag tag) {
		Element parent = new Element(tag.getName());
		if( tag.getSelectedChildrenArr() != null) {								// if parent tag contains other tag
			ArrayList<XmlTag> xmlChildren = tag.getSelectedChildrenArr();
			for(int i = 0; i < xmlChildren.size(); i++) {						// for each child
				XmlTag child = xmlChildren.get(i);
				Element childEl = new Element(child.getName());
				ArrayList<XmlAttribute> xmlAttrArr =child.getSelectedAttrArr();
				if(xmlAttrArr != null) {
					for(int j = 0; j < xmlAttrArr.size(); j++) {
						Attribute attribute = addAttribute(xmlAttrArr.get(j));
						if (attribute != null) childEl.setAttribute(attribute);
					}
				}
				if (child.getSelectedChildrenArr() != null) {						// if child tag contains children tag
					parent.addContent( addChild(child));		// recursion
				}
				else {															// if child tag does not contains children tag
					childEl.setText(child.getContent());
					parent.addContent(childEl);
				}
			}
		}
		return parent;
	}
	
	
	
	private static Attribute addAttribute(XmlAttribute attr ) {
		
		XmlAttribute xmlAttr = attr;
		Attribute attribute = null;;
		if( (xmlAttr.isRequired() && xmlAttr.getValue() == "") || (xmlAttr.isRequired() && xmlAttr == null) ) {		// if attribute is required and attribute is empty
			System.out.println("Attribute :" + xmlAttr.getName() + " is required");
		}
		if(xmlAttr.getNameSpace() != null) {
			XmlNameSpace xmlNameSpace = xmlAttr.getNameSpace();
			Namespace ns = Namespace.getNamespace(xmlNameSpace.getPrefix(), xmlNameSpace.getUrl());
			attribute = new Attribute((String) xmlAttr.getName(),(String)  xmlAttr.getValue() , ns);
		}
		else {
			attribute = new Attribute((String) xmlAttr.getName(),(String)  xmlAttr.getValue());
		}
		return attribute;
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
