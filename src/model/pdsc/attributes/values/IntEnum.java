package model.pdsc.attributes.values;

import model.XmlEnum;

public class IntEnum  extends XmlEnum{
	
	public IntEnum(int start, int end) {
		for( Integer i = start; i <= end; i++) {
			this.add(i.toString());
		}
	}
	
}
