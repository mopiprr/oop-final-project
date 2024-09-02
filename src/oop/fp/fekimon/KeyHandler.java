package oop.fp.fekimon;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class KeyHandler implements KeyListener{
	public boolean up, down, right, left;
	GamePanel gamePanel;
	
	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		/* Registered Keys: 
		 * - Up, Down, Right, Left Key Arrows
		 * - Z, X, Esc
		 * - Etc
		 * */
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if (gamePanel.gameState == gamePanel.titleState) {
			if (code == KeyEvent.VK_Z || code == KeyEvent.VK_ENTER) {
				gamePanel.gameState = gamePanel.menuState;
			}
			if (code == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
		
		if (gamePanel.gameState == gamePanel.menuState) {
			
		}
		
		// GAME STATE
//		if (gamePanel.gameState == gamePanel.gam)
		
		
		if (code == KeyEvent.VK_UP) {
			up = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			down = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			up = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			left = false;
		}
	}
	
}