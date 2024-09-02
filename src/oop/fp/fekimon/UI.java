package oop.fp.fekimon;

import java.awt.*;
import java.util.List;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;

import oop.fp.fekimon.Items.Equipment;
import oop.fp.fekimon.Items.Inventory;

public class UI {
	GamePanel gamePanel;
	Graphics2D g2;
	public Rectangle attackButtonRect;
    public Rectangle defendButtonRect;
    private boolean isAttackButtonClicked = false;
    private boolean isDefendButtonClicked = false;
    
    public Rectangle battleButtonRect;
    public Rectangle inventoryButtonRect;
	
	public String currentDialogue = "Test dialogue";
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		initializeMenuButtons();
		
//		 FONT
		try {
			
		}
		catch (Exception e) {
			
		}
	}
	
	public void setAttackButtonClicked(boolean clicked) {
        isAttackButtonClicked = clicked;
    }

    public void setDefendButtonClicked(boolean clicked) {
        isDefendButtonClicked = clicked;
    }
	
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
//		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gamePanel.gameState == gamePanel.titleState) {
			drawTitleScreen();
		}
		
		// MENU STATE
		if(gamePanel.gameState == gamePanel.menuState) {
			drawMenuScreen(g2);
		}
		
		if(gamePanel.gameState == gamePanel.inventoryState) {
		    drawInventoryScreen(g2);
		}
		
		// DIALOGUE STATE	
		if(gamePanel.gameState == gamePanel.dialogueState) {
			drawDialogueScreen();
		}
		
		// BATTLE STATE
		if(gamePanel.gameState == gamePanel.battleState) {
            drawBattleScreen();
        }
	}


	private void drawDialogueScreen() {
		
		// WINDOW
		int x = gamePanel.TILE_SIZE / 2;
		int y = gamePanel.SCREEN_HEIGHT - gamePanel.TILE_SIZE * 7 / 2;
		int width = gamePanel.SCREEN_WIDTH - gamePanel.TILE_SIZE;
		int height = gamePanel.TILE_SIZE * 3;
		
		drawSubWindow(x, y, width, height);
		
		x += gamePanel.TILE_SIZE / 2;
		y += gamePanel.TILE_SIZE;
		g2.setFont(new Font("MONOSPACED", Font.PLAIN, 36));
		g2.drawString(currentDialogue, x, y);
		
	}


	private void drawSubWindow(int x, int y, int width, int height) {
		Color c = Color.darkGray;
		g2.setColor(c);
		g2.fillRoundRect(x+10, y+10, width, height, 35, 35);
		
		c = Color.black;
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = Color.WHITE;
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}


	private void drawTitleScreen() {
		// BACKGROUND
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);
		
		// TITLE NAME
		g2.setFont(new Font("MONOSPACED", Font.BOLD, 128));
		String text = "Fekimon";
		int x = getCenteredTextX(text);
		int y = gamePanel.TILE_SIZE*3;
		
		// SHADOW
		g2.setColor(Color.DARK_GRAY);
		g2.drawString(text, x+5, y+5);
		
		// MAIN
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// MENU
		g2.setFont(new Font("MONOSPACED", Font.BOLD, 24));
		
		text = "PRESS Z OR ENTER TO START";
		x = getCenteredTextX(text);
		y = gamePanel.SCREEN_HEIGHT - gamePanel.TILE_SIZE*1;
		g2.drawString(text, x, y);
	}
	
	private void initializeMenuButtons() {
        int buttonWidth = 150;
        int buttonHeight = 50;
        int spacing = 20;  // Space between buttons

        // Assuming center alignment for buttons
        int startX = (gamePanel.SCREEN_WIDTH - buttonWidth) / 2;
        int startY = (gamePanel.SCREEN_HEIGHT - 2 * buttonHeight - spacing) / 2;

        battleButtonRect = new Rectangle(startX, startY, buttonWidth, buttonHeight);
        inventoryButtonRect = new Rectangle(startX, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
    }
	
	private void drawMenuScreen(Graphics2D g2) {
	    // Draw menu background, etc.

	    // Draw battle button
	    g2.setColor(Color.GRAY);
	    g2.fill(battleButtonRect);
	    g2.setColor(Color.BLACK);
	    g2.draw(battleButtonRect);
	    drawCenteredString("Battle", battleButtonRect, g2);

	    // Draw inventory button
	    g2.setColor(Color.GRAY);
	    g2.fill(inventoryButtonRect);
	    g2.setColor(Color.BLACK);
	    g2.draw(inventoryButtonRect);
	    drawCenteredString("Inventory", inventoryButtonRect, g2);
	}
	private void drawInventoryScreen(Graphics2D g2) {
	    int x = 100; 
	    int y = 100; 
	    int width = gamePanel.SCREEN_WIDTH - 200; 
	    int height = gamePanel.SCREEN_HEIGHT - 200; 

	    drawSubWindow(x, y, width, height); 

	    int slotWidth = 64;
	    int slotHeight = 64;
	    int slotsPerRow = (width - 200) / slotWidth;
	    int spacing = 10; 

	    int startX = x + (width - (slotsPerRow * slotWidth + (slotsPerRow - 1) * spacing)) / 2;
	    int startY = y + 50; 

	    // Load inventory items from file
	    Inventory inventory = new Inventory();
	    inventory.loadEquipmentFromFile();
	    List<Equipment> inventoryItems = inventory.getEquipmentList();

	    // Draw the inventory slots with items
	    for (int i = 0; i < inventoryItems.size(); i++) {
	        Equipment item = inventoryItems.get(i);

	        int slotX = startX + (i % slotsPerRow) * (slotWidth + spacing);
	        int slotY = startY + (i / slotsPerRow) * (slotHeight + spacing);

	        g2.setColor(Color.GRAY);
	        g2.fillRect(slotX, slotY, slotWidth, slotHeight);

	        // Load the image and handle potential errors
	        String imagePath = item.getImagePath();
	        java.net.URL imageUrl = getClass().getClassLoader().getResource(imagePath);
	        if (imageUrl != null) {
	            ImageIcon itemIcon = new ImageIcon(imageUrl);
	            Image itemImage = itemIcon.getImage();
	            g2.drawImage(itemImage, slotX, slotY, slotWidth, slotHeight, null);
	        } else {
	            // Log an error or load a default 'image not found' image
	            System.err.println("Image not found: " + imagePath);
	            // Optionally draw a placeholder or some error text
	            g2.setColor(Color.RED);
	            g2.drawString("Image not found", slotX, slotY + slotHeight / 2);
	        }

	        // Draw the item name or other text
	        g2.setColor(Color.WHITE);
	        g2.drawString(item.getName(), slotX, slotY + slotHeight + 15);
	    }
	    int buttonWidth = 100;
	    int buttonHeight = 30;
	    int buttonX = x; // same as the start of the inventory screen
	    int buttonY = y - buttonHeight - 10; // above the inventory screen

	    g2.setColor(Color.LIGHT_GRAY);
	    g2.fillRect(buttonX, buttonY, buttonWidth, buttonHeight);
	    g2.setColor(Color.BLACK);
	    g2.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
	    g2.drawString("Return", buttonX + 20, buttonY + 20);
	}

	
	private void drawBattleScreen() {
        // Set the background for the battle screen
        g2.setColor(new Color(50, 50, 50)); // Dark gray background
        g2.fillRect(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);

        // Draw Player
        drawPlayer();

        // Draw Enemy
        drawEnemy();

        // Draw Health Bars
        drawHealthBars();
        
        // Draw BattleButton
        drawBattleButtons();
    }
	
	private void drawBattleButtons() {
        // Draw Attack Button
        attackButtonRect = new Rectangle(100, gamePanel.SCREEN_HEIGHT - 150, 150, 50);
        g2.setColor(isAttackButtonClicked ? Color.LIGHT_GRAY : Color.GRAY);
        g2.fill(attackButtonRect);
        g2.setColor(Color.BLACK);
        g2.draw(attackButtonRect);
        drawCenteredString("Attack", attackButtonRect, g2);

        // Draw Defend Button
        defendButtonRect = new Rectangle(300, gamePanel.SCREEN_HEIGHT - 150, 150, 50);
        g2.setColor(isDefendButtonClicked ? Color.LIGHT_GRAY : Color.GRAY);
        g2.fill(defendButtonRect);
        g2.setColor(Color.BLACK);
        g2.draw(defendButtonRect);
        drawCenteredString("Defend", defendButtonRect, g2);
    }

    private void drawPlayer() {
        // Replace with your player's sprite drawing logic
        ImageIcon playerIcon = new ImageIcon(getClass().getClassLoader().getResource("Enemy1.png"));
        Image playerImage = playerIcon.getImage();
        g2.drawImage(playerImage, 100, 500, null); // Example position
    }

    private void drawEnemy() {
        // Replace with your enemy's sprite drawing logic
        ImageIcon enemyIcon = new ImageIcon(getClass().getClassLoader().getResource("Enemy2.png"));
        Image enemyImage = enemyIcon.getImage();
        g2.drawImage(enemyImage, 500, 100, null); // Example position
    }

    private void drawHealthBars() {
        // Example health bars
        g2.setColor(Color.RED);
        g2.fillRect(100, 50, 200, 30); // Player health bar
        g2.fillRect(500, 50, 200, 30); // Enemy health bar

        // Optionally, add text to display actual health numbers
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("MONOSPACED", Font.BOLD, 20));
        g2.drawString("Player HP", 100, 45);
        g2.drawString("Enemy HP", 500, 45);
    }
	
	public int getCenteredTextX(String text) {
		int length = (int)(g2.getFontMetrics().getStringBounds(text, g2)).getWidth();
		int x = gamePanel.SCREEN_WIDTH / 2 - length/2;
		return x;
	}
	
	private void drawCenteredString(String s, Rectangle rect, Graphics2D g) {
        FontMetrics fm = g.getFontMetrics();
        int x = rect.x + (rect.width - fm.stringWidth(s)) / 2;
        int y = rect.y + ((rect.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(s, x, y);
    }

}