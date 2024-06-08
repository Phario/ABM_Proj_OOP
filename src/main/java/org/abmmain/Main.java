 package org.abmmain;

 import org.critters.ACritter;
 import org.maps.Map;

 import static org.maps.Config.*;
 import static org.maps.Map.map;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 public class Main {
    public static void main(String[] args) {
        config();
        Map.startSimulation(mapLength, mapWidth, bearAmount, deerAmount, wolfAmount, hareAmount, foxAmount, berryAmount, burrowAmount);
        for (int i = 0; i < turnAmount; i++) {
           Map.turnManager();
        }
        System.out.println(map[1][1].getSpecies());
    }
}