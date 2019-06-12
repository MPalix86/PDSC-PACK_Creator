package view;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.wizardFrame.WizardFrame;




public class StartUp extends JFrame {
	

	public static void main(String[] args) throws Exception {
		

		
		
		  try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		  WizardFrame frame = new WizardFrame(); 
		


//		XmlTagDao tagDao = XmlTagDao.getInstance();
//		XmlTag root = tagDao.getRootTag();
//		XmlTag components = tagDao.getTagFromIdAndParent(11, root);
//		XmlTagBusiness.printTag(components, 0);
		
	}
		
		
		

	
	
}
	

    