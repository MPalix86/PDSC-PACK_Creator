package view.comp;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.LookAndFeel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import business.Session;

public class DialogUtils {
	private Session session;
	
	/**
	 * Show new file creation frame.
	 *
	 * @return chosen destination path 
	 */
	
	public static File showChooseDirectoryFrame() {

		/** setting up fileChooser */
		JFileChooser fileChooser = null;
		LookAndFeel previousLF = UIManager.getLookAndFeel();
		
		 
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        fileChooser = new JFileChooser();
	     
	    } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
		
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Select destination folder");
		
		/** handling user's choice */
		int val = fileChooser.showOpenDialog(null);
		 try {
			UIManager.setLookAndFeel(previousLF);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(val == JFileChooser.APPROVE_OPTION) {
			File destinationPath = fileChooser.getSelectedFile();
			return destinationPath;
		}
		else if(val == JFileChooser.ERROR_OPTION) {  
			JOptionPane.showMessageDialog(null, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else if(val == JFileChooser.CANCEL_OPTION) {}
		return null;
	}
	
	
	
	
	
	/**
	 * Show browse file frame.
	 *
	 * @return chosen path 
	 */
	
	public static File showChooseFileFrame() {
		
		/** setting up fileChooser */
		JFileChooser fileChooser = null;
		
		LookAndFeel previousLF = UIManager.getLookAndFeel();
		
		 
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        fileChooser = new JFileChooser();
	     
	    } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
	   
	    
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(Session.getInstance().getLastDirectoryOpenPath() != null) {
			fileChooser.setCurrentDirectory(Session.getInstance().getLastDirectoryOpenPath());
		}
		
		/** handling user's choice */
		int val = fileChooser.showOpenDialog(null);
		
		 try {
				UIManager.setLookAndFeel(previousLF);
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		if(val == JFileChooser.APPROVE_OPTION) {
			File destinationPath = fileChooser.getSelectedFile();
			Session.getInstance().setLastDirectoryOpenPath(destinationPath);
			return destinationPath;
		}
		else if(val == JFileChooser.ERROR_OPTION) {  
			JOptionPane.showMessageDialog(null, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else if(val == JFileChooser.CANCEL_OPTION) {}
		return null;
	}
	
	
	
	
	public static File showSaveFileFrame() {
		
		/** setting up fileChooser */
		JFileChooser fileChooser = null;
		
		LookAndFeel previousLF = UIManager.getLookAndFeel();
		
		 
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        fileChooser = new JFileChooser();
	     
	    } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
	   
	    
		fileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		
		
		/** handling user's choice */
		int val = fileChooser.showSaveDialog(null);
		
		 try {
				UIManager.setLookAndFeel(previousLF);
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(val == JFileChooser.APPROVE_OPTION) {
			File destinationPath = fileChooser.getSelectedFile();
			Session.getInstance().setLastDirectoryOpenPath(destinationPath);
			return destinationPath;
		}
		else if(val == JFileChooser.ERROR_OPTION) {  
			JOptionPane.showMessageDialog(null, "Some error occurred", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else if(val == JFileChooser.CANCEL_OPTION) {}
		return null;
	}
	
	
	
	

	/**
	 * Show option pane warning message with "yes-no" option
	 * 
	 * @param message	message to show inside option pane
	 * @return true if yes was selected, false otherwise
	 */
	
	public static boolean yesNoWarningMessage(String message) {
		ImageIcon icon = new ImageIcon (DialogUtils.class.getClassLoader().getResource("icons/warning48.png"));
		Object[] options = { "YES", "NO" };
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
		if(value == 0) return true;
		else return false;
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public static void warningMessage(String message) {
		ImageIcon icon = new ImageIcon (DialogUtils.class.getClassLoader().getResource("icons/warning48.png"));
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
	}
	
	
	
	

	public static String showInputDialog(String title, String fieldLabelText) {
        ImageIcon icon = new ImageIcon("src/images/turtle32.png");
        String n = (String)JOptionPane.showInputDialog(null, fieldLabelText, title, JOptionPane.QUESTION_MESSAGE, icon, null, null);
        return n;
	}
	
	
	
	/**
	 * Clone tag option pane. Show number spinner to select number of copy
	 * 
	 * @return selected number of copy
	 */
	
	public  static int cloneDialog() {
		SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
		ImageIcon cloneIcon = new ImageIcon(DialogUtils.class.getClassLoader().getResource("icons/clone40.png"));
		JSpinner spinner = new JSpinner(sModel);
		int option = JOptionPane.showOptionDialog(null, spinner, "Clone Tag", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, cloneIcon, null, null);
		if (option == JOptionPane.CANCEL_OPTION) {}
		else if (option == JOptionPane.OK_OPTION){ return (int)spinner.getValue();}
		return -1;
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public static void okMessage(String message, String title) {
		ImageIcon icon = new ImageIcon (DialogUtils.class.getClassLoader().getResource("icons/ok40.png"));
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, title, JOptionPane.OK_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 icon, options, options[0]); 
	
	}

}
