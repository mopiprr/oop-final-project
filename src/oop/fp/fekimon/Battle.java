package oop.fp.fekimon;

//package main;
//
//import Entity.Player;
//import Entity.Enemy;
//import java.util.Scanner;
//
//public class Battle {
//  private Player player;
//  private Enemy enemy;
//  private Scanner scanner;
//
//  public Battle(Player player, Enemy enemy) {
//      this.player = player;
//      this.enemy = enemy;
//      this.scanner = new Scanner(System.in);
//  }
//
//  public void start() {
//      while (player.getHealth() > 0 && enemy.getHealth() > 0) {
//          System.out.println("The battle begins!");
//          System.out.println("Choose an action: \n1. Attack \n2. Defend");
//
//          int choice = scanner.nextInt();
//          boolean playerDefended = false;
//
//          if (choice == 2) {
//              System.out.println("Player chooses to defend!");
//              playerDefended = true;
//              player.setDefense(player.getDefense() * 2); // Double the defense
//          }
//
//          if (player.getSpeed() >= enemy.getSpeed()) {
//              if (!playerDefended) {
//                  playerAttack();
//              }
//              enemyAttack();
//          } else {
//              enemyAttack();
//              if (!playerDefended) {
//                  playerAttack();
//              }
//          }
//
//          // Reset player defense if they defended this turn
//          if (playerDefended) {
//              player.setDefense(player.getDefense() / 2);
//          }
//
//          // Display current health status
//          System.out.println("Player Health: " + player.getHealth());
//          System.out.println("Enemy Health: " + enemy.getHealth());
//
//          System.out.println("Press Enter to continue...");
//          scanner.nextLine();
//          scanner.nextLine(); // To catch the newline character
//      }
//
//      if (player.getHealth() <= 0) {
//          System.out.println("Player is defeated!");
//      } else {
//          System.out.println("Enemy is defeated!");
//      }
//  }
//
//  private void playerAttack() {
//      System.out.println("Player attacks!");
//      int damage = Math.max(0, player.getAttackPower() - enemy.getDefense());
//      enemy.setHealth(enemy.getHealth() - damage);
//      System.out.println("Enemy takes " + damage + " damage.");
//  }
//
//  private void enemyAttack() {
//      System.out.println("Enemy attacks!");
//      int damage = Math.max(0, enemy.getAttackPower() - player.getDefense());
//      player.setHealth(player.getHealth() - damage);
//      System.out.println("Player takes " + damage + " damage.");
//  }
//}


import oop.fp.fekimon.Entity.Player;
import oop.fp.fekimon.Entity.Enemy;
import oop.fp.fekimon.Elements;

public class Battle {
    private Player player;
    private Enemy enemy;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void playerAttack() {
        int baseDamage = player.getAttackPower() - enemy.getDefense();
        int damage = calculateElementalDamage(player.getCurrentElement(), enemy.getCurrentElement(), baseDamage);
        enemy.setHealth(enemy.getHealth() - damage);
        if (enemy.getHealth() > 0) {
            enemyAttack();
        }
        checkEndCondition();
    }

    public void playerDefend() {
        int originalDefense = player.getDefense();
        player.setDefense(originalDefense * 2);
        enemyAttack();
        player.setDefense(originalDefense);
        checkEndCondition();
    }

    private void enemyAttack() {
        int baseDamage = enemy.getAttackPower() - player.getDefense();
        int damage = calculateElementalDamage(enemy.getCurrentElement(), player.getCurrentElement(), baseDamage);
        player.setHealth(player.getHealth() - damage);
        checkEndCondition();
    }

    private int calculateElementalDamage(Elements attacker, Elements defender, int baseDamage) {
        if (attacker == Elements.FIRE && defender == Elements.GRASS) {
            return baseDamage * 2;
        } else if (attacker == Elements.GRASS && defender == Elements.FIRE) {
            return baseDamage / 2;
        } else if (attacker == Elements.WATER && defender == Elements.FIRE) {
            return baseDamage * 2;
        } else if (attacker == Elements.FIRE && defender == Elements.WATER) {
            return baseDamage / 2;
        }
        // Add more elemental interactions as needed
        return baseDamage;
    }

    private void checkEndCondition() {
        if (player.getHealth() <= 0) {
            System.out.println("Player is defeated!");
        } else if (enemy.getHealth() <= 0) {
            System.out.println("Enemy is defeated!");
        } else {
            // No one is defeated, continue the battle
            System.out.println("Player Health: " + player.getHealth());
            System.out.println("Enemy Health: " + enemy.getHealth());
        }
    }
}



