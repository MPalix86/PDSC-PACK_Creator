package dBConnection;

import java.util.HashMap;

public class TableRecord {
	private HashMap<String,String> row;
	
	public TableRecord(int colnumber){
		row= new HashMap<String,String>(colnumber);
	}
	
	public String get(String column_index){
		String value = row.get(column_index);
		if(value!=null) return row.get(column_index);
		return null;
	}
	
	public void put(String key, String value){
		row.put(key, value);
	}
	
	public int getColNumber(){
		return row.size();
	}
	
	
	// Debug
	public String toString(){
		return row.toString();
	}
}
