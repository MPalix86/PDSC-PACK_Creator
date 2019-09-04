package business;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
	
	
	
	
	public static Response createFile(String path , String extension , Document doc, boolean createOnTempFile, boolean overrideIfExists) {
		
		File file;
		file = new File (path + "." + extension);

		try {	
			
			if(createOnTempFile) {
				file = File.createTempFile(path, extension);
				if(file.exists()) {
					file.delete();
					file = File.createTempFile(path, extension);
				}
				
			}
			
			if ( (createOnTempFile) || (file.exists() && overrideIfExists) || (!file.exists())) {
				if (doc == null) doc = new Document();
				Format format = Format.getPrettyFormat();
				format.setTextMode(Format.TextMode.NORMALIZE);
				format.setIndent("	");
				XMLOutputter xmlOutputter = new XMLOutputter(format);
		        xmlOutputter.output(doc, new FileOutputStream(file));
		        return new Response.ResponseBuilder().status(FILE_CREATED_CORRECTLY).message("file created correctly").object(file).build();
			}
			else return new Response.ResponseBuilder().status(FILE_ALREADY_EXIST).message("file already exists").build();

			
		}
		catch(IOException e) {
			System.out.println("IOException :" + e);
			return new Response.ResponseBuilder().status(IO_EXCEPTION).message("I/O exception").build();
		}
		
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
	  
	  
	  
	/**
	 * Validate xml against xsd schema
	 * 
	 * @param JDOM doc the document to validate
	 * @return String with validations status
	 */
	  
	public static Response validateXMLSchema(Document doc){
		
		/** loading xsd path */
		String xsdPath = "/resources/PACK.XSD";

		String returnMessage = "";
		
		try {
			InputStream in = FileBusiness.class.getResourceAsStream(xsdPath); 
			
		    File xsdFile = File.createTempFile("PACK", "XSD");
			
		    FileUtils.copyInputStreamToFile(in, xsdFile);
			 
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	        Schema schema = factory.newSchema(xsdFile);
	       
	        /** creating temp pdsc file */ 
	        Response response = FileBusiness.createFile("pdsc_temp", "PDSC", doc,true , false);
		    File tempPDSC = null;
		   
		    /** recovering temp pdsc file */
		    if(response.getStatus() == FileBusiness.FILE_CREATED_CORRECTLY) tempPDSC = (File) response.getObject();
		   
		    /** validation */
		    Validator validator = schema.newValidator();
		    validator.validate(new StreamSource(tempPDSC));
		    returnMessage = "validation seccessfull";
	   }
	   catch (IOException e) {
		   returnMessage = "IOException";
	   }
	   catch (SAXParseException e) {
	       int line = (e.getLineNumber() - 1);
	       int col = e.getColumnNumber();
	       String el = e.getPublicId();
	       String message = e.getMessage();
	       String selected_document;
	       if(Session.getInstance().getSelectedPDSCDoc().getSourcePath() == null) selected_document = "untitled";
	       else selected_document = Session.getInstance().getSelectedPDSCDoc().getSourcePath().toString();
	       
	       returnMessage = 	"Selected Document : " + selected_document + "\n" +
	    		   			"--------------------------------------------------------------------------------\n" +
	       					"Error when validate XML against XSD Schema\n" +  "line: " + line + "\n" +
	    		    		"column: " + col + "\n" +
	    		    		"element: " + el + "\n" +
	    		    		"message: " + message.substring(message.indexOf(":") + 2 ) + "\n\n";
	      
	       /** return message with line number */
	       return new Response.ResponseBuilder().message(returnMessage).object(line).build();
	   }
	   catch (SAXException e) {
		   returnMessage = "SAXException";
	   }
		  /** return only message */
		 return new Response.ResponseBuilder().message(returnMessage).build();
	 }
   
   
   
   
	
	//--------------------------------------------------------------------------
	public static Document genratePDSCDocument(XmlTag root) {
		Document doc = new Document();	
		XmlTag xmlTag = root;
		Element el = addChild(xmlTag);														
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
					parent.addContent(addChild(child));		
			}
		}
		else if(tag.getContent() != null) parent.setText(tag.getContent());	
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
	//--------------------------------------------------------------------------

	

}
	
