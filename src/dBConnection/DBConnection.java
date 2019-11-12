package dBConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
	
   /** flag to indicate status of connection */
   private static boolean connesso;    
   
   /** static instance of the class for singleton pattern */
   private static DBConnection instance;
   
   /** connection */
   private static Connection conn;

   /** singleton */
   public static synchronized DBConnection getInstance() {
	   if(instance == null)
		   instance = new DBConnection();
	  
	   if(connesso != true)
	   { 
		   try {
		         Class.forName("org.sqlite.JDBC");
		         conn = DriverManager.getConnection("jdbc:sqlite::resource:dBConnection/packCreator.db");
			} catch (SQLException | ClassNotFoundException e) {
				 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		         System.exit(0);
			}
			System.out.println("Opened database successfully");
			connesso = true;
	   }
	   return instance;
   }
   
   

   /**
    * query on db
    * 
    * @param query query to execute on db
    * @return result of the query
    */
   public ArrayList<TableRecord> query(String query) {
      ArrayList<TableRecord> a = null;
      TableRecord record = null;
      int colonne = 0;
      try {
         Statement stmt = conn.createStatement();   // Creo lo Statement per l'esecuzione della query
         ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
         ResultSetMetaData rsmd = rs.getMetaData();
         colonne = rsmd.getColumnCount();
         a = new ArrayList();
         while(rs.next()) {   // Creo il vettore risultato scorrendo tutto il ResultSet
        	record = new TableRecord(colonne);
    	 	for (int i=1; i<=colonne; i++) {
    	 		if(rs.getString(i) == null)record.put(rsmd.getColumnLabel(i), null);
    	 		record.put(rsmd.getColumnLabel(i), rs.getString(i));
    	 	}
    	 	a.add(record); 
         }
         rs.close();     // Chiudo il ResultSet
         stmt.close();   // Chiudo lo Statement
      } catch (Exception e) { e.printStackTrace(); }

      return a;
   }

   
   /*
 	* execute update query on db;
 	* @param query
    */
   public int update(String query) {
	   int affected_rows=0;
      try {
         Statement stmt = conn.createStatement();
         affected_rows= stmt.executeUpdate(query);
         stmt.close();
      } catch (Exception e) {
         e.printStackTrace();
         affected_rows = 0;
      }
      return affected_rows;
   }
   
   
   
   
   /**
    * execute insert query on db
    * 
    * @param query
    * @return
    */
   public int insert(String query) {
	  int lastid=0;
      try {
         Statement stmt = conn.createStatement();
         stmt.executeUpdate(query);
         ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() AS last_id ");
         lastid = Integer.parseInt(rs.getString("last_id"));
         stmt.close();
      } catch (Exception e) {
         e.printStackTrace();
         lastid = 0;
      }
      return lastid;
   }
   
   
   
   
   
   private static Properties getConnParameters(String xml_file_name){
	   Properties props = new Properties();
	   try{
		   FileInputStream in = 
		      new FileInputStream(xml_file_name);
		   props.loadFromXML(in);
		   in.close();
	   }catch(Exception e){
		   System.out.println(e);
	   }
	   return props;
   }
   
   
   
 
   public static String escape(String s){
	    int length = s.length();
	    int newLength = length;
	    // first check for characters that might
	    // be dangerous and calculate a length
	    // of the string that has escapes.
	    for (int i=0; i<length; i++){
	      char c = s.charAt(i);
	      switch(c){
	        case '\\':
	        case '\"':
	        case '\'':
	        case '\0':{
	          newLength += 1;
	        } break;
	      }
	    }
	    if (length == newLength){
	      // nothing to escape in the string
	      return s;
	    }
	    StringBuffer sb = new StringBuffer(newLength);
	    for (int i=0; i<length; i++){
	      char c = s.charAt(i);
	      switch(c){
	        case '\\':{
	          sb.append("\\\\");
	        } break;
	        case '\"':{
	          sb.append("\\\"");
	        } break;
	        case '\'':{
	          sb.append("\\\'");
	        } break;
	        case '\0':{
	          sb.append("\\0");
	        } break;
	        default: {
	          sb.append(c);
	        }
	      }
	    }
	    return sb.toString();
	  }

   
   
   
   private void close() {
      try {
         conn.close();
         connesso = false;
      } catch (Exception e) { e.printStackTrace(); }
   }

   public boolean isConnected() { return connesso; }   // Ritorna TRUE se la connessione con il Database Ë attiva
}