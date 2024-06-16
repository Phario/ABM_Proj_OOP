package org.critters;

import java.util.ArrayList;

public class  Burrows extends ACritter {
    public int getStaticID(ACritter ID) {
        return critterID;
    }

    /**
     * Default constructor for Burrows.
     */
    public Burrows() {
        super("N/A", 0, 0, 0);
    }

    /**
     * Constructs a Burrows object with the specified species, offspring chance, and position.
     *
     * @param species          the species of the burrow
     * @param offspringChance  the chance of burrow reproduction
     * @param x                the x-coordinate position of the burrow
     * @param y                the y-coordinate position of the burrow
     */
    public Burrows(String species, double offspringChance, int x, int y) {
        super(species, offspringChance, x, y);
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}
}