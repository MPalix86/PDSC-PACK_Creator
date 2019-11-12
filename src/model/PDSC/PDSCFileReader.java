package model.PDSC;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import business.Session;
import business.TagManager;
import business.XmlAttributeManager;
import business.utils.XmlTagUtils;
import dao.XmlAttributeDao;
import dao.XmlTagDao;
import mao.XmlTagMao;
import model.Response;
import model.xml.XmlAttribute;
import model.xml.XmlNameSpace;
import model.xml.XmlTag;

/**
 * Read PDSC file from file systems converting it into dynamic XmlTag taht user can 
 * easily modify.
 * 
 * @author mircopalese
 */
public class PDSCFileReader {
	
	/** File to read */
	private File file;
	
	/** session */
	private static Session session;
	
	public PDSCFileReader(File file) {
		this.file = file;
		session = Session.getInstance();
	}
	
	/** read PDSC File */
	public XmlTag read() {
		if(this.file != null) {
			XmlTag root = readFile(null,null);
			return root;
		}
		return null;
	}
	
	/** read PDSC file converting it from JDOM elements into into XmlTag objects */
	private XmlTag readFile(Element parentEl , XmlTag xmlParent) {

    	SAXBuilder builder = new SAXBuilder();
    	try {
    		/** parentEl = root in this case */
    		if(parentEl  == null && xmlParent == null) {

				Document document = (Document) builder.build(file);
				parentEl = document.getRootElement();
				xmlParent = new XmlTag(parentEl.getName().trim() , true , null , 1 , "all");
				xmlParent.setTagId(1);
				xmlParent.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(xmlParent));
				xmlParent.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(xmlParent , xmlParent));
				xmlParent.setParent(xmlParent);
				TagManager.adjustTagAttributeException(xmlParent, xmlParent);
				addNameSpaces(parentEl, xmlParent);
				addSelectedAttributes(parentEl, xmlParent);
    		}
		
    		else {
				//System.out.println("i'm out of the root\n");
    			XmlTag xmlChild = null;
    			
    			/** recovering parent id that is mandatory for others query */
    			Integer parentId = XmlTagMao.getTagIdFromTagName(xmlParent.getName());
    			
    			if(parentId == null) {
    				//System.out.println("non ho trovato l'id del tag " + xmlParent.getName());
    				parentId = -1;
    			}
    			xmlParent.setTagId(parentId);
    			
    			xmlChild = XmlTagMao.getCompleteTagFromNameAndParent(parentEl.getName().trim(), xmlParent);
    			
				
				/** if find tag in standard with dependencies */
				if(xmlChild != null) {
	    			if(parentEl.getText().trim().length() > 0) xmlChild.setContent(parentEl.getText().trim());
					
					if(xmlChild.getValueType() != null && xmlChild.getValueType().equals("File")){
						File f = null;
						String filePathWithoutName =  file.toString().replace(file.getName(), "");	
						if(xmlChild.getContent() != null) f = new File(filePathWithoutName + "/" + xmlChild.getContent());
						if( f != null && f.exists() ) {
							xmlChild.setFile(f);
						}
					}
					
				}
				
				else {
					
	    			xmlChild = new XmlTag(parentEl.getName() , false , xmlParent , XmlTag.MAX_OCCURENCE_NUMBER, "All");
					
					/** recovering id that is mandatory for others query */
	    			Integer childId = XmlTagMao.getTagIdFromTagName(parentEl.getName());
	    			
	    			
	    			if(childId == null) childId = 0;
	    			else {
	    				xmlChild.setTagId(childId);
	    				xmlChild = XmlTagMao.getCompleteTagFromTagInstance(xmlChild);
	    			}
	    			
	    			if(parentEl.getText().trim().length() > 0)  xmlChild.setContent(parentEl.getText().trim());
				}
				
				
				/** adding child in parent */
				XmlTag modelChild = XmlTagUtils.findModelChildFromSelectedChildName(xmlParent, xmlChild.getName());
				
				if(modelChild != null) TagManager.addTagInParent(xmlChild, modelChild, xmlParent, false, false,false, null);
    			
				else TagManager.addTagInParent(xmlChild, null, xmlParent, false, false, false, null);
				
				addNameSpaces(parentEl, xmlChild);
				addSelectedAttributes(parentEl, xmlChild);
					
				xmlParent = xmlChild;		
    		}
		
			if( parentEl.getChildren() != null) {
				List<Element> children = parentEl .getChildren();
				
				/** iterating trough selected children */
				for(int i = 0; i < children.size(); i++) {
					
					Element child = children.get(i);		
					readFile(child, xmlParent);
				}
			}
    	} catch (IOException io) { } catch (JDOMException jdomex) {}
    	
	  return xmlParent;
	}
	
	
	
	
	/**
	 * Read attribute from JDOM element and add into XmlTag tag
	 * 
	 * @param parentEl jdom element
	 * @param tag tag on which add attributes
	 */
	private void addSelectedAttributes(Element parentEl, XmlTag tag) {
		List<Attribute> attrList = parentEl.getAttributes();
		for(int j = 0; j < attrList.size(); j++) {	
			Attribute attr = attrList.get(j);
			Response r = XmlAttributeManager.verifyAttributeFromName(tag, attr.getName());
			XmlAttribute xmlAttr = (XmlAttribute) r.getObject();
			if(xmlAttr != null) {
				xmlAttr.setValue(attr.getValue());
				TagManager.addAttributeInTag(tag, xmlAttr, false, false, null);			
				
				if(xmlAttr.getPossibleValuesType() != null && xmlAttr.getPossibleValuesType().equals("File")){
					File f = null;
					String filePathWithoutName =  file.toString().replace(file.getName(), "");	
					if(xmlAttr.getValue() != null) f = new File(filePathWithoutName + "/" + xmlAttr.getValue());
					if( f != null && f.exists() ) {
						xmlAttr.setFile(f);
					}
				}

			}
			for(int i = 0; i < attr.getNamespacesIntroduced().size(); i++) {
				Namespace namespace = attr.getNamespace();
				XmlNameSpace xmlNamespace = new XmlNameSpace(namespace.getPrefix() , namespace.getURI());
				xmlAttr.setNameSpace(xmlNamespace);
			}
		}
	}
	
	
	
	/**
	 * Read namespaces from JDOM element and add into XmlTag tag
	 * 
	 * @param parentEl
	 * @param tag
	 */
	private void addNameSpaces(Element parentEl, XmlTag tag) {
		for(int i = 0; i < parentEl.getNamespacesIntroduced().size(); i++) {
			Namespace namespace = parentEl.getNamespacesIntroduced().get(i);
			XmlNameSpace xmlNamespace = new XmlNameSpace(namespace.getPrefix() , namespace.getURI());
			tag.setNameSpace(xmlNamespace);
		}
	}
	
	  
	
	
	
	
	public File getFile() {
		return this.file;
	}

}
