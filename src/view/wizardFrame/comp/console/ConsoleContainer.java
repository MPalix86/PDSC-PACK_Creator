package view.wizardFrame.comp.console;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ConsoleContainer extends JPanel{
	
	private JScrollPane scrollPane;
	private Console console;

	public ConsoleContainer() {
		console = new Console();
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		horizontalScrollBar.setUnitIncrement(40);
		
		JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
		verticalScrollBar.setUnitIncrement(40);

		this.scrollPane = new JScrollPane(console);
		
		scrollPane.setHorizontalScrollBar(horizontalScrollBar);
		scrollPane.setVerticalScrollBar(verticalScrollBar);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		
		this.add(scrollPane,BorderLayout.CENTER);
	}
	
	
	public Console getConsole() {
		return this.console;
	}

}
