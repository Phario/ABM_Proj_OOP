package org.critters;
import org.maps.*;

import java.util.Random;

import static org.maps.Map.map;
public abstract class ACritter implements Critters {
    private static Random randomAge = new Random();
    protected String Species;
    protected int x, y; // New attributes for the position
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
    static int globalCritterID = 1;
    int critterID;

    /**
     * Constructs an ACritter object with specified species, offspring chance, and position.
     *
     * @param Species          the species of the critter
     * @param offspringChance  the chance of offspring production
     * @param x                the x-coordinate position of the critter
     * @param y                the y-coordinate position of the critter
     */
    public ACritter(String Species, double offspringChance, int x, int y) {
        this.Species = Species;
        this.offspringChance = offspringChance;
        this.hunger = 100;
        this.age = randomAge.nextInt(50);
        this.x = x; // Initialize position x
        this.y = y; // Initialize position y
        this.critterID = globalCritterID;
        globalCritterID++;
    }

    /**
     * Default constructor for ACritter.
     */
    public ACritter() {
        this.Species = "N/A";
        this.offspringChance = 0;
        this.hunger = 100;
        this.age = 0;
        this.critterID = globalCritterID;
        globalCritterID += 1;
    }

    /**
     * Increases the hunger level of the critter by the specified food value.
     *
     * @param foodValue the amount of food consumed
     */
    public void eat(int foodValue) {
        this.hunger += foodValue;
    }

    /**
     * Scans the environment for a specified object.
     *
     * @param objectName the name of the object to scan for
     * @return an Integer indicating the presence of the object
     */
    public Integer scanEnvironment(String objectName) {
        return 0;
    }
     //Input:   Desired object
     //Output: True/False whether it's in the object's vicinity
     //Usage:  Scanning for food, prey, mates, burrows and free spaces
    public Integer getCritterID() {
        return critterID;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    public int getHunger() {
        return hunger;
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
    public String getSpecies() {return Species;}
    public void setSpecies() {this.Species = Species; }
    public static int getGlobalCritterID() {
        return globalCritterID;
    }

    // Getters and setters for position
    public int getX() {
        return x;
    }
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getY() {
        return y;
    }
}
