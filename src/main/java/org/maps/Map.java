package org.maps;


import org.critters.*;
import java.util.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class Map {
    private static Random random = new Random();
    public static ACritter[][] map;             //map that contains the instances of ACritter subclasses
    private void turnManager(ACritter[][] map) {
        ArrayList<Integer> critterIDList = new ArrayList<Integer>();
        //scans the map for critters and adds their ids to a list
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && !"NULL".equals(map[i][j].getSpecies())) {
                    critterIDList.add(map[i][j].getCritterID());
                }
            }
        }
        //uses a random number generator to choose a number from a list,
        //takes a critter's id and looks for it on the map
        //then uses critterTurnManager so the critter can make his move
        while(!critterIDList.isEmpty()) {
            boolean moveFinished = false;
            int critterIndex = random.nextInt(critterIDList.size());
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    //checks if an object has the correct ID and lets it do its turn
                    if (map[i][j] != null && !"NULL".equals(map[i][j].getSpecies()) && map[i][j].getCritterID().equals(critterIDList.get(critterIndex))) {
                        critterTurnManager(map, critterIDList.get(critterIndex));
                        moveFinished = true;
                        break;
                        //end search when correct ID is found
                    }

                }
                if (moveFinished) break;
                //end search when correct ID is found
            }
            critterIDList.remove(critterIndex);
            //removes an object from an array so it can't make 2 moves in a turn
        }
    }
    //takes the map as a parameter and a random number generator to
    //decide which critter moves first, then the critterTurnManager takes over so the critter can make its turn
    //This method creates a list of critters and removes them after they move, they are chosen
    //based on their ID
    //turnManager manages ONLY 1 TURN, the main method is going to have a loop with the number of turns
    private void critterTurnManager(ACritter[][] map, Integer critterID) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && !"NULL".equals(map[i][j].getSpecies())) {
                    //needs a method to get move a critter based on his id and a switch/case statement
                    //to make a critter's move based on his species
                }
            }
        }
    }
    //input: map and critterID
    //manages a critter's turn depending on what kind of object it is
    //needs a switch/case statement dependent on species, calls on other methods
    //so it will be the last one to be written
    private void environmentObjectSpawner(String objectType, int amount) {
        int rows = map.length;
        int cols = map[0].length;

        for (int i = 0; i < amount; i++) {
            boolean placed = false;

            while (!placed) {
                int x = random.nextInt(rows);
                int y = random.nextInt(cols);

                if (map[x][y] == null) {
                    ACritter newCritter = createCritter(objectType, x, y);
                    if (newCritter != null) {
                        map[x][y] = newCritter;
                        placed = true;
                    }
                }
            }
        }
    }                                           //takes 2 random numbers from 0 to array length/width and
                                                //places an object there only if the space is free, if it isn't then
                                                //it looks again for a free space, needs input parameters such as
                                                //type of object to spawn and the amount of the object

    private ACritter createCritter(String objectType, int x, int y) {
        switch (objectType) {
            case "Bear":
                return new Bear("Bear", 100, 0.2, x, y);
            case "Deer":
                return new Deer("Deer", 50, 0.5, x, y);
            case "Fox":
                return new Fox("Fox", 1, 0.3, x, y);
            case "Hare":
                return new Hare("Hare", 1, 0.4, x, y);
            case "Wolf":
                return new Wolf("Wolf", 80, 0.3, x, y);
            case "Berries":
                return new Berries("Berries", 3, 0, x, y);
            case "Burrows":
                return new Burrows("Burrow", 5, 0, x, y);
            default:
                System.out.println("Unknown type: " + objectType);
                return null;
        }
        // Input: coordinates and species
        // Usage: creates an object of the given coordinates and species
    }
    public void startSimulation() {
        int N = 10;
        int M = 10;
                    map = new ACritter[N][M];

                    // Example population of the map
                    map[0][0] = new Bear("Bear", 100, 0.02, 0, 0);
                    map[0][1] = new Deer("Deer", 50, 0.05, 0, 1);
                    map[1][0] = new Fox("Fox", 30, 0.03, 1, 0);
                    System.out.println(map[1][0].getSpecies());

                    // Example call to environmentObjectSpawner
                    environmentObjectSpawner("Bear", 5);
                    environmentObjectSpawner("Deer", 5);
                    environmentObjectSpawner("Fox", 5);
    }
    private void PETAHandler(int critterID) {
        int rows = map.length;
        int columns = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j] != null && map[i][j].getCritterID() == critterID) {
                    map[i][j] = null;
                    return;
                }
            }
        }
    }   // removes an object from the map, needs  ID
        // search the entire map for the animal and remove it

    // Input:   ID
    // Usage:  searches the map until it finds coordinates that match the ID, after that sets the coordinate data to null
    private void breeder() {
        int rows = map.length;
        int columns = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j] != null) {
                    ACritter parent = map[i][j];
                    if (parent.getOffspringChance() > random.nextDouble()) {
                        boolean bred = false;

                        for (int y = i - 1; y <= i + 1 && !bred; y++) {
                            for (int x = j - 1; x <= j + 1 && !bred; x++) {
                                if (isValid(x, y, rows, columns) && map[x][y] == null) {
                                    ACritter offspringbaby = createCritter(parent.getSpecies(), x, y);
                                    if (offspringbaby != null) {
                                        offspringbaby.setAge(0);
                                        map[x][y] = offspringbaby;
                                        bred = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }       // looks for an empty space around an animal with scanEnvironment and places a new
                                    //instance of the animal there with age = 0

    // Usage:checks all fields on the map, if it encounters an object it checks all fields around it,
    // if the field is empty it creates an object of the same class with age 0 and breaks the loop
    // (I didn't know how to use scanEnvironment)

    private void dataCollector() {} //at the end of every turn opens a file, writes the data such as amount of objects
                                    //and closes the file in case you wanted to stop the simulation early
    private void aging() {
        int rows = map.length;
        int cols = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] != null) {
                    ACritter critter = map[i][j];
                    critter.setAge(critter.getAge() + 1);

                    switch (critter.getSpecies()) {
                        case "Bear":
                            if(critter.getAge()>20){PETAHandler(critter.getCritterID());}
                            break;

                        case "Deer":
                            if(critter.getAge()>10){PETAHandler(critter.getCritterID());}
                            break;
                        case "Fox":
                            if(critter.getAge()>4){PETAHandler(critter.getCritterID());}
                            break;

                        case "Hare":
                            if(critter.getAge()>4){PETAHandler(critter.getCritterID());}
                            break;
                        case "Wolf":
                            if(critter.getAge()>10){PETAHandler(critter.getCritterID());}
                            break;

                        default:
                            break;
                    }

                }
            }
        }
    }
            // Usage: searches the entire map, there is an object increases its age checks its species
            // and calls PETAHandler to remove object if it's too old.
            // if it encounters another object(berry) nothing happens


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
    }//gets an object's name from an array's field and returns it
}
