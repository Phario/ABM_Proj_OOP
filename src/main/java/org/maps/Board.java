package org.maps;

import javax.swing.*;
import java.awt.*;

import static org.maps.Config.*;
import static org.maps.Map.map;

/**
 * The Board class represents the main visual component for the Forest Shenanigans simulation.
 * It handles the creation of the game window and the rendering of the game map.
 */
public class Board extends JPanel {
    public static int cellSize = 10;

    private static JFrame frame;

    /**
     * Initializes and starts the game map by creating a JFrame and adding the Board component to it.
     * The frame is sized based on the map dimensions and cell size, and is set to be visible.
     */
    public static void startMap() {
        frame = new JFrame("Forest Shenanigans");
        Board forest = new Board();
        frame.add(forest);
        frame.setSize(mapLength * cellSize, mapHeight * cellSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Overrides the paintComponent method of JPanel to render the game map.
     * It fills the background with a green color and draws each cell based on the species present.
     *
     * @param g the Graphics object used for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(51, 114, 11));
        g2.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != null) {
                    switch (map[i][j].getSpecies()) {
                        case "Bear":
                            g2.setColor(new Color(96, 38, 0));
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                        case "Deer":
                            g2.setColor(Color.YELLOW);
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                        case "Fox":
                            g2.setColor(new Color(241, 134, 5));
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                        case "Hare":
                            g2.setColor(Color.CYAN);
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                        case "Wolf":
                            g2.setColor(Color.GRAY);
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                        case "Berries":
                            g2.setColor(Color.BLUE);
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                        case "Burrow":
                            g2.setColor(Color.BLACK);
                            g2.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                            break;
                    }
                }
            }
        }
    }

    /**
     * Refreshes the frame by repainting it. This method can be called to update the visual representation
     * of the game map.
     */
    public static void refreshFrame() {
        frame.repaint();
    }
}
