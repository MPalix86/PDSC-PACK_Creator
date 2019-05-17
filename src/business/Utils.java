package business;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Utils class of stati fuinctions
 * 
 * @author Mirco Palese
 */
public class Utils {
	private static Utils instance;
	
	
	/** singleton pattern */
	public static synchronized Utils getInstance(){
		if(instance==null)
			instance = new Utils();
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
		    img = ImageIO.read(Utils.class.getResource(path));
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
		    img = ImageIO.read(Utils.class.getResource(path));
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

}
