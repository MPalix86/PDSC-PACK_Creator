package business;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * CustomUtils class of stati fuinctions
 * 
 * @author Mirco Palese
 */
public class CustomUtils {
	private static CustomUtils instance;
	
	
	/** singleton pattern */
	public static synchronized CustomUtils getInstance(){
		if(instance==null)
			instance = new CustomUtils();
		return instance;
	}
	
	
	
	
	
	public static String firstLetterCaps ( String data ){
		String firstLetter = data.substring(0,1).toUpperCase();
		String restLetters = data.substring(1).toLowerCase();
		return firstLetter + restLetters;
	}
	  
	  
	  
	  
	  
	/**
	 * Return scaled image of passed image
	 *   
	 * @param imgSize desired image size
	 * @param boundary image boundary
	 * @param path image path
	 * @return scaled instance of image
	 */
	  
	public static Image getScaledImage(Dimension imgSize, Dimension boundary, String path) {
		BufferedImage img = null; 
		try {
		    img = ImageIO.read(CustomUtils.class.getResource(path));
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;
	
	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }
	
	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }
	    Image dimg = img.getScaledInstance(new_width, new_height,Image.SCALE_SMOOTH);
	    return dimg;
	}
	
	
	
	
	/**
	 * Return scaled image of passed image
	 *   
	 * @param imgWidth image width
	 * @param imgHeight image height
	 * @param boundaryWidth boundary  width
	 * @param boundaryHeight boundary height
	 * @param path image path
	 * @return scaled instance of image
	 */
	  
	public static Image getScaledImage(int imgWidth , int imgHeight, int boundaryWidth , int boundaryHeight, String path) {
		
		Dimension imgSize = new Dimension (imgWidth,imgHeight);
		
		Dimension boundary = new Dimension (boundaryWidth,boundaryHeight);
		
		
		BufferedImage img = null; 
		try {
		    img = ImageIO.read(CustomUtils.class.getResource(path));
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;
	
	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }
	
	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }
	    Image dimg = img.getScaledInstance(new_width, new_height,Image.SCALE_SMOOTH);
	    return dimg;
	}
	
	
	
	
	/**
	 * return the number of lines in a string
	 *
	 * @param str
	 */
	
	public static boolean thereAreMoreLinesInString(String str) {
	    if(str == null || str.isEmpty())
	    {
	        return false;
	    }
	    int lines = 1;
	    int pos = 0;
	    while ((pos = str.indexOf("\n", pos) + 1) != 0) {
	        lines++;
	        if(lines > 1) return true;
	    }
	    return false;
	}
	
	public static int countLinesInString(String str){
		   String[] lines = str.split(System.lineSeparator());
		   return  lines.length;
		}
	
	
	public static String[] separateText (String text , String letter) {
		String[] tokens = text.split(letter);
		return tokens;
	}
	
	public static Object reverseArray(Object[] array) {
	    for (int i = 0; i < array.length / 2; i++) {
	        Object temp = array[i];
	        array[i] = array[array.length - 1 - i];
	        array[array.length - 1 - i] = temp;
	    }
	    return array;
	}
	

}