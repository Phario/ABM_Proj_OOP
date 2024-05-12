package org.maps;


import org.critters.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {
    private int width, height;
    private HashMap<Integer, List<ACritter>> map = new HashMap<>();
    // each key is an integer representing a specific location (calculated from x and y coordinates)
    // each value is a List of ACritter objects present at that location

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < width * height; i++) {
            map.put(i, new ArrayList<>());
        }
    }
    //Sets the width and height.
    //Loops through each possible position (from 0 to width*height-1) and initializes each position with an empty ArrayList.


    public void placeCritter(ACritter critter) {
        int index = critter.getY() * width + critter.getX();
        map.get(index).add(critter);
    }
    //calculates the index for the critter's position, and adds it to that position


    public List<ACritter> getCrittersAt(int x, int y) {
        int index = y * width + x;
        return map.getOrDefault(index, new ArrayList<>());
    }
    // Uses getOrDefault to return the list of critters at the given index.
    // If no critters are present at that index, it returns an empty list.

    public void checkAndDamage() {
        for (List<ACritter> critters : map.values()) {
            if (critters.size() > 1) {
                boolean hasFox = false, hasWolf = false, hasBear = false;
                for (ACritter critter : critters) {
                    if (critter instanceof Fox) hasFox = true;
                    if (critter instanceof Wolf) hasWolf = true;
                    if (critter instanceof Bear) hasBear = true;
                }

                for (ACritter critter : critters) {
                    if (hasFox && critter instanceof Hare) {
                        critter.receiveDamage(50); // Fox damages Hare
                    }
                    if (hasWolf && critter instanceof Hare) {
                        critter.receiveDamage(100); // Wolf damages Hare
                    }
                    if (hasWolf && critter instanceof Deer) {
                        critter.receiveDamage(100); // Wolf damages Deer
                    }
                    if (hasBear && critter instanceof Deer) {
                        critter.receiveDamage(200); // Bear damages Deer
                    }
                    // we need to increase hunger after dealing dmg
                }
            }
        }
    }
}