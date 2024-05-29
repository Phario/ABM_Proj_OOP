package org.critters;

import java.util.ArrayList;

public class Wolf extends ACritter {
    public void receiveDamage(int dmg) {
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Wolf() {
        super("N/A", 0, 0, 0);
    }
    public Wolf(String species, double offspringChance, int x, int y) {
        super(species, offspringChance, x, y);
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}

}
