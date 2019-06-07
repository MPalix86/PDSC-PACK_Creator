package model.pdsc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import business.FileBusiness;
import business.XmlTagBusiness;
import model.XmlAttribute;
import model.XmlTag;

public class Pack {
	private String name;
	private String vendor;
	private HashMap <String,String> destinationSourcePathFilesHashMap;
	
	public static final int PACK_CREATED_CORRECTLY = 0;
	public static final int REQUIRED_FIELDS_MISSING	 = 1;
	
	
	private static final String MAIN_DIR_NAME = "PDSC";
	
	public Pack() {
		if(destinationSourcePathFilesHashMap == null) destinationSourcePathFilesHashMap = new HashMap<String, String> ();
	}
	

	
	
	public int createPack(XmlTag root, File choosenPath) throws IOException {
		
		/** preparing breadth first search */
		ArrayList <XmlTag> children = new ArrayList<XmlTag>();
		children.add(root);
		
		String log = "";
		
		if (checkRequiredFields(root) != 0) return REQUIRED_FIELDS_MISSING;
		
		/** mainPathFile = userChoosenPath/PDSC/ */
		File mainPathFile = generateMainPath(choosenPath);
		String mainPathString = mainPathFile.getAbsolutePath() + "/";
		
		/** completePathFile = mainPathFile/vendor/name/ */
		File completePathFile = new File(mainPathString + this.vendor + "/" + this.name);
		String completePathString = completePathFile.getAbsolutePath() + "/";
		
		/** log file in mainPathFile */
		File logFile = new File(mainPathString + "log.txt");
		if(logFile.exists()) logFile.delete();
		logFile.createNewFile();
		
		/** fileWriter to write log file */
		FileOutputStream writer = new FileOutputStream(logFile , true);
		
		/** date */
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date(); 
		
		/** log file beginning string */
		log = "pack " + completePathString + "...\n creation time : " +  dateFormat.format(date) + "\n\n";
		writer.write(log.getBytes());
		
		/** breadth first search */
		while(!children.isEmpty()) {
			XmlTag child = children.get(0);
			children.remove(0);
			
			/** if find tag file and parent tag == files */
			if(child.getName().equals("file") && child.getParent().getName().equals("files")) {
				
				/** keep attributes */
				for(int i = 0; i < child.getSelectedAttrArr().size(); i++) {
					XmlAttribute attr = child.getSelectedAttrArr().get(i);
					
					/** find attribute name */
					if(attr.getName().equals("name")) {
						
						/** recovering path only without file */
						File destinationPath = new File(completePathString + FileBusiness.pathComponent(attr.getValue()));
						
						/** recovering path with file */
						File destinationFile = new File(completePathString + attr.getValue());
						
						/** if destination file has associated source file */
						if (destinationSourcePathFilesHashMap.containsKey(attr.getValue())) {
							
							/** recovering source file */
							File srcFile = new File( (String) destinationSourcePathFilesHashMap.get(attr.getValue()) ) ;	
							
							/** making destination directories */
							destinationPath.mkdirs();
							
							/** copy file in destination directory */
							Files.copy(srcFile.toPath(), destinationFile.toPath());
							
							/** write log */
							log = srcFile.getPath() + " ----> " + destinationFile.getPath() + " ADDED CORRECTLY \n";
						}
						/** if destination file has't associated source file */
						else {
							destinationPath.mkdirs();
							log = "NO SOURCE FILE, PATH CREATED WITHOUTH FILE ----> " + destinationPath.getPath() + "PATH CREATED WITHOUT ANY FILE \n";
						}
					}
					/** write log */
					writer.write(log.getBytes());
				}
				
			}
			
			if( child.getSelectedChildrenArr() != null ) {
				child.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		
		writer.close();
		return PACK_CREATED_CORRECTLY;
	}

	
	
	private File generateMainPath(File file) {
		
		int i = 1;
		File mainDir = new File(file + "/" + MAIN_DIR_NAME + "/");
		
		while (mainDir.exists()) {
			mainDir = new File(file + "/" + MAIN_DIR_NAME + i + "/");
			i++;
		}
		mainDir.mkdirs();
		return mainDir ;
	}
	
	
	
	private int checkRequiredFields(XmlTag root) {
		
		XmlTag vendor = XmlTagBusiness.findSelectedChildFromTagName(root, "vendor");
		XmlTag name = XmlTagBusiness.findSelectedChildFromTagName(root, "name");
		
		if (name.getContent() != null) this.name = name.getContent();
		else return REQUIRED_FIELDS_MISSING;
		if (vendor.getContent() != null) this.vendor = vendor.getContent();
		
		return 0;
		
	}
	
	
	
	
	
	/**
	 * @return the destinationSourcePathFilesHashMap
	 */
	public HashMap<String, String> getDestinationSourcePathFilesHashMap() {
		return this.destinationSourcePathFilesHashMap;
	}
	
	/**
	 * @param destinationSourcePathFilesHashMap the destinationSourcePathFilesHashMap to set
	 */
	public void setDestinationSourcePathFilesHashMap(HashMap<String, String> destinationSourcePathFilesHashMap) {
		this.destinationSourcePathFilesHashMap = destinationSourcePathFilesHashMap;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}
}
