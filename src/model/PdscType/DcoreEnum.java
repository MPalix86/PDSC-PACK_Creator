package model.pdscType;

import java.util.ArrayList;

public class DcoreEnum extends ArrayList<String>{
	
	public	DcoreEnum(){
		this.add("");
		
		this.add("SC000");
		this.add("SC300");
		
		this.add("Cortex-M0");
		this.add("Cortex-M0+");
		this.add("Cortex-M1");
		this.add("Cortex-M3");
		this.add("Cortex-M4");
		this.add("Cortex-M7");
		
		this.add("Cortex-R4");
		this.add("Cortex-R5");
		
		this.add("Cortex-A5");
		this.add("Cortex-A7");
		this.add("Cortex-A8");
		this.add("Cortex-A9");
		
		this.add("Cortex-A15");
		this.add("Cortex-A17");
		this.add("Cortex-A53");
		this.add("Cortex-A57");
		this.add("Cortex-A72");
		this.add("other");
	}

	public ArrayList<String> getthis() {
		return this;
	}
	



}
