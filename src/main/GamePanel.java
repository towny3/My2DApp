package main;

import java.awt.*;

import javax.swing.*;

import entity.Player;
import tile.TileManager;

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
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
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
		
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		// used to calculate the in-game FPS
		long timer = 0;
		int drawCount = 0;
		
		// when the next frame must be drawn
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
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
				// 2. DRAW: draw the screen with the updated information
				repaint(); // draws everything from paintComponent
				
				// wait until the next frame is drawn
				try {
					double remainingTime = nextDrawTime - System.nanoTime();
					remainingTime /= 1000000;
					
					if (remainingTime < 0) {
						remainingTime = 0;
					}
					
					Thread.sleep((long) remainingTime);
					
					nextDrawTime += drawInterval;
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose(); // saves some memory
	}
}
