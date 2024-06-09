package org.abmmain;

import org.maps.Map;
import org.maps.Board;

import java.util.List;

import static org.maps.Config.*;
import static org.maps.Map.map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        config();
        Map.startSimulation(mapLength, mapHeight, bearAmount, deerAmount, wolfAmount, hareAmount, foxAmount, berryAmount, burrowAmount);
        Board.startMap();
        Map.AnimalCounter.countAnimals(map);
        for (int i = 0; i < turnAmount; i++) {
            Board.refreshFrame();
            //open JFrame and paint it with adequate colours
            //call to data-saving method
            Map.turnManager(map);
            //repaint JFrame

            // Animal count at the end of each turn.
            Map.AnimalCounter.countAnimals(map);
        }
        // Downloading the history of animal counts
        List<java.util.Map<String, Integer>> animalCountsHistory = Map.AnimalCounter.getAnimalCountsHistory();

        // Saving the data to an Excel file
        Map.ExcelWriter.writeToExcel(animalCountsHistory);
    }
}