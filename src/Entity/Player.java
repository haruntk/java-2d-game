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

	// For player animation
	private BufferedImage[][] walkFrames;
	private int frameIndex = 0;
	private int frameCounter = 0;
	private int frameDelay = 10;
	private int directionIndex = 0;

	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		setX(100);
		setY(100);
		setSpeed(4);
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
							frameHeight);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		boolean walking = false;

		if (keyHandler.isUpPressed()) {
			setY(getY() - getSpeed());
			setDirection("up");
			directionIndex = 3;
			walking = true;
		} else if (keyHandler.isDownPressed()) {
			setY(getY() + getSpeed());
			setDirection("down");
			directionIndex = 0;
			walking = true;
		} else if (keyHandler.isLeftPressed()) {
			setX(getX() - getSpeed());
			setDirection("left");
			directionIndex = 1;
			walking = true;
		} else if (keyHandler.isRightPressed()) {
			setX(getX() + getSpeed());
			setDirection("right");
			directionIndex = 2;
			walking = true;
		}

		if (walking) {
			frameCounter++;
			if (frameCounter >= frameDelay) {
				frameIndex = (frameIndex + 1) % walkFrames[directionIndex].length;
				frameCounter = 0;
			}
		} else {
			frameIndex = 0;
		}

	}

	public void draw(Graphics g2) {
//		g2.setColor(Color.white);
//
//		g2.fillRect(getX(), getY(), gp.getTileSize(), gp.getTileSize());

		BufferedImage image = walkFrames[directionIndex][frameIndex];
		g2.drawImage(image, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
	}

}
