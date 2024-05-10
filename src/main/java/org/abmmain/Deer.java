package org.abmmain;

public class Deer extends ACritter {
    public void receiveDamage(int dmg) {
        maxHealth -= dmg;
    }
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Deer() {
        super("N/A", 0, 0);
    }
    public Deer(String name, int maxHealth, double offspringChance) {
        super(name, maxHealth, offspringChance);
    }
}
