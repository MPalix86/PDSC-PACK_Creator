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

   private static boolean connesso;    // Flag che indica se la connessione Ë attiva o meno
   private static DBConnection instance; //istanza statica della classe
   private static Connection conn;

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
   
   // Apre la connessione con il Database
   public static boolean open(String server, String nomeDB, String nomeUtente, String pwdUtente) {
	  if(connesso) return true;
      try {
         // Carico il driver JDBC per la connessione con il database MySQL
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://"+server+"/" + nomeDB + "?user=" + nomeUtente + "&password=" + pwdUtente);
         connesso=true;
         System.out.println("Got DB Connection.");
         
      } catch (Exception e) {
    	  e.printStackTrace(); 
     }
      return connesso;
   }
   
   

   /* 
    * Esegue una query di selezione dati sul Database
    * query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
    * colonne: il numero di colonne di cui sar‡ composta la tupla del risultato
    * restituisce un ArrayList contenente tutte le tuple del risultato
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
    *  Esegue una query di aggiornamento sul Database
    *  query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE o DELETE da eseguire
    *  ritorna un il numero di record aggiornati se l'esecuzione Ë andata a buon fine, 0 se c'Ë stata un'eccezione o non sono stati
       aggiornati record
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
   
   /*
    *  Esegue una query di inserimento sul Database
    *  query: una stringa che rappresenta un'istuzione SQL di tipo INSERT da eseguire
    *  ritorna un l'id del record aggiunto se l'esecuzione Ë andata a buon fine, 0 se c'Ë stata un'eccezione o non sono stati
       aggiunti record alla tabella
    */
   public int insert(String query) {
	  int lastid=0;
      try {
         Statement stmt = conn.createStatement();
         stmt.executeUpdate(query);
         ResultSet rs = stmt.executeQuery("SELECT last_insert_id() AS last_id ");
         rs.next();
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
   
   /*
    * Esegue l'escape di una stringa per evitare l'SQL injection
    *  Bisogna usarlo per ogni stringa nelle query SQL
    */
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

   /*
    *  Chiude la connessione con il Database
    */
   private void close() {
      try {
         conn.close();
         connesso = false;
      } catch (Exception e) { e.printStackTrace(); }
   }

   public boolean isConnected() { return connesso; }   // Ritorna TRUE se la connessione con il Database Ë attiva
}