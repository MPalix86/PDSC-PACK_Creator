package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import business.XmlAttributeBusiness;
import business.XmlTagBusiness;
import business.XmlTagUtils;

public class PDSCFileReader {
	private File file;
	
	public PDSCFileReader(File file) {
		this.file = file;
	}
	
	
	public XmlTag read() {
		XmlTag root = readFile(null,null);
		return root;
	}
	
	private XmlTag readFile(Element parentEl , XmlTag xmlParent) {

    	SAXBuilder builder = new SAXBuilder();
    	try {
    		/** parentEl = root in this case */
    		if(parentEl  == null && xmlParent == null) {
			
				
				Document document = (Document) builder.build(file);
				parentEl = document.getRootElement();
				xmlParent = new XmlTag(parentEl.getName() , true , null , 1 , "all");	
				
				addNameSpaces(parentEl, xmlParent);
				addSelectedAttributes(parentEl, xmlParent);
    		}
		
    		else {
				//System.out.println("i'm out of the root\n");
    			XmlTag xmlChild = null;
    			
    			/** recovering parent id that is mandatory for others query */
    			Integer parentId = XmlTagBusiness.getTagIdFromTagName(xmlParent.getName());
    			
    			if(parentId != null) xmlParent.setTagId(parentId);
    			
    			xmlChild = XmlTagBusiness.getCompleteTagFromNameAndParent(parentEl.getName(), xmlParent);
				
				/** if find tag in standard with dependencies */
				if(xmlChild != null) {
	    			
	    			if(parentEl.getText().trim().length() > 0) xmlChild.setContent(parentEl.getText().trim());
					
					xmlParent.addSelectedChild(xmlChild);
				
					xmlChild.setMax(xmlChild.getMax() - 1);
					
					if(xmlChild.getValueType() != null && xmlChild.getValueType().equals("File")){
						File f = null;
						String filePathWithoutName =  file.toString().replace(file.getName(), "");	
						if(xmlChild.getContent() != null) f = new File(filePathWithoutName + "/" + xmlChild.getContent());
						if( f != null && f.exists() ) {
							System.out.println("setto il file per il tag" + xmlChild.getName());
							xmlChild.setFile(f);
						}
					}
					
				}
				
				else {
					
	    			xmlChild = new XmlTag(parentEl.getName() , false , xmlParent , XmlTag.MAX_OCCURENCE_NUMBER, "all");
					
					/** recovering parent id that is mandatory for others query */
	    			Integer childId = XmlTagBusiness.getTagIdFromTagName(parentEl.getName());
	    			
	    			xmlChild.setTagId(childId);
	    			
	    			if(childId != null) xmlChild = XmlTagBusiness.getCompleteTagFromTagInstance(xmlChild);
	    			
	    			if(parentEl.getText().trim().length() > 0)  xmlChild.setContent(parentEl.getText().trim());

					xmlParent.addSelectedChild(xmlChild);
				}
				
				XmlTag modelChild = XmlTagUtils.findModelChildFromSelectedChildName(xmlParent, xmlChild.getName());
				
				if(modelChild != null) {

					modelChild.setMax(modelChild.getMax() - 1);
				}
				
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
			Response r = XmlAttributeBusiness.verifyAttributeFromName(tag, attr.getName());
			XmlAttribute xmlAttr = (XmlAttribute) r.getObject();
			if(xmlAttr != null) {
				xmlAttr.setValue(attr.getValue());
				xmlAttr.setTag(tag);
				tag.addSelectedAttr(xmlAttr);
				
				
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
