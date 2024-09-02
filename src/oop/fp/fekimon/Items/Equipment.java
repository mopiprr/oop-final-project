package oop.fp.fekimon.Items;

import oop.fp.fekimon.Elements;

public class Equipment {
    private String name;
    private String type;
    private Elements element;
    private int attackBonus;
    private int defenseBonus;
    private String imagePath; // Path to the image file

    public Equipment(String name, String type, Elements element, int attackBonus, int defenseBonus, String imagePath) {
        this.name = name;
        this.type = type;
        this.element = element;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Elements getElement() {
        return this.element; // Returns the element of the equipment
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", element='" + element + '\'' +
                ", attackBonus=" + attackBonus +
                ", defenseBonus=" + defenseBonus +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
