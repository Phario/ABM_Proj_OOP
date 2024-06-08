package org.maps;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // This ensures proper painting behavior.
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, getWidth(), getHeight()); // Use getWidth() and getHeight() for flexible dimensions.
        g2.setColor(Color.BLUE);
        g2.fillRect(200, 200, 20, 20);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Forest Shenanigans");
        Board forest = new Board();
        frame.add(forest);
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        while (true) {
            forest.repaint(); // Use forest.repaint() instead of frame.repaint().
            Thread.sleep(100);
        }
    }
}