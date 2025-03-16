package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	private GamePanel gp;
	private KeyHandler keyHandler;
	private final int screenX;
	private final int screenY;

	// For player animation
	private BufferedImage[][] walkFrames;
	private int frameIndex = 0;
	private int frameCounter = 0;
	private int frameDelay = 10;
	private int directionIndex = 0;
	
	// GETTERS
	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}


	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		this.screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
		this.screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		setWorldX(gp.getTileSize() * 46);
		setWorldY(gp.getTileSize() * 42);
		setSpeed(4);
		setDirection("down");
	}

	public void getPlayerImage() {
		try {
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/Sword_Walk_full.png"));

			int rows = 4;
			int cols = 6;
			int frameWidth = spriteSheet.getWidth() / cols;
			int frameHeight = spriteSheet.getHeight() / rows;

			walkFrames = new BufferedImage[rows][cols];

			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					walkFrames[row][col] = spriteSheet.getSubimage(col * frameWidth, row * frameHeight, frameWidth,
							frameHeight); // Get sub images
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		boolean walking = false;

		if (keyHandler.isUpPressed()) {
			setWorldY(getWorldY() - getSpeed());
			setDirection("up");
			directionIndex = 3;
			walking = true;
		} else if (keyHandler.isDownPressed()) {
			setWorldY(getWorldY() + getSpeed());
			setDirection("down");
			directionIndex = 0;
			walking = true;
		} else if (keyHandler.isLeftPressed()) {
			setWorldX(getWorldX() - getSpeed());
			setDirection("left");
			directionIndex = 1;
			walking = true;
		} else if (keyHandler.isRightPressed()) {
			setWorldX(getWorldX() + getSpeed());
			setDirection("right");
			directionIndex = 2;
			walking = true;
		}

		if (walking) { // Change animation frame (Every 10 frames)
			frameCounter++;
			if (frameCounter >= frameDelay) {
				frameIndex = (frameIndex + 1) % walkFrames[directionIndex].length;
				frameCounter = 0;
			}
		} else {
			frameIndex = 0; // If character stops get back to first frame
		}

	}

	public void draw(Graphics g2) {
		BufferedImage image = walkFrames[directionIndex][frameIndex];
		g2.drawImage(image, screenX, screenY, gp.getTileSize() + 96, gp.getTileSize() + 96, null);
	}

}
