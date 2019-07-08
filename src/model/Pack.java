package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import org.jdom2.Document;

import business.CustomUtils;
import business.FileBusiness;
import business.Session;
import business.XmlTagBusiness;
import view.wizardFrame.comp.PackStatusUpdateFrame.PackStatusUpdateFrame;

public class Pack extends SwingWorker<Integer,String>{
	private String name;
	private String vendor;
	private String highestReleaseVersion;
	private HashMap <XmlAttribute,String> pathFilesHashMap;
	private PackStatusUpdateFrame frame;
	
	public static final int PACK_CREATED_CORRECTLY = 0;
	public static final int REQUIRED_FIELDS_MISSING	 = 1;
	
	
	private static final String MAIN_DIR_NAME = "PDSC";
	
	public Pack() {
		if(pathFilesHashMap == null) pathFilesHashMap = new HashMap<XmlAttribute, String> ();
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
		log = "pack " + completePathString + "...\n creation time : " +  dateFormat.format(date) + "\n\n";
		
		writer.write(log.getBytes());
		
		/** breadth first search */
		while(!children.isEmpty()) {
			XmlTag child = children.get(0);
			children.remove(0);
			
			/** if find tag file , and parent tag == files */
			if(child.getName().equals("file")) {
				
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
						if (pathFilesHashMap.containsKey(attr)) {
							
							/** recovering source file */
							File srcFile = new File( (String) pathFilesHashMap.get(attr) ) ;	
							
							/** making destination directories */
							destinationPath.mkdirs();
							
							/** copy file in destination directory */
							if(destinationFile.exists()) {
								log = " FILE ALREADY INSERTED 	" + srcFile.getPath() + " ----> " + destinationFile.getPath() + "\n";
								publish(log);
							}
							else {
								Files.copy(srcFile.toPath(), destinationFile.toPath());
								log = " FILE ADDED CORRECTLY 	" + srcFile.getPath() + " ----> " + destinationFile.getPath() + "\n";
								publish(log);
							}
						}
						
						/** verify if file is present in opened pack */
						else if (Session.getInstance().getSelectedPDSCDoc().getSourcePath() != null) {
							
							/** recovering opened pdsc file path */
							File PDSClocation =  Session.getInstance().getSelectedPDSCDoc().getSourcePath();
							
							/** removing pdsc document name from path */
							String oldPackPathWithouthName =  PDSClocation.toString().replace(PDSClocation.getName(), "");	
							
							/** 
							 * recovering file from opened older pack version 
							 * note that oldPackFile represent the same as srcFile in if statement above
							 */
							File oldPackFile = new File(oldPackPathWithouthName + "/" + attr.getValue().trim());
							
							
							/** 
							 * FROM HERE IS THE SAME CODE AS IF ABOVE 
							 * change only srcFile -> oldPackFile
							 * making destination directories 
							 */
							
							/** making destination directories */
							destinationPath.mkdirs();
							
							/** copy file in destination directory */
							if(destinationFile.exists()) {
								log = " FILE ALREADY INSERTED 	" + oldPackFile.getPath() + " ----> " + destinationFile.getPath() + "\n";
								publish(log);
							}
							else {
								Files.copy(oldPackFile.toPath(), destinationFile.toPath());
								log = " FILE ADDED CORRECTLY 	" + oldPackFile.getPath() + " ----> " + destinationFile.getPath() + "\n";
								publish(log);
							}
							
							/** END OF SAME CODE */
						}
						
						
						/** if destination file has't associated source file */
						else {
							destinationPath.mkdirs();
							log = "NO SOURCE FILE 	PATH CREATED WITHOUTH FILE ----> " + destinationPath.getPath() + "\n";
						}
						writer.write(log.getBytes());
					}
				}
				
			}
			
			if( child.getSelectedChildrenArr() != null ) {
				child.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		
		/** generate PDSC Document */
		Document doc = FileBusiness.genratePDSCDocument(Session.getInstance().getSelectedPDSCDoc().getForm().getRoot());
		String fileName = name + "." + vendor ;
		Response response = FileBusiness.createFile(completePathString + fileName, "pdsc", doc, false, true);
		
		log = response.getMessage();
		writer.write(log.getBytes());
		
		writer.close();
		return PACK_CREATED_CORRECTLY;
	}

	
	/**
	 *  create verify if name is already taken and in this case generate new name 
	 *  while name not exists
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
		XmlTag vendor = XmlTagBusiness.findSelectedChildFromTagName(root, "vendor");
		XmlTag name = XmlTagBusiness.findSelectedChildFromTagName(root, "name");
		
		if(getVerifyHighestReleaseVersion(root));
		else return REQUIRED_FIELDS_MISSING;
		if (name.getContent() != null) this.name = name.getContent();
		else return REQUIRED_FIELDS_MISSING;
		if (vendor.getContent() != null) this.vendor = vendor.getContent();
		else return REQUIRED_FIELDS_MISSING;
		
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
		
		XmlTag releases = XmlTagBusiness.findSelectedChildFromTagName(root,"releases");
		if(releases != null) {
			
			ArrayList<XmlTag> children = releases.getSelectedChildrenArr();
			
			if(children != null) {
				for(int i = 0; i < children.size(); i++) {
					XmlTag release = children.get(i);
					XmlAttribute attr = XmlTagBusiness.findChildSelectedAttrFromName(release, "version");
					if(attr != null && attr.getValue() != null) {
						Pattern pattern = Pattern.compile("^(\\d+\\.)?(\\d+\\.)?(\\*|\\d+)$");
						Matcher matcher = pattern.matcher(attr.getValue());
						if(matcher.matches()) {
							String result[] = CustomUtils.separateText(attr.getValue(), "\\.");
							for (int j = 0; j < result.length; j++) {
								System.out.println(result[j]);
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
	
	
	public void addPath(XmlAttribute attr, String sourcePath) {
		if(pathFilesHashMap.containsKey(attr)) pathFilesHashMap.replace(attr, sourcePath);
		else {
			this.pathFilesHashMap.put(attr , sourcePath);
			System.out.println("aggiunto");
		}
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	protected Integer doInBackground() throws Exception {
		return null;
	}
	
	@Override
	protected void process(List<String> infoList) {
		
	}
	
	@Override
	protected void done() { 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the destinationSourcepathFilesHashMap
	 */
	public HashMap<XmlAttribute, String> getPathFilesHashMap() {
		return this.pathFilesHashMap;
	}
	
	/**
	 * @param destinationSourcepathFilesHashMap the destinationSourcepathFilesHashMap to set
	 */
	public void setPathFilesHashMap(HashMap<XmlAttribute, String> pathFilesHashMap) {
		this.pathFilesHashMap = pathFilesHashMap;
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