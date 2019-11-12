package business.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import business.PDSCDocumentManager;
import business.Session;
import model.Response;

/** 
 * Utils to manage PDSC Document objects
 * 
 * NOTE : all functions in someNameUils class do not change the instance of the object
 * 
 * @author mircopalese
 */
public class PDSCDocumentUtils {
	
	

	/**
	 * return document in form of string
	 * 
	 * @param doc
	 * @return
	 */
	public static String getStringFromJDomDocument(Document doc) {
		
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
	  
	public static Response validatePDSCDocumentWithXMLSchema(Document doc, File xsd){
		

		String returnMessage = "";
		
		try {			 
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	        Schema schema = factory.newSchema(xsd);
	       
	        /** creating temp pdsc file */ 
	        Response response = PDSCDocumentManager.createPDSCFileFromJDomDocument("pdsc_temp", "PDSC", doc, true , false);
		    File tempPDSC = null;
		   
		    /** recovering temp pdsc file */
		    if(response.getStatus() == PDSCDocumentManager.FILE_CREATED_CORRECTLY) tempPDSC = (File) response.getObject();
		   
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
	    		   			"Selected XSD Document : " + xsd.getName() + "\n" + 
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
	
	
	

	

}
