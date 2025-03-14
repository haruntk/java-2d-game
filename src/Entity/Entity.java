package Entity;

import java.awt.image.BufferedImage;

public class Entity { // Super class for entities

	private int x, y;
	private int speed;
	private String direction;

	// GETTERS
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeed() {
		return speed;
	}
	
    public String getDirection() {
        return direction;
    }

	
	// SETTERS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }

}
