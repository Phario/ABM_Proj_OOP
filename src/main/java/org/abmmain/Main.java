package org.abmmain;

import org.maps.Map;
import org.maps.Board;
import static org.maps.Config.*;

public class Main {
    public static void main(String[] args) {
        config();
        Map.startSimulation(mapLength, mapHeight, bearAmount, deerAmount, wolfAmount, hareAmount, foxAmount, berryAmount, burrowAmount);
        Board.startMap();
        for (int i = 0; i < turnAmount; i++) {
            //open JFrame and paint it with adequate colours
            //call to data-saving method
            Map.turnManager();
            //repaint JFrame
        }
    }
}