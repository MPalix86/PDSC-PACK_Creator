package model.pdscType;

import java.util.ArrayList;

public class CompilerEnumType extends ArrayList<String>{
	public	CompilerEnumType(){
		this.add("");
		
		this.add("GCC");
		this.add("G++");
		
		this.add("ARMCC");
		this.add("IAR");
		this.add("Tasking");
		this.add("GHS");
	}
}
