package org.critters;

import java.util.ArrayList;

public class  Burrows extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }


    public int getStaticID(ACritter ID) {
        return critterID;
    }

    public Burrows() {
        super("N/A", 5, 0, 0, 0);
    }

    public Burrows(String species, int maxHealth, double offspringChance, int x, int y) {
        super(species, maxHealth, offspringChance, x, y);
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}
}