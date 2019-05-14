package model.pdsc.attributes.values;

import model.XmlEnum;

public class DfpuEnum extends XmlEnum{
	
	
	public DfpuEnum() {
		this.add("");
		
		this.add("NO_FPU");
		this.add("FPU");
		this.add("DP_FPU");
		this.add("SP_FPU");
	}


}
