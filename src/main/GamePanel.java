package main;

import java.awt.*;

import javax.swing.*;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings
	final int originalTileSize = 16; // 16x16 tiles
	final int maxScreenCol = 16; // number of tiles width
	final int maxScreenRow = 12; // number of tiles height
	
	final int scale = 3; // scaling factor
	
	public final int tileSize = originalTileSize * scale;
	final int screenWidth = maxScreenCol * tileSize;
	final int screenHeight = maxScreenRow * tileSize;
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
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
		// Time between frames
		double drawInterval = 1000000000/FPS;
		
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		// used to calculate the in-game FPS
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				
				// 1. UPDATE: update information such as character positions
				update(); // handles the logic of the game loop
				
				// 2. DRAW: draw the screen with the updated information
				repaint(); // draws everything from paintComponent
				drawCount++;
				
				delta--;
			}
			
			// Once the timer reaches 1 second
			if (timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	// updated each frame
	public void update() {
		player.update();
	}
	
	// painted onto the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose(); // saves some memory
	}
}
