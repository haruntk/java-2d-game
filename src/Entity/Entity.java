package Entity;

import java.awt.image.BufferedImage;

public class Entity { // Super class for entities

	private int worldX, worldY;
	private int speed;
	private String direction; // 0: Down, 1: Left, 2: Right, 3: Up

	// GETTERS
	public int getWorldX() {
		return worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public int getSpeed() {
		return speed;
	}
	
    public String getDirection() {
        return direction;
    }

	
	// SETTERS
    public void setWorldX(int x) {
        this.worldX = x;
    }

    public void setWorldY(int y) {
        this.worldY = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }

}
