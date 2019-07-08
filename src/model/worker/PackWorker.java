package model.worker;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import model.Pack;

public class PackWorker extends SwingWorker<Integer,String> {
	
	private JFrame statusUpdateFrame;
	
	private Pack pack;
	
	public PackWorker(JFrame statusUpdateFrame , Pack pack) {
		this.statusUpdateFrame = statusUpdateFrame;
		this.pack = pack;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		return null;
	}
	
	@Override
	protected void process(List<String> infoList) {
		
	}
	
	@Override
	protected void done() { 
		
	}
	
	

}
