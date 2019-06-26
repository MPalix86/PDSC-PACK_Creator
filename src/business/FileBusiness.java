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

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import model.Response;
import model.XmlAttribute;
import model.XmlNameSpace;
import model.XmlTag;

public class FileBusiness {
	public  final static int FILE_ALREADY_EXIST = 0;
	public  final static int FILE_CREATED_CORRECTLY = 1;
	public  final static int IO_EXCEPTION = 2;
	public  final static int FILE_READ_CORRECTLY = 3;
	public  final static int FILE_READ_EXCEPTION = 4;
	public  final static int FILE_ALREADY_OPEN = 5;
	private static Session session;
	
	
	//--------------------------------------------------------------------------createFile()
	public static Response createFile(String path , String extension , Document doc) {
		try {
				String file = path + "." + extension;
			if (!fileExist(file)) {
				
				if (doc == null) {
					doc = new Document();
				}

				Format format = Format.getPrettyFormat();
				format.setTextMode(Format.TextMode.NORMALIZE);
				format.setIndent("	");
				XMLOutputter xmlOutputter = new XMLOutputter(format);
		        xmlOutputter.output(doc, new FileOutputStream(file));
		        Response response = new Response.ResponseBuilder().status(FILE_CREATED_CORRECTLY).message("file created correctly").build();
		        return response;
			}
			else {
				Response response = new Response.ResponseBuilder().status(FILE_ALREADY_EXIST).message("file already exists").build();
				return response;
			}
			
		}
		catch(IOException e) {
			System.out.println("IOException :" + e);
			Response r = new Response.ResponseBuilder().status(IO_EXCEPTION).message("I/O exception").build();
			return r;
		}
		
	}
	
	
	public static boolean fileExist(String path) {
		File temp = new File(path);
		if(temp.exists()) return true;
		return false;
	}

	
	
	public static String getDocumentPreview(Document doc) {
		
		Format format = Format.getPrettyFormat();
		format.setIndent("    ");
		format.setLineSeparator(System.lineSeparator());
		format.setTextMode(Format.TextMode.NORMALIZE);
		
		XMLOutputter xmlOutputter = new XMLOutputter(format);
		
		String preview = xmlOutputter.outputString(doc);
		if(preview != null) {
			return xmlOutputter.outputString(doc);
		}
		else return "";
	}
	
	
	
	
	public static boolean isFilePath(String path) {
		Pattern windowsPath = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
		
		Pattern linuxPath = Pattern.compile("^(.+)/([^/]+)$");
		Matcher m = windowsPath.matcher(path);  
		
		if  (m.matches()) return true;
		
		if(path.length() > 0) {
			if( path.substring(path.length()-1).equals("/")){
				 path = path.substring(0, path.length() - 1);
			}
		}
		
		m = linuxPath.matcher(path);  
		
		if  (m.matches()) return true;
		
		return false;
	}
	
	
	
	
	 /**
	   * Remove file information from a filename returning only its path component
	   * 
	   * @param filename
	   *            The filename
	   * @return The path information
	   */
	
	  public static String pathComponent(String filename) {
		  if(filename != null) {
			  int i = filename.lastIndexOf(File.separator);
		      return (i > -1) ? filename.substring(0, i) : filename;
		  }
	      return "";
	  }
	
	  
	  
	  
	  
	  
   public static String validateXMLSchema(Document doc){
	   String xsdPath = "/Users/mircopalese/Desktop/pdscdescriptor1.xsd";
	      try {
	         SchemaFactory factory =
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	         	File file = new File(xsdPath);
	            Schema schema = factory.newSchema(file);
	            Validator validator = schema.newValidator();
	            
	            String ex = null;
	            DOMOutputter output = new DOMOutputter();
	            org.w3c.dom.Document dom = null;
				try {
					dom = output.output(doc);
				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            	            
	            createFile("/Users/mircopalese/Desktop/prova", "xml", doc);
	            validator.validate(new DOMSource(dom));
	            
	      } catch (IOException e){
	         System.out.println("Exception: "+e.getMessage());
	         String ex = "Exception: "+e.getMessage();
	         return ex;
	      }catch(SAXException e1){
	    	  String ex = "Exception: "+e1.getMessage();
	         return ex;
	      }
	      String ex = "No Error";
	      return ex;
	 }
   
   
   
   
   public static XmlTag ReadPDSCFile(Element parentEl , XmlTag xmlParent , File pdscFile ) {

	  SAXBuilder builder = new SAXBuilder();
	 
	  try {
		  /** parentEl = root in this case */
		if(pdscFile != null && parentEl  == null && xmlParent == null) {
			//System.out.println("i'm in the root");
			Document document = (Document) builder.build(pdscFile);
			parentEl = document.getRootElement();
			xmlParent = new XmlTag(parentEl.getName() , true , null , 1);	
			
			
			List<Attribute> attrList = parentEl.getAttributes();
			for(int j = 0; j < attrList.size(); j++) {	
				Attribute attr = attrList.get(j);
				Response r = XmlAttributeBusiness.verifyAttributeFromName(xmlParent, attr.getName());
				XmlAttribute xmlAttr = (XmlAttribute) r.getObject();
				xmlAttr.setValue(attr.getValue());
				if(xmlAttr != null) xmlParent.addSelectedAttr(xmlAttr);
			}
		}
		
		else {
			//System.out.println("i'm out of the root\n");
			XmlTag xmlChild = XmlTagBusiness.getCompleteTagFromNameAndParent(parentEl.getName(), xmlParent);
			
			/** if found in standard */
			if(xmlChild != null) {
				//System.out.println("found child ="+ xmlChild.getName() +"\n");
				
				xmlChild.setContent(parentEl.getValue());
				
				xmlParent.addSelectedChild(xmlChild);
			
				xmlChild.setMax(xmlChild.getMax() - 1);
				
			}
			else {
				//System.out.println("NO child found in standard for tag" + parentEl.getName() + "\n");
				xmlChild = new XmlTag(parentEl.getName() , false , xmlParent , XmlTag.MAX_OCCURENCE_NUMBER);
				
				xmlChild.setContent(parentEl.getValue());
				
				xmlParent.addSelectedChild(xmlChild);
				
			}
			
			List<Attribute> attrList = parentEl.getAttributes();
			for(int j = 0; j < attrList.size(); j++) {	
				Attribute attr = attrList.get(j);
				Response r = XmlAttributeBusiness.verifyAttributeFromName(xmlParent, attr.getName());
				XmlAttribute xmlAttr = (XmlAttribute) r.getObject();
				xmlAttr.setValue(attr.getValue());
				xmlAttr.setTag(xmlChild);
				if(xmlAttr != null) xmlChild.addSelectedAttr(xmlAttr);
			}
			
			xmlParent = xmlChild;		
			
		}
		
		if( parentEl.getChildren() != null) {
			List<Element> children = parentEl .getChildren();
			
			/** iterating trough selected children */
			for(int i = 0; i < children.size(); i++) {
				
				Element child = children.get(i);
				List<Attribute> attrList1 = child.getAttributes();
				for(int j = 0; j < attrList1.size(); j++) {	
					Attribute attr = attrList1.get(j);
					System.out.print(" " + attr.getName() + " = " + attr.getValue());
				}

				ReadPDSCFile(child, xmlParent , null);
			}
		}

	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  } catch (JDOMException jdomex) {
		System.out.println(jdomex.getMessage());
	  }
	  
	  return xmlParent;
	
	}
   
   
   
   
	
	/* CAUTION 
	 * this function use XmlTag XmlAttribute XmlTagContent defined 
	 * in model/xmlComponents. To write file with JDOM library need to
	 * to convert xmlTag into JDOM Element;
	 */
	//--------------------------------------------------------------------------writePdsc()
	public static Document genratePDSCDocument(XmlTag root) {
		Document doc = new Document();	

			XmlTag xmlTag = root;
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
					
					if (xmlAttrArr.get(j).getValue() != null &&  xmlAttrArr.get(j).getNameSpace() == null) {
						Attribute attribute = new Attribute (xmlAttrArr.get(j).getName(), xmlAttrArr.get(j).getValue());
						el.setAttribute(attribute);	
					}
								
				}
			}
																	// element root
			doc.setRootElement(el);
	
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
				Attribute attribute ;
				if(xmlAttr.getValue() != null )  attribute = new Attribute( xmlAttr.getName(),xmlAttr.getValue());
				else attribute = new Attribute( xmlAttr.getName(),"");
				
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
	
	
	
	
 
	
	
	
	public static void readPDSCRegex(Document doc) throws IOException {
		File file = new File("dsad/dssdada/dsas/");
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
	
