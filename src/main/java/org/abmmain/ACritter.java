package org.abmmain;

public abstract class ACritter implements Critters {
    protected String name;
    protected int maxHealth;
    protected int hunger;
    protected int thirst;
    protected double offspringChance;
    protected int age;
    static int globalCritterID = 0;
    int critterID;
    public ACritter(String name, int maxHealth, double offspringChance) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.offspringChance = offspringChance;
        this.hunger = 100;
        this.age = 0;
        this.thirst = 100;
        this.critterID = globalCritterID;
        critterID++;
    }
    public ACritter() {
        this.name = "N/A";
        this.maxHealth = 1;
        this.offspringChance = 0;
        this.hunger = 100;
        this.age = 0;
        this.thirst = 100;
        this.critterID = globalCritterID;
        critterID += 1;
    }
}
