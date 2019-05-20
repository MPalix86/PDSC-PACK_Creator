package model.pdsc;

import java.util.HashMap;

import model.pdsc.tags.Conditions;
import model.pdsc.tags.Taxonomy;

public class PackageChildEnum extends HashMap<String,Class>{
	
	public PackageChildEnum() {
		put("taxonomy", Taxonomy.class);
		put("conditions", Conditions.class);
	}
}
