package Tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	private GamePanel gp;
	private Tile[] tiles;
	private int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tiles = new Tile[48];
		mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		getTileImage();
		loadMap("/maps/world01.txt");
	}

	public void getTileImage() {

		try {
			BufferedImage tileSet = ImageIO
					.read(getClass().getResourceAsStream("/tiles/0x72_DungeonTilesetII_v1.7.png"));

			int tileSize = 16; // Tile set 16x16 size

			int index = 0;
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 6; col++) { // For every sub images we assign an index (0 for wall for example)
					BufferedImage subTile = tileSet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
					tiles[index] = new Tile();
					tiles[index].setImage(subTile);
					index++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadMap(String filePath) { // Load Map Method
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {

				String line = br.readLine(); // Reads text line

				while (col < gp.getMaxWorldCol()) {

					String nums[] = line.split(" ");

					int num = Integer.parseInt(nums[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.getMaxWorldCol()) {
					col = 0;
					row++;
				}
			}
			
			br.close();

		} catch (Exception e) {
		}
	}

	public void draw(Graphics2D g2) { // Draw the map
		
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			// Calculating where the tile will appear on the screen. (Camera Logic)
			int worldX = worldCol * gp.getTileSize();
			int worldY = worldRow * gp.getTileSize();
			int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
			int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
			
			g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
			worldCol++;
			
			if (worldCol == gp.getMaxWorldCol()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

}
