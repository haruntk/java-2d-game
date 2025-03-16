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
		mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
		getTileImage();
		loadMap("/maps/map01.txt");
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

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {

				String line = br.readLine(); // Reads text line

				while (col < gp.getMaxScreenCol()) {

					String nums[] = line.split(" ");

					int num = Integer.parseInt(nums[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.getMaxScreenCol()) {
					col = 0;
					row++;
				}
			}
			
			br.close();

		} catch (Exception e) {
		}
	}

	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
			
			int tileNum = mapTileNum[col][row];
			g2.drawImage(tiles[tileNum].getImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
			col++;
			x += gp.getTileSize();
			
			if (col == gp.getMaxScreenCol()) {
				col = 0;
				x = 0;
				row++;
				y += gp.getTileSize();
			}
		}
	}

}
