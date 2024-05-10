package org.abmmain;

public abstract class ACritter {
    protected String name;
    protected int health;
    protected int hunger;
    protected int thirst;
    protected float offspringChance;
    protected int age;

    public ACritter(String name, int health, int offspringChance) {
        this.name = name;
        this.health = health;
        this.offspringChance = offspringChance;
    }
}
