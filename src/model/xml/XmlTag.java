package model.xml;

import java.io.File;
import java.util.ArrayList;

import model.PDSC.PDSCTagAttributeException;
import model.PDSC.PDSCTagChildrenException;

/**
 * XmlTag class represent an abstraction of generic xml tag. To manage Dynamically tags
 * with them attributes, symbolic division has been made.
 * <p>
 * MODEL'S FIELDS : defined during class modeling; they should not be changed in
 * 					run time. These fields represent main features of an elements (tag)
 * 					defined in some xml standard which is modeled in form of class. 
 * 					However only these fields are not necessary to completely 
 * 					describes generic tag, because many properties depends on 
 * 					the context, for example from the parent tag.
 *                  you can find all these other fields in local fields above.
 * <p>
 * LOCAL FIELDS :	These fields are used to personalize model's field. For example
 * 					to select only certain attributes among all those present in model's
 * 					fields, or to set some properties that depend only from parent tag
 * 					like, for instance the max occurrence number (max),	
 * 
 * @author Mirco Palese
 */

public class XmlTag{
	
/**
 * ==================================
 * LOCAL FIELDS
 * ==================================
 */
	
	/** tag's content */
	protected String content; 
	
	/** tag's content type in string form*/
	private String valueType;

	/** true if tag is mandatory, false otherwise */
	protected boolean required;
	
	
	/** tag's selected childrendArr */
	private  ArrayList<XmlTag> selectedChildrenArr;
	
	
	/** tag's selected attributes array */
	private ArrayList<XmlAttribute> selectedAttrArr;
	
	
	/** tag's parent */
	private XmlTag parent; 
	
	
	/** tag's max occurrence number in parent */
	private Integer max;
	
	
	/** tag's name space */
	private XmlNameSpace nameSpace; 
	
	
	/**
	 * tag's default content, set to void string ("") if tag can have content, 
	 * set do desire content if tag has predefined content, set to null if tag
	 * can't have any content (tag can only contains other tags).
	 */
	private String defaultContent; 
	
	
	/** tag's possible values */
	private XmlEnum possibleValues;
	
	
	/** 
	 * Some tag can have associated source file for pack creation;
	 * this file represent source file path on filesystem.
	 * 
	 * NOTE: before setting thi variable, control that valueType is equals to File
	 */
	private File file;

	
	
/**
 * ==================================
 * MODEL'S FIELD
 * ==================================
 */
	
	/** relation tag id (id of tags_tags_relations table on db)*/
	protected Integer relId; 
	

	/** tag id (id of tags table on db)*/
	protected Integer tagId;
	
	
	/** tag's name */
	private String name;

	
	/** tag's attributes array */
	private ArrayList<XmlAttribute> attrArr;
	

	/** tag's description */
	private String description;
	
	
	/** tag's childrendArr */
	private  ArrayList<XmlTag> childrenArr;	
	
	
	/** contains attribute that in certain condition can have exception in this tag */
	private ArrayList <PDSCTagAttributeException> tagAttributeExceptionArr;
	
	
	/** contains child that in certain condition can have exception in this tag */
	private ArrayList<PDSCTagChildrenException> tagChildrenExceptionArr;
	
	/** general max occurrence number */
	public final static int MAX_OCCURENCE_NUMBER = 1000;
	
	
	
	
	/** 
	 * Constructor, return new instance of tag
	 * 
	 * @param tagId tag's id 
	 * @param relId not used
	 * @param name tag's name
	 * @param required true or false if tag is required or not
	 * @param parent tag's parent
	 * @param max max occurrence number in parent
	 * @param possibleValues tag's possibleValues 
	 * @param defaultContent tag's default content
	 * @param nameSpace tag's name space
	 * @param valueType tag's value type
	 */
	public XmlTag(Integer tagId,Integer relId, String name, boolean required, XmlTag parent, int max, XmlEnum possibleValues, String defaultContent,XmlNameSpace nameSpace , String valueType) {
		this.name = name;
		this.required = required;
		this.parent = parent;
		this.max = max;
		this.possibleValues = possibleValues;
		this.defaultContent = defaultContent;
		this.nameSpace = nameSpace;
		this.tagId = tagId;
		this.relId = relId;
		this.valueType = valueType;
	}
	
	
	
	/**
	 * Constructor, return new instance of tag
	 * 
	 * @param name tag's name
	 * @param required true or false if tag is required or not
	 * @param parent tag's parent
	 * @param max max occurrence number in parent
	 * @param valueType tag's value type
	 */
	public XmlTag(String name, boolean required, XmlTag parent, int max, String valueType) {
		this.name = name;
		this.required = required;
		this.parent = parent;
		this.max = max;
		this.valueType = valueType;
	}
	


	/**
	 * Constructor, return new instance of tag
	 * 
	 * @param name name tag's name
	 * @param possibleValues possibleValues tag's possible values 
	 * @param defaultContent tag's default content
	 * @param valueType tag's value type
	 */
	public XmlTag(String name, XmlEnum possibleValues, String defaultContent, String valueType) {
		this.name = name;
		this.possibleValues = possibleValues;	
		this.defaultContent = defaultContent;
		this.valueType = valueType;
	}




	/**
	 * This constructor return the exact PARENTLESS copy of tag passed by parameter in a new instance
	 * 
	 * @param tag the tag to be copied into new instance
	 */
	public XmlTag(XmlTag tag) {
		
		if(tag.getAttrArr() != null) {
			
			this.attrArr = new ArrayList<XmlAttribute>(); 
			
			/** for each attribute add new instance inside attrArr */
			tag.getAttrArr().forEach((a)->this.attrArr.add(new XmlAttribute(a,this)));
		}

		if(tag.getSelectedAttrArr() != null) {
			
			this.selectedAttrArr = new ArrayList<XmlAttribute>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getSelectedAttrArr().forEach((a)-> this.selectedAttrArr.add(new XmlAttribute(a,this)));
		}
		
		if(tag.getChildrenArr() != null) {
			
			this.childrenArr = new ArrayList<XmlTag>(); 
			
			/** for each children add new instance inside childrenArr */
			tag.getChildrenArr().forEach((c)->this.childrenArr.add(new XmlTag(c, this)));
		}
		
		if(tag.getSelectedChildrenArr() != null) {
			
			this.selectedChildrenArr = new ArrayList<XmlTag>(); 
			
			/** for each selected children add new instance inside selectedchildrendArr */
			tag.getSelectedChildrenArr().forEach((c)-> this.selectedChildrenArr.add(new XmlTag(c, this)));
		}
		
		if(tag.getTagAttributeExceptionArr() != null) {
			
			this.tagAttributeExceptionArr = new ArrayList<PDSCTagAttributeException>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getTagAttributeExceptionArr().forEach((e)-> this.tagAttributeExceptionArr.add(new PDSCTagAttributeException(e)));
		}
		
		if(tag.getTagChildrenExceptionArr() != null) {
			
			this.tagChildrenExceptionArr = new ArrayList<PDSCTagChildrenException>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getTagChildrenExceptionArr().forEach((e)-> this.tagChildrenExceptionArr.add(new PDSCTagChildrenException(e)));
		}
		
	
		if(tag.getParent() != null) this.parent = null ;
		
		if(tag.getDefaultContent() != null) this.defaultContent = new String(tag.getDefaultContent());
		
		if(tag.getContent() != null) this.content = new String(tag.getContent());
		
		if(tag.getDescription() != null) this.description = new String (tag.getDescription());
		
		if(tag.getNameSpace() != null) this.nameSpace = new XmlNameSpace(tag.getNameSpace());
		
		if(tag.getName() != null) this.name = new String(tag.getName());
		
		if(tag.getMax() != null) this.max = tag.getMax();
		
		if(tag.getPossibleValues() != null) this.possibleValues = tag.getPossibleValues();
		
		if(tag.getRelId() != null) this.relId = tag.getRelId();
		
		if(tag.getTagId() != null) this.tagId = tag.getTagId();
		
		if(tag.getValueType() != null) this.valueType = tag.getValueType();
		
		if(tag.getFile() != null) this.file = tag.getFile();
		
		this.required = tag.isRequired();
		
	}
	
	
	
	
	/**
	 * This constructor return the exact copy of tag passed by parameter in a new instance.
	 * Parent is set to the parent passed by parameter
	 * 
	 * @param tag the tag to be copied into new instance
	 */
	public XmlTag(XmlTag tag, XmlTag parent) {
		
		if(tag.getAttrArr() != null) {
			
			this.attrArr = new ArrayList<XmlAttribute>(); 
			
			/** for each attribute add new instance inside attrArr */
			tag.getAttrArr().forEach((a)->this.attrArr.add(new XmlAttribute(a,this)));
		}

		if(tag.getSelectedAttrArr() != null) {
			
			this.selectedAttrArr = new ArrayList<XmlAttribute>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getSelectedAttrArr().forEach((a)-> this.selectedAttrArr.add(new XmlAttribute(a,this)));
		}
		
		
		if(tag.getChildrenArr() != null) {
			
			this.childrenArr = new ArrayList<XmlTag>(); 
			
			/** for each children add new instance inside childrenArr */
			tag.getChildrenArr().forEach((c)->this.childrenArr.add(new XmlTag(c, this)));
		}
		
		if(tag.getSelectedChildrenArr() != null) {
			
			this.selectedChildrenArr = new ArrayList<XmlTag>(); 
			
			/** for each selected children add new instance inside selectedchildrendArr */
			tag.getSelectedChildrenArr().forEach((c)-> this.selectedChildrenArr.add(new XmlTag(c, this)));
		}
		
		if(tag.getTagAttributeExceptionArr() != null) {
			
			this.tagAttributeExceptionArr = new ArrayList<PDSCTagAttributeException>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getTagAttributeExceptionArr().forEach((e)-> this.tagAttributeExceptionArr.add(new PDSCTagAttributeException(e)));
		}
		
		if(tag.getTagChildrenExceptionArr() != null) {
			
			this.tagChildrenExceptionArr = new ArrayList<PDSCTagChildrenException>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getTagChildrenExceptionArr().forEach((e)-> this.tagChildrenExceptionArr.add(new PDSCTagChildrenException(e)));
		}
	
		if(tag.getParent() != null) this.parent = parent ;
		
		if(tag.getDefaultContent() != null) this.defaultContent = new String(tag.getDefaultContent());
		
		if(tag.getContent() != null) this.content = new String(tag.getContent());
		
		if(tag.getDescription() != null) this.description = new String (tag.getDescription());
		
		if(tag.getNameSpace() != null) this.nameSpace = new XmlNameSpace(tag.getNameSpace());
		
		if(tag.getName() != null) this.name = new String(tag.getName());
		
		if(tag.getMax() != null) this.max = tag.getMax();
		
		if(tag.getPossibleValues() != null) this.possibleValues = tag.getPossibleValues();
		
		if(tag.getRelId() != null) this.relId = tag.getRelId();
		
		if(tag.getTagId() != null) this.tagId = tag.getTagId();
		
		if(tag.getValueType() != null) this.valueType = tag.getValueType();
		
		if(tag.getFile() != null) this.file = tag.getFile();
		
		this.required = tag.isRequired();
		
	}
	
	
	
	
	
	/**
	 * Replace this tag with passed tag
	 * 
	 * @param tag with which replace this instance
	 */
	public void replaceWith(XmlTag tag , XmlTag parent) {
		
		if(tag.getAttrArr() != null) {
			
			this.attrArr = new ArrayList<XmlAttribute>(); 
			
			/** for each attribute add new instance inside attrArr */
			tag.getAttrArr().forEach((a)->this.attrArr.add(new XmlAttribute(a,this)));
		}

		if(tag.getSelectedAttrArr() != null) {
			
			this.selectedAttrArr = new ArrayList<XmlAttribute>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getSelectedAttrArr().forEach((a)-> this.selectedAttrArr.add(new XmlAttribute(a,this)));
		}
		
		
		if(tag.getChildrenArr() != null) {
			
			this.childrenArr = new ArrayList<XmlTag>(); 
			
			/** for each children add new instance inside childrenArr */
			tag.getChildrenArr().forEach((c)->this.childrenArr.add(new XmlTag(c, this)));
		}
		
		if(tag.getSelectedChildrenArr() != null) {
			
			this.selectedChildrenArr = new ArrayList<XmlTag>(); 
			
			/** for each selected children add new instance inside selectedchildrendArr */
			tag.getSelectedChildrenArr().forEach((c)-> this.selectedChildrenArr.add(new XmlTag(c, this)));
		}
		
		if(tag.getTagAttributeExceptionArr() != null) {
			
			this.tagAttributeExceptionArr = new ArrayList<PDSCTagAttributeException>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getTagAttributeExceptionArr().forEach((e)-> this.tagAttributeExceptionArr.add(new PDSCTagAttributeException(e)));
		}
		
		if(tag.getTagChildrenExceptionArr() != null) {
			
			this.tagChildrenExceptionArr = new ArrayList<PDSCTagChildrenException>(); 
			
			/** for each selected attribute add new instance inside attrArr */
			tag.getTagChildrenExceptionArr().forEach((e)-> this.tagChildrenExceptionArr.add(new PDSCTagChildrenException(e)));
		}
	
		if(tag.getParent() != null) this.parent = parent ;
		
		if(tag.getDefaultContent() != null) this.defaultContent = new String(tag.getDefaultContent());
		
		if(tag.getContent() != null) this.content = new String(tag.getContent());
		
		if(tag.getDescription() != null) this.description = new String (tag.getDescription());
		
		if(tag.getNameSpace() != null) this.nameSpace = new XmlNameSpace(tag.getNameSpace());
		
		if(tag.getName() != null) this.name = new String(tag.getName());
		
		if(tag.getMax() != null) this.max = tag.getMax();
		
		if(tag.getPossibleValues() != null) this.possibleValues = tag.getPossibleValues();
		
		if(tag.getRelId() != null) this.relId = tag.getRelId();
		
		if(tag.getTagId() != null) this.tagId = tag.getTagId();
		
		if(tag.getValueType() != null) this.valueType = tag.getValueType();
		
		if(tag.getFile() != null) this.file = tag.getFile();
		
		this.required = tag.isRequired();
		
	}
	
	
	
	
	
	
	/**
	 * void Constructor be careful to use it
	 * 
	 * @return new void instance
	 */
	public XmlTag() {}
	

	

	
	/**
	 * Return the tag's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	
	
	

	/**
	 * set the tag's name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	/**
	 * Return the tag's content
	 * 
	 * @return tag's content
	 */
	public String getContent() {
		return content;
	}

	
	
	
	
	/**
	 * set tag's content
	 * 
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	/**
	 * true if tag is required false otherwise
	 * 
	 * @return true if tag is required false otherwise
	 */
	public boolean isRequired() {
		return required;
	}

	
	
	
	
	/**
	 * set if tag is required
	 * 
	 * @param required true if tag is required false otherwise
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	
	

	/**
	 * Return tag's selected childrendArr
	 * 
	 * @return the selectedchildrendArr
	 */
	public ArrayList<XmlTag> getSelectedChildrenArr() {
		return selectedChildrenArr;
	}
	
	
	
	
	
	/**
	 * set selected childrendArr
	 * 
	 * @param selectedchildrendArr the selectedchildrendArr array to set
	 */
	public void setSelectedChildrenArr(ArrayList<XmlTag> selectedChildrenArr) {
		this.selectedChildrenArr = selectedChildrenArr;
	}

	
	
	
	
	/**
	 * get tag's parent
	 * 
	 * @return tag's parent
	 */
	public XmlTag getParent() {
		return parent;
	}
	
	
	
	
	
	/**
	 * set tag's parent
	 * 
	 * @param parent the parent to set
	 */
	public void setParent(XmlTag parent) {
		this.parent = parent;
	}
	
	
	
	

	/**
	 * tag's max occurrence
	 * 
	 * @return the max occurrence 
	 */
	public Integer getMax() {
		return this.max;
	}

	
	
	
	
	/**
	 * set tag's max occurrence number
	 * 
	 * @param max the max to set
	 */
	public void setMax(Integer max) {
		this.max = max;
	}
	
	
	
	

	/**
	 * return tag's default content
	 * 
	 * @return the defaultContent
	 */
	public String getDefaultContent() {
		return defaultContent;
	}
	
	
	
	

	/**
	 * set tag's default content
	 * 
	 * @param defaultContent the defaultContent to set
	 */
	public void setDefaultContent(String defaultContent) {
		this.defaultContent = defaultContent;
	}

	
	
	
	
	/**
	 * return tag's name space
	 * 
	 * @return the nameSpace
	 */
	public XmlNameSpace getNameSpace() {
		return nameSpace;
	}
	
	
	
	

	/**
	 * set tag's name space
	 * 
	 * @param nameSpace the nameSpace to set
	 */
	public void setNameSpace(XmlNameSpace nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	
	
	
	/**
	 * set tag's possible values
	 * 
	 * @param possibleValues the possibleValues to set
	 */
	public void setPossibleValues(XmlEnum o) {
		this.possibleValues = o;
	}
	
	
	
	
	/**
	 * get tag's possible values
	 * 
	 * @return the possibleValues 
	 */
	public XmlEnum getPossibleValues() {
		return this.possibleValues;
	}

	
	
	
	
	/**
	 * return arraylist containing all tag's attributes
	 * 
	 * @return the arraylist containing all tag's attributes
	 */
	public ArrayList<XmlAttribute> getAttrArr() {
		return attrArr;
	}

	
	
	
	
	/**
	 * set tag's possible attribute's
	 * 
	 * @param attrArr the attrArr to set
	 */
	public void setAttrArr(ArrayList<XmlAttribute> attrArr) {
		this.attrArr = attrArr;
	}

	
	
	
	
	/**
	 * return arraylist containing all selected attributes
	 * 
	 * @return the selectedAttrArr
	 */
	public ArrayList<XmlAttribute> getSelectedAttrArr() {
		return selectedAttrArr;
	}

	
	
	
	
	/**
	 * set selcted attrbute's array
	 * 
	 * @param selectedAttrArr the selectedAttrArr to set
	 */
	public void setSelectedAttrArr(ArrayList<XmlAttribute> selectedAttrArr) {
		this.selectedAttrArr = selectedAttrArr;
	}

	
	
	
	
	/**
	 * return tag's description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	
	
	

	/**
	 * set tag's description
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
	/**
	 * return array containig all tag's children
	 * 
	 * @return the childrendArr
	 */
	public ArrayList<XmlTag> getChildrenArr() {
		return childrenArr;
	}

	
	
	
	
	/**
	 * set tag's children array
	 * 
	 * @param childrendArr the childrendArr to set
	 */
	public void setChildrenArr(ArrayList<XmlTag> childrendArr) {
		this.childrenArr = childrendArr;
	}

	
	
	
	
	/**
	 * return max occurrence number
	 * 
	 * @return the maxOccurenceNumber
	 */
	public static int getMaxOccurenceNumber() {
		return MAX_OCCURENCE_NUMBER;
	}
	
	
	
	
	
	/**
	 * Free memory occupied by model's fields. Be careful to use it
	 * 
	 * @return void
	 */
	
	public void freeModelFields() {
		this.attrArr = null;
		this.childrenArr = null;
		this.parent = null;
		this.max = null;
		this.tagAttributeExceptionArr = null;
	}
	
	
	
	
	
	/**
	 * Free memory occupied by local's fields. Be careful to use it
	 * 
	 * @return void
	 */
	public void freeLocalFields() {
		this.name = null;
		this.content = null;
		this.selectedAttrArr = null;
		this.selectedChildrenArr = null;
		this.max = null;
		this.parent = null;
		this.nameSpace = null;
		this.defaultContent = null;
		this.possibleValues = null;	
	}
	
	
	
	
	
	/**
	 * Add child inside childrendArr
	 * 
	 * @return void
	 * @param child the child to add
	 */
	public void addChild(XmlTag child) {
		if (childrenArr == null) childrenArr = new ArrayList<XmlTag>();
		this.childrenArr.add(child);
	}
	
	
	
	
	/**
	 * Add attribute inside attrArr
	 * 
	 * @return void
	 * @param attr the attr to add
	 */
	public void addAttr(XmlAttribute attr) {
		if (attrArr == null) attrArr = new ArrayList<XmlAttribute>();
		this.attrArr.add(attr);
	}
	
	
	
	
	
	/**
	 * Add child inside selctedChildrenArr
	 * 
	 * @return void
	 * @param child the child to add
	 */
	public void addSelectedChild(XmlTag child) {
		if (selectedChildrenArr == null) selectedChildrenArr = new ArrayList<XmlTag>();
		this.selectedChildrenArr.add(child);
	}
	
	
	
	/**
	 * Add child inside selctedChildrenArr at specified index
	 * 
	 * @return void
	 * @param child the child to add
	 */
	public void addSelectedChildAtIndex(XmlTag child, int index) {
		if (selectedChildrenArr == null) selectedChildrenArr = new ArrayList<XmlTag>();
		this.selectedChildrenArr.add(index , child);
	}
	
	
	
	
	/**
	 * Add attribute inside selectedAttrArr
	 * 
	 * @return void
	 * @param attr the attribute to add
	 */
	public void addSelectedAttr(XmlAttribute attr) {
		if (selectedAttrArr == null) selectedAttrArr = new ArrayList<XmlAttribute>();
		this.selectedAttrArr.add(attr);
	}
	
	
	
	
	/**
	 * Add attribute inside selectedAttrArr at specified index
	 * 
	 * @return void
	 * @param attr the attribute to add
	 */
	public void addSelectedAttrAtIndex(XmlAttribute attr,int index) {
		if (selectedAttrArr == null) selectedAttrArr = new ArrayList<XmlAttribute>();
		this.selectedAttrArr.add(index,attr);
	}
	
	
	
	
	/**
	 * Add PDSCTagAttributeException inside tagAttributeExceptionArr
	 * 
	 * @param e Exception to insert
	 */
	public void addTagAttributeExceptionArr(PDSCTagAttributeException e) {
		if(tagAttributeExceptionArr == null) tagAttributeExceptionArr = new ArrayList<PDSCTagAttributeException>();
		this.tagAttributeExceptionArr.add(e);
	}
	
	
	
	/**
	 * Add attribute inside selectedAttrArr
	 * 
	 * @param child the child to remove
	 * @return void
	 */
	public void removeSelectedChild(XmlTag child) {
		this.selectedChildrenArr.remove(child);
		if(this.selectedChildrenArr.isEmpty()) this.selectedChildrenArr = null;
	}
	
	
	
	
	/**
	 * remove selected attribute from selectedAttrArr
	 * 
	 * @param attr attr the attribute to remove
	 * @return void
	 */
	public void removeSelectedAttr(XmlAttribute attr) {
		this.selectedAttrArr.remove(attr);
	}
	
	
	
	
	
	/**
	 * Remove PDSCTagAttributeException from tagAttributeExceptionArr
	 * 
	 * @param e
	 */
	public void removeTagAttributeExceptionArr(PDSCTagAttributeException e) {
		this.tagAttributeExceptionArr.remove(e);
	}
	
	
	
	
	
	/**
	 * set tagAttributeExceptionArr
	 * 
	 * @param tagAttributeExceptionArr to set
	 */
	public void setTagAttributeExceptionArr(ArrayList<PDSCTagAttributeException> tagAttributeExceptionArr) {
		this.tagAttributeExceptionArr = tagAttributeExceptionArr;
	}
	
	
	
	
	
	/** 
	 *  Retrurn tagAttributeExceptionArr
	 * 
	 * @return tagAttributeExceptionArr
	 */
	public ArrayList<PDSCTagAttributeException> getTagAttributeExceptionArr(){
		return this.tagAttributeExceptionArr;
	}
	
	
	/**
	 * set tagChildrenExceptionArr
	 * 
	 * @param tagChildrenExceptionArr to set
	 */
	public void setTagChildrenExceptionArr(ArrayList<PDSCTagChildrenException> tagChildrenExceptionArr) {
		this.tagChildrenExceptionArr = tagChildrenExceptionArr;
	}
	
	
	
	
	
	/** 
	 *  Retrurn tagChildrenExceptionArr
	 * 
	 * @return tagChildrenExceptionArr
	 */
	public ArrayList<PDSCTagChildrenException> getTagChildrenExceptionArr(){
		return this.tagChildrenExceptionArr;
	}
	
	
	
	
	
	/**
	 * Return relId
	 * 
	 * @return the tagId
	 */
	public Integer getRelId() {
		return relId;
	}
	
	
	
	
	/**
	 * Set RelId
	 * 
	 * @param relId the relId to set
	 * @return void
	 */
	public void setRelId(Integer relId) {
		this.relId = relId;
	}
	
	
	
	
	
	/**
	 * Return tagId
	 * 
	 * @return the tagId
	 */
	public Integer getTagId() {
		return tagId;
	}
	
	
	
	
	
	/**
	 * Set tagId
	 * 
	 * @param tagId the tagId to set
	 * @return void
	 */
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
	
	
	
	
	/**
	 * Return contetType in form of string
	 * 
	 * @return contetType in form of string
	 */
	public String getValueType() {
		return this.valueType;
	}
	
	
	
	
	/**
	 * Return file 
	 * 
	 * @return file
	 */
	public File getFile() {
		return this.file;
	}
	
	
	
	
	
	/**
	 * Set file
	 * 
	 * @param file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}


	
	
	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
	
	
	
	
	/**
	 * verify if this tag contains passed tag.
	 * 
	 * @param tag to search 
	 */
	public boolean containsChild(XmlTag tag) {
		ArrayList <XmlTag> list = new ArrayList<XmlTag>();  
		
		list.add(this);
		
		while (!list.isEmpty()) {
			XmlTag t = list.get(0);
			list.remove(0);
			if(list.contains(tag)) return true;
			else if(t.getSelectedChildrenArr() != null) t.getSelectedChildrenArr().forEach(c -> list.add(c));
		}
		return false;
	}
	
	
	
	
	/**
	 * verify if this tag contains passed attr.
	 * 
	 * @param attr to search 
	 */
	public boolean containsAttr(XmlAttribute attr) {
			
		if(selectedAttrArr != null) {
			for (int i = 0; i < selectedAttrArr.size(); i++) {
				XmlAttribute selectedAttr = selectedAttrArr.get(i);
				if(attr.getName().equals(selectedAttr.getName())) return true;
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * verify if this tag contains passed attr.
	 * 
	 * @param attr to search 
	 */
	public boolean containsAttr(String attrName) {
			
		if(selectedAttrArr != null) {
			for (int i = 0; i < selectedAttrArr.size(); i++) {
				XmlAttribute selectedAttr = selectedAttrArr.get(i);
				if(attrName.equals(selectedAttr.getName())) return true;
			}
		}
		return false;
	}
	
	
	
	
	
	/**
	 * verify if this tag contains passed attr.
	 * 
	 * @param attr to search 
	 */
	public XmlAttribute getSelectedAttrByName(String name) {
			
		if(selectedAttrArr != null) {
			for (int i = 0; i < selectedAttrArr.size(); i++) {
				XmlAttribute selectedAttr = selectedAttrArr.get(i);
				if(selectedAttr.getName().equals(name)) return selectedAttr;
			}
		}
		return null;
	}




}