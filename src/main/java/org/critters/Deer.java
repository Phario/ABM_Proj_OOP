package org.critters;

import java.util.ArrayList;

public class Deer extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Deer() {
        super("N/A", 0, 0,0,0);
    }
    public Deer(String species, int maxHealth, double offspringChance, int x, int y) {
        super(species, maxHealth, offspringChance, x, y);
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}

}
