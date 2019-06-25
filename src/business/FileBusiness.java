package business;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.DOMOutputter;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import model.Response;

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
				//format.setIndent("	");
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
	
	//--------------------------------------------------------------------------fileExist()
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
   
   
   
   
   public static void ReadPDSCFile(Element parentEl , File pdscFile) {

	  SAXBuilder builder = new SAXBuilder();
	 

	  try {

		if(pdscFile != null && parentEl  == null) {
			Document document = (Document) builder.build(pdscFile);
			parentEl = document.getRootElement();
		}
		
		
		
		
		if( parentEl.getChildren() != null) {
			List<Element> children = parentEl.getChildren();

			/** iterating trough selected children */
			for(int i = 0; i < children.size(); i++) {		
				Element child = children.get(i);
				String name = child.getName();
				String content = child.getText();
				
				System.out.println("");
				System.out.print(" " +  name + " ");
				List<Attribute> attrList = child.getAttributes();
				
				for(int j = 0; j < attrList.size(); j++) {	
					Attribute attr = attrList.get(j);
					System.out.print(" " + attr.getName() + " = " + attr.getValue());
				}
				
				System.out.print("  " +  content);
				
				
	
				ReadPDSCFile(child,null);
			}
		}

	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  } catch (JDOMException jdomex) {
		System.out.println(jdomex.getMessage());
	  }
	
	}
 
	

	

}
	
