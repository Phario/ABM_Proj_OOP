package org.abmmain;

import org.maps.Map;
import org.maps.Board;

import java.util.List;

import static org.maps.Config.*;

public class Main {
    public static void main(String[] args) {
        config();
        Map.startSimulation(mapLength, mapHeight, bearAmount, deerAmount, wolfAmount, hareAmount, foxAmount, berryAmount, burrowAmount);
        Board.startMap();
        Map.AnimalCounter.countAnimals(Map.map);
        for (int i = 0; i < turnAmount; i++) {
            //open JFrame and paint it with adequate colours
            //call to data-saving method
            Map.turnManager();
            //repaint JFrame

            // Animal count at the end of each turn.
            Map.AnimalCounter.countAnimals(Map.map);

        }
        // Downloading the history of animal counts
        List<java.util.Map<String, Integer>> animalCountsHistory = Map.AnimalCounter.getAnimalCountsHistory();

        // Saving the data to an Excel file
        Map.ExcelWriter.writeToExcel(animalCountsHistory);
    }
}