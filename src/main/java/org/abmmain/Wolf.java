package org.abmmain;

public class Wolf extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Wolf() {
        super("N/A", 0, 0);
    }
    public Wolf(String species, int maxHealth, double offspringChance) {
        super(species, maxHealth, offspringChance);
    }
}
