package business;

import java.util.ArrayList;

import dao.XmlAttributeDao;
import model.Response;
import model.XmlAttribute;
import model.XmlEnum;
import model.XmlTag;

public class XmlAttributeBusiness {
	private static XmlAttributeBusiness instance;

	
	
	
	
	/*
	 * SINGLETON
	 */
	public static XmlAttributeBusiness getInstance(){
		if(instance == null)
			instance = new XmlAttributeBusiness();
		return instance;
	}
	
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
	
	
	
	
	public static Response verifyAttributeFromName(XmlTag tag, String attrName) {

		XmlAttribute newAttr = null;
		
		if (!verifyName(attrName)) {
			return new Response.ResponseBuilder()
					.flag(true)
					.status(XmlAttribute.INVALID_NAME)
					.message(" invalid name " )
					.build();
		}
	
		
		if(attrName != null) {
			
			/** check if attribute is already present in tag */
			ArrayList<XmlAttribute> selectedAttrArr = tag.getSelectedAttrArr();
			
			if(selectedAttrArr != null) {
				for (int i = 0 ; i < selectedAttrArr.size(); i++) {
					XmlAttribute attr = selectedAttrArr.get(i);
					if(attr.getName().equals(attrName)) {
						return new Response.ResponseBuilder()
								.flag(true)
								.status(XmlAttribute.ALREADY_PRESENT)
								.message(" attribute already present " )
								.build();
					}
				}
			}
			
			
			/** check if attribute is PDSC standard attribute for this tag */
			ArrayList<XmlAttribute> AttrArr = tag.getAttrArr();
			if(AttrArr != null) {
				for (int i = 0 ; i < AttrArr.size(); i++) {
					XmlAttribute attr = AttrArr.get(i);
					if(attr.getName().equals(attrName)) {
						newAttr = attr;
						return new Response.ResponseBuilder()
								.flag(true)
								.status(XmlAttribute.IS_STANDARD_FOR_TAG)
								.message(" attribute is standard attribute for this tag " )
								.object(newAttr)
								.build();
					}
				}
			}
				
					
			/** check if attribute is PDSC standard attribute in general */
			newAttr = XmlAttributeBusiness.getAttributeWithPossibleValuesFromName(attrName);
			
			if(newAttr != null) {
				return new Response.ResponseBuilder()
						.flag(true)
						.status(XmlAttribute.IS_GENRAL_PDSC)
						.message(" PDSC standard does not provide the attribute + " + newAttr.getName() + " for tag " + tag.getName() )
						.object(newAttr)
						.build();
			}
		}
		
		return 	new Response.ResponseBuilder()
				.flag(true)
				.status(XmlAttribute.IS_NEW)
				.message(" Attribute " + attrName + "is not PDSC standard attribute" )
				.object(newAttr)
				.build();
	}
	
	
	public  static XmlAttribute getModelAttrFromAttrName(XmlTag tag, String attrName) {
		if(tag.getAttrArr() == null) return null;
		for (int i = 0; i < tag.getAttrArr().size(); i ++) {
			XmlAttribute attr = tag.getAttrArr().get(i);
			if(attr.getName().equals(attrName)) return attr;
		}
		return null;
	}
	
	
	public static XmlAttribute getAttributeWithPossibleValuesFromName(String name) {
		XmlAttribute attr = XmlAttributeDao.getInstance().getAttributeFromName(name);
		
		if(attr != null) {
			XmlEnum possibleValues = XmlAttributeDao.getInstance().getAttrPossibleValuesFromAttrName(attr.getName());
			attr.setPossibleValues(possibleValues);
		}
		return attr;
	}
	
	public static String getAttrDescription(XmlAttribute attr) {
		return XmlAttributeDao.getInstance().getAttrDescription(attr);
	}
	
	public static boolean verifyName(String name) {
		
		if (name.equals(null) || name.equals("")) return false;
		return true;
	}
	
	public static String getAttributeDescription(XmlAttribute attr) {
		String description = XmlAttributeDao.getInstance().getAttrDescriptionFromAttrName(attr);
		return description;
	}

}
