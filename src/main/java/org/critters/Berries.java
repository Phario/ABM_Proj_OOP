package org.critters;

import java.util.ArrayList;

public class Berries extends ACritter {

    public int getStaticID(ACritter ID) {
        return critterID;
    }

    /**
     * Default constructor for Berries.
     */
    public Berries() {
        super("N/A", 0, 0, 0);
    }

    /**
     * Constructs a Berries object with the specified species, offspring chance, and position.
     *
     * @param species          the species of the berries
     * @param offspringChance  the chance of berry reproduction
     * @param x                the x-coordinate position of the berries
     * @param y                the y-coordinate position of the berries
     */
    public Berries(String species, double offspringChance, int x, int y) {
        super(species, offspringChance, x, y);
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}
}