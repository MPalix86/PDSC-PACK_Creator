package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import business.utils.CustomUtils;

public class ContactListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("stSite")) {
			System.out.println("apro l'url");
			try {
				CustomUtils.openWebpage(new URL("https://www.st.com/"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		
		else if(command.equals("facebookSite")) {
			
			try {
				CustomUtils.openWebpage(new URL("https://www.facebook.com/STMicroelectronics.NV"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		
		else if(command.equals("twitterSite")) {
		
			try {
				CustomUtils.openWebpage(new URL("https://twitter.com/st_world"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		
		else if(command.equals("youtubeSite")) {
			
			try {
				CustomUtils.openWebpage(new URL("https://www.youtube.com/c/STMicroelectronics"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		
		else if (command.equals("PDSCStandard")) {
			try {
				CustomUtils.openWebpage(new URL("https://www.keil.com/pack/doc/CMSIS/Pack/html/packFormat.html"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}


}
