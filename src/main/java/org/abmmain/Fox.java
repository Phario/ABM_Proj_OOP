package org.abmmain;

public class Fox extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Fox() {
        super("N/A", 0, 0);
    }
    public Fox(String name, int maxHealth, double offspringChance) {
        super(name, maxHealth, offspringChance);
    }
}