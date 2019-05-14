package model.pdsc.attributes.values;

import model.XmlEnum;

public class CompilerEnum extends XmlEnum{
	public	CompilerEnum(){
		this.add("");
		
		this.add("GCC");
		this.add("G++");
		
		this.add("ARMCC");
		this.add("IAR");
		this.add("Tasking");
		this.add("GHS");
	}
}
