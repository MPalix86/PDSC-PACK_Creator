package business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import business.utils.XmlTagUtils;
import listeners.UndoManagerListener;
import mao.XmlTagMao;
import model.Response;
import model.UndoManager;
import model.PDSC.PDSCConditionsChecker;
import model.PDSC.PDSCDocument;
import model.xml.XmlTag;
import view.wizardFrame.comp.xmlForm.XmlForm;

/**
 * Functions to manage PDSCDocument objects
 * 
 * NOTE : all functions in someNameManager classes change the instance of the object
 * unlike SomeNameUtils classes
 * 
 * @author mircopalese
 */
public class PDSCDocumentManager {
	
	private static Session session = Session.getInstance();
	public  final static int FILE_ALREADY_EXIST = 0;
	public  final static int FILE_CREATED_CORRECTLY = 1;
	public  final static int IO_EXCEPTION = 2;
	public  final static int FILE_READ_CORRECTLY = 3;
	public  final static int FILE_READ_EXCEPTION = 4;
	public  final static int FILE_ALREADY_OPEN = 5;
	
	
	
	/**
	 * Create a NEW PDSC file on filesystem representing passed document.
	 * 
	 * @param path	path on wich create file
	 * @param extension	file's extension (usually pdsc)
	 * @param doc	JDom document object to transform into a file
	 * @param createOnTempFile set to true if you want to create a temp file instead of a normal file on filesystem. 
	 * 		  Please note that enabling this option, @param path, @param extension and 
	 * 		  @param overrideIfExists will be ignored.
	 * @param overrideIfExists set to true to override document if during creation, document already exists. 
	 * @return	response object containing status of operation, message, and if creation was successful created file.
	 */
	public static Response createPDSCFileFromJDomDocument(String path , String extension , Document doc, boolean createOnTempFile, boolean overrideIfExists) {
		
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
	
	
	
	
	/**
	 * Save PDSC document on fileSystem
	 * 
	 * NOTE : This function is the same as createPDSCFileFromJDomDocument, but with createOnTempFile = false
	 * 		  and overrideIfExists = false.
	 * 
	 * @param path path (with document name and extension of an already existing pdsc file)
	 * @param extension file extension (usually PDSC)
	 * @param doc JDMOM Document to save.
	 * @return response object containing status of operation, message, and if creation was successful created file.
	 */
	public static Response savePDSCDDocumentOnFileSystem(String path , Document doc) {
		return PDSCDocumentManager.createPDSCFileFromJDomDocument(path, "", doc, false ,  true);
		 
	}
	
	
	
	
	/**
	 * Create new PDSCDocument and return created document;
	 * 
	 * @return created PDSC document
	 */
	public static PDSCDocument createNewPDSCDocument() {
		
		XmlTag root = XmlTagMao.getRoot();
		
		TagManager.addTagInParent(XmlTagMao.getCompleteTagFromNameAndParent("vendor", root), XmlTagUtils.findModelChildFromSelectedChildName(root, "vendor"), root, false, false, false, null);
		TagManager.addTagInParent(XmlTagMao.getCompleteTagFromNameAndParent("name", root), XmlTagUtils.findModelChildFromSelectedChildName(root, "name"), root, false, false,false, null);
		TagManager.addTagInParent(XmlTagMao.getCompleteTagFromNameAndParent("description", root), XmlTagUtils.findModelChildFromSelectedChildName(root, "description"), root, false, false,false, null);
		TagManager.addTagInParent(XmlTagMao.getCompleteTagFromNameAndParent("license", root), XmlTagUtils.findModelChildFromSelectedChildName(root, "license"), root, false, false, false, null);
		TagManager.addTagInParent(XmlTagMao.getCompleteTagFromNameAndParent("url", root), XmlTagUtils.findModelChildFromSelectedChildName(root, "url"), root, false, false, false, null);
		XmlTag releases  = XmlTagMao.getCompleteTagFromNameAndParent("releases", root);
		XmlTag release  = XmlTagMao.getCompleteTagFromNameAndParent("release", releases);
		TagManager.addRequiredAttributes(release, false);
		TagManager.addTagInParent(releases, XmlTagUtils.findModelChildFromSelectedChildName(root, "releases"), root, false, false, false, null);
		TagManager.addTagInParent(release, XmlTagUtils.findModelChildFromSelectedChildName(releases, "release"), releases, false, false, false, null);
		
		
		/** creating new XmlForm */
		XmlForm form = new XmlForm(root);
		
		/** creating unndoManager to manage operation of undo/redo on PDSCDocument*/
		UndoManager manager = new UndoManager(root);
		
		/** adding specific listener that is called after every undo or redo operation */
		manager.addUndoAbleEditListener(new UndoManagerListener());
		
		PDSCConditionsChecker checker = new PDSCConditionsChecker(root);
		
		/** creating new PDSCDocument instance */
		PDSCDocument doc = new PDSCDocument(form , (File) null, root, manager,checker);
		
		return doc;
		
	}

}
