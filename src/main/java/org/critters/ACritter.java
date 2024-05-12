package org.critters;

public abstract class ACritter implements Critters {
    protected String Species;
    protected int x, y; // New attributes for the position
    protected int maxHealth;
    protected int hunger;
    //    When one of these 2 drop down to 0 the animal is supposed to die
    protected double offspringChance;
    protected int age;
//    Bears live up to 25 years
//    Deer live up to 10 years
//    Hares live up to 4 years
//    Wolves live up to 10 years
//    Foxes live up to 4 years
//    What do we do with it? I don't fucking know, at least now we know how long our favourite animals live
    static int globalCritterID = 0;
    int critterID;
    public ACritter(String Species, int maxHealth, double offspringChance, int x, int y) {
        this.Species = Species;
        this.maxHealth = maxHealth;
        this.offspringChance = offspringChance;
        this.hunger = 100;
        this.age = 0;
        this.x = x; // Initialize position x
        this.y = y; // Initialize position y
        this.critterID = globalCritterID;
        globalCritterID++;
    }
    public ACritter() {
        this.Species = "N/A";
        this.maxHealth = 1;
        this.offspringChance = 0;
        this.hunger = 100;
        this.age = 0;
        this.critterID = globalCritterID;
        globalCritterID += 1;
    }
    public int getCritterID() {
        return critterID;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    public int getHunger() {
        return hunger;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setOffspringChance(double offspringChance) {
        this.offspringChance = offspringChance;
    }
    public double getOffspringChance() {
        return offspringChance;
    }

    public static int getGlobalCritterID() {
        return globalCritterID;
    }

    // Getters for position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
