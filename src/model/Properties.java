package model;

public class Properties {
	private static boolean enable_warnigns;
	private static Properties instance ;
	
	private Properties() {
		
	}
	
	
	/*
	 * SINGLETON
	 */
	
	public static Properties getInstance(){
		if(instance == null)
			instance = new Properties();
		return instance;
	}
	

}
