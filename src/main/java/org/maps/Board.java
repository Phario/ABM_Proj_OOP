package org.maps;

import javax.swing.*;
import java.awt.*;
import org.critters.*;

import static org.maps.Config.*;

public class Board extends JPanel {
    public static ACritter[][] map;


    public static void startMap() {
        JFrame frame = new JFrame("Forest Shenanigans");
        Board forest = new Board();
        frame.add(forest);
        frame.setSize(mapLength * 4, mapHeight * 4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintMap() {
        repaint();

        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapHeight; j++) {
                switch (map[i][j].getSpecies()) {
                    case "Bear":
                        //paint a brown rectangle at [i][j]
                        break;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.BLUE);
        g2.fillRect(200, 200, 20, 20);
    }
    //placeholder for when I actually learn how to use this POS
}