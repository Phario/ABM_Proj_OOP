package org.critters;

import java.util.ArrayList;

public class Berries extends ACritter {

    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }

    public int getStaticID(ACritter ID) {
        return critterID;
    }

    public Berries() {
        super("N/A", 3, 0, 0, 0);
    }

    public Berries(String species, int maxHealth, double offspringChance, int x, int y) {
        super(species, maxHealth, offspringChance, x, y);
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}
}