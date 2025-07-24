package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_up2.png"));

			down1 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_down2.png"));

			left1 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_left2.png"));

			right1 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/link_move_right2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed) {
			direction = "up";
			y -= speed;
		}
		
		if (keyH.leftPressed) {
			direction = "left";
			x -= speed;
		}
		
		if (keyH.downPressed) {
			direction = "down";
			y += speed;
		}
		
		if (keyH.rightPressed) {
			direction = "right";
			x += speed;
		}
		
		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			} else {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			} else {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
