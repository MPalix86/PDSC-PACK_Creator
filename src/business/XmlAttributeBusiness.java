 package business;

import java.util.ArrayList;

import dao.XmlAttributeDao;
import model.Response;
import model.UndoManager;
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
	
	
	
	public static void setAttributeValue(XmlAttribute attr, String value, boolean registerOperation) {
		if(value != null && !value.trim().contentEquals("")) attr.setValue(value);
		if(registerOperation) UndoManager.registerOperation();
	}
	

	
	
	
	
	public static Response verifyAttributeFromName(XmlTag tag, String attrName) {

		XmlAttribute newAttr = null;
		
		if (attrName.equals(null) || attrName.equals("")) {
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
						//System.out.println("attribute ialready present " + attr.getName());
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
						//System.out.println("attribute is standard attribute for this tag " + attr.getName());
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
				//System.out.println(" PDSC standard does not provide the attribute + " + newAttr.getName() + " for tag " + tag.getName());
				return new Response.ResponseBuilder()
						.flag(true)
						.status(XmlAttribute.IS_GENRAL_PDSC)
						.message(" PDSC standard does not provide the attribute " + newAttr.getName() + " for tag " + tag.getName() )
						.object(newAttr)
						.build();
			}
		}
		
		newAttr =  new XmlAttribute(attrName, tag, "All");
		return 	new Response.ResponseBuilder()
				.flag(true)
				.status(XmlAttribute.IS_NEW)
				.message(" Attribute " + attrName + "is not PDSC standard attribute" )
				.object(newAttr)
				.build();
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
	

	
	public static String getAttributeDescription(XmlAttribute attr) {
		String description = XmlAttributeDao.getInstance().getAttrDescriptionFromAttrName(attr);
		return description;
	}

}
