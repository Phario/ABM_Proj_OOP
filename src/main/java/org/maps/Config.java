package org.maps;

import java.util.Scanner;

public class Config {
    public static int turnAmount = 100;
    public static int turnInterval = 10;
    public static int bearAmount = 50;
    public static int foxAmount = 60;
    public static int deerAmount = 50;
    public static int wolfAmount = 50;
    public static int hareAmount = 80;
    public static int berryAmount = 40;
    public static int burrowAmount = 20;
    //Object amounts for sim start
    protected static int burrowChance = 1;
    //chance for an animal to make a burrow
    protected static int berryRespawnRate = 30;
    //amount of berries spawned per turn

    protected static int ageOfConsent = 6;
    //"Without Rules, We Live With The Animals" - John Wick
    protected static int burrowLife = 100;
    protected static int berryLife = 10;
    //non-movable object lifetimes

    public static int mapLength = 80;
    public static int mapHeight = 80;
    protected static int hungerDrain = 10;
    //Hunger drain per turn

    protected static int bearVsDeer = 80;
    protected static int bearVsWolf = 50;
    protected static int bearVsHare = 20;
    protected static int bearVsFox = 30;
    //Bear hunting success chance variables (in %)

    protected static int wolfVsBear = 0;
    protected static int wolfVsDeer = 80;
    protected static int wolfVsHare = 80;
    protected static int wolfVsFox = 5;
    //Wolf hunting success chance variables (in %)

    protected static int foxVsHare = 40;
    //Fox hunting success chance variables (in %)

    protected static int bearFoodValue = 100;
    protected static int deerFoodValue = 60;
    protected static int foxFoodValue = 30;
    protected static int hareFoodValue = 40;
    protected static int wolfFoodValue = 50;
    protected static int berriesFoodValue = 40;
    protected static int hungerKillSwitch = 80;
    protected static int hungerBreederSwitch = 50;
    //food values

    protected static int bearOffspringChance = 40;
    protected static int deerOffspringChance = 40;
    protected static int foxOffspringChance = 40;
    protected static int hareOffspringChance = 40;
    protected static int wolfOffspringChance = 40;
    //offspringChances (in %)

    protected static int bearMaxAge = 90;
    protected static int deerMaxAge = 90;
    protected static int hareMaxAge = 90;
    protected static int foxMaxAge = 90;
    protected static int wolfMaxAge = 90;
    public static void config() {
        Scanner configInput = new Scanner(System.in);
        System.out.println("Quick setup? (true/false)");
        boolean quickSetup = configInput.nextBoolean();
        if (!quickSetup) {
            System.out.println("Use default hunting chance, food and max age values? (true/false):");
            boolean defaultValues = configInput.nextBoolean();
            if (!defaultValues) {
                //ask whether to use default variable values or change them
                System.out.println("Set hunger drain:");
                hungerDrain = configInput.nextInt();
                System.out.println("Enter Bear vs Deer hunting chance (in %):");
                bearVsDeer = configInput.nextInt();
                System.out.println("Enter Bear vs Wolf hunting chance (in %):");
                bearVsWolf = configInput.nextInt();
                System.out.println("Enter Bear vs Hare hunting chance (in %):");
                bearVsHare = configInput.nextInt();
                System.out.println("Enter Bear vs Fox hunting chance (in %):");
                bearVsFox = configInput.nextInt();
                System.out.println("Enter Wolf vs Deer hunting chance (in %):");
                wolfVsDeer = configInput.nextInt();
                System.out.println("Enter Wolf vs Bear hunting chance (in %):");
                wolfVsBear = configInput.nextInt();
                System.out.println("Enter Wolf vs Fox hunting chance (in %):");
                wolfVsFox = configInput.nextInt();
                System.out.println("Enter Wolf vs Hare hunting chance (in %):");
                wolfVsHare = configInput.nextInt();
                System.out.println("Enter Fox vs Hare hunting chance (in %):");
                foxVsHare = configInput.nextInt();
                //hunting chances

                System.out.println("Enter animal food values:");
                System.out.println("Bear:");
                bearFoodValue = configInput.nextInt();
                System.out.println("Deer:");
                deerFoodValue = configInput.nextInt();
                System.out.println("Fox:");
                foxFoodValue = configInput.nextInt();
                System.out.println("Hare:");
                hareFoodValue = configInput.nextInt();
                System.out.println("Wolf:");
                wolfFoodValue = configInput.nextInt();
                System.out.println("Berries:");
                berriesFoodValue = configInput.nextInt();
                System.out.println("Enter hunting hunger threshold:");
                hungerKillSwitch = configInput.nextInt();
                //food values for animals

                System.out.println("Enter berry lifetime in number of turns:");
                berryLife = configInput.nextInt();
                System.out.println("Enter burrow lifetime in number of turns:");
                burrowLife = configInput.nextInt();
                //non-movable object lifetime

                System.out.println("Enter burrow spawn chance (in %):");
                burrowChance = configInput.nextInt();
                System.out.println("Enter berry respawn rate in amount spawned per turn:");
                berryRespawnRate = configInput.nextInt();
                //berry respawn rate

                System.out.println("Enter bear offspringChance (in %):");
                bearOffspringChance = configInput.nextInt();
                System.out.println("Enter deer offspringChance (in %):");
                deerOffspringChance = configInput.nextInt();
                System.out.println("Enter fox offspringChance (in %):");
                foxOffspringChance = configInput.nextInt();
                System.out.println("Enter hare offspringChance (in %):");
                hareOffspringChance = configInput.nextInt();
                System.out.println("Enter wolf offspringChance (in %):");
                wolfOffspringChance = configInput.nextInt();
                System.out.println("Enter age of consent (in turns):");
                ageOfConsent = configInput.nextInt();
                System.out.println("Enter breeding hunger threshold:");
                hungerBreederSwitch = configInput.nextInt();
                //offspring chance and ageOfConsent values

                System.out.println("Enter bear max age (in amount of turns):");
                bearMaxAge = configInput.nextInt();
                System.out.println("Enter deer max age (in amount of turns):");
                deerMaxAge = configInput.nextInt();
                System.out.println("Enter fox max age (in amount of turns):");
                foxMaxAge = configInput.nextInt();
                System.out.println("Enter hare max age (in amount of turns):");
                hareMaxAge = configInput.nextInt();
                System.out.println("Enter wolf max age (in amount of turns):");
                wolfMaxAge = configInput.nextInt();
                //max age values
            }
            System.out.println("Enter map width");
            mapHeight = configInput.nextInt();
            System.out.println("Enter map length");
            mapLength = configInput.nextInt();
            System.out.println("Enter the amount of turns:");
            turnAmount = configInput.nextInt();
            System.out.println("Enter the time between turns:");
            turnInterval = configInput.nextInt();
            System.out.println("Enter the amount of bears:");
            bearAmount = configInput.nextInt();
            System.out.println("Enter the amount of deer:");
            deerAmount = configInput.nextInt();
            System.out.println("Enter the amount of foxes:");
            foxAmount = configInput.nextInt();
            System.out.println("Enter the amount of hares:");
            hareAmount = configInput.nextInt();
            System.out.println("Enter the amount of wolves:");
            wolfAmount = configInput.nextInt();
            System.out.println("Enter the amount of berries:");
            berryAmount = configInput.nextInt();
            System.out.println("Enter the amount of burrows:");
            burrowAmount = configInput.nextInt();
        }
    }
}
