package org.maps;


import org.critters.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {
    private void turnManager() {}
    private void environmentObjectSpawner() {}
    public void startSimulation() {}
    private void PETAHandler() {}
    private void breeder() {}
    private void dataCollector() {}
    private void aging() {}

    public void checkAndDamage() {
        for (--) {
            if (--) {
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