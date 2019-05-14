package model;

/**
 * this class defined an abstraction of xml name space
 * 
 * @author Mirco Palese
 */

public class XmlNameSpace {
	
	/** xml name space prefix */
	private String prefix;
	
	/** xml name space url */
	private String url;
	
	
	
	
	/**
	 * Return new instance
	 * 
	 * @param prefix xml name space prefix
	 * @param url xml name space url
	 */
	
	public XmlNameSpace(String prefix, String url) {
		this.prefix = prefix;
		this.url = url;
	}
	
	
	
	
	/**
	 * This constructor return the exact copy of name space passed by parameter in a new instance
	 * 
	 * @param prefix xml name space prefix
	 * @param url xml name space url
	 */
	
	public XmlNameSpace(XmlNameSpace nameSpace) {
		this.prefix = new String(nameSpace.getPrefix());
		this.url = new String (nameSpace.getUrl());
	}




	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}




	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}




	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}




	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	

	

}
