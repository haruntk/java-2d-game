package Tile;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private boolean collision = false;
	
	// GETTERS
	public BufferedImage getImage() {
		return image;
	}
	
	public boolean isCollision() {
		return collision;
	}
	
	// SETTERS
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

}
