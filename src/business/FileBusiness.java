package business;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.FileUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
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
	
	
	
	
	/**
	 * Read PDSC File from file system converting every tag and attribute in XmlTag 
	 * and XmlAttribute element. Form more details see XmlTag and XmlAttrbute class
	 * in model folder
	 * 
	 * CAUTION : parentEl and xmlParent are used only for recursion;
	 *  
	 * 
	 * @param parentEl 	Parameter used only for recursion set it to null;
	 * @param xmlParent Parameter used only for recursion set it to null;
	 * @param pdscFile	file to read
	 * 
	 * @return XmlTag root containing all children
	 */
	
    public static XmlTag ReadPDSCFile(Element parentEl , XmlTag xmlParent , File pdscFile ) {

    	SAXBuilder builder = new SAXBuilder();
    	try {
    		/** parentEl = root in this case */
    		if(pdscFile != null && parentEl  == null && xmlParent == null) {
			
				
				Document document = (Document) builder.build(pdscFile);
				parentEl = document.getRootElement();
				xmlParent = new XmlTag(parentEl.getName() , true , null , 1 , "all");	
				
				for(int i = 0; i < parentEl.getNamespacesIntroduced().size(); i++) {
					Namespace namespace = parentEl.getNamespacesIntroduced().get(i);
					XmlNameSpace xmlNamespace = new XmlNameSpace(namespace.getPrefix() , namespace.getURI());
					xmlParent.setNameSpace(xmlNamespace);
				}
				
				
				List<Attribute> attrList = parentEl.getAttributes();
				for(int j = 0; j < attrList.size(); j++) {	
					Attribute attr = attrList.get(j);
					Response r = XmlAttributeBusiness.verifyAttributeFromName(xmlParent, attr.getName());
					XmlAttribute xmlAttr = (XmlAttribute) r.getObject();
					xmlAttr.setValue(attr.getValue());
					xmlAttr.setTag(xmlParent);
					if(xmlAttr != null) xmlParent.addSelectedAttr(xmlAttr);
					
					if(!attr.getNamespacePrefix().equals("")) {
						Namespace namespace = attr.getNamespace();
						XmlNameSpace xmlNamespace = new XmlNameSpace(attr.getNamespacePrefix() , namespace.getURI());
						xmlAttr.setNameSpace(xmlNamespace);
					}
				}
			
    		}
		
    		else {
				//System.out.println("i'm out of the root\n");
    			XmlTag xmlChild = new XmlTag();
    			
    			/** recovering parent id that is mandatory for others query */
    			Integer parentId = XmlTagBusiness.getTagIdFromTagName(xmlParent.getName());
    			
    			if(parentId != null) xmlParent.setTagId(parentId);
    				
    			xmlChild = XmlTagBusiness.getCompleteTagFromNameAndParent(parentEl.getName(), xmlParent);
				
				/** if find tag in standard with dependencies */
				if(xmlChild != null) {
	    			
	    			if(parentEl.getText().trim().length() > 0) xmlChild.setContent(parentEl.getText().trim());
					
					xmlParent.addSelectedChild(xmlChild);
				
					xmlChild.setMax(xmlChild.getMax() - 1);
					
				}
				
				else {
					
	    			xmlChild = new XmlTag(parentEl.getName() , false , xmlParent , XmlTag.MAX_OCCURENCE_NUMBER, "all");
					
					/** recovering parent id that is mandatory for others query */
	    			Integer childId = XmlTagBusiness.getTagIdFromTagName(parentEl.getName());
	    			
	    			xmlChild.setTagId(childId);
	    			
	    			if(childId != null) xmlChild = XmlTagBusiness.getCompleteTagFromTagInstance(xmlChild);
	    			
	    			if(parentEl.getText().trim().length() > 0)  xmlChild.setContent(parentEl.getText().trim());

					xmlParent.addSelectedChild(xmlChild);
					}
				
				XmlTag modelChild = XmlTagUtils.findModelChildFromSelectedChildName(xmlParent, xmlChild.getName());
				
				if(modelChild != null) {

					modelChild.setMax(modelChild.getMax() - 1);
				}
					
					for(int i = 0; i < parentEl.getNamespacesIntroduced().size(); i++) {
						Namespace namespace = parentEl.getNamespace();
						XmlNameSpace xmlNamespace = new XmlNameSpace(namespace.getPrefix() , namespace.getURI());
						xmlParent.setNameSpace(xmlNamespace);
					}
					
					
					List<Attribute> attrList = parentEl.getAttributes();
					for(int j = 0; j < attrList.size(); j++) {	
						Attribute attr = attrList.get(j);
						Response r = XmlAttributeBusiness.verifyAttributeFromName(xmlParent, attr.getName());
						XmlAttribute xmlAttr = (XmlAttribute) r.getObject();
						if(xmlAttr != null) {
							xmlAttr.setValue(attr.getValue());
							xmlAttr.setTag(xmlChild);
							xmlChild.addSelectedAttr(xmlAttr);
						}
						
						for(int i = 0; i < attr.getNamespacesIntroduced().size(); i++) {
							Namespace namespace = attr.getNamespace();
							XmlNameSpace xmlNamespace = new XmlNameSpace(namespace.getPrefix() , namespace.getURI());
							xmlAttr.setNameSpace(xmlNamespace);
						}
					}
					
					xmlParent = xmlChild;		
    			}
		
				if( parentEl.getChildren() != null) {
					List<Element> children = parentEl .getChildren();
					
					/** iterating trough selected children */
					for(int i = 0; i < children.size(); i++) {
						
						Element child = children.get(i);		
						ReadPDSCFile(child, xmlParent , null);
					}
				}
	  } catch (IOException io) {

	  } catch (JDOMException jdomex) {

	  }
	 
	  return xmlParent;
	
	}
    
    
    
    
    /**
     * Zip Directory or single file
     * 
     * @param fileToZip
     * @param fileName
     * @param zipOut
     * @param extension
     * @throws IOException
     */
    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
 
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileToZip.toString()));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileToZip.toString() + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
   
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
   
   
   
   
	

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
	
