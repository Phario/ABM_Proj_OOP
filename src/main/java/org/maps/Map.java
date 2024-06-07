package org.maps;


import org.critters.*;
import java.util.*;
import static org.maps.Config.*;

public class Map {
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
                                breeder();
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
                                breeder();
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
                                breeder();
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
                                breeder();
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
                                breeder();
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
        int newX, newY;
        do {
            newX = x + random.nextInt(3) - 1; // Random number between -1 and 1
            newY = y + random.nextInt(3) - 1; // Random number between -1 and 1
        } while (!isValid(newX, newY, map.length, map[0].length) || map[newX][newY] != null);}
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
                return new Bear("Bear", 0.2, x, y);
            case "Deer":
                return new Deer("Deer", 0.5, x, y);
            case "Fox":
                return new Fox("Fox", 0.4, x, y);
            case "Hare":
                return new Hare("Hare", 0.6, x, y);
            case "Wolf":
                return new Wolf("Wolf", 0.3, x, y);
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
    private static void breeder() {
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
                                    ACritter offSpringBaby = createCritter(parent.getSpecies(), x, y);
                                    if (offSpringBaby != null) {
                                        offSpringBaby.setAge(0);
                                        map[x][y] = offSpringBaby;
                                        bred = true;
                                    }
                                }
                            }
                        }
                    }
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
}
