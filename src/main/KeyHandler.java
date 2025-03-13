package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	private boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int pressedKey = e.getKeyCode();

		if (pressedKey == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (pressedKey == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (pressedKey == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (pressedKey == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int pressedKey = e.getKeyCode();

		if (pressedKey == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (pressedKey == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (pressedKey == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (pressedKey == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
	
	// Getters
    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }
    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }

}
