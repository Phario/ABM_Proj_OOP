package org.maps;

import java.util.List;

import org.critters.*;
import java.util.*;
import static org.maps.Config.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xddf.usermodel.chart.*;
import java.io.File;




public class Map {
    private static Random random = new Random();
    public static ACritter[][] map;
    //map that contains the instances of ACritter subclasses

    /**
     * Initializes and starts the ecosystem simulation based on the specified parameters.
     *
     * Input:
     * - int mapSizeX: The width of the map grid.
     * - int mapSizeY: The height of the map grid.
     * - int bearAmount: The initial number of Bear critters to spawn.
     * - int deerAmount: The initial number of Deer critters to spawn.
     * - int wolfAmount: The initial number of Wolf critters to spawn.
     * - int hareAmount: The initial number of Hare critters to spawn.
     * - int foxAmount: The initial number of Fox critters to spawn.
     * - int berryAmount: The initial number of Berries to spawn.
     * - int burrowAmount: The initial number of Burrows to spawn.
     *
     * Output:
     * - void: This method does not return any value.
     *
     * This method initializes the map with dimensions mapSizeX x mapSizeY.
     * It then spawns the specified number of critters and environment objects onto the map:
     * - Bears, Deer, Foxes, Hares, Wolves, Berries, and Burrows.
     * The spawning of critters and objects is handled by the environmentObjectSpawner method.
     */
    public static void startSimulation(int mapSizeX, int mapSizeY, int bearAmount, int deerAmount, int wolfAmount, int hareAmount, int foxAmount, int berryAmount, int burrowAmount) {
        map = new ACritter[mapSizeX][mapSizeY];
        environmentObjectSpawner("Bear", bearAmount);
        environmentObjectSpawner("Deer", deerAmount);
        environmentObjectSpawner("Fox", foxAmount);
        environmentObjectSpawner("Hare", hareAmount);
        environmentObjectSpawner("Wolf", wolfAmount);
        environmentObjectSpawner("Berries", berryAmount);
        environmentObjectSpawner("Burrow", burrowAmount);
    }
    /**
     * Manages the turns of all critters on the map, handling their actions, interactions,
     * and environment changes for each turn cycle.
     *
     * Input:
     * - ACritter[][] map: The 2D array representing the map grid containing critters.
     *
     * Throws:
     * - InterruptedException: This method may throw an InterruptedException due to Thread.sleep().
     *
     * Output:
     * - void: This method does not return any value.
     *
     * This method iterates through the map to collect all critter IDs and stores them in a list.
     * It then processes each critter's turn in a random order until all critters have taken their turn.
     * For each critter, it invokes the critterTurnManager to handle specific behaviors based on their species.
     * After all critters have completed their turns, it performs aging for all critters on the map.
     * It also respawns environment objects (berries) and introduces a delay (turnInterval) using Thread.sleep().
     */
    public static void turnManager(ACritter[][] map) throws InterruptedException {
        ArrayList<Integer> critterIDList = new ArrayList<>();
        //scans the map for critters and adds their ids to a list
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && !"NULL".equals(map[i][j].getSpecies())) {
                    critterIDList.add(map[i][j].getCritterID());
                }
            }
        }
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
        aging();
        for (int i = 0; i < berryRespawnRate; i++) {
            environmentObjectSpawner("Berries",1);
        }
        Thread.sleep(turnInterval);
    }
    /**
     * Manages the turn-based behavior of a specific critter on the map, including movement,
     * interaction with the environment (other critters and food sources), breeding, aging,
     * and hunger management.
     *
     * Input:
     * - ACritter[][] map: The 2D array representing the map grid containing critters.
     * - Integer critterID: The unique identifier of the critter whose turn is being managed.
     *
     * Output:
     * - void: This method does not return any value.
     *
     * This method iterates through the entire map to find the critter with the specified critterID.
     * Once found, it processes the critter's behavior based on its species:
     * - For each critter type (e.g., Bear, Deer, Fox, Hare, Wolf), specific actions are taken:
     *   - Breeding: If conditions are met (age and breeding chance), a new critter may be created nearby.
     *   - Interaction: The critter may interact with other critters.
     *   - Movement: The critter may move to an adjacent empty cell if conditions allow.
     *   - Hunger management: The critter's hunger level decreases each turn; if it reaches zero, the critter is removed.
     *   - Specific species behaviors are managed using switch-case statements based on the critter's species.
     * - After processing the critter's turn, its hunger level is updated and checked for removal if hunger is zero or below.
     */
    private static void critterTurnManager(ACritter[][] map, Integer critterID) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if ((map[i][j] != null) && (Objects.equals(map[i][j].getCritterID(), critterID))) {
                    map[i][j].setX(i);
                    map[i][j].setY(j);
                    switch (map[i][j].getSpecies()) {

                        case "Bear":
                            if(map[i][j].scanEnvironment("Bear") != 0 && map[i][j].getHunger() > hungerBreederSwitch){
                                breeder(map[i][j].getCritterID());
                            }
                            if(map[i][j].scanEnvironment("Deer") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Deer");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
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
                            if(map[i][j].scanEnvironment("Fox") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Fox");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(burrowID != 0 && map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
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
                            if(map[i][j].scanEnvironment("Hare") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Hare");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(burrowID != 0 && map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
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
                            if(map[i][j].scanEnvironment("Berries") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Berries");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
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
                            moveCritter(map[i][j], map, i, j);
                            break;
                        case "Deer":
                            if(map[i][j].scanEnvironment("Deer") != 0 && map[i][j].getHunger() > hungerBreederSwitch){
                                breeder(map[i][j].getCritterID());
                            }
                            if(map[i][j].scanEnvironment("Berries") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Berries");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
                                            critterFound = true;
                                            PETAHandler(preyID);
                                            map[i][j].eat(berriesFoodValue-80);
                                            if (map[i][j].getHunger() > 100) {
                                                map[i][j].setHunger(100);
                                            }
                                            break;
                                        }
                                    }
                                    if (critterFound) break;
                                }
                            }
                            moveCritter(map[i][j], map, i, j);
                            break;
                        case "Fox":
                            if(map[i][j].scanEnvironment("Fox") != 0 && map[i][j].getHunger() > hungerBreederSwitch){
                                breeder(map[i][j].getCritterID());
                            }
                            if(map[i][j].scanEnvironment("Hare") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Hare");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(burrowID != 0 && map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
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
                            digBurrow(map[i][j].getCritterID());
                            moveCritter(map[i][j], map, i, j);
                            break;
                        case "Hare":
                            if(map[i][j].scanEnvironment("Hare") != 0 && map[i][j].getHunger() > hungerBreederSwitch){
                                breeder(map[i][j].getCritterID());
                            }
                            if(map[i][j].scanEnvironment("Berries") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Berries");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
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
                            digBurrow(map[i][j].getCritterID());
                            moveCritter(map[i][j], map, i, j);
                            break;
                        case "Wolf":
                            if(map[i][j].scanEnvironment("Deer") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Deer");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
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
                            if(map[i][j].scanEnvironment("Fox") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Fox");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(burrowID != 0 && map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
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
                            if(map[i][j].scanEnvironment("Hare") != 0){
                                Integer preyID = map[i][j].scanEnvironment("Hare");
                                boolean critterFound = false;
                                for (int k = i-1; k <= i+1; k++) {
                                    if (k < 0 || k >= map.length) continue;
                                    for (int l = j-1; l <= j+1; l++) {
                                        if (l < 0 || l >= map[0].length) continue;
                                        if (map[k][l] != null && preyID.equals(map[k][l].getCritterID())&& map[i][j].getHunger() < hungerKillSwitch) {
                                            critterFound = true;
                                            Integer burrowID = map[k][l].scanEnvironment("Burrow");
                                            if(burrowID != 0 && map[k][l].scanEnvironment("Burrow").equals(burrowID)) break;
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
                            if(map[i][j].scanEnvironment("Wolf") != 0 && map[i][j].getHunger() > hungerBreederSwitch){
                                breeder(map[i][j].getCritterID());
                            }
                            moveCritter(map[i][j], map, i, j);
                            break;
                        default:
                            break;
                    }
                    // Decrease hunger by hungerDrain at the end of a turn
                    if(map[i][j] != null) {
                        map[i][j].setHunger(map[i][j].getHunger() - hungerDrain);
                    }
                    // If hunger reaches 0 or below, remove the critter
                    if (map[i][j] != null && map[i][j].getHunger() <= 0) {
                        PETAHandler(critterID);
                        return; // Exit the turn as the critter is removed
                    }

                    break; // End search once the correct critter is found and moved
                }
            }
        }
    }
    /**
     * Moves a critter to an adjacent empty cell on the map if available.
     *
     * Input:
     *  - ACritter critter: The critter object to be moved.
     *  - ACritter[][] map: The 2D array representing the map grid.
     *  - int x: The current X coordinate of the critter.
     *  - int y: The current Y coordinate of the critter.
     *
     * Output:
     *  - void: This method does not return any value.
     *
     * This method collects all available empty cells adjacent to the critter's current position.
     * If there are available cells, it randomly selects one, moves the critter to that cell, and
     * updates its coordinates on the map.
     * If no empty cells are available, the critter remains in its current position.
     */
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
    /**
     * Spawns a specified number of environment objects of a given type in random empty positions on the map.
     *
     * Input:
     *  - String objectType: The type of environment object to spawn (e.g., "Bear", "Deer").
     *  - int amount: The number of objects to spawn.
     *
     * Output:
     *  - void: This method does not return any value.
     *
     * This method takes the type of environment object and the amount to spawn as input. It randomly
     * places the specified number of objects on the map in empty cells. It uses a loop to ensure
     * each object is placed in a valid, empty position on the map.
     */
    private static void environmentObjectSpawner(String objectType, int amount) {
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

    /**
     * Creates a new critter object based on the specified type and location.
     *
     * Input:
     *  - String objectType: The type of critter to create (e.g., "Bear", "Deer").
     *  - int x: The x-coordinate for the critter's position.
     *  - int y: The y-coordinate for the critter's position.
     *
     * Output:
     *  - ACritter: The newly created critter object or null if the objectType is unknown.
     *
     * This method takes the type of critter and its coordinates as input and returns a new instance
     * of the corresponding critter class. If the objectType is unknown, it prints an error message
     * and returns null.
     */

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
            case "Burrow":
                return new Burrows("Burrow", 0, x, y);
            default:
                System.out.println("Unknown type: " + objectType);
                return null;
        }

    }

    /**
     * Removes a critter from the map based on its ID.
     *
     * Input:
     *  - Integer critterID: The ID of the critter to be removed.
     *
     * Output:
     *  - No direct output, but the critter with the given ID will be removed from the map if found.
     *
     * This method iterates over the entire map to find the critter with the specified ID and sets its
     * position on the map to null, effectively removing it from the map.
     */
    private static void PETAHandler(Integer critterID) {
        int rows = map.length;
        int columns = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j] != null && map[i][j].getCritterID().equals(critterID)) {
                    map[i][j] = null;
                    return;
                }
            }
        }
    }

    /**
     * Attempts to breed a critter with the given ID if it is of age and the conditions for breeding are met.
     *
     * Input:
     *  - Integer critterID: The ID of the critter to be checked for breeding.
     *
     * Output:
     *  - No direct output, but may add a new critter to the map if breeding conditions are met.
     *
     * This method iterates over the entire map to find the critter with the given ID. If the critter is of age,
     * it determines the species-specific breeding chance. If the breeding chance condition is met, it finds
     * empty neighboring cells and places a new critter in one of those cells.
     */
    private static void breeder( Integer critterID) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && map[i][j].getCritterID().equals(critterID) && map[i][j].getAge() > ageOfConsent) {
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
                                newCritter.setHunger(50);
                                map[newCell[1]][newCell[0]] = newCritter; // Place new critter on the map
                            }
                        }
                    }

                    return; // end method when critter is found
                }
            }
        }
    }


    /**
     * Ages all the critters and plants on the map by incrementing their age by 1.
     * If any critter or plant exceeds its maximum age, it triggers the PETAHandler method to handle its removal.
     *
     * Input:
     *  - No direct input parameters, but uses the global 'map' array.
     *
     * Output:
     *  - No direct output, but modifies the state of critters and plants in the 'map'.
     *
     * This method iterates over the entire map, increments the age of each non-null entity,
     * and checks if the entity's age exceeds its maximum allowed age, handling the removal if necessary.
     */
    private static void aging() {
        int rows = map.length;
        int cols = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] != null) {
                    map[i][j].setAge(map[i][j].getAge() + 1);

                    switch (map[i][j].getSpecies()) {
                        case "Bear":
                            if(map[i][j].getAge()>bearMaxAge){PETAHandler(map[i][j].getCritterID());}
                            break;
                        case "Deer":
                            if(map[i][j].getAge()>deerMaxAge){PETAHandler(map[i][j].getCritterID());}
                            break;
                        case "Fox":
                            if(map[i][j].getAge()>foxMaxAge){PETAHandler(map[i][j].getCritterID());}
                            break;
                        case "Hare":
                            if(map[i][j].getAge()>hareMaxAge){PETAHandler(map[i][j].getCritterID());}
                            break;
                        case "Wolf":
                            if(map[i][j].getAge()>wolfMaxAge){PETAHandler(map[i][j].getCritterID());}
                            break;
                        case "Berries":
                            if(map[i][j].getAge()>berryLife){PETAHandler(map[i][j].getCritterID());}
                            break;
                        case "Burrow":
                            if(map[i][j].getAge()>burrowLife){PETAHandler(map[i][j].getCritterID());}
                            break;
                        default:
                            break;
                    }

                }
            }
        }
    }

    /**
     * Checks if the given coordinates (x, y) are valid within a grid or matrix defined by the number of rows and columns.
     *  Input:
     *  -The x and y coordinate of the map array.
     *  -The total number of rows and columns in the grid.
     */
    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    /**
     * Retrieves the species name of the critter (object) located at the specified coordinates (x, y) on the map.
     * Input: The x and y coordinate of the map array.
     */

    public static String getArrayObjectName(int x, int y) {
        return map[x][y].getSpecies();
    }//gets an object's name from an array's field and returns it

    /**
     * Retrieves the critter ID (object ID) located at the specified coordinates (x, y) on the map.
     * Input: The x and y coordinate of the map array.
     */
    public static Integer getArrayObjectID(int x, int y) {
        return map[x][y].getCritterID();
    }

    /**
     * Handles the behavior of a critter digging a burrow in the simulation.
     *
     * Input:
     * - Integer critterID: The ID of the critter that wants to dig a burrow.
     *
     * Output:
     * - void: This method does not return any value.
     *
     * This method iterates through the map to find the critter with the specified critterID.
     * If the critter is found, it checks if a new burrow should be created based on a random chance (burrowChance).
     * If the random chance is met, the method then looks for neighboring empty cells to place the new burrow.
     * Once an empty cell is found, a new burrow critter (of type "Burrow") is created and placed on the map.
     * The new burrow starts with an age of 0.
     * This method ensures that only one burrow is created per call and stops further search once the critter is found.
     */
    private static void digBurrow(Integer critterID) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null && map[i][j].getCritterID().equals(critterID)) {

                    // Determine if a new burrow should be created
                    if (random.nextInt(100) < burrowChance) {
                        int parentX = map[i][j].getX();
                        int parentY = map[i][j].getY();
                        List<int[]> emptyCells = new ArrayList<>();

                        // Find all empty neighboring cells
                        for (int y = parentY - 1; y <= parentY + 1; y++) {
                            for (int x = parentX - 1; x <= parentX + 1; x++) {
                                if (isValid(x, y, map.length, map[0].length) && map[y][x] == null) {
                                    emptyCells.add(new int[]{x, y});
                                }
                            }
                        }

                        // If there are empty cells, randomly select one to create a new burrow
                        if (!emptyCells.isEmpty()) {
                            int[] newCell = emptyCells.get(random.nextInt(emptyCells.size()));
                            ACritter newBurrow = createCritter("Burrow", newCell[0], newCell[1]);

                            if (newBurrow != null) {
                                newBurrow.setAge(0); // New burrow starts with age 0
                                map[newCell[1]][newCell[0]] = newBurrow; // Place new burrow on the map
                            }
                        }
                    }

                    return; // End method when critter is found
                }
            }
        }
    }
    /**
     * Counts the animals on the map, excluding burrows and berries, and stores the counts in the history list.
     *
     * Input:
     *  - map: A 2D array of ACritter objects representing the map.
     *
     * Output:
     *  - None. The method updates the static list animalCountsHistory.
     *
     * This method iterates over the 2D array map, counts the occurrences of each animal species (excluding burrows and berries),
     * and stores these counts in a map. This map is then added to the animalCountsHistory list to keep track of the counts for each turn.
     */
    public class AnimalCounter {

        // A list to store the history of animal counts for each turn
        private static List<java.util.Map<String, Integer>> animalCountsHistory = new ArrayList<>();

        // Method to count the animals on the map, excluding burrows and berries
        public static void countAnimals(ACritter[][] map) {
            // A map to store the counts of each species
            java.util.Map<String, Integer> animalCounts = new HashMap<>();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    ACritter critter = map[i][j];
                    if (critter != null) {
                        String species = critter.getSpecies();
                       if (!species.equals("Burrow") && !species.equals("Berries")) { // Skips burrows and berries
                            animalCounts.put(species, animalCounts.getOrDefault(species, 0) + 1);
                       }
                    }
                }
            }
            // Add the counts for this turn to the count history
            animalCountsHistory.add(animalCounts);
        }
        // Method to retrieve the history of animal counts
        public static List<java.util.Map<String, Integer>> getAnimalCountsHistory() {
            return animalCountsHistory;
        }
    }



    public class ExcelWriter {

        /**
         * Writes the animal counts history to an Excel file and creates a chart to visualize the data.
         *
         * Input:
         *  - animalCountsHistory: A list of maps where each map contains the counts of different animal species for a turn.
         *
         * Output:
         *  - An Excel file named "AnimalCounts.xlsx" with the animal counts data and a line chart.
         *
         * This method creates a new Excel workbook, adds the animal counts data to a sheet, creates a line chart to visualize the data,
         * and saves the file with a unique name to avoid overwriting existing files.
         */

        public static void writeToExcel(List<java.util.Map<String, Integer>> animalCountsHistory) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("AnimalCounts");

            int rowNum = 0;
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Turn");

            if (!animalCountsHistory.isEmpty()) {
                // Get the first set of animal counts to create the headers for the species
                java.util.Map<String, Integer> firstCounts = animalCountsHistory.get(0);
                int colNum = 1;
                // Create headers for each species
                for (String species : firstCounts.keySet()) {
                    headerRow.createCell(colNum++).setCellValue(species);
                }

                // Write data for each turn
                for (int turn = 0; turn < animalCountsHistory.size(); turn++) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(turn);

                    java.util.Map<String, Integer> counts = animalCountsHistory.get(turn);
                    colNum = 1;
                    for (String species : firstCounts.keySet()) {
                        row.createCell(colNum++).setCellValue(counts.getOrDefault(species, 0));
                    }
                }
            }

            // Add a chart
            createChart(sheet, workbook, animalCountsHistory);

            // Get a unique file name to avoid overwriting existing files
            String fileName = getUniqueFileName("AnimalCounts.xlsx");

            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
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


        /**
         * Creates a line chart in the given Excel sheet to visualize the animal population over time.
         *
         * Input:
         *  - sheet: The Excel sheet where the chart will be created.
         *  - workbook: The workbook containing the sheet.
         *  - animalCountsHistory: A list of maps representing the counts of different animal species for each turn.
         *
         * Output:
         *  - None. The method modifies the provided Excel sheet by adding a chart.
         *
         * This method generates a line chart in the specified Excel sheet to visualize how the population of each animal species changes over time.
         * It uses the Apache POI library to create the chart and define its properties, such as title, legend, axes, and data series.
         */
        private static void createChart(XSSFSheet sheet, XSSFWorkbook workbook, List<java.util.Map<String, Integer>> animalCountsHistory) {

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, sheet.getLastRowNum() + 2, 10, sheet.getLastRowNum() + 20);

            // Title
            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("Animal Population Over Time");
            chart.setTitleOverlay(false);

            // Legend
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            // Create X and Y axes for the chart and set their titles
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("Turn");
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("Population");

            // Define the data source for the X axis (turns)
            XDDFNumericalDataSource<Double> turns = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, sheet.getLastRowNum(), 0, 0));

            java.util.Map<String, Integer> firstCounts = animalCountsHistory.get(0);
            int colNum = 1;
            XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
            for (String species : firstCounts.keySet()) {

                // Define the data source for the Y axis (population) for the current species
                XDDFNumericalDataSource<Double> population = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, sheet.getLastRowNum(), colNum, colNum));

                // Add a data series for the current species to the chart
                XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(turns, population);
                series.setTitle(species, null);
                series.setMarkerStyle(MarkerStyle.NONE); // no marks, just lines
                colNum++;
            }

            chart.plot(data);
        }

        /**
         * Generates a unique file name by appending a counter to the base name if a file with the base name already exists.
         *
         * Input:
         *  - baseName: The base name for the file, which can include an extension (e.g., "file.xlsx").
         *
         * Output:
         *  - A unique file name string with an absolute path.
         *
         * This method ensures that the file name generated is unique by checking if a file with the same name already exists in the directory.
         * If it does, it appends a counter to the base name until it finds a name that is not taken.
         */
        private static String getUniqueFileName(String baseName) {
            String fileName = baseName;
            String fileExtension = "";
            int dotIndex = baseName.lastIndexOf('.');
            if (dotIndex > 0) {
                fileName = baseName.substring(0, dotIndex);
                fileExtension = baseName.substring(dotIndex);
            }

            int counter = 1;
            File file = new File(fileName + fileExtension);
            while (file.exists()) {
                file = new File(fileName + counter + fileExtension);
                counter++;
            }

            return file.getAbsolutePath();
        }
    }
}

