package org.critters;

public interface Critters {
//    Reduces an entity's health by dmg amount.
    public void receiveDamage(int dmg);
//    Used for interactions between entities
    public int getStaticID(ACritter ID);
}
