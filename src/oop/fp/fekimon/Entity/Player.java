package oop.fp.fekimon.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oop.fp.fekimon.Elements;
import oop.fp.fekimon.Items.Equipment;

//Player.java

public class Player {
    private int health;
    private int defense;
    private int attackPower;
    private int speed;
    private Elements currentElement; // New field for the element type
    private Set<Equipment> equippedItems;
    private List<Equipment> inventory;
	private String imageFileName;

    public Player(int health, int defense, int attackPower, int speed) {
        this.health = health;
        this.defense = defense;
        this.attackPower = attackPower;
        this.speed = speed;
        this.currentElement = Elements.NEUTRAL; // Default element, replace with your default
        this.imageFileName = "Enemy1.png";        
        this.equippedItems = new HashSet<>();
        this.inventory = new ArrayList<>();
    }

 // Getter methods for the attributes
 public void setHealth(int health) {
     this.health = health;
 }
 
 public int getHealth() {
     return health;
 }
 
 public void setDefense(int defense) {
     this.defense = defense;
 }

 public int getDefense() {
     return defense;
 }

 public int getAttackPower() {
     return attackPower;
 }

 public int getSpeed() {
     return speed;	
 }

 public String getImageFileName() {
     return imageFileName;
 }
 
 public Elements getCurrentElement() {
     return currentElement;
 }

 public void equipItem(Equipment item) {
     if (equippedItems.add(item)) {
         attackPower += item.getAttackBonus();
         defense += item.getDefenseBonus();
         currentElement = item.getElement();
     }
 }

 public void unequipItem(Equipment item) {
     if (equippedItems.remove(item)) {
         attackPower -= item.getAttackBonus();
         defense -= item.getDefenseBonus();
         // Reset or recalculate the element based on remaining equipped items
         currentElement = Elements.NEUTRAL; // or another logic to determine the element
     }
 }

 public boolean isItemEquipped(Equipment item) {
     return equippedItems.contains(item);
 }
 
 public List<Equipment> getInventory() {
     return inventory;
 }

 public void addItemToInventory(Equipment item) {
     inventory.add(item);
 }

 public void removeItemFromInventory(Equipment item) {
     inventory.remove(item);
 }

}

