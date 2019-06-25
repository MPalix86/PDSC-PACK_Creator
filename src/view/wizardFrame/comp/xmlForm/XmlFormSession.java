package view.wizardFrame.comp.xmlForm;

import java.util.ArrayList;

public class XmlFormSession {
	
	private static XmlFormSession instance;
	
	private ArrayList<XmlForm> FormArray;
	
	private XmlForm selectedForm;
	
	/**
	 * Sinngleton
	 * 
	 * @return this
	 */
	public static synchronized XmlFormSession getInstance(){
		if(instance==null)
			instance = new XmlFormSession();
		return instance;
	}

}
