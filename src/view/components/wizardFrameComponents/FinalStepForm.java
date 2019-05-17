package view.Components.wizardFrameComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import listeners.WizardFrameListener;

public class FinalStepForm extends JPanel{
	private WizardFrameListener listener;
	
	public FinalStepForm(WizardFrameListener listenenr) {
		placeComponent();
		this.listener = listenenr;
	}
	
	public FinalStepForm() {
		this.listener = new WizardFrameListener();  
		placeComponent();
	
	}
	
	private void placeComponent() {
		setForeground(Color.BLACK);
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		setPreferredSize(new Dimension(420,590));
		
		JLabel titleLabel = new JLabel("Wizard Complete");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(6, 0, 408, 44);
		add(titleLabel);
		
		JButton generatePdscBtn = new JButton("Generate PDSC");
		generatePdscBtn.setForeground(Color.BLACK);
		generatePdscBtn.setBackground(Color.WHITE);
		generatePdscBtn.setBounds(116, 145, 185, 44);
		generatePdscBtn.addActionListener(listener);
		generatePdscBtn.setActionCommand("generatePdsc");
		
		add(generatePdscBtn);
	}
}
	
	
	

