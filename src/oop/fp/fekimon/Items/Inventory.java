package oop.fp.fekimon.Items;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import oop.fp.fekimon.Elements;

public class Inventory {
    private List<Equipment> equipmentList;

    public Inventory() {
        this.equipmentList = new ArrayList<>();
    }

    public void loadEquipmentFromFile() {
        String filename = "res/Item/equipment.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String name = "", type = "", element = "", imagePath = "";
            int attackBonus = 0, defenseBonus = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("name : ")) {
                    name = line.substring(7);
                } else if (line.startsWith("type : ")) {
                    type = line.substring(7);
                } else if (line.startsWith("element : ")) {
                    element = line.substring(10);
                } else if (line.startsWith("imagePath : ")) {
                    imagePath = line.substring(12);
                } else if (line.startsWith("stat : ")) {
                    String[] stats = line.substring(7).split(", ");
                    for (String stat : stats) {
                        String[] parts = stat.split(" ");
                        int value = Integer.parseInt(parts[1]);
                        if (stat.contains("attack")) {
                            attackBonus = value;
                        } else if (stat.contains("defense")) {
                            defenseBonus = value;
                        }
                    }
                }

                if (!name.isEmpty() && !type.isEmpty() && !element.isEmpty() && !imagePath.isEmpty()) {
                    Elements elementEnum = Elements.valueOf(element.toUpperCase()); // Convert String to Elements enum
                    equipmentList.add(new Equipment(name, type, elementEnum, attackBonus, defenseBonus, imagePath));
                    name = "";
                    type = "";
                    element = "";
                    imagePath = "";
                    attackBonus = 0;
                    defenseBonus = 0;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }
}
