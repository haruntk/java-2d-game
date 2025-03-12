package main;

import java.awt.Color;
import java.awt.Dimension;

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
	
	private Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true); // Enables double buffering to reduce flickering and improve rendering performance.
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() { // When gameThread starts, it will automatically call run method
		
	}

}
