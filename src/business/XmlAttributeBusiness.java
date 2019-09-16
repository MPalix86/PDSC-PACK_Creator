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
			newAttr = XmlAttributeBusiness.getAttributeWithPossibleValuesFromNameAndTag(attrName , tag);
			
			if(newAttr != null) {
				//System.out.println(" PDSC standard does not provide the attribute " + newAttr.getName() + " for tag " + tag.getName());
				return new Response.ResponseBuilder()
						.flag(true)
						.status(XmlAttribute.IS_GENRAL_PDSC)
						.message(" PDSC standard does not provide the attribute " + newAttr.getName() + " for tag " + tag.getName() )
						.object(newAttr)
						.build();
			}
		}
		
		newAttr =  new XmlAttribute(attrName, tag, "All");
		//System.out.println("attribute is new");
		return 	new Response.ResponseBuilder()
				.flag(true)
				.status(XmlAttribute.IS_NEW)
				.message(" Attribute " + attrName + "is not PDSC standard attribute" )
				.object(newAttr)
				.build();
	}
	

	
	
	public static XmlAttribute getAttributeWithPossibleValuesFromNameAndTag(String name, XmlTag parent) {
		XmlAttribute attr = XmlAttributeDao.getInstance().getAttributeFromName(name);
		
		if(attr != null) {
			XmlEnum possibleValues = XmlAttributeDao.getInstance().getAttrPossibleValuesFromAttrNameAndTag(attr.getName(), parent);
			attr.setPossibleValues(possibleValues);
		}
		return attr;
	}
	

	
	public static String getAttributeDescription(XmlAttribute attr, XmlTag tag) {
		
		/** trying to get description from tags_attribute_relations table for this attr from tag and tag's parent */
		String description = XmlAttributeDao.getInstance().getAttrDescriptionFromAttrTagAndParent(attr , tag, tag.getParent());
		
		/** trying to get description from tags_attribute_relations table only from attr and tag*/
		if(description == null || description.trim().length() == 0) description = XmlAttributeDao.getInstance().getAttrDescriptionFromAttrAndTag(attr , tag);
		
		/** 
		 * if description == null,it possible that attribute for this tag is an exception 
		 * (see PDSCTagAttributeException class).
		 * in this case description is located in tags_attributes_exception table
		 */
		
		if( description == null || description.trim().length() == 0) description = XmlAttributeDao.getInstance().getAttrDescriptionFromTagAttrExceptions(attr, tag);
		return description;
	}

}
