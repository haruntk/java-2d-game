package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lets the window close when user clicks the close button
		window.setResizable(false);
		window.setTitle("2D-Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null); // The location of the window is not specified so it will always appear at the center of the screen
		window.setVisible(true);
	}

}
