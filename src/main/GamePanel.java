package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	private final int originalTitleSize = 16; // 16x16 tile (characters etc.)
	private final int scale = 3; // For scaling characters etc. for higher resolutions

	private final int tileSize = originalTitleSize * scale; // 48x48 tile
	private final int maxScreenCol = 16;
	private final int maxScreenRow = 12;
	private final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	private final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// FPS
	int FPS = 60;

	private KeyHandler keyHandler = new KeyHandler();
	private Thread gameThread;

	// Set player's default position
	private int playerX = 100;
	private int playerY = 100;
	private int playerSpeed = 4;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true); // Enables double buffering to reduce flickering and improve rendering
										// performance.
		this.addKeyListener(keyHandler);
		this.setFocusable(true); // To receive key inputs

	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() { // When gameThread starts, it will automatically call run method

		double drawInterval = 1000000000 / FPS; // 0.0166 seconds FPS based timing
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) { // Game loop

			update();

			repaint(); // Sends a repaint request. Draws the updated content by calling the
						// paintComponent(Graphics g) method at the appropriate time.

			try {
				double remainingTime = nextDrawTime - System.nanoTime(); // Time left until next drawing (to keep FPS constant)
				remainingTime = remainingTime / 1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void update() { // UPDATE: updates information such as character positions

		if (keyHandler.isUpPressed()) { // X increases to the right & Y increases to the down
			playerY -= playerSpeed;
		} else if (keyHandler.isDownPressed()) {
			playerY += playerSpeed;
		} else if (keyHandler.isRightPressed()) {
			playerX += playerSpeed;
		} else if (keyHandler.isLeftPressed()) {
			playerX -= playerSpeed;
		}
	}

	public void paintComponent(Graphics g) { // DRAW: draws the screen with the updated information

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g; // For drawing

		g2.setColor(Color.white);

		g2.fillRect(playerX, playerY, tileSize, tileSize);

		g2.dispose();
	}

}
