package model.xml;

import java.util.ArrayList;

/**
 * Simple Extension of ArrayList Class for managing attributes or tags that contains 
 * have particular type
 * 
 * @author mircopalese
 */
public class XmlEnum extends ArrayList<String>{
	
	public boolean contains(XmlEnum e) {
		boolean found = false;
		if(e != null) {
			if(this.size() != e.size() ) return false;			
			for (int i = 0; i < e.size(); i++) {
				found = false;
				for (int j = 0; j < this.size(); j++) {
					if (e.get(i).equals(this.get(j))) {
						found = true;
						break;
					}
				}
				if (!found) return found;
			}
		}
		else return false;
		return found;
	}
	
	
	public boolean contains(String e) {
		if(e != null) {		
			for (int j = 0; j < this.size(); j++) {
				if (this.get(j).equals(e)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void removeAll() {
		this.removeRange(0, this.size());
	}
	
	
	
	
}
