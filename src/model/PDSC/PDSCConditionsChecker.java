package model.PDSC;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import model.Response;
import model.xml.XmlAttribute;
import model.xml.XmlEnum;
import model.xml.XmlTag;
import view.comp.AttributeRadioButton;

/**
 * PDSCConditionsChecker class represent an abstraction of some particular rules 
 * on PDSC tags <condition> and <conditions> . 
 * 
 * 1) attribute id on conditions must be unique
 * 2) attributes Cclass Cbundle and Cgroup in <condition> can have only some values 
 * 	  based on tag <component> present in the same document.
 * 3) ...
 * 
 * NOTE : The ideal would be to implement this and other similar function as a dynamical functions
 * 	      (like PDSCTagAttributeException for instance), to provide user the possibility 
 *        to define its own rules. I have no time to do this. 
 * 
 * @author mircopalese
 *
 */
public class PDSCConditionsChecker {
	
	/**
	 * ArrayList containing all tags <components> in root tag
	 * 
	 * NOTE : Following PDSC standard there may be only one tag <components>, 
	 * 		  but PDSC-PACK Creator gives the opportunity to break the PDSC rules. 
	 *        So we need to provide this type of implementation.
	 */
	private ArrayList<XmlTag> componentsArr;
	
	private ArrayList<XmlAttribute> cbundleArr;
	
	private HashMap<XmlAttribute , XmlAttribute> cbundleCclassHashMap;
	
	private HashMap<XmlAttribute , XmlAttribute> cbundleCgroupHashMap;
	
	private XmlEnum originalCgroupEnum;

	public PDSCConditionsChecker(XmlTag root){
		componentsArr = new ArrayList<XmlTag>();
		cbundleArr = new ArrayList<XmlAttribute>();
		cbundleCgroupHashMap = new HashMap<XmlAttribute , XmlAttribute>();
		cbundleCclassHashMap = new HashMap<XmlAttribute , XmlAttribute>();
		findComponents(root);
	}
	

	
	
	
	
	
	private void findComponents(XmlTag tag) {
		if(tag.getSelectedChildrenArr() != null) {
			for (int i = 0; i < tag.getSelectedChildrenArr().size(); i ++) {
				XmlTag child = tag.getSelectedChildrenArr().get(i);
				if(child.getName().equals("components")) {
					componentsArr.add(child);
				}
				else {
					findComponents(child);
				}
			}
		}
	}
	
	
	
	
	/**
	 * 
	 */
	public void checkComponents() {
		cbundleArr.clear();
		cbundleCgroupHashMap.clear();
		cbundleCclassHashMap.clear();
		if(componentsArr != null) {
			componentsArr.forEach(c -> {
				XmlTag components = c;
				if(components.getSelectedChildrenArr() != null) {
					components.getSelectedChildrenArr().forEach( c1 -> {
						if ( c1.getName().equals("bundle")) {
							if(c1.containsAttr("Cbundle")) {
								XmlAttribute cbundle = c1.getSelectedAttrByName("Cbundle");
								if(!cbundleArr.contains(cbundle)) {
									cbundleArr.add(cbundle);
								}
								
								
								if(c1.containsAttr("Cclass")) {
									XmlAttribute cclass = c1.getSelectedAttrByName("Cclass");
									if(!cbundleCclassHashMap.containsKey(cbundle) ) {
										cbundleCclassHashMap.put(cbundle, cclass);
									}
								}
								
								if(c1.getSelectedChildrenArr() != null) c1.getSelectedChildrenArr().forEach(c2 -> {
									if(c2.getName().equals("component")) {
										if(c2.containsAttr("Cgroup")) {
											XmlAttribute cgroup = c2.getSelectedAttrByName("Cgroup");
											if(cgroup.getPossibleValues() != null ) this.originalCgroupEnum = cgroup.getPossibleValues();
											if(!cbundleCgroupHashMap.containsKey(cbundle)) {
												cbundleCgroupHashMap.put(cbundle, cgroup);
											}
										}
									}
								});
								
							}
							
				
						}
					});
				}
			});
		}

		
//		
//		if(cbundleArr != null) {
//			System.out.println("CBUNDLE---");
//			cbundleArr.forEach( a -> {
//				if(a != null)
//					if(a.getValue() != null) System.out.println(a.getValue());
//			});
//		}
//		
//		
//		if (cclassArr != null) {
//			System.out.println("CCLASS---");
//			cclassArr.forEach( a -> {
//				if(a != null)
//					if(a.getValue() != null) System.out.println(a.getValue());
//			});
//		}
//		
//		
//		if(cgroupArr != null) {
//			System.out.println("CGROUP---");
//			cgroupArr.forEach( a -> {
//				if(a != null)
//					if(a.getValue() != null) System.out.println(a.getValue());
//			});
//		}
//		
//		System.out.println("------------------------------------------------");
		
		
	}
	
	
	
	
	/**
	 * set attribute's values based on Cbundle
	 */
	public boolean setAttributesValues(XmlTag tag){
		ArrayList<XmlAttribute> linkedCbundleArr = new ArrayList<XmlAttribute>();
		boolean attrChanged = false;
		if(tag.getParent() != null && tag.getParent().getName().equals("condition")){
			if(tag.containsAttr("Cbundle")) {
				XmlAttribute cbundle = tag.getSelectedAttrByName("Cbundle");
				XmlAttribute linkedCbundle = null;
				if(this.cbundleArr != null) {
					for (int i = 0; i < cbundleArr.size();  i++) {
						XmlAttribute attr = cbundleArr.get(i);
						if(cbundle.getValue()!= null && cbundle.getValue().equals(attr.getValue())) {
							linkedCbundleArr.add(attr);
							linkedCbundle = attr;
						}
					}
				}
				
				if(linkedCbundleArr != null && linkedCbundleArr.size() > 1) {
					Response r = selectDesiredBundleDialog(linkedCbundleArr);
					if(r.getStatus() == 0 ) {
						linkedCbundle = (XmlAttribute) r.getObject();
					}
				}
				
				if(linkedCbundle != null) {
					if(tag.containsAttr("Cclass")) {
						XmlAttribute cclass = tag.getSelectedAttrByName("Cclass");
						XmlAttribute linkedCclass = cbundleCclassHashMap.get(linkedCbundle);
						if(linkedCclass != null) {
							if(linkedCclass.getValue() != null) {
								if(cclass.getValue() == null || !cclass.getValue().equals(linkedCclass.getValue())) {
									if(cclass.getPossibleValues() != null && !cclass.getPossibleValues().contains(linkedCclass.getValue())) {
										cclass.getPossibleValues().add(linkedCclass.getValue());
									}
									cclass.setValue(linkedCclass.getValue());
									attrChanged = true;
								}
							}
						}
					}
					
					if(tag.containsAttr("Cgroup")) {
						XmlAttribute cgroup = tag.getSelectedAttrByName("Cgroup");
						XmlEnum newCgroupEnum = getCgroupEnum(linkedCbundle.getTag());
						if(newCgroupEnum != null) {
							cgroup.getPossibleValues().removeAll();
							newCgroupEnum.forEach(v->cgroup.getPossibleValues().add(v));
							if(newCgroupEnum.size() == 2) cgroup.setValue(newCgroupEnum.get(1));
							else cgroup.setValue("");
							attrChanged = true;
						}
					
					}
				}
	
			}
		}
		return attrChanged;
	}
	
	
	
	
	
	/**
	 * 
	 */
	public XmlEnum getCbundleEnum() {
		XmlEnum e = new XmlEnum();
		cbundleArr.forEach( a -> {
			if(a.getValue() != null && a.getValue().trim() != "") {
				if(!e.contains(a.getValue())) e.add(a.getValue());
			}
		});
		if(e.size() > 0 ) return e;
		return null;
	}
	
	
	
	
	/**
	 * 
	 */
	public XmlEnum getCgroupEnum(XmlTag bundle) {
		XmlEnum cgroupEnum = new XmlEnum();
		if(bundle.getSelectedChildrenArr() != null) {
			for (int i = 0; i < bundle.getSelectedChildrenArr().size(); i++) {
				XmlTag tag = bundle.getSelectedChildrenArr().get(i);
				if(tag.getName().equals("component")) {
					if(tag.containsAttr("Cgroup")) {
						XmlAttribute cgroup = tag.getSelectedAttrByName("Cgroup");
						if(!cgroupEnum.contains(cgroup.getValue())) cgroupEnum.add(cgroup.getValue());
					}
				}
			}
		}
		if(cgroupEnum.size() > 0 ) {
			cgroupEnum.add(0,"");
			return cgroupEnum;
		}
		return null;
	}
	
	
	
	
	
	public void removeComponents(XmlTag tag) {
		if(componentsArr != null && componentsArr.contains(tag)) componentsArr.remove(tag);
	}
	
	public void addComponents(XmlTag tag) {
		if(componentsArr != null && !componentsArr.contains(tag) && tag.getName().equals("components")) this.componentsArr.add(tag);
	}
	
	
	
	public Response selectDesiredBundleDialog(ArrayList<XmlAttribute> attrArr) {

		int size = attrArr.size();
		Object[] options = new Object[size + 5] ;
		options[0] = "Select desired bundle";
		options[1] = new JSeparator();
		XmlAttribute selectedAttribute = null;
		ButtonGroup bgroup = new ButtonGroup();
		ArrayList<AttributeRadioButton> buttonArr = new ArrayList<AttributeRadioButton>();
		if(attrArr != null) {
			String text = "";
			for (int i = 0; i < attrArr.size(); i++) {
				XmlAttribute attr = attrArr.get(i);
				text = attr.getName() + " = " + attr.getValue() + "  | ";
				XmlAttribute cclass = cbundleCclassHashMap.get(attr);
				XmlAttribute cgroup = cbundleCgroupHashMap.get(attr);
				XmlEnum cgroupEnum = getCgroupEnum(attr.getTag());
				if(cclass != null) text += cclass.getName() + " = " + cclass.getValue();
				if(cgroupEnum != null && cgroupEnum.size() <= 1 && cgroup != null) text += cclass.getName() + " = " + cclass.getValue() + "  | " + cgroup.getName() + " = " + cgroup.getValue();
				AttributeRadioButton b = new AttributeRadioButton(attr);
				b.setText(text);
				b.setBackground(Color.WHITE);
				bgroup.add(b);
				options[i + 2] = b;
				buttonArr.add(b);
			}
		}
		Object[] buttons = { "Continue", "cancel"};
		int val = JOptionPane.showOptionDialog (null, options, "Select desired <bundle>" , JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, buttons, buttons); 
		for (int i = 0; i < buttonArr.size(); i++) {
			AttributeRadioButton b1 = buttonArr.get(i);
			if(b1 != null && b1.isSelected()) selectedAttribute = b1.getAttr();
		}
		return new Response.ResponseBuilder().status(val).object(selectedAttribute).build();
	}
	

}
