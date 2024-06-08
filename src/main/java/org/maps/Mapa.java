package org.maps;

import java.util.List;
import java.util.Map;
import org.critters.*;
import java.util.*;
import static org.maps.Config.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;


public class Mapa {
    private static Random random = new Random();
    public static ACritter[][] map;             //map that contains the instances of ACritter subclasses
    public static void startSimulation(int mapSizeX, int mapSizeY, int bearAmount, int deerAmount, int wolfAmount, int hareAmount, int foxAmount, int berryAmount, int burrowAmount) {
    //simulation initialization
    }
    public static void turnManager(ACritter[][] map) {
        ArrayList<Integer> critterIDList = new ArrayList<>();
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
            //removes an object from an array, so it can't make 2 moves in a turn
        }
    }
    //takes the map as a parameter and a random number generator to
    //decide which critter moves first, then the critterTurnManager takes over so the critter can make its turn
    //This method creates a list of critters and removes them after they move, they are chosen
    //based on their ID
    //turnManager manages ONLY 1 TURN, the main method is going to have a loop with the number of turns
    private static void critterTurnManager(ACritter[][] map, Integer critterID) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && Objects.equals(map[i][j].getCritterID(), critterID)) {
                    ACritter critter = map[i][j]; //makes a copy of a critter for more readable code
                    map[i][j].setX(i);
                    map[i][j].setY(j);
                    switch (critter.getSpecies()) {
                        case "Bear":
                            if(critter.scanEnvironment("Bear") != 0){
                                breeder(critter.getCritterID());
                            }
                            if(critter.scanEnvironment("Deer") != 0){
                                Integer preyID = critter.scanEnvironment("Deer");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= bearVsDeer){
                                                PETAHandler(preyID);
                                                map[i][j].eat(deerFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Fox") != 0){
                                Integer preyID = critter.scanEnvironment("Fox");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= bearVsFox){
                                                PETAHandler(preyID);
                                                map[i][j].eat(foxFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Hare") != 0){
                                Integer preyID = critter.scanEnvironment("Hare");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= bearVsHare){
                                                PETAHandler(preyID);
                                                map[i][j].eat(hareFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Wolf") != 0){
                                Integer preyID = critter.scanEnvironment("Wolf");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= bearVsWolf){
                                                PETAHandler(preyID);
                                                map[i][j].eat(wolfFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                            else PETAHandler(critter.getCritterID());
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Berries") != 0){
                                Integer preyID = critter.scanEnvironment("Berries");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            PETAHandler(preyID);
                                            map[i][j].eat(berriesFoodValue);
                                            if (map[i][j].getHunger() > 100) {
                                                map[i][j].setHunger(100);
                                            }
                                            break;
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            moveCritter(critter, map, i, j);
                            break;
                        case "Deer":
                            if(critter.scanEnvironment("Deer") != 0){
                                breeder(critter.getCritterID());
                            }
                            if(critter.scanEnvironment("Berries") != 0){
                                Integer preyID = critter.scanEnvironment("Berries");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            PETAHandler(preyID);
                                            map[i][j].eat(berriesFoodValue);
                                            if (map[i][j].getHunger() > 100) {
                                                map[i][j].setHunger(100);
                                            }
                                            break;
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            moveCritter(critter, map, i, j);
                            break;
                        case "Fox":
                            if(critter.scanEnvironment("Fox") != 0){
                                breeder(critter.getCritterID());
                            }
                            if(critter.scanEnvironment("Hare") != 0){
                                Integer preyID = critter.scanEnvironment("Hare");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= foxVsHare){
                                                PETAHandler(preyID);
                                                map[i][j].eat(hareFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            moveCritter(critter, map, i, j);
                            break;
                        case "Hare":
                            if(critter.scanEnvironment("Hare") != 0){
                                breeder(critter.getCritterID());
                            }
                            if(critter.scanEnvironment("Berries") != 0){
                                Integer preyID = critter.scanEnvironment("Berries");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            PETAHandler(preyID);
                                            map[i][j].eat(berriesFoodValue);
                                            if (map[i][j].getHunger() > 100) {
                                                map[i][j].setHunger(100);
                                            }
                                            break;
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            moveCritter(critter, map, i, j);
                            break;
                        case "Wolf":
                            if(critter.scanEnvironment("Bear") != 0){
                                Integer preyID = critter.scanEnvironment("Bear");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= wolfVsBear){
                                                PETAHandler(preyID);
                                                map[i][j].eat(bearFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                            else PETAHandler(critter.getCritterID());
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Deer") != 0){
                                Integer preyID = critter.scanEnvironment("Deer");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= wolfVsDeer){
                                                PETAHandler(preyID);
                                                map[i][j].eat(deerFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Fox") != 0){
                                Integer preyID = critter.scanEnvironment("Fox");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= wolfVsFox){
                                                PETAHandler(preyID);
                                                map[i][j].eat(foxFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Hare") != 0){
                                Integer preyID = critter.scanEnvironment("Hare");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (preyID.equals(map[k][l].getCritterID())) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
                                            int successChance = random.nextInt(101);
                                            if(successChance <= wolfVsHare){
                                                PETAHandler(preyID);
                                                map[i][j].eat(hareFoodValue);
                                                if (map[i][j].getHunger() > 100) {
                                                    map[i][j].setHunger(100);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            if(critter.scanEnvironment("Wolf") != 0){
                                breeder(critter.getCritterID());
                            }
                            moveCritter(critter, map, i, j);
                            break;
                        default:
                            break;
                    }
                    // Decrease hunger by hungerDrain at the beginning of the turn
                    critter.setHunger(critter.getHunger() - hungerDrain);

                    // If hunger reaches 0 or below, remove the critter
                    if (critter.getHunger() <= 0) {
                        PETAHandler(critterID);
                        return; // Exit the turn as the critter is removed
                    }

                    break; // End search once the correct critter is found and moved
                }
            }
        }
    }
    //input: map and critterID
    //manages a critter's turn depending on what kind of object it is
    //add behaviour for each critter when hunger is at 100;

    // General method to move critters
    private static void moveCritter(ACritter critter, ACritter[][] map, int x, int y) {
        List<int[]> availableCells = new ArrayList<>();

        // Collect available empty cells around the critter
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int newX = x + dx;
                int newY = y + dy;
                if (isValid(newX, newY, map.length, map[0].length) && map[newX][newY] == null) {
                    availableCells.add(new int[]{newX, newY});
                }
            }
        }

        // If there are no available cells, do not move the critter
        if (availableCells.isEmpty()) {
            return;
        }

        // Randomly select one available cell to move to
        int[] newCell = availableCells.get(random.nextInt(availableCells.size()));
        int newX = newCell[0];
        int newY = newCell[1];

        // Move the critter to the new location
        map[x][y] = null; // Clear the current cell
        critter.setX(newX); // Update critter's X coordinate
        critter.setY(newY); // Update critter's Y coordinate
        map[newX][newY] = critter; // Place the critter in the new cell
    }
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
    }
    //takes two random numbers from 0 to array length/width and
    //places an object there only if the space is free, if it isn't then
    //it looks again for a free space, needs input parameters such as
    //type of object to spawn and the amount of the object

    private static ACritter createCritter(String objectType, int x, int y) {
        switch (objectType) {
            case "Bear":
                return new Bear("Bear", bearOffspringChance, x, y);
            case "Deer":
                return new Deer("Deer", deerOffspringChance, x, y);
            case "Fox":
                return new Fox("Fox", foxOffspringChance, x, y);
            case "Hare":
                return new Hare("Hare", hareOffspringChance, x, y);
            case "Wolf":
                return new Wolf("Wolf", wolfOffspringChance, x, y);
            case "Berries":
                return new Berries("Berries", 0, x, y);
            case "Burrows":
                return new Burrows("Burrow", 0, x, y);
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
                    map[0][0] = new Bear("Bear", 0.02, 0, 0);
                    map[0][1] = new Deer("Deer", 0.05, 0, 1);
                    map[1][0] = new Fox("Fox", 0.03, 1, 0);
                    System.out.println(map[1][0].getSpecies());

                    // Example call to environmentObjectSpawner
                    environmentObjectSpawner("Bear", 5);
                    environmentObjectSpawner("Deer", 5);
                    environmentObjectSpawner("Fox", 5);
    }
    //method that WILL ask the user to input simulation start data, trust me bro.
    private static void PETAHandler(int critterID) {
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
    }
    // removes an object from the map, needs  ID

    // Input:   ID
    // Usage:  searches the map until it finds coordinates that match the ID, after that sets the coordinate data to null
    private static void breeder( Integer critterID) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && map[i][j].getCritterID().equals(critterID)) {
                    ACritter critter = map[i][j];
                    int offspringChance;

                    switch (critter.getSpecies()) {
                        case "Bear":
                            offspringChance = bearOffspringChance;
                            break;
                        case "Deer":
                            offspringChance = deerOffspringChance;
                            break;
                        case "Fox":
                            offspringChance = foxOffspringChance;
                            break;
                        case "Hare":
                            offspringChance = hareOffspringChance;
                            break;
                        case "Wolf":
                            offspringChance = wolfOffspringChance;
                            break;
                        default:
                            return; // If species is unknown, do nothing
                    }

                    // determine if a new critter should be created
                    if (random.nextInt(100) < offspringChance) {
                        // find all empty neighboring cells
                        int parentX = critter.getX();
                        int parentY = critter.getY();
                        List<int[]> emptyCells = new ArrayList<>();

                        for (int y = parentY - 1; y <= parentY + 1; y++) {
                            for (int x = parentX - 1; x <= parentX + 1; x++) {
                                if (isValid(x, y, map.length, map[0].length) && map[y][x] == null) {
                                    emptyCells.add(new int[]{x, y});
                                }
                            }
                        }

                        // if there are empty cells, randomly select one to create a new critter
                        if (!emptyCells.isEmpty()) {
                            int[] newCell = emptyCells.get(random.nextInt(emptyCells.size()));
                            ACritter newCritter = createCritter(critter.getSpecies(), newCell[0], newCell[1]);

                            if (newCritter != null) {
                                newCritter.setAge(0); // New critter starts with age 0
                                map[newCell[1]][newCell[0]] = newCritter; // Place new critter on the map
                            }
                        }
                    }

                    return; // end method when critter is found
                }
            }
        }
    }
    // looks for an empty space around an animal and places a new
    // instance of the animal there with age = 0
    private void dataCollector() {
    //figure out how to save data to a file so a graph can be made later
    }
    //at the end of every turn opens a file, writes the data such as amount of objects
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
                        case "Berries":
                            if(critter.getAge()>3){PETAHandler(critter.getCritterID());}
                            break;
                        case "Burrow":
                            if(critter.getAge()>3){PETAHandler(critter.getCritterID());}
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }
            // Usage: searches the entire map, if there is an object it increases its age, checks its species
            // and calls PETAHandler to remove object if it's too old.
            // if it encounters a berry nothing happens


    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    public static String getArrayObjectName(int x, int y) {
        return map[x][y].getSpecies();
    }//gets an object's name from an array's field and returns it
    public static Integer getArrayObjectID(int x, int y) {
        return map[x][y].getCritterID();
    }

    public class AnimalCounter {

        public static List<Map<String, Integer>> getAnimalCounts(ACritter[][] map, int turns) {
            List<Map<String, Integer>> animalCountsHistory = new ArrayList<>();

            for (int turn = 0; turn < turns; turn++) {
                Map<String, Integer> animalCounts = countAnimals(map);
                animalCountsHistory.add(animalCounts);

            }

            return animalCountsHistory;
        }

        private static Map<String, Integer> countAnimals(ACritter[][] map) {
            Map<String, Integer> animalCounts = new HashMap<>();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    ACritter critter = map[i][j];
                    if (critter != null) {
                        String species = critter.getSpecies();
                        animalCounts.put(species, animalCounts.getOrDefault(species, 0) + 1);
                    }
                }
            }

            return animalCounts;
        }
    }
    public class ExcelWriter {

        public static void writeToExcel(List<Map<String, Integer>> animalCountsHistory) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("AnimalCounts");

            int rowNum = 0;
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Turn");

            Map<String, Integer> firstCounts = animalCountsHistory.get(0);
            int colNum = 1;
            for (String species : firstCounts.keySet()) {
                headerRow.createCell(colNum++).setCellValue(species);
            }

            for (int turn = 0; turn < animalCountsHistory.size(); turn++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(turn);

                Map<String, Integer> counts = animalCountsHistory.get(turn);
                colNum = 1;
                for (int count : counts.values()) {
                    row.createCell(colNum++).setCellValue(count);
                }
            }

            // output of the file
            try (FileOutputStream fileOut = new FileOutputStream("AnimalCounts.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
