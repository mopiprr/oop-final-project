package oop.fp.fekimon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import oop.fp.fekimon.Items.Equipment;

public class MouseHandler extends MouseAdapter {
    private GamePanel gamePanel;

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if (gamePanel.gameState == gamePanel.menuState) {
            if (gamePanel.ui.battleButtonRect != null && gamePanel.ui.battleButtonRect.contains(mx, my)) {
                gamePanel.gameState = gamePanel.battleState;
            } else if (gamePanel.ui.inventoryButtonRect != null && gamePanel.ui.inventoryButtonRect.contains(mx, my)) {
                gamePanel.gameState = gamePanel.inventoryState;
            }
        }
        
        if (gamePanel.gameState == gamePanel.inventoryState) {
            // Handle double-click for inventory items
            if (e.getClickCount() == 2) {
                handleInventoryDoubleClick(mx, my);
            }
        }

        if (gamePanel.gameState == gamePanel.battleState) {
            boolean isOnAttackButton = gamePanel.ui.attackButtonRect != null && gamePanel.ui.attackButtonRect.contains(mx, my);
            boolean isOnDefendButton = gamePanel.ui.defendButtonRect != null && gamePanel.ui.defendButtonRect.contains(mx, my);

            gamePanel.ui.setAttackButtonClicked(isOnAttackButton);
            gamePanel.ui.setDefendButtonClicked(isOnDefendButton);

            if (isOnAttackButton) {
                gamePanel.battle.playerAttack();
            } else if (isOnDefendButton) {
                gamePanel.battle.playerDefend();
            }

            gamePanel.repaint();
        }
        
        if ((gamePanel.gameState == gamePanel.inventoryState || gamePanel.gameState == gamePanel.battleState)
                && isReturnButtonClick(mx, my)) {
                handleReturnButtonClick();
            }
    }

        
    private void handleInventoryDoubleClick(int mx, int my) {
        int slotWidth = 64; 
        int slotHeight = 64; 
        int startX = 100; 
        int startY = 100; 
        int cols = 5; 

        List<Equipment> inventoryItems = gamePanel.inventory.getEquipmentList(); 

        for (int i = 0; i < gamePanel.inventory.getEquipmentList().size(); i++) {
            int x = startX + (i % cols) * slotWidth;
            int y = startY + (i / cols) * slotHeight;

            if (mx >= x && mx < x + slotWidth && my >= y && my < y + slotHeight) {
                Equipment clickedItem = gamePanel.inventory.getEquipmentList().get(i);
                
                if (gamePanel.player.isItemEquipped(clickedItem)) {
                    gamePanel.player.unequipItem(clickedItem);
                } else {
                    gamePanel.player.equipItem(clickedItem);
                }

                break;
            }
        }
    }
    
    private boolean isReturnButtonClick(int mx, int my) {
        // Define the bounds of the "Return" button
        int buttonX = 100; // X-coordinate
        int buttonY = 50;  // Y-coordinate
        int buttonWidth = 100; // Width
        int buttonHeight = 30;  // Height

        return mx >= buttonX && mx < buttonX + buttonWidth && my >= buttonY && my < buttonY + buttonHeight;
    }	

    private void handleReturnButtonClick() {
        // Change the game state to MenuScreen
        gamePanel.gameState = gamePanel.menuState;
    }
}
