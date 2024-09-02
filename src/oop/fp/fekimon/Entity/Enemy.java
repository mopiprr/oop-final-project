package oop.fp.fekimon.Entity;

import oop.fp.fekimon.Elements;

//Enemy.java

public class Enemy {
    private String name;
    private int health;
    private int defense;
    private int attackPower;
    private int speed;
    private Elements element;
    private String imageFileName;

    public Enemy(String name, int health, int defense, int attackPower, int speed, Elements element, String imageFileName) {
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.attackPower = attackPower;
        this.speed = speed;
        this.element = element;
        this.imageFileName = imageFileName;
    }

 // Getter methods for the attributes
 public void setHealth(int health) {
     this.health = health;
 }
 
 public int getHealth() {
     return health;
 }
 
 public Elements getCurrentElement() {
     return element;
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
 

 // Other methods and attributes...
}

