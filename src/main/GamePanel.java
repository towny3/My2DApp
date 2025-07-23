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
	
	// FPS
	int fps = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	// sets player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	// the game loop
	@Override
	public void run() {
		while (gameThread != null) {
			
			// 1. UPDATE: update information such as character positions
			update(); // handles the logic of the game loop
			
			// 2. DRAW: draw the screen with the updated information
			repaint(); // draws everything from paintComponent
		}
	}
	
	// updated each frame
	public void update() {
		if (keyH.upPressed) {
			playerY -= playerSpeed;
		}
		
		if (keyH.leftPressed) {
			playerX -= playerSpeed;
		}
		
		if (keyH.downPressed) {
			playerY += playerSpeed;
		}
		
		if (keyH.rightPressed) {
			playerX += playerSpeed;
		}
	}
	
	// painted onto the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose(); // saves some memory
	}
}
