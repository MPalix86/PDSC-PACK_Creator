package model;

import java.util.ArrayList;


/** 
 * response object. Some function needs to return different data, 
 * of different types. This class allows you to specify more information in a 
 * return statement.
 * 
 * 
 * @author mircopalese
 */
public class Response {
	
	/** simple boolen flag, usually used for a check */
	private boolean flag;
	
	/** message depending on the context */
	private String message;
	
	/** int that representing status depending on the context */
	private int status;
	
	/** object that depending on the context */
	private Object object;
	
	/**array list of object depending on the context */
	private ArrayList<Object> arrObject;
	
	
	/**
	 * Builder pattern
	 * 
	 * @param builder
	 */
	private Response(ResponseBuilder builder) {
		this.flag = builder.flag;
		this.message = builder.message;
		this.status = builder.status;
		this.object = builder.object;
		this.arrObject = builder.arrObject;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public Object getObject() {
		return this.object;
	}
	
	public ArrayList<Object> getArrObject() {
		return this.arrObject;
	}
	
	//  builder pattern
	public static class ResponseBuilder{

		// required parameters
		

		// optional parameters
		private boolean flag;
		private String message;
		private int status;
		private Object object;
		private ArrayList<Object> arrObject;
		
		public ResponseBuilder(){
		}

		public ResponseBuilder message(String message) {
			this.message = message;
			return this;
		}
		
		public ResponseBuilder flag(boolean flag) {
			this.flag = flag;
			return this;
		}

		public ResponseBuilder status(int status) {
			this.status = status;
			return this;
		}
		
		public ResponseBuilder object(Object object) {
			this.object = object;
			return this;
		}
		
		public ResponseBuilder arrObject(ArrayList<Object> arrObject) {
			this.arrObject = arrObject;
			return this;
		}
		
		public Response build(){
			return new Response(this);
		}

	}
	

}

