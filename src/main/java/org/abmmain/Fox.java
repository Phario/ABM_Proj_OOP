package org.abmmain;

public class Fox extends ACritter implements Burrower {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Fox() {
        super("N/A", 0, 0);
    }
    public Fox(String species, int maxHealth, double offspringChance) {
        super(species, maxHealth, offspringChance);
    }

    @Override
    public void burrower() {
        //code for making a burrow next to the creature's location

    }
}