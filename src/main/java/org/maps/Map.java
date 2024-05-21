package org.maps;


import org.critters.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {
    private void turnManager(ACritter[][] map) {
    }
    private void environmentObjectSpawner() {}
    public void startSimulation() {
        int N = 10;
        int M = 10;
                    ACritter[][] map = new ACritter[10][10];

                    // Przykładowe wypełnienie mapy zwierzętami
                    map[0][0] = new Bear("Bear", 100, 0.2, 0, 0);
                    map[0][1] = new Deer("Deer", 50, 0.5, 0, 1);
                    map[1][0] = new Fox("Fox", 30, 0.3, 1, 0);
    }
    private void PETAHandler() {}
    private void breeder() {}
    private void dataCollector() {}
    private void aging() {}


    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private static void performAction(ACritter critter, ACritter neighbor) {
        if (neighbor instanceof Bear) {

        } else if (neighbor instanceof Deer) {

        } else if (neighbor instanceof Fox) {

        }

    }

//    // gettery
//    public int getX() {
//        return x;
//    }
//    public int getY() {
//        return y;
//    }
}
