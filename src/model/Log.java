package model;

public class Log {
	
	private int type;
	private String text;
	
	
	public final static int  WARNING = 0;
	public final static int  ERROR = 1;
	public final static int  MESSAGE = 2;
	
	public Log(int type , String text) {
		this.type = type;
		this.text = text;
	}
	
	public Log(Log log) {
		this.type = log.getType();
		if(log.getText() != null) this.text = log.getText();
	}

	public Log(String text ,int type ) {
		this.type = type;
		this.text = text;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
