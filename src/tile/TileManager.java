package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		getTileImage();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dungeon_ground.png"));
			

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dungeon_wall.png"));
			

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dungeon_floor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int y = 0;
		
		for (int i = 0; i < 16; i++) {
			if (i <= 5 || i >= 10) {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 4 && i < 11) {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		for (int k = 0; k < 3; k++) {
			y += gp.tileSize;
			
			for (int i = 0; i < 16; i++) {
				if (i > 4 && i < 11) {
					g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
				} else if ((i == 4 || i == 11) && k != 1) {
					g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
				} else {
					g2.drawImage(tile[2].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
				}
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 4 && i < 11) {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 6 && i < 9) {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 5 && i < 9) {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 5 && i < 9) {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 5 && i < 10) {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 5 && i < 10) {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
		
		y += gp.tileSize;
		
		for (int i = 0; i < 16; i++) {
			if (i > 5 && i < 10) {
				g2.drawImage(tile[1].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			} else {
				g2.drawImage(tile[0].image, gp.tileSize * i, y, gp.tileSize, gp.tileSize, null);
			}
		}
	}
}
