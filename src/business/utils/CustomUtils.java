package business.utils;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.UIManager;


/**
 * CustomUtils class of static heterogeneous functions
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
	
	
	
	
	
	public static String getlongestString(String[] array) {
		if (array != null) {
			String string =  array[0];
			for(int i=1; i< array.length ; i++) {
			    if(array[i].length() > string.length()) {
			        string = array[i];
			    }
			}
			return string;
		}
		return null;
	}
	
	
	
	
	
	public static int countNewLinesInString(String str){
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
	
	
	
	
	
	public static boolean invertBooleanValue(boolean val) {
		if(val == true) return false;
		else	return true;
	}
	
	
	
	
	
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 
	
	
	
	
	
    public static String getAlphaNumericString(int n) { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    
    
    
    
    
    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    
    
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    
    
    
    
    public static boolean arrayContains(String[] arr, String targetValue) {
    	for(String s: arr){
    		if(s.equals(targetValue))
    			return true;
    	}
    	return false;
    }
	
    
    
    

	
	

}
