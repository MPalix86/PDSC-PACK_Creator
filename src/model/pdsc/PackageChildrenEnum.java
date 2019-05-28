package model.pdsc;

import java.util.HashMap;

import model.pdsc.tags.Components;
import model.pdsc.tags.Conditions;
import model.pdsc.tags.Taxonomy;

public class PackageChildrenEnum extends HashMap<String,Class>{
	
	public PackageChildrenEnum() {
		put("taxonomy", Taxonomy.class);
		put("conditions", Conditions.class);
		put("components", Components.class);
	}
}
