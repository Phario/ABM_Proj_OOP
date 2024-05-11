package org.critters;

public class Bear extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Bear() {
        super("N/A", 0, 0);
    }
    public Bear(String species, int maxHealth, double offspringChance) {
        super(species, maxHealth, offspringChance);
    }
}
