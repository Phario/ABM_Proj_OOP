package org.abmmain;

import org.maps.Map;
import org.maps.Board;

import java.util.List;

import static org.maps.Config.*;
import static org.maps.Map.map;

/**
 * The Main class serves as the entry point for starting and running the simulation.
 * It initializes the simulation configuration, starts the map visualization, runs the simulation for a specified number of turns,
 * and finally saves the animal counts history to an Excel file.
 */
public class Main {

    /**
     * The main method initializes and runs the simulation.
     *
     * @param args command-line arguments (not used in this application)
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    public static void main(String[] args) throws InterruptedException {
        config();
        Map.startSimulation(mapLength, mapHeight, bearAmount, deerAmount, wolfAmount, hareAmount, foxAmount, berryAmount, burrowAmount);
        Board.startMap();
        for (int i = 0; i < turnAmount; i++) {
            Board.refreshFrame();
            Map.AnimalCounter.countAnimals(map);
            Map.turnManager(map);
        }
        Map.AnimalCounter.countAnimals(map);
        //Downloading the history of animal counts
        List<java.util.Map<String, Integer>> animalCountsHistory = Map.AnimalCounter.getAnimalCountsHistory();

        //Save to excel
        Map.ExcelWriter.writeToExcel(animalCountsHistory);
    }
}