package view.comp.utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.LookAndFeel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import business.Session;
import model.XmlTag;
import view.comp.SquareButton;

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
	   
	    
		fileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
		
		
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
	
	
	
	/**
	 * Show JDialog without buttons only that go off after "millis" seconds
	 * 
	 * @param message	message to show in dialog
	 * @param icon		icon to set in dialog	
	 * @param millis	number of seconds before dialog dispose
	 */
	
	public static void  noButtonsTemporaryMessage(String message, ImageIcon icon , int millis , Component parentFrame ) {
		final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon, new Object[]{}, null);
		final JDialog dialog = new JDialog();
		if(parentFrame != null) dialog.setLocationRelativeTo(parentFrame);
		dialog.setTitle("Message");
		dialog.setModal(true);

		dialog.setContentPane(optionPane);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();

		//create timer to dispose of dialog after "millis" seconds
		Timer timer = new Timer(millis, new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		        dialog.dispose();
		    }
		});
		
		timer.setRepeats(false);//the timer should only go off once

		//start timer to close JDialog as dialog modal we must start the timer before its visible
		timer.start();

		dialog.setVisible(true);
	}
	
	
	
	
	public static int intEnumJoptionPane(XmlTag parent) {
		JComboBox <XmlTag> jcb = new JComboBox(parent.getSelectedChildrenArr().toArray());
		SquareButton addBtn = new SquareButton("Add");
		Object[] options = { "Paste", "Cncel" };
		int choice = JOptionPane.showOptionDialog(null, jcb, "Paste tag after element : ", 0,JOptionPane.INFORMATION_MESSAGE, IconUtils.FAgetClipboardIcon(48, ColorUtils.FOLDER_BROWN), options, options[0]); 
		System.out.println("choice " + choice);
		if(choice == 0) {
			int index =  jcb.getSelectedIndex();
			return index + 1;
		
		}
		else return -1;
	}
	
	
	public static boolean CustomButtonTrueFalsePane(String message, String trueButtonMessage, String falseButtonMessage, ImageIcon icon) {
		Object[] options = { trueButtonMessage, falseButtonMessage };
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
		if(value == 0) return true;
		else return false;
	
	}
	
	


}
