package model.PDSC;

public class PDSCConstants {
	
	/** FILES TYPES DEFINED IN PDSC STANDARD */
	public static int FILE_HEADER = 0;
	public static int FILE_SOURCE = 1;
	public static int FILE_IMAGE = 2;
	public static int FILE_DOC = 3;
	public static int FILE_INCLUDE = 4;
	public static int FILE_OTHER = -1;
	
	/** extensions used to identify files type */
	public static String[] sourceFilesExtension = { "c" , "s" , "cpp" };
	public static String[] imageFilesExtension = { "jpg" , "png" , "gif" , "webp" , "tiff" , "psd" , "raw" , "bmp", "heif" , "indd" };
	public static String[] docFilesExtension = { "pdf" , "chm" };
	public static String[] headerFilesExtension = { "h" };
	
	
	public static final int REQUIRED_FIELDS_MISSING	 = 5;
	

}
