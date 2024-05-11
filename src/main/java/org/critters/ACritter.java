package org.critters;

public abstract class ACritter implements Critters {
    protected String Species;
    protected int maxHealth;
    protected int hunger;
    protected int thirst;
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
    public ACritter(String Species, int maxHealth, double offspringChance) {
        this.Species = Species;
        this.maxHealth = maxHealth;
        this.offspringChance = offspringChance;
        this.hunger = 100;
        this.age = 0;
        this.thirst = 100;
        this.critterID = globalCritterID;
        globalCritterID++;
    }
    public ACritter() {
        this.Species = "N/A";
        this.maxHealth = 1;
        this.offspringChance = 0;
        this.hunger = 100;
        this.age = 0;
        this.thirst = 100;
        this.critterID = globalCritterID;
        globalCritterID += 1;
    }
}
