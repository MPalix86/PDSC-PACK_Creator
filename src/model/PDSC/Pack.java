package model.PDSC;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.jdom2.Document;
import org.zeroturnaround.zip.ZipUtil;

import business.PDSCDocumentManager;
import business.Session;
import business.utils.CustomUtils;
import business.utils.XmlTagUtils;
import model.Log;
import model.Response;
import model.xml.XmlAttribute;
import model.xml.XmlTag;

/**
 * Abstraction of Pack
 * 
 * @author mircopalese
 */
public class Pack {
	
	/**
	 *  pack's name
	 *  this attribute is auto extracted from PDSC document 
	 */
	private String name;
	
	/* 
	 * pack's vendor 
	 * this attribute is auto extracted from PDSC document 
	 */
	private String vendor;
	
	/** 
	 * pack's highest release version 
	 * this attribute is auto extracted from PDSC document  
	 */
	private String highestReleaseVersion;
	
	/** choosen path to save pack */
	private File choosenPath;
	
	/** 
	 * the root tag of pdsc document used for
	 * extracting all main info and create pack itself 
	 */
	private XmlTag root;
	
	/** PDSCDocument Object containing all main information about PDSC document */
	private PDSCDocument PDSCDoc;
	
	/** log for reporting errors and warnings */
	private ArrayList <Log> lastPackCreatedLogs;
	
	/** 
	 * main path of PACK "name.vendor.version" 
	 * this attribute is auto extracted from PDSC document
	 */
	private File mainPathFile;
	
	
	public static final int PACK_CREATED_CORRECTLY = 0;
	public static final int NO_CHOOSEN_PATH	 = 2;
	
	/** main dir name */
	private static final String MAIN_DIR_NAME = "PACK";
	
	
	public Pack(PDSCDocument PDSCDoc  , File choosenPath ) {
		this.choosenPath = choosenPath;
		lastPackCreatedLogs = new ArrayList<Log>();
		
		/** recovering fields from PDSCDoc */
		this.PDSCDoc = PDSCDoc;
		this.root = PDSCDoc.getRoot();
	}
	
	
	/** Create new pack */
	public Response createPack() throws IOException {
		
		if(choosenPath != null) {
			
			/** Clear log arr */
			lastPackCreatedLogs.clear();
			
			/** preparing breadth first search */
			ArrayList <XmlTag> children = new ArrayList<XmlTag>();
			
			/** preparing BFS */
			children.add(root);
			
			if (checkRequiredFields(root) != 0) return new Response.ResponseBuilder().status(PDSCConstants.REQUIRED_FIELDS_MISSING).build();
			
			/** mainPathFile = userChoosenPath/PDSC/ */
			mainPathFile = generateMainPath(choosenPath);
			String mainPathString = mainPathFile.getAbsolutePath() + "/";
			
			/** completePathFile = mainPathFile/vendor/name/ */
			File completePathFile = new File(mainPathString + this.vendor + "." + this.name + "." + highestReleaseVersion);
			String completePathString = completePathFile.getAbsolutePath() + "/";
			completePathFile.mkdir();
			
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
			String logIniti = "pack " + completePathString + "...\n creation time : " +  dateFormat.format(date) + "\n\n";
			
			/** write log */
			writer.write(logIniti.getBytes());
			
			/** crating new log object */
			Log log = new Log("" , Log.MESSAGE);
			
			/** breadth first search */
			while(!children.isEmpty()) {
				
				/** recovering source file */
				XmlTag child = children.get(0);
				children.remove(0);
				
				/** contains ONLY destination path without file name */
				String destinationPathString = completePathString;
				
				/** contains destination path with file name */
				String destinationFileString = completePathString;
				
				
				/** ======================================================================================================== */
				if(child.getValueType().equals("File")) {
					if(child.getContent() != null) {
						destinationPathString += child.getContent().replace(FilenameUtils.getName(child.getContent()), "");
						destinationFileString += child.getContent();
					}
					
					/** recovering path only without file */
					File destinationPath = new File(destinationPathString);
					
					/** recovering path with file */
					File destinationFile = new File(destinationFileString);
					
					File srcFile = null;
					
					/** if destination file has associated source file */
					if (child.getFile() != null) srcFile = child.getFile() ;	

					/** verify if file is present in opened pack */
					else if (this.PDSCDoc.getSourcePath() != null) {
						
						/** recovering opened pdsc file path */
						File PDSClocation =  this.PDSCDoc.getSourcePath();
						
						/** removing pdsc document name from path */
						String oldPackPathWithouthName =  PDSClocation.toString().replace(PDSClocation.getName(), "");	
						
						/** 
						 * recovering file from opened older pack version 
						 * note that oldPackFile represent the same as srcFile in if statement above
						 */
						if (child.getContent() != null) srcFile = new File(oldPackPathWithouthName + "/" + child.getContent().trim());
						else srcFile = new File(oldPackPathWithouthName);
						
					}
					
					/** if destination directory not exists, create it */
					if(!destinationPath.exists()) {
						
						/** if can't create destination dir */
						if(!destinationPath.mkdirs()) {
							log.setText("DIRECTORY NOT CREATED ----> " + destinationPath.getPath());
							log.setType(Log.ERROR);
						}
						else {
							log.setText("No source file : PATH CREATED WITHOUTH FILE ----> " + destinationPath.getPath() + "\n");
							log.setType(Log.WARNING);
						}
					}
					
					
					if(srcFile != null && srcFile.exists()) {
						/** copy file in destination directory */
						if(destinationFile.exists()) {
							log.setText(" FILE ALREADY INSERTED 	" + srcFile.getPath() + " ----> " + destinationFile.getPath() + "\n");
							log.setType(Log.WARNING);
						}
						else {
							Files.copy(srcFile.toPath(), destinationFile.toPath());
							log.setText(" FILE ADDED CORRECTLY 	" + srcFile.getPath() + " ----> " + destinationFile.getPath() + "\n");
							log.setType(Log.MESSAGE);
						}
					}

					
					/** writing log in log file */
					lastPackCreatedLogs.add(new Log(log));
					writer.write(log.getText().getBytes());
				}
				
				
				/** ======================================================================================================== */				
					
				/** verify attributes */
				if(child.getSelectedAttrArr() != null) {
					for(int i = 0; i < child.getSelectedAttrArr().size(); i++) {
						XmlAttribute attr = child.getSelectedAttrArr().get(i);
						 
						/** find attribute with possible value type = File */
						if(attr.getPossibleValuesType()!= null && attr.getPossibleValuesType().equals("File")) {
							
							System.out.println("attr is : " + attr.getName());
							
							if(attr.getValue() != null) {
								destinationPathString = completePathString + attr.getValue().replace(FilenameUtils.getName(attr.getValue()), "");
								destinationFileString = completePathString + attr.getValue();
							}
							
							/** recovering path only without file */
							File destinationPath = null;
								 destinationPath = new File(destinationPathString);
							
							/** recovering path with file */
							File destinationFile = null;
								 destinationFile = new File(destinationFileString);
							
							System.out.println("destinationPath : " + destinationPathString);
							System.out.println("destinationFile : " + destinationFileString);
							
							File srcFile = null;
							
							/** if destination file has associated source file */
							if (attr.getFile() != null) srcFile = attr.getFile();
							
							/** verify if file is present in opened pack */
							else if (this.PDSCDoc.getSourcePath() != null) {
								
								
								/** recovering opened pdsc file path */
								File PDSClocation =  this.PDSCDoc.getSourcePath();
								
								/** removing pdsc document name from path */
								String oldPackPathWithouthName =  PDSClocation.toString().replace(PDSClocation.getName(), "");	
								
								/** 
								 * recovering file from opened older pack version 
								 * note that oldPackFile represent the same as srcFile in if statement above
								 */
								if (attr.getValue() != null) srcFile = new File(oldPackPathWithouthName + "/" + attr.getValue().trim());
								else srcFile = new File(oldPackPathWithouthName);
								
							}
							
							/** if destination directory not exists, create it */
							if(!destinationPath.exists()) {
								
								/** if can't create destination dir */
								if(!destinationPath.mkdirs()) {
									log.setText("DIRECTORY NOT CREATED ----> " + destinationPath.getPath());
									log.setType(Log.ERROR);
								}
								else {
									log.setText("No source file : PATH CREATED WITHOUTH FILE ----> " + destinationPath.getPath() + "\n");
									log.setType(Log.WARNING);
								}
							}
							
							
							if(srcFile != null && srcFile.exists()) {
								/** copy file in destination directory */
								if(destinationFile.exists()) {
									log.setText(" FILE ALREADY INSERTED 	" + srcFile.getPath() + " ----> " + destinationFile.getPath() + "\n");
									log.setType(Log.WARNING);
								}
								else {
									Files.copy(srcFile.toPath(), destinationFile.toPath());
									log.setText(" FILE ADDED CORRECTLY 	" + srcFile.getPath() + " ----> " + destinationFile.getPath() + "\n");
									log.setType(Log.MESSAGE);
								}
							}
		
							
							/** writing log in log file */
							lastPackCreatedLogs.add(new Log(log));
							writer.write(log.getText().getBytes());
						}
					}
				}

				
				if( child.getSelectedChildrenArr() != null ) {
					child.getSelectedChildrenArr().forEach((c)-> children.add(c));
				}
			}
			
			/** generate PDSC Document */
			Document doc = XmlTagUtils.getJDomDocumentFromXmlTag(Session.getInstance().getSelectedRoot());
			String fileName = vendor + "." + name ;
			Response response = PDSCDocumentManager.createPDSCFileFromJDomDocument(completePathString + fileName, "pdsc", doc, false, true);
			
			/** writing log about PDSC file creation */
			log = new Log ("PDSC file creation status :" + response.getMessage() + "\n\n\n\n\n",Log.MESSAGE);
			writer.write(log.getText().getBytes());
			
			/** creating zip archive */
			File zipFile = new File(completePathFile.toString() + ".pack");
			ZipUtil.pack(completePathFile,zipFile);

			writer.close();
			
			return new Response.ResponseBuilder().status(PACK_CREATED_CORRECTLY).object(lastPackCreatedLogs).build();
			
		}

		return new Response.ResponseBuilder().status(NO_CHOOSEN_PATH).object(lastPackCreatedLogs).build();

	}
	
	
	
	
	
	/**
	 *  verify if name is already taken and in this case generate new name 
	 *  while name is not a new name
	 *  
	 *  @param file 
	 */
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
	
	
	/**
	 * verify presence of required fields;
	 * 
	 * @param root
	 * @return
	 */
	private int checkRequiredFields(XmlTag root) {
		XmlTag vendor = XmlTagUtils.findSelectedChildFromTagName(root, "vendor");
		XmlTag name = XmlTagUtils.findSelectedChildFromTagName(root, "name");
		
		if(getVerifyHighestReleaseVersion(root));
		else return PDSCConstants.REQUIRED_FIELDS_MISSING;
		if (name.getContent() != null) this.name = name.getContent();
		else return PDSCConstants.REQUIRED_FIELDS_MISSING;
		if (vendor.getContent() != null) this.vendor = vendor.getContent();
		else return PDSCConstants.REQUIRED_FIELDS_MISSING;
		
		return 0;
	}
	
	
	
	
	/**
	 * getVerifyHighestReleaseVersion
	 * 
	 * @param root
	 * @return the highest release version String and verify pattern
	 */
	private boolean getVerifyHighestReleaseVersion(XmlTag root) {
		
		int[] highestVersion = {0,0,0} ;
		
		XmlTag releases = XmlTagUtils.findSelectedChildFromTagName(root,"releases");
		if(releases != null) {
			
			ArrayList<XmlTag> children = releases.getSelectedChildrenArr();
			
			if(children != null) {
				for(int i = 0; i < children.size(); i++) {
					XmlTag release = children.get(i);
					XmlAttribute attr = XmlTagUtils.findChildSelectedAttrFromName(release, "version");
					if(attr != null && attr.getValue() != null) {
						Pattern pattern = Pattern.compile("^(\\d+\\.)?(\\d+\\.)?(\\*|\\d+)$");
						Matcher matcher = pattern.matcher(attr.getValue());
						if(matcher.matches()) {
							String result[] = CustomUtils.separateText(attr.getValue(), "\\.");
							for (int j = 0; j < result.length; j++) {
								int n = Integer.parseInt(result[j]);
								if( n > highestVersion[j]) highestVersion[j] = n;
							}
						}
					}
				}
			}
			highestReleaseVersion = highestVersion[0] + "." + highestVersion[1] + "." + highestVersion[2]; 
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	public void setChoosenPath(File path) {
		this.choosenPath = path;
	}
	

	public String getName() {
		return name;
	}

	public String getVendor() {
		return vendor;
	}
	
	public ArrayList<Log> getLastPackCreatedLogs() {
		return this.lastPackCreatedLogs;
	}
	
	public File getMainPathFile() {
		return this.mainPathFile;
	}

}