package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;

import business.CustomUtils;
import view.wizardFrame.WizardFrame;




public class StartUp extends JFrame {
	

	public static void main(String[] args) throws Exception {
		

		
		 try {
		      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		      new SetUiManager();
		    } catch (Exception e) {
		      System.err.println("Look and feel not set.");
		    }
		 
		 CustomUtils.setUIFont(new javax.swing.plaf.FontUIResource("SansSerif",Font.PLAIN,14));

		 new WizardFrame(); 
		


		
	}
		
		
		

	
	
}
	

    