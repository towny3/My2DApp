package main;

import java.awt.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings
	final int originalTileSize = 16; // 16x16 tiles
	final int maxScreenCol = 16; // number of tiles width
	final int maxScreenRow = 12; // number of tiles height
	
	final int scale = 3; // scaling factor
	
	final int tileSize = originalTileSize * scale;
	final int screenWidth = maxScreenCol * tileSize;
	final int screenHeight = maxScreenRow * tileSize;
	
	Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		while (gameThread != null) {
//			System.out.println("Game loop is running.");
			
			// 1. UPDATE: update information such as character positions
			update();
			
			// 2. DRAW: draw the screen with the updated information
			repaint();
		}
	}
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(100, 100, tileSize, tileSize);
		g2.dispose(); // saves some memory
	}
}
