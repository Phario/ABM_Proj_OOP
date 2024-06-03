package org.critters;

import java.util.ArrayList;

public class Hare extends ACritter implements Burrower {
    public void receiveDamage(int dmg) {
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Hare() {
        super("N/A", 0, 0, 0);
    }
    public Hare(String species, double offspringChance, int x, int y) {
        super(species, offspringChance,x ,y);
    }

    @Override
    public void burrower() {

    }
    public void scanEnvironment(ArrayList<Integer> maps) {}

}
