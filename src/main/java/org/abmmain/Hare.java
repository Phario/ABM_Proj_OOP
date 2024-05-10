package org.abmmain;

public class Hare extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Hare() {
        super("N/A", 0, 0);
    }
    public Hare(String name, int maxHealth, double offspringChance) {
        super(name, maxHealth, offspringChance);
    }
}
