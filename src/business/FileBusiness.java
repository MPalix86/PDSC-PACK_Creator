package business;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
				
		        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
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
	
	
	//--------------------------------------------------------------------------openFile()
	public static Response openFile(File file) {
		session = new Session();
		ArrayList<File> arrWorkingFile = session.getArrWorkingFile();
		if(arrWorkingFile.contains(file)) {
			Response response = new Response.ResponseBuilder().status(FILE_ALREADY_OPEN).message("file already open").build();
			return response;
		};
        try { 
            String s1 = "", sl = ""; 

            // File reader 
            FileReader fr = new FileReader(file); 

            // Buffered reader 
            BufferedReader br = new BufferedReader(fr); 

            // Initilize sl 
            sl = br.readLine(); 

            // Take the input from the file 
            while ((s1 = br.readLine()) != null) { 
                sl = sl + "\n" + s1; 
            } 
            Response response = new Response.ResponseBuilder().status(FILE_READ_CORRECTLY).message("file read correctly").object(sl).build();
            return response;
        } 
        catch (Exception evt) { 
        	Response response = new Response.ResponseBuilder().status(FILE_READ_EXCEPTION).message("some error during file reading").object(null).build();
            return response;
        } 
        
		
	}
	
	
	public static String getDocumentPreview(Document doc) {
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		String preview = xmlOutputter.outputString(doc);
		if(preview != null) {
			return xmlOutputter.outputString(doc);
		}
		else return "";
	}
	

}
	
