package business.utils;

import java.util.ArrayList;

import model.xml.XmlAttribute;
import model.xml.XmlTag;

/**
 * Utils to manage PDSC XmlAttribute objects
 * 
 * NOTE : all functions in someNameUils class do not change the instance of the object
 *
 * @author mircopalese
 */
public class XmlAttributeUtils {
	
	/**
	 * Return arraylist containing not selected attributes
	 * 
	 * @param tag tag on wich search attributes
	 * @return arraylist containing not selected attributes
	 */
	public static ArrayList<XmlAttribute> getNotSelectedAttributes(XmlTag tag){
		ArrayList<XmlAttribute> attrArr = tag.getAttrArr();
		ArrayList<XmlAttribute> selectedAttrArr = tag.getSelectedAttrArr();
		ArrayList<XmlAttribute> notSelectedAttrArr = new ArrayList<XmlAttribute>();;
		
		boolean attrIsSelected = false;
		if (attrArr != null) {
			for(int i = 0; i < attrArr.size(); i++) {
				attrIsSelected = false;
				XmlAttribute attr = attrArr.get(i);
				if(selectedAttrArr != null) {
					
					for(int j = 0; j < selectedAttrArr.size(); j ++) {
						XmlAttribute selectedAttr = selectedAttrArr.get(j);
						if(attr.getName().equals(selectedAttr.getName())) {
							attrIsSelected = true;
						}
					}
				}
				
				if(!attrIsSelected) notSelectedAttrArr.add(new XmlAttribute(attr,tag));
			}
		}
		
		if (notSelectedAttrArr.size() == 0) return null;
		return notSelectedAttrArr;
	}
	
	
	
	
	/**
	 * If exists return model instance of attrName, null otherwise.
	 * 
	 * @param tag tag on wich search attribute
	 * @param attrName desired attribute name
	 * @return model instance of attrName if attr exist, null otherwise.
	 */
	public  static XmlAttribute getModelAttrFromAttrName(XmlTag tag, String attrName) {
		if(tag.getAttrArr() == null) return null;
		for (int i = 0; i < tag.getAttrArr().size(); i ++) {
			XmlAttribute attr = tag.getAttrArr().get(i);
			if(attr.getName().equals(attrName)) return attr;
		}
		return null;
	}

}
