package business;

import java.util.ArrayList;

import dao.XmlAttributeDao;
import dao.XmlTagDao;
import model.PDSCTagAttributeException;
import model.PDSCTagChildrenException;
import model.Response;
import model.UndoManager;
import model.XmlAttribute;
import model.XmlTag;
import model.XmlTagConstants;
import view.comp.utils.DialogUtils;

public class XmlTagBusiness{
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
	 * setSelecteAttributes array
	 * 
	 * @param tag tag on which set setSelecteAttributes
	 * @param attrArr array to set
	 * @param registerOperation set to true if you want to register operation for undo manager
	 */
	public static void setSelectedAttributesArr(XmlTag tag, ArrayList<XmlAttribute> attrArr, boolean registerOperation) {
		tag.setSelectedAttrArr(attrArr);
		attrArr.forEach((a) -> a.setTag(tag));
		if(registerOperation) UndoManager.registerOperation();
	}
	
	
	
	/**
	 * add custom tag (custom tags are not defined in PDSC STANDARD).
	 * 
	 * @param tag tag on which add custom tags
	 * @param names array containing custom tag names
	 * @param registerOperation set to true if you want to register operation for undo manager
	 * @param enableUserControl set to true if you want the user to be alerted if there are choices to be made
	 */
	public static void addCustomTags(XmlTag tag,String[] names, boolean registerOperation, boolean enableUserControl) {
		boolean choice = true;
		
		/** verify if tag haven't content setted*/
		if(tag.getContent() != null && tag.getContent().trim().length() > 0) {
			if(enableUserControl)
				choice = DialogUtils.yesNoWarningMessage("Following PDSC standard tags with textual content cannot have children. \n Do you want to continue ? ");
		}
		
		if(choice){
			/** reverse array */
			names = (String[]) CustomUtils.reverseArray(names);
			
			for (String name : names){
				
				Response response = XmlTagBusiness.verifyTagFromName(name, tag);
				
				boolean confirmation = true ;
				if (response.getStatus() == XmlTagConstants.MAX_REACHED) {
					confirmation = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n maximum number of children reached for tag < " + name + " > Do you want to continue ?");
				}
				if(confirmation) {
					XmlTag child = (XmlTag) response.getObject();
					if(child != null) addTagInParent(new XmlTag(child , child.getParent()) , child, tag, false, false, null);
				}
			}	 
		}
		
		if(registerOperation) UndoManager.registerOperation();
		
	}
	
	
	
	/**
	 * Add custom attributes (custom attr are not defined in PDSC STANDARD)
	 * 
	 * @param tag	tag on which add attributes
	 * @param attrNames  attribute names
	 * @param registerOperation true if you want to register operation for undo redo
	 * @return String with error message if errors occurs, null otherwise
	 */
	public static void addCustomAttributes(XmlTag tag, String[] attrNames , boolean registerOperation) {
		String errorMessage = "";
		for (String name : attrNames){
			
			Response response = XmlAttributeBusiness.verifyAttributeFromName(tag, name);
			
			if(response.getStatus() == XmlAttribute.INVALID_NAME) errorMessage += " '" + name + "' Invalid name \n";
			else if (response.getStatus() == XmlAttribute.ALREADY_PRESENT) {
				errorMessage += "Attribute \" " + name  + " \" is already present \n" ;
			}
			else {
				XmlAttribute attr = (XmlAttribute) response.getObject();
				attr.setTag(tag);
				tag.addSelectedAttrAtIndex(attr, 0);
			}
		}
		if(registerOperation) UndoManager.registerOperation();
		if(errorMessage != null && !errorMessage.equals("")) DialogUtils.warningMessage(errorMessage);
		else;
	}

	
	
	
	/**
	 * set content in tag 
	 * 
	 * @param tag tag on which set the content
	 * @param content to add
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 */
	public static void setTagContent(XmlTag tag , String content , boolean registerOperation) {
		if(content != null && !content.trim().contentEquals("")) tag.setContent(content);
		if(registerOperation) UndoManager.registerOperation();
	}
	
	
	
	/**
	 * Add attribute in tag
	 * 
	 * @param tag tag on which add attribute
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 */
	public static void addAttributeInTag(XmlTag tag, XmlAttribute attr, boolean registerOperation, boolean enableUserControl, Integer index) {
		boolean choice  = true;
		
		if (enableUserControl){
			if(tag.getSelectedAttrArr() != null) {
				for (int i = 0; i < tag.getSelectedAttrArr().size();  i++) {
					XmlAttribute attr1 = tag.getSelectedAttrArr().get(i);
					if(attr.getName().equals(attr1.getName())) {
						choice = DialogUtils.yesNoWarningMessage("Attribute is already present ! Do you want to continue ? ");
					}
				}
			}
			
		}
		
		if(choice) {
			if(index != null) tag.addSelectedAttrAtIndex(attr, index);
			else tag.addSelectedAttr(attr);
			if(registerOperation) UndoManager.registerOperation();
			attr.setTag(tag);
		}
		
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
	
	public static void adjustTagAttributeException(XmlTag tag, XmlTag parent) {
		
//		for (int i = 0 ; i < 50 ; i++) {System.out.println("\n");}
//		System.out.print(" verifying consistency exceptions for tag " + tag.getName() + " ");
		
		/** verify consistency of all required parameters*/
		if(tag.getTagAttributeExceptionArr() == null) return ;
		if(parent == null) return ;
		
//		System.out.println(" consistency passed for tag " + tag.getName());
		
		/** for each exception */
		for (int j = 0; j < tag.getTagAttributeExceptionArr().size(); j++) {
			
			/** recovering exception */
			PDSCTagAttributeException exception = tag.getTagAttributeExceptionArr().get(j);
			
			/** if tags aren't the same return */
			if(!tag.getName().equals(exception.getTag().getName())) return;
			
//			System.out.println("verifying attr: " + exception.getAttribute().getName());
			
			/** for each attr */
			if(tag.getAttrArr() != null) {
				for (int i = 0; i < tag.getAttrArr().size(); i ++) {

					/** recovering attribute */
					XmlAttribute attr = tag.getAttrArr().get(i);
					
					/** if attributes coincides */
					if(attr.getName().equals(exception.getAttribute().getName())) {
						
//						System.out.println("ok attributes coicides " + attr.getName());
						
						/**
						 *  verify exception.
						 * 
						 *  0 = tag must not contains attribute
						 *  1 = tag must contains attribute
						 */
						if(exception.getException() == 0) {
//							System.out.println("exception parent : " + exception.getParent().getName() + " tag parent : " + tag.getParent().getName());
							
							if (!parent.getName().equals(exception.getParent().getName())) {
								//System.out.println("parent not coincides " + exception.getParent().getName());
								break;
							}
							
//							System.out.println("parent coincides, removing attr :" + attr.getName());
							
							tag.getAttrArr().remove(attr);
							
							/** removing attribute from selectedAttrArr */
							if(tag.getSelectedAttrArr() != null && tag.getSelectedAttrArr().size() > 0) {
								
								/** for each attr in selectedAttrArr */
								for (int h = 0; h < tag.getSelectedAttrArr().size(); h ++) {
									
									/** recovering selectedAttr */
									XmlAttribute selectedAttr = tag.getSelectedAttrArr().get(h);
									
									/** if selectedAttr coincides with exceptioAttr, remove it*/
									if(selectedAttr.getName().equals(exception.getAttribute().getName())) {
//										System.out.println(selectedAttr.getName() + " = " + exception.getAttribute().getName() + " removing attr from selectedAttrArr " + attr.getName());
										tag.getSelectedAttrArr().remove(selectedAttr);
									}
								}
							}
						}
					}
				}
			}
			if(exception.getException() == 1) {
//				System.out.println("exception == 1, adding attribute");
				
				/** adding attribute */
				XmlAttribute newAttr = new XmlAttribute(exception.getAttribute() , tag);
				if(tag.getAttrArr() == null ) tag.setAttrArr(new ArrayList<XmlAttribute>());
				tag.getAttrArr().add(newAttr);
			}
			
		}
	}
	
	
	
	

	public static void adjustTagChildrenException(XmlTag tag, XmlTag parent) {
		
//		for (int i = 0 ; i < 50 ; i++) {System.out.println("\n");}
//		System.out.print(" verifying consistency exceptions for tag " + tag.getName() + " ");
		
		/** verify consistency of all required parameters*/
		if(tag.getTagChildrenExceptionArr() == null) return ;
		if(parent == null) return ;
		
//		System.out.println(" consistency passed for tag " + tag.getName());
		
		/** for each exception */
		for (int j = 0; j < tag.getTagChildrenExceptionArr().size(); j++) {
			
			/** recovering exception */
			PDSCTagChildrenException exception = tag.getTagChildrenExceptionArr().get(j);
			
			/** if tags aren't the same return */
			if(!tag.getName().equals(exception.getTag().getName())) return;
			
//			System.out.println("verifying child: " + exception.getChild.getName());
			
			/** for each child */
			if(tag.getChildrenArr() != null) {
				for (int i = 0; i < tag.getChildrenArr().size(); i ++) {

					/** recovering child */
					XmlTag child = tag.getChildrenArr().get(i);
					
					/** if children coincides */
					if(child.getName().equals(exception.getChild().getName())) {
						
//						System.out.println("ok childrend coincides " + child.getName());
						
						/**
						 *  verify exception.
						 * 
						 *  0 = tag must not contains attribute
						 *  1 = tag must contains attribute
						 */
						if(exception.getException() == 0) {
//							System.out.println("exception parent : " + exception.getParent().getName() + " tag parent : " + tag.getParent().getName());
							
							if (!parent.getName().equals(exception.getParent().getName())) {
								//System.out.println("parent not coincides " + exception.getParent().getName());
								break;
							}
							
//							System.out.println("parent coincides, removing child :" + child.getName());
							
							tag.getChildrenArr().remove(child);
							
							/** removing child from selectedChildrenArr */
							if(tag.getSelectedChildrenArr() != null && tag.getSelectedChildrenArr().size() > 0) {
								
								/** for each child in selectedChildrenArr */
								for (int h = 0; h < tag.getSelectedChildrenArr().size(); h ++) {
									
									/** recovering selectedChild */
									XmlTag selectedChild = tag.getSelectedChildrenArr().get(h);
									
									/** if selectedChild coincides with exceptioAttr, remove it*/
									if(selectedChild.getName().equals(exception.getChild().getName())) {
//										System.out.println(selectedChild.getName() + " = " + exception.getChild().getName() + " removing child from selectedChildArr " + attr.getName());
										tag.getSelectedChildrenArr().remove(selectedChild);
									}
								}
							}
						}
					}
				}
			}
			if(exception.getException() == 1) {
//				System.out.println("exception == 1, adding attribute");
				
				/** adding child */
				XmlTag newChild = new XmlTag(exception.getChild() , tag);
				if(tag.getChildrenArr() == null ) tag.setChildrenArr(new ArrayList<XmlTag>());
				tag.getChildrenArr().add(newChild);
			}
			
		}
	}
	
	
	
	/**
	 * Add passed tag in passed parent
	 * 
	 * @param tag tag to add
	 * @param parent parent in which adding tag
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 * @param enableUserControls set to true if you want the user to be alerted if there are choices to be made
	 */
	public static void addTagInParent(XmlTag newTag, XmlTag modelTag, XmlTag parent, boolean registerOperation, boolean enableUserControls, Integer index) {
		boolean choice = true;
		
		/** it is possible that some exception removed some foundamentals attribute for this tag, so we ned to restore attrArr (for copy paste)*/
		newTag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(newTag));
		
		/** reset and recovering tag attribute exception for this parent */
		newTag.setTagAttributeExceptionArr(null);
		newTag.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(newTag, parent));
		newTag.setTagChildrenExceptionArr(null);
		newTag.setTagChildrenExceptionArr(XmlTagDao.getInstance().getTagChildrenExceptionArr(newTag, parent));
		
		
		
		
		if(enableUserControls) {
			if(modelTag != null && modelTag.getMax() <= 0 ) {
				String message = "Followind PDSC standard, maximum number of children reached for tag < " + modelTag.getName() + " > \n Do you want to continue ? ";
				choice = DialogUtils.yesNoWarningMessage(message);
			}
		}
		
		if(choice) {
			
			/** adding tag */
			newTag.setParent(parent);
			if(index != null && index >= 0) parent.addSelectedChildAtIndex(newTag, index);
			else parent.addSelectedChild(newTag);
			
			/** adjusting attributes exception */
			XmlTagBusiness.adjustTagAttributeException(newTag,parent);
			/** adjusting children exception */
			XmlTagBusiness.adjustTagChildrenException(newTag, parent);
			
			/** if exceptions remove all attributes or child from tag , resetting array to null */
			if(newTag.getAttrArr() != null && newTag.getAttrArr().isEmpty()) newTag.setAttrArr(null);
			if(newTag.getChildrenArr() != null && newTag.getChildrenArr().isEmpty()) newTag.setChildrenArr(null);
			
			/** adjust model tag */
			if(modelTag != null ) modelTag.setMax(modelTag.getMax() -1);
		}
		
		if(registerOperation) UndoManager.registerOperation();
	}
	
	
	
	
	/**
	 * paste passed tag in passed parent
	 * 
	 * Same as addTagInParent above with the difference that attrArr is restored before pasting.
	 * This because for some previous exception some foundamental attributes for this tag may not be there
	 * so we need to restore attrArr to verify another time attribute exception
	 */
	public static void pasteTagInParent(XmlTag newTag, XmlTag modelTag, XmlTag parent, boolean registerOperation, boolean enableUserControls, Integer index) {
		
		/** it is possible that some exception removed some foundamentals attribute for this tag, so we ned to restore attrArr (for copy paste)*/
		newTag.setAttrArr(XmlAttributeDao.getInstance().getTagAttributes(newTag));
		
		/** it is possible that some exception removed some foundamentals child for this tag, so we ned to restore childArr (for copy paste)*/
		newTag.setChildrenArr(XmlTagDao.getInstance().getChildrenArrFromTag(newTag));
		
		addTagInParent(newTag, modelTag, parent , true, true, index);
		
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
//			System.out.println("analyzing " +  element.getName());
			children.remove(element);
			ArrayList <XmlTag> requiredChildren = new ArrayList<XmlTag>(XmlTagUtils.getRequiredChildren(element));
			if(!requiredChildren.isEmpty()) {
//				System.out.println(element.getName() + " has dependencies");
				for(int i = 0; i < requiredChildren.size(); i++) {
					XmlTag requiredChild = requiredChildren.get(i);
//					System.out.println("check if there is the denpendency :  "  + requiredChild.getName());
					boolean found = false;
					if(element.getSelectedChildrenArr() != null) {
						//System.out.println("let's see if " +  requiredChild.getName() + " is present in selected children of " +element.getName());
						for(int j = 0; j < element.getSelectedChildrenArr().size(); j++) {
							XmlTag selectedChild = element.getSelectedChildrenArr().get(j);
//							System.out.println("analizyng selected child " + selectedChild.getName());
							
							if(requiredChild.getName().equals(selectedChild.getName())) found = true;
							if( found ) System.out.println(selectedChild .getName() + " found ");break;
						}
						if(!found) {
							/** if max child number is > 0, add child */
							addRequiredAttributes(requiredChild , false);
							if(requiredChild.getMax() > 0 ) XmlTagBusiness.addTagInParent(new XmlTag(requiredChild, requiredChild.getParent()) ,requiredChild, requiredChild.getParent(), false, false, null);
						}
					}
					else {
						/** if max child number is > 0, add child */
						addRequiredAttributes(requiredChild , false);
						if(requiredChild.getMax() > 0 ) XmlTagBusiness.addTagInParent(new XmlTag(requiredChild, requiredChild.getParent()) , requiredChild, requiredChild.getParent(), false, false, null);	
					}

				}
			}else {/**System.out.println(element.getName() + " has no dependencies");*/}
			if( element.getSelectedChildrenArr() != null ) {
				element.getSelectedChildrenArr().forEach((c)-> children.add(c));
			}
		}
		if(registerOperation) {
			/** register operation only if changes was made */
			if(!parentCopy.equals(parent)) UndoManager.registerOperation();
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
		
		if(registerOperation) UndoManager.registerOperation();
		
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
			System.out.println("ok");
			
			parent = tag.getParent();
			XmlTag modelTag = XmlTagUtils.findModelChildFromSelectedChildName(parent, tag.getName());
			
			/** make controls */
			if(enableUserControls) {
				
				/** if max child number is > copiesNumber, add selected number of clones */
				if( (modelTag != null && modelTag.getMax() >= copiesNumber) || modelTag == null) choice = true;
				
				else {
					if(modelTag != null && modelTag.getMax() <= 0) 	choice = DialogUtils.yesNoWarningMessage("<html><p><span style=\"font-size: 14pt; color: #333333;\"> "
																						+ " Maximum number of children reached for element  <" + tag.getName() + 
																						"> <br> do you want to continue ?</span></p></html>");
					else choice = DialogUtils.yesNoWarningMessage("You can make at most : " + tag.getMax() + " copy for tag <" + tag.getName() + " > \n do you want to continue ?");
				}
			}
			
			/** Cone tag */
			if(choice) {
				for (int i = 0; i < copiesNumber ; i++) {
					System.out.println("faccio la copia");
					clone = new XmlTag(tag,tag.getParent());
					parent.addSelectedChild(clone);	
				}
			}
			
			/** adjust model tag and register operation */
			if(modelTag != null) modelTag.setMax(modelTag.getMax() - copiesNumber);
			if(registerOperation) UndoManager.registerOperation();
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
			if(registerOperation) UndoManager.registerOperation();
			if(!parent.getSelectedChildrenArr().contains(child)) return child;
		}
		
		return null;
		
	}
	
	
	
	/**
	 * Remove selected Attribute from parent
	 * 
	 * @param attr attr to remove
	 * @param parent parent that contains attribute
	 * @param registerOperation set to true if you want to register operation for undo redo system
	 * @param enableUserControls if you want the user to be alerted if there are choices to be made
	 */
	public static void removeSelectedAttributeFromParent(XmlAttribute attr, XmlTag parent, boolean registerOperation, boolean enableUserControls){
	boolean response = true;
		
		if(enableUserControls) {
			if(attr.isRequired()) {
				response = DialogUtils.yesNoWarningMessage("Following PDSC standard : \n < " + parent.getName() + "> must contain one attribute " + attr.getName() + "\n Do you want to continue ?");
			}
		}

		if(response) {
			parent.getSelectedAttrArr().remove(attr);
			if(registerOperation) UndoManager.registerOperation();
		}
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
				.object(new XmlTag(childName , false , parent , XmlTag.MAX_OCCURENCE_NUMBER , "All"))
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
		root.setTagAttributeExceptionArr(XmlTagDao.getInstance().getTagAttributeExceptionArr(root , root));
		root.setParent(root);
		root.setChildrenArr(XmlTagDao.getInstance().getChildrenArrFromTag(root));
		XmlTagBusiness.adjustTagAttributeException(root, root);
		
		return root; 
		
	}
	
	
	
	
	/** 
	 * return tag's not required children;
	 * 
	 * @param parent parent tag
	 * @return tag's not required children or null
	 */
	
	public static  ArrayList<XmlTag> getNotRequiredChildren(XmlTag parent) {
		return XmlTagDao.getInstance().getAllChildrenFromTag(parent);
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
		//else System.out.println("il tag è null " + name + " parent " + parent.getName());
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
		
		ArrayList<XmlTag> childrenArr = XmlTagDao.getInstance().getChildrenArrFromTag(tag);
		tag.setChildrenArr(childrenArr);
		
		if(childrenArr != null) {
			/** iterating trough selected children */
			for(int i = 0; i < childrenArr.size(); i++) {		
				XmlTag child = childrenArr.get(i);	
				/** recursion */
				if(!child.getName().equals(tag.getName()))getCompleteTagFromTagInstance(child);
				
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
		String description = XmlTagDao.getInstance().getTagDescriptionFromTagAndParent(tag, tag.getParent());
		
		/** 
		 * if description == null,it possible that attribute for this tag is an exception 
		 * (see PDSCTagAttributeException class).
		 * in this case description is located in tags_attributes_exception table
		 */
		
		if( description == null || description.trim().length() == 0) {
			System.out.println("daje co sta descrizione");
			description = XmlTagDao.getInstance().getTagDescriptionFromTagChildrenExceptions(tag, tag.getParent());
		}
		return description;
	}
	
	
	
	
	
	
	
	

	
	
}
