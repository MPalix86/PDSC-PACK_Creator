package mao;

import dao.XmlAttributeDao;
import model.xml.XmlAttribute;
import model.xml.XmlEnum;
import model.xml.XmlTag;

/**
 * 
 * model access object
 * contains query that return tag's model instance
 * 
 * 
 * @author mircopalese
 *
 */
public class XmlAttributeMao {
	
	
	
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
