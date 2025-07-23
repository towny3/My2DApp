package main;

import javax.swing.*;

public class Main {
	public static String WINDOW_TITLE = "2D Adventure";
	
	public static void main(String[] args) {
		// create the window
		JFrame window = new JFrame(WINDOW_TITLE);
		
		// sets what the X button does
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		// create the rendering surface
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		// causes the window to be sized to fit the size and layouts of its components
		window.pack();
		
		// causes the window to appear in the middle of the screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}
