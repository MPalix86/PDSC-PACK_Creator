package dao;

import dBConnection.DBConnection;

public class PropetyDao {
	private static DBConnection conn;
	private static PropetyDao instance;
	
	/*
	 * SINGLETON
	 */
	
	public static PropetyDao getInstance(){
		if(conn == null)
			conn = DBConnection.getInstance();
		if(instance == null)
			instance = new PropetyDao();
		return instance;
	}
	
	
	
	public 
}
