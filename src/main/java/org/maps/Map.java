package org.maps;


import org.critters.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map {
    public static ACritter[][] map;             //map that contains the instances of ACritter subclasses
    private void turnManager(ACritter[][] map) {//takes the map as a parameter and a random number generator to
                                                //decide which critter moves first, then the critterTurnManager takes over
                                                //it creates a list of critters and removes them after they move, they are chosen
                                                //based on their ID
    }
    private void critterTurnManager(ACritter[][] map) {
    }                                           //manages a critter's turn depending on what kind of object it is
                                                //needs a switch/case statement dependent on species, calls on other methods
                                                //so it will be the last one to be written
    private void environmentObjectSpawner() {}  //takes 2 random numbers from 0 to array length/width and
                                                //places an object there only if the space is free, if it isn't then
                                                //it looks again for a free space, needs input parameters such as
                                                //type of object to spawn and the amount of the object
    public void startSimulation() {
        int N = 10;
        int M = 10;
                    map = new ACritter[N][M];

                    // Przykładowe wypełnienie mapy zwierzętami
                    map[0][0] = new Bear("Bear", 100, 0.2, 0, 0);
                    map[0][1] = new Deer("Deer", 50, 0.5, 0, 1);
                    map[1][0] = new Fox("Fox", 30, 0.3, 1, 0);
                    System.out.println(map[1][0].getSpecies());
    }
    private void PETAHandler() {}   //removes an object from the map, needs x and y coordinates of the object OR the ID
                                    //choose whichever one seems easier to implement
                                    //ID would search the entire map for the animal and remove it
    private void breeder() {}       //looks for an empty space around an animal with scanEnvironment and places a new
                                    //instance of the animal there with age = 0
    private void dataCollector() {} //at the end of every turn opens a file, writes the data such as amount of objects
                                    //and closes the file in case you wanted to stop the simulation early
    private void aging() {}         //increments an object's age and calls PETAHandler to remove object if it's too old


    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /*private static void performAction(ACritter critter, ACritter neighbor) {
        if (neighbor instanceof Bear) {

        } else if (neighbor instanceof Deer) {

        } else if (neighbor instanceof Fox) {

        }

    }*/ //commented for now, the critter turn manager is going to check each animal's parameters and then make decisions
    public static String getArrayObjectName(int x, int y) {
        return map[x][y].getSpecies();
    }//figure out how to get an object's name from an array's field and return it
}
