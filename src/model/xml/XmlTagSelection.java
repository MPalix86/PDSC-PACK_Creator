package model.xml;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.jdom2.Document;

import business.utils.PDSCDocumentUtils;
import business.utils.XmlTagUtils;


/**
 * A <code>Transferable</code> which implements the capability required
 * to transfer a <code>XmlTag</code>.
 *  
 * @author Mirco Palese
 */
public class XmlTagSelection implements Transferable , ClipboardOwner{
	
	private static final int XML_TAG = 0;
	private static final int XML_TAG_TO_STRING = 1;
	
	public static DataFlavor xmlTagFlavor = new DataFlavor (XmlTag.class , "Xml tag");;
	public static DataFlavor stringFlavor = DataFlavor.stringFlavor;
	
	
    private static final DataFlavor[] flavors = {
           xmlTagFlavor,
           DataFlavor.stringFlavor,
        };
    
    
    private XmlTag tag;
    
    /**
     * Creates a <code>Transferable</code> capable of transferring
     * the specified <code>XmlTag</code>.
     */
    public XmlTagSelection(XmlTag tag) {
    	this.tag = tag;
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
		if (flavor.equals(flavors[XML_TAG])) {
            return (Object) tag;
        } 
		else if(flavor.equals(flavors[XML_TAG_TO_STRING])) {
			Document doc = XmlTagUtils.getJDomDocumentFromXmlTag(tag);
			String xmlText = PDSCDocumentUtils.getStringFromJDomDocument(doc);
			return xmlText;
		}
		else return null;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		System.out.println("lost ownership");
		
	}

}
