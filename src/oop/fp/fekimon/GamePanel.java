package oop.fp.fekimon;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import oop.fp.fekimon.Entity.Enemy;
import oop.fp.fekimon.Entity.Player;
import oop.fp.fekimon.Items.Equipment;
import oop.fp.fekimon.Items.Inventory;


public class GamePanel extends JPanel implements ActionListener, Runnable{
	// VARIABLES
	final int TILE_SIZE = 64;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int SCREEN_WIDTH = TILE_SIZE * maxScreenCol;
	final int SCREEN_HEIGHT = TILE_SIZE * maxScreenRow;
	final int REFRESH_RATE = 60;
	
	// SYSTEM
	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler(this);
	public UI ui = new UI(this);
	MouseHandler mouseHandler = new MouseHandler(this);
	
	// INVENTORY
	public Inventory inventory;
	Equipment equipment;
	
	
	// BATTLE
	Player player;
    Enemy enemy;
    Battle battle;
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int menuState = 1;
	public final int dialogueState = 2;
	public final int battleState = 3;
	public final int inventoryState = 4;
	
	public int nestedState;
	public final int newSaveScreen = 0;
	public final int existingSaveScreen = 1;
	public final int controlScreen = 2;
	public final int avatarCreationScreen = 3;
	
	
	public GamePanel() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setDoubleBuffered(true);
		addKeyListener(keyHandler);
		addMouseListener(mouseHandler);
		setFocusable(true);
	}
	
	public void setupGame() {
		gameState = titleState;
		setupBattle();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public List<Equipment> getPlayerInventory() {
        if (player != null) {
            return player.getInventory(); 
        } else {
            return new ArrayList<>(); 
        }
    }
	
	public void someMethod() {
        List<Equipment> inventoryItems = getPlayerInventory();
    }
	
	private void setupBattle() {
        player = new Player(100, 20, 10, 5);
        enemy = new Enemy("Dragon", 80, 15, 8, 4, Elements.FIRE, "Enemy2.png");

        // Initialize battle
        battle = new Battle(player, enemy);
    }

	@Override
	public void run() {
		while(gameThread != null) {
			
			
			// Draw Screen!
			update();
			repaint();
			
//			System.out.println("Game is running on state " + gameState);
			
			try {
				Thread.sleep(1000 / REFRESH_RATE);
			}
			catch (InterruptedException ie) {}
		}
	}
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// UI
		ui.draw(g2);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
}