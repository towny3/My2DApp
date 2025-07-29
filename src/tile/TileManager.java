package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	static final String TILE_FILE_SRC = "/tiles/%s.png";
	static final String[] TILE_FILE_NAMES = {
			"dungeon_ground", 
			"dungeon_wall", 
			"dungeon_floor", 
			"dungeon_empty", 
			"dungeon_statue1", 
			"dungeon_statue2",
			"overworld_grass.png",
			"overworld_tombstone.png",
			"overworld_tree.png"
	};
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[TILE_FILE_NAMES.length];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImages();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImages() {
		try {
			for (int i = 0; i < TILE_FILE_NAMES.length; i++) {
				tile[i] = new Tile();
				getTileImage(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getTileImage(int tileNum) throws IOException {
		String tileFilePath = String.format(TILE_FILE_SRC, TILE_FILE_NAMES[tileNum]);
		tile[tileNum].image = ImageIO.read(getClass().getResourceAsStream(tileFilePath));
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				while (col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				
				if (col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			
			// Increment x-position
			col++;
			x += gp.tileSize;
			
			// check if increment y-position
			if (col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				
				row++;
				y += gp.tileSize;
			}
		}
	}
}
