package org.critters;

import java.util.ArrayList;

public class Fox extends ACritter implements Burrower {
    public void receiveDamage(int dmg) {
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Fox() {
        super("N/A", 0, 0, 0);
    }
    public Fox(String species, double offspringChance, int x, int y) {
        super(species, offspringChance, x, y);
    }

    @Override
    public void burrower() {
        //code for making a burrow next to the creature's location
    }
    public void scanEnvironment(ArrayList<Integer> maps) {}

}