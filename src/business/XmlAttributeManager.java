 package business;

import java.util.ArrayList;

import listeners.AttributeManagerListener;
import mao.XmlAttributeMao;
import model.Response;
import model.UndoManager;
import model.interfaces.events.AttributeEvent;
import model.xml.XmlAttribute;
import model.xml.XmlTag;

/**
 * this class is a collection of static methods to manage operations on XML tattributes 
 * assuming that the tags are part of a certain standard and therefore must comply 
 * with them (In This case PDSC Standard). So this class has the task to perform 
 * operations on tags without braking standard's rules.
 * 
 * for every performed operations, a static listener is notified to manage adjunctive 
 * situations.
 * 
 * NOTE : all functions in someNameManager classes change the instance of the object
 * unlike someNameUtils classes
 * 
 * @author mircopalese
 */
public class XmlAttributeManager {
	private static XmlAttributeManager instance;
	private static AttributeManagerListener listener = new AttributeManagerListener();

	
	
	/*
	 * SINGLETON
	 */
	public static XmlAttributeManager getInstance(){
		if(instance == null)
			instance = new XmlAttributeManager();
		return instance;
	}
	
	
	
	public static void setAttributeValue(XmlAttribute attr, String value, boolean registerOperation) {
		if(value != null && !value.trim().contentEquals("")) attr.setValue(value);
		if(registerOperation) UndoManager.registerOperation();
		listener.valueChanged(new AttributeEvent(attr));
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
			newAttr = XmlAttributeMao.getAttributeWithPossibleValuesFromNameAndTag(attrName , tag);
			
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
		System.out.println("attribute is new");
		return 	new Response.ResponseBuilder()
				.flag(true)
				.status(XmlAttribute.IS_NEW)
				.message(" Attribute " + attrName + "is not PDSC standard attribute" )
				.object(newAttr)
				.build();
	}
	



}
