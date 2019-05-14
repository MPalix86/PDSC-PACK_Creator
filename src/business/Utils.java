package business;

public class Utils {
	private static Utils instance;
	
	//--------------------------------------------------------------------------getInstance() 
	public static synchronized Utils getInstance(){
		if(instance==null)
			instance = new Utils();
		return instance;
	}
	
	//--------------------------------------------------------------------------firstLetterCaps() 
	  public static String firstLetterCaps ( String data )
	  {
	      String firstLetter = data.substring(0,1).toUpperCase();
	      String restLetters = data.substring(1).toLowerCase();
	      return firstLetter + restLetters;
	  }
	  
		//--------------------------------------------------------------------------firstLetterCaps() 
	  public static void print( String message )
	  {
		  System.out.println(message);
	  }
	  
	 
}
