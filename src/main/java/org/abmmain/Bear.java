package org.abmmain;

public class Bear extends ACritter{

    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Bear() {
        super("N/A", 0, 0);
    }
    public Bear(String name, int maxHealth, double offspringChance) {
        super(name, maxHealth, offspringChance);
    }
}
