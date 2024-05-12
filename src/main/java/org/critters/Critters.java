package org.critters;

import java.util.ArrayList;

public interface Critters {
//    Reduces an entity's health by dmg amount.
    public void receiveDamage(int dmg);
    public void scanEnvironment(ArrayList<Integer> maps);
}
