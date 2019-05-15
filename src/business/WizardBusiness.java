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
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		doc = new Document();	
		for(int i = 0 ; i < tagArr.size(); i++) { 
			XmlTag xmlTag = tagArr.get(i);
			ArrayList<XmlAttribute> xmlAttrArr = null;
			try { xmlAttrArr = xmlTag.getSelectedAttrArr();} 			// if attrArr != null
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
			else if (xmlTag.getContent() != null) {	
				el.setText(xmlTag.getContent());
			}

			
			if(xmlAttrArr != null) {											// if tag contains attributes
				
				
				for(int j = 0 ; j < xmlAttrArr.size(); j++) {
					
					if (xmlAttrArr.get(j).getValue() != null) {
						Attribute attribute = new Attribute (xmlAttrArr.get(j).getName(), xmlAttrArr.get(j).getValue());
						el.setAttribute(attribute);	
					}
								
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
	
	
	/**
	 * Add all XmlTag's selected children with respective attributes inside 
	 * new JDom Element 
	 * 
	 * @param tag
	 * @return
	 */
	
	private static Element addChild(XmlTag tag) {
		Element parent = new Element(tag.getName());
		
		addAttribute(tag,parent);
		
		if( tag.getSelectedChildrenArr() != null) {								
			ArrayList<XmlTag> xmlChildren = tag.getSelectedChildrenArr();
			for(int i = 0; i < xmlChildren.size(); i++) {						
				XmlTag child = xmlChildren.get(i);	
					parent.addContent( addChild(child));		
			}
		}
		if(tag.getContent() != null) parent.setText(tag.getContent());	
		return parent;
	}
	
	
	
	
	
	/**
	 * add all XmlTag's (tag) selected attributes inside passed JDom Element (el)
	 * 
	 * @param tag XmlTag with selected attributes
	 * @param el  JDom Element in which to move attributes
	 */

	private static void addAttribute(XmlTag tag,Element parent) {
		
		ArrayList<XmlAttribute> xmlAttrArr = tag.getSelectedAttrArr();
		
		/** if tag contains attributes */
		if(xmlAttrArr != null) {
			
			for(int j = 0; j < xmlAttrArr.size(); j++) {	
				
				/** saving current attribute */
				XmlAttribute xmlAttr = xmlAttrArr.get(j);
				
				/** conversion from XmlAttribute to JDom attribute */
				Attribute attribute = new Attribute((String) xmlAttr.getName(),(String)  xmlAttr.getValue());
				
				/** check attribute's value */
				if( xmlAttr.getValue() == "" || xmlAttr == null ) {		
					//System.out.println("Attribute :" + xmlAttr.getName() + " was no inserted ");
				}
				
				/** if attribute have name space */
				if(xmlAttr.getNameSpace() != null) {
					XmlNameSpace xmlNameSpace = xmlAttr.getNameSpace();
					Namespace ns = Namespace.getNamespace(xmlNameSpace.getPrefix(), xmlNameSpace.getUrl());
					attribute.setNamespace(ns);
				}
				
				/** add JDom attribute inside JDome element parent */
				if (attribute != null) parent.setAttribute(attribute);
			}
		}
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
