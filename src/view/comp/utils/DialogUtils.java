package view.comp.utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileView;

import business.Session;
import business.TagManager;
import model.Response;
import model.xml.XmlAttribute;
import model.xml.XmlTag;
import view.comp.AttributeCheckBox;

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
	
	public static File showChooseFileFrame(File directoryToOpen, boolean setTraversable) {
		
		/** setting up fileChooser */
		JFileChooser fileChooser = null;
		
		LookAndFeel previousLF = UIManager.getLookAndFeel();
		
		 
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        fileChooser = new JFileChooser(directoryToOpen);
	        if(!setTraversable) {
	 	        fileChooser.setFileView(new FileView() {
	 	            @Override
	 	            public Boolean isTraversable(File f) {
	 	                 return directoryToOpen.equals(f);
	 	            }
	 	        });
	        }
	       
	     
	    } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
	   
	    
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(directoryToOpen == null) {
			if(Session.getInstance().getLastDirectoryOpenPath() != null) {
				/** 
				 * LastDirectoryOpenPath is used to to open the " next file chooser " in  LastDirectoryOpenPath directly.
				 * set it only if setTraversable is true to avoid security issue and to avoid user's access to non traversable
				 * folder in future.
				 */
				if(setTraversable)fileChooser.setCurrentDirectory(Session.getInstance().getLastDirectoryOpenPath());
			}
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
	
	
	
	
	/**
	 * Same as function above but without any folder to pen
	 * in this case starts from home (~) or last open directory
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
	
	
	
	
	public static File[] showChooseMultipleFilesFrame() {

		JFileChooser chooser = null;
		
		
		LookAndFeel previousLF = UIManager.getLookAndFeel();
		
		 
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        chooser = new JFileChooser();
	        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        chooser.setMultiSelectionEnabled(true);
			chooser.showOpenDialog(null);
	     
	    } catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException | ClassNotFoundException e) {}
	  
	    
	
		
		if(Session.getInstance().getLastDirectoryOpenPath() != null) {
			if(chooser  != null) chooser.setCurrentDirectory(Session.getInstance().getLastDirectoryOpenPath());
		}
		
		 try {
				UIManager.setLookAndFeel(previousLF);
		} 
		 catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		 

		 
		File[] files = chooser.getSelectedFiles();
		 
		return files;
		 
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
		Object[] options = { "YES", "NO" };
		int value = JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, IconsUtils.getWarningIcon(40), options, options[0]); 
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
		ImageIcon icon = IconsUtils.getWarningIcon(40);
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, "Warning", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
	}
	
	
	
	

	public static String showInputDialog(String title, String fieldLabelText) {
        String n = (String)JOptionPane.showInputDialog(null, fieldLabelText, title, JOptionPane.QUESTION_MESSAGE, null, null, null);
        return n;
	}
	
	
	
	/**
	 * Clone tag option pane. Show number spinner to select number of copy
	 * 
	 * @return selected number of copy
	 */
	
	public  static int cloneDialog() {
		SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
		JSpinner spinner = new JSpinner(sModel);
		int option = JOptionPane.showOptionDialog(null, spinner, "Clone Tag", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, IconsUtils.getCloneIcon(40), null, null);
		if (option == JOptionPane.CANCEL_OPTION) {}
		else if (option == JOptionPane.OK_OPTION){ 
			System.out.println("premuto ok" + (int) spinner.getValue());
			
			return (int)spinner.getValue();
			}
		return -1;
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public static void okMessage(String message, String title, ImageIcon icon) {
		Object[] options = { "OK"};
		JOptionPane.showOptionDialog (null, message, title, JOptionPane.OK_OPTION,
				 JOptionPane.INFORMATION_MESSAGE,
				 icon, options, options[0]); 
	
	}
	
	
	
	
	/**
	 * Show option pane warning message with only "ok" option
	 * 
	 * @param message	message to show inside option pane
	 * @return void
	 */
	
	public static void okMessage(String message, String title, Icon icon) {
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
	
	
	
	
	public static int indexTagEnumJoptionPane(XmlTag parent) {
		JComboBox <XmlTag> jcb = new JComboBox(parent.getSelectedChildrenArr().toArray());
		jcb.setRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                if(value instanceof XmlTag){
	                	XmlTag tag = (XmlTag) value;
	                    setText("paste after element " + tag.getName());
	                }
	                return this;
	            }
	        } );
		Object[] options = { "Paste", "Cancel" };
		int choice = JOptionPane.showOptionDialog(null, jcb, "Paste tag after element : ", 0,JOptionPane.INFORMATION_MESSAGE, IconsUtils.FAgetClipboardIcon(48, ColorUtils.FOLDER_BROWN), options, options[0]); 
		System.out.println("choice " + choice);
		if(choice == 0) {
			int index =  jcb.getSelectedIndex();
			return index + 1;
		
		}
		else return -1;
	}
	
	
	public static boolean CustomButtonsTrueFalsePane(String title ,String message, String trueButtonMessage, String falseButtonMessage, ImageIcon icon) {
		Object[] options = { trueButtonMessage, falseButtonMessage };
		int value = JOptionPane.showOptionDialog (null, message, title , JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
		if(value == 0) return true;
		else return false;
	
	}
	
	
	
	public static int CustomButtonsMessagePane(String title ,String message, Object[] horizontalElements, String falseButtonMessage, ImageIcon icon) {
		int value = -1;
		value = JOptionPane.showOptionDialog (null, message, title , JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, horizontalElements, horizontalElements[0]); 
		return value;
	
	}
	
	
	
	public static int AddCustomAttribute(String title ,String message,Object[] options , ImageIcon icon) {
		int value = JOptionPane.showOptionDialog (null, message, title , JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon, options, options[0]); 
		return value;
	
	}
	
	
	
	public static Response multipleFilesTagSettingsDialog(XmlTag tag, String title ,String message,ImageIcon icon) {
		
		int size = tag.getAttrArr().size();
		Object[] options = new Object[size + 5] ;
		options[0] = "Select desired attributes for tag <file> \n";
		options[1] = new JSeparator();
		if(tag.getAttrArr() != null) {
			for (int i = 0; i < tag.getAttrArr().size(); i++) {
				XmlAttribute attr = tag.getAttrArr().get(i);
				AttributeCheckBox c = new AttributeCheckBox(attr);
				options[i + 2] = c;
				c.setForeground(ColorUtils.ATTR_COLOR);
				c.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						AttributeCheckBox c = (AttributeCheckBox) e.getItem();
						XmlAttribute attr =  c.getAttr();
						/** if attribute was selected */
						if(c.isSelected()) {
							if(!tag.containsAttr(attr)) TagManager.addAttributeInTag(tag, new XmlAttribute(attr, attr.getTag()), false, false, null);
						}	
						else {
							if(tag.containsAttr(attr)) TagManager.removeSelectedAttributeFromParent(attr, tag, false, false);
						}
						
					}
					
				});
				
				if(attr.isRequired()) c.setSelected(true);
	
			}
		}
		JTextField f = new JTextField();
		options[size + 2] = new JSeparator();
		options[size + 3] = "\nInsert common path to all files\n";
		options[size + 4] = f;
		Object[] buttons = { "Select Files", "cancel"};
		int val = JOptionPane.showOptionDialog (null, options, title , JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, icon, buttons, buttons); 
		return new Response.ResponseBuilder().status(val).message(f.getText()).build();
	}
	
	
	
	
	
	
	
	
	

	


}
