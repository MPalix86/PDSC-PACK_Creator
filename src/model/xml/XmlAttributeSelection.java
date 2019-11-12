package model.xml;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * A Transferable which implements the capability required to transfer Attributes. 
 * This Transferable properly supports xmlAttrFlavor
 * 
 * @author mircopalese
 */
public class XmlAttributeSelection implements Transferable , ClipboardOwner{
	

	private static final int XML_ATTR = 0;
	private static final int XML_ATTR_TO_STRING = 1;
	
	/** xmlAttrFlavor */
	public static DataFlavor xmlAttrFlavor = new DataFlavor (XmlAttribute.class , "Xml attribute");;
	public static DataFlavor stringFlavor = DataFlavor.stringFlavor;
	
	
    private static final DataFlavor[] flavors = {
    		xmlAttrFlavor,
           DataFlavor.stringFlavor,
        };
    
    
    /** attribute to copy */
    private XmlAttribute attr;
    
    
    /**
     * Creates a <code>Transferable</code> capable of transferring
     * the specified <code>XmlAttribute</code>.
     */
    public XmlAttributeSelection(XmlAttribute attr) {
    	this.attr = attr;
    }
    
    
    
    
    /**
     * Returns an array of flavors in which this <code>Transferable</code>
     * can provide the data.
     *
     * @return an array, whose elements are <code>DataFlavor</code>.
     */
    
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return (DataFlavor[])flavors.clone();
	}
	
	
	
	
    /**
     * Returns whether the requested flavor is supported by this
     * <code>Transferable</code>.
     *
     * @param flavor the requested flavor for the data
     * @return true if <code>flavor</code> is equal to flavor present
     * 	 in flavors array. 
     * @throws NullPointerException if flavor is <code>null</code>
     */
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		for (int i = 0; i < flavors.length; i++) {
            if (flavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
	}
	
	
	
	
    /**
     * Returns the <code>Transferable</code>'s data in the requested
     * <code>DataFlavor</code> if possible. If the desired flavor is
     *
     * @param flavor the requested flavor for the data
     * @return the data in the requested flavor, 
     * @throws UnsupportedFlavorException if the requested data flavor is
     *         not equivalent to data flavor specified in flavors array
     * @throws IOException if an IOException occurs while retrieving the data.
     * @throws NullPointerException if flavor is <code>null</code>
     */   
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(flavors[XML_ATTR])) {
            return (Object) attr;
        } 
		else if(flavor.equals(flavors[XML_ATTR_TO_STRING])) {
			String value = attr.getName();
			if(attr.getValue()!=null) value += " = \" " + attr.getValue() + " \" "; 
			return value;
		}
		else return null;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		System.out.println("lost ownership");
		
	}

}
