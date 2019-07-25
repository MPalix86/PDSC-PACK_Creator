package business;

import java.util.ArrayList;

import dao.XmlAttributeDao;
import dao.XmlTagDao;
import model.PDSCTagAttributeException;
import model.Response;
import model.XmlAttribute;
import model.XmlTag;
import model.XmlTagConstants;
import view.comp.utils.DialogUtils;

public class XmlTagBusiness {
	private static XmlTagBusiness instance;
	

	
	/*
	 * SINGLETON
	 */
	public static XmlTagBusiness getInstance(){
		if(instance == null)
			instance = new XmlTagBusiness();
		return instance;
	}
	
	
	
	/**
	 * set content in tag 
	 * 
	 * @param tag tag on which set the content
	 * @param content to add
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 */
	public static void setTagContent(XmlTag tag , String content , boolean registerOperation) {
		tag.setContent(content);
		if(registerOperation) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
	}
	
	
	
	/**
	 * Add attribute in tag
	 * 
	 * @param tag tag on which add attribute
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 */
	public static void addAttributeInTag(XmlTag tag, XmlAttribute attr, boolean registerOperation) {
		tag.addSelectedAttr(attr);
		if(registerOperation) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
	}
	
	
	
	
	/**
	 * Verify tagAttribute exception, adding or removing attribute based on exception
	 * 
	 * example : Tag  " component " have attribute " Cvendor ", but 
	 * 			 must not be specified for a component within a bundle.
	 * 
	 * for more info uncomment all System.out.println();
	 * 
	 * @param tag tag on which verify exceptions
	 * @return void
	 */
	
	public static void adjustTagAttributeException(XmlTag tag) {
		
		//System.out.println(" verifying consistency exceptions for tag " + tag.getName());
		
		/** verify consistency of all required parameters*/
		if(tag.getTagAttributeExceptionArr() == null || tag.getTagAttributeExceptionArr().size() <= 0) return ;
		if(tag.getParent() == null) return ;
		if(tag.getAttrArr() == null || tag.getAttrArr().size() <= 0) return ;
		
		/** for each exception */
		for (int j = 0; j < tag.getTagAttributeExceptionArr().size(); j++) {
			
			/** flag to know if attribute is present in tag */
			boolean foundException = false;
			
			/** recovering exception */
			PDSCTagAttributeException exception = tag.getTagAttributeExceptionArr().get(j);
			
			/** if tags aren't the same return */
			if(!tag.getName().equals(exception.getTag().getName())) return;
				
			/** if parents aren't the same return */
			if(!tag.getParent().getName().equals(exception.getParent().getName())) return ;
			
			//System.out.println("name and parent control passed: verifying if exception for attr : " + exception.getAttribute().getName() + " is present in attrArr ");
			
			/** for each attr */
			for (int i = 0; i < tag.getAttrArr().size(); i ++) {

				/** recovering attribute */
				XmlAttribute attr = tag.getAttrArr().get(i);
				
				/** if attributes coincides */
				if(attr.getName().equals(exception.getAttribute().getName())) {
					
					//System.out.println("ok exception is present in attrArr");
					
					/** if this tag contains attribute change flag to true */
					foundException = true;
					
					/**
					 *  verify exception.
					 * 
					 *  0 = tag must not contains attribute
					 *  1 = tag must contains attribute
					 */
					if(exception.getException() == 0) {
						
						//System.out.println("removing attr");
						
						tag.getAttrArr().remove(attr);
						
						/** removing attribute from selectedAttrArr */
						if(tag.getSelectedAttrArr() != null && tag.getSelectedAttrArr().size() > 0) {
							
							/** for each attr in selectedAttrArr */
							for (int h = 0; h < tag.getSelectedAttrArr().size(); h ++) {
								
								/** recovering selectedAttr */
								XmlAttribute selectedAttr = tag.getSelectedAttrArr().get(h);
								
								/** if selectedAttr coincides with exceptioAttr, remove it*/
								if(selectedAttr.getName().equals(exception.getAttribute().getName())) {
									
									tag.getSelectedAttrArr().remove(selectedAttr);
								}
							}
						}
					}
				}
			}
			
			/** if attrException is not present in tag */
			if(!foundException) {
				/**
				 *  verify exception.
				 * 
				 *  0 = tag must not contains attribute
				 *  1 = tag must contains attribute
				 */
				if(exception.getException() == 1) {
					
					/** adding attribute */
					tag.getAttrArr().add(new XmlAttribute(exception.getAttribute() , tag));
				}
			}
			
		}
	}
	
	
	
	/**
	 * Add passed tag in passed parent
	 * 
	 * @param tag tag to add
	 * @param parent parent in which adding tag
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 * @param enableUserControls if you want the user to be alerted if there are choices to be made
	 */
	public static void addTagInParent(XmlTag modelTag, XmlTag parent, boolean registerOperation, boolean enableUserControls) {
		boolean choice = true;
		
		if(enableUserControls) {
			if(modelTag.getMax() > 0 ) {
				String message = "Followind PDSC standard, maxcimum number reached for tag < " + modelTag.getName() + " > \n Do you want to continue ? ";
				choice = DialogUtils.yesNoWarningMessage(message);
			}
		}
		
		if(choice) {
			XmlTag newtag = new XmlTag(modelTag,parent);
			parent.addSelectedChild(newtag);
			
			/** adjust model tag */
			modelTag.setMax(modelTag.getMax() -1);
		}
		
//		if(registerOperation) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
	}
	
	
	
	
	
	
	/** 
	 * dependencies research on xml tag (graph).
	 * uncomment all Sytem.out.println() for more info.
	 * 
	 * @param parent tag on which put required tags
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 * @return void
	 */
	
	public static void addRequiredChildren(XmlTag parent, boolean registerOperation) {
		
		XmlTag parentCopy = new XmlTag(parent,parent.getParent());
		
		ArrayList <XmlTag> children = new ArrayList<XmlTag>();
		children.add(parent);
		while(!children.isEmpty()) {
			XmlTag element = children.get(0);
			//System.out.println("analyzing " +  element.getName());
			children.remove(element);
			ArrayList <XmlTag> requiredChildren = new ArrayList<XmlTag>(XmlTagUtils.getRequiredChildren(element));
			if(!requiredChildren.isEmpty()) {
				//System.out.println(element.getName() + " has dependencies");
				for(int i = 0; i < requiredChildren.size(); i++) {
					XmlTag requiredChild = requiredChildren.get(i);
					//System.out.println("check if there is the denpendency :  "  + requiredChild.getName());
					boolean found = false;
					if(element.getSelectedChildrenArr() != null) {
						//System.out.println("let's see if " +  requiredChild.getName() + " is present in selected children of " +element.getName());
						for(int j = 0; j < element.getSelectedChildrenArr().size(); j++) {
							XmlTag selectedChild = element.getSelectedChildrenArr().get(j);
							//System.out.println("analizyng selected child " + selectedChild.getName());
							
							if(requiredChild.getName().equals(selectedChild.getName())) found = true;
							if( found ) /*System.out.println(selectedChild .getName() + " found ");*/break;
						}
						if(!found) {
							/** if max child number is > 0, add child */
							addRequiredAttributes(requiredChild , false);
							if(requiredChild.getMax() > 0 ) XmlTagBusiness.addTagInParent(requiredChild, requiredChild.getParent(), false, false);
						}
					}
					else {
						/** if max child number is > 0, add child */
						addRequiredAttributes(requiredChild , false);
						if(requiredChild.getMax() > 0 ) XmlTagBusiness.addTagInParent(requiredChild, requiredChild.getParent(), false, false);	
					}

				}
			}else {/*System.out.println(element.getName() + " has no dependencies");*/}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		if(registerOperation) {
			/** register operation only if changes was made */
			if(!parentCopy.equals(parent)) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		}
	}
	
	
	
	
	/**
	 * Add required attribute for passed tag
	 * 
	 * @param tag
	 */
	public static void addRequiredAttributes(XmlTag tag , boolean registerOperation) {
		if(tag.getAttrArr() == null) return;
		for(int i = 0; i < tag.getAttrArr().size(); i++) {
			 XmlAttribute attr = tag.getAttrArr().get(i);
			 if(attr.isRequired() && !XmlTagUtils.tagHasAttribute(tag, attr))
				 tag.addSelectedAttr(new XmlAttribute(attr, tag));
		}
		
		if(registerOperation) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		
	}
	
	
	
	
	
	/**
	 * make "copiesNumber" copy for selected tag in parent adjusting maximum 
	 * in relative model from parent
	 * 
	 * @param tag to clone
	 * @param copiesNumber number of copies
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 * @param enableUserControls if you want the user to be alerted if there are choices to be made
	 * @return void
	 */
	public static void cloneTag(XmlTag tag, int copiesNumber , boolean registerOperation , boolean enableUserControls) {
		XmlTag parent = null;
		XmlTag clone = null;
		boolean choice = true;

		
		if(tag.getParent() != null && copiesNumber > 0) {
			
			parent = tag.getParent();
			XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(parent, tag.getName());
			
			/** make controls */
			if(enableUserControls) {
				
				/** if max child number is > copiesNumber, add selected number of clones */
				if( (modelTag != null && modelTag.getMax() >= copiesNumber) || modelTag == null) choice = true;
				
				else {
					if(modelTag != null && modelTag.getMax() == 0) 	choice = DialogUtils.yesNoWarningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
																						+ " Maximum number of children reached for tag  " + tag.getName() + 
																						" <br> do you want to continue ?</span></p></html>");
					else choice = DialogUtils.yesNoWarningMessage("You can make at least : " + tag.getMax() + " copy for tag <" + tag.getName() + " > \n do you want to continue ?");
				}
			}
			
			/** Cone tag */
			if(choice) {
				for (int i = 0; i < copiesNumber ; i++) {
					clone = new XmlTag(tag,tag.getParent());
					parent.addSelectedChild(clone);	
				}
			}
			
			/** adjust model tag and register operation */
			if(modelTag != null) modelTag.setMax(modelTag.getMax() - copiesNumber);
			if(registerOperation) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
		}
	}
	
	
	
	
	
	/**
	 * Remove child from selectedChildAttr adjusting maximum in relative model from parent
	 * 
	 * @param child tag to remove
	 * @param parent tag in which is contained child
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 * @param enableUserControls if you want the user to be alerted if there are choices to be made
	 * @return removed tag if tag was successfully removed, null otherwise
	 */
	public static XmlTag removeSelectedChildFromParent(XmlTag child, XmlTag parent , boolean registerOperation, boolean enableUserControls) {
		boolean response = true;
		
		if(enableUserControls) {
			int tagOccurrenceInParent = XmlTagUtils.findChildOccurrenceNumber(parent,child.getName());
			if(child.isRequired() && tagOccurrenceInParent <= 1) {
				response = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n < " + parent.getName() + "> must contain at least one tag < " + child.getName() + " > \n Do you want to continue ?");
			}
		}

		if(response) {
			XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(parent, child.getName());
			if(modelTag != null) modelTag.setMax(modelTag.getMax() + 1);
			parent.removeSelectedChild(child);
			if(registerOperation) Session.getInstance().getSelectedPDSCDoc().getUndoManager().addState();
			if(!parent.getSelectedChildrenArr().contains(child)) return child;
		}
		
		return null;
		
	}
	
	
	
	
	
	/**
	 * starting from tagName verify if tag is PDSC standard tag for this parent
	 * or f tag is PDSC standard tag in general or if tag is new tag not present in PDSC standard
	 * 
	 * @param childName
	 * @param parent
	 * @return 
	 */
	
	public static Response verifyTagFromName(String childName , XmlTag parent) {
		
		if(childName != null) {
			XmlTag child;
			if(parent != null) {
				/** check if tag is PDSC standard tag for this parent */
				XmlTag modelChild = XmlTagUtils.findModelChildFromSelectedChildName(parent, childName);
				
				if(modelChild != null) {
					child = new XmlTag(modelChild , parent);
					XmlTagBusiness.addRequiredAttr(child);
					if(modelChild.getMax() <= 0) {
						return new Response.ResponseBuilder()
								.flag(true)
								.status(XmlTagConstants.MAX_REACHED)
								.message(" tag is standard attribute for this parent, but max is reached " )
								.object(child)
								.build();
					}
					
					else {
						return new Response.ResponseBuilder()
								.flag(true)
								.status(XmlTagConstants.IS_STANDARD_FOR_TAG)
								.message(" tag is standard attribute for this parent " )
								.object(child)
								.build();
					}
				}
				
			}
			
					
			/** check if tag is PDSC standard tag in general */
			Integer childId = XmlTagBusiness.getTagIdFromTagName(childName);
			
			if(childId != null) {
				child = XmlTagBusiness.getCompleteTagFromTagId(childId);
				if(child != null) {
					child.setParent(parent);
					XmlTagBusiness.addRequiredAttr(child);
					return new Response.ResponseBuilder()
							.flag(true)
							.status(XmlTagConstants.IS_GENERAL_PDSC)
							.object(child)
							.build();
				}
			}
		}
		
		return 	new Response.ResponseBuilder()
				.flag(true)
				.status(XmlTagConstants.IS_NEW_TAG)
				.message(" tag " + childName + "is not PDSC standard tag" )
				.object(new XmlTag(childName , false , parent , XmlTag.MAX_OCCURENCE_NUMBER , "all"))
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * return root tag, the only one that hava parent == null
	 * 
	 * @return root tag
	 */
	
	public static XmlTag getRoot() {
		XmlTag root = XmlTagDao.getInstance().getRootTag();
		root.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(root));
		return root; 
		
	}
	
	
	
	
	/** 
	 * return tag's not required children;
	 * 
	 * @param parent parent tag
	 * @return tag's not required children or null
	 */
	
	public static  ArrayList<XmlTag> getNotRequiredChildren(XmlTag parent) {
		return XmlTagDao.getInstance().getNotRequiredChildrenFromTag(parent);
	}
	
	
	
	
	/**
	 * this function assume that tag have name , required and other filed already set.
	 * this function simply add attrArr , possibleVlaues
	 * 
	 * @param name		tag's name
	 * @param parent	parent tag 
	 * @return	tag found or null
	 */
	
	public static XmlTag getCompleteTagFromNameAndParent(String name , XmlTag parent) {
		XmlTag tag = XmlTagDao.getInstance().getTagFromNameAndParent(name, parent);
		if(tag != null) return getCompleteTagFromTagInstance(tag);
		return null;
	}
	
	
	
	
	

	
	
	
	
	/**
	 * this function assume that tag have name , required and other filed already set.
	 * this function simply add attrArr , possibleVlaues
	 * 
	 * @param tag
	 * @return passed tag with attrArr, possibleValues and TagAttributeExceptionArr set
	 */
	
	public static XmlTag getCompleteTagFromTagInstance(XmlTag tag) {
		
		tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
		tag.setPossibleValues(XmlTagDao.getInstance().getTagPossibleValues(tag));
		
		/** verifying tag attribute exception */
		tag.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(tag));
		if(tag.getTagAttributeExceptionArr() != null ) adjustTagAttributeException(tag);
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** recursion */
				getCompleteTagFromTagInstance(child);
			}
		}
		
		return tag;
	}
	
	
	
	
	/**
	 * this function assume that tag have name , required and other filed already set.
	 * this function simply add attrArr and possibleVlaues
	 * 
	 * @param tag
	 * @return passed tag with attrArr and possibleValues set
	 */
	
	public static XmlTag getCompleteTagFromTagId(Integer id) {
		
		XmlTag tag = XmlTagDao.getInstance().getTagFromTagId(id);
		
		tag.setTagId(id);

		tag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(tag));
		tag.setPossibleValues(XmlTagDao.getInstance().getTagPossibleValues(tag));
		
		tag.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(tag));
		if(tag.getTagAttributeExceptionArr() != null ) adjustTagAttributeException(tag);
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** recursion */
				getCompleteTagFromTagId(child.getTagId());
			}
		}
		return tag;
	}
	
	
	
	
	/**
	 * return tag id of tagName passed
	 * 
	 * @param name tag's name
	 * @return tag id of tagName passed
	 */
	
	public static Integer getTagIdFromTagName(String name) {
		return XmlTagDao.getInstance().getTagIdFromTagName(name);
	}
	
	
	
	
	/**
	 * add required attributes inside passed tag
	 * 
	 * @param tag 
	 */
	
	public static void addRequiredAttr(XmlTag tag) {
		if(tag.getAttrArr() != null) {
			for ( int i = 0; i < tag.getAttrArr().size(); i++) {
				XmlAttribute attr = tag.getAttrArr().get(i);
				if(attr.isRequired()) tag.addSelectedAttr(attr);
			}
		}
	}
	
	
	
	
	/**
	 * return tag description
	 */
	public static String getTagDescription(XmlTag tag) {
		return XmlTagDao.getInstance().getTagDescriptionFromTagName(tag);
	}
	
	
	
	
	
	
	
	

	
	
}
