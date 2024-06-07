package org.maps;

import java.util.Scanner;

public class Config {
    public static int turnAmount = 0;
    public static int bearAmount = 0;
    public static int foxAmount = 0;
    public static int deerAmount = 0;
    public static int wolfAmount = 0;
    public static int hareAmount = 0;
    public static int berryAmount = 0;
    public static int burrowAmount = 0;
    //Object amounts for sim start

    public static int mapWidth = 10;
    public static int mapLength = 10;
    protected static int hungerDrain = 5;
    //Hunger drain per turn

    protected static int bearVsDeer = 80;
    protected static int bearVsWolf = 60;
    protected static int bearVsHare = 30;
    protected static int bearVsFox = 30;
    //Bear hunting success chance variables (in %)

    protected static int wolfVsBear = 30;
    protected static int wolfVsDeer = 60;
    protected static int wolfVsHare = 70;
    protected static int wolfVsFox = 70;
    //Wolf hunting success chance variables (in %)

    protected static int foxVsHare = 80;
    //Fox hunting success chance variables (in %)

    protected static int bearFoodValue = 100;
    protected static int deerFoodValue = 50;
    protected static int foxFoodValue = 20;
    protected static int hareFoodValue = 30;
    protected static int wolfFoodValue = 40;
    protected static int berriesFoodValue = 30;
    //food values

    protected static int burrowLife = 3;
    protected static int berryLife = 3;
    //non-movable object lifetimes

    protected static int berryRespawnRate = 5;
    //amount of berries spawned per turn

    protected static int bearOffspringChance = 40;
    protected static int deerOffspringChance = 40;
    protected static int foxOffspringChance = 50;
    protected static int hareOffspringChance = 60;
    protected static int wolfOffspringChance = 40;

    //offspringChances (in %)
    public static void config() {
        System.out.println("Use default hunting chance/food values? (true/false):");
        Scanner configInput = new Scanner(System.in);
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
            //food values for animals

            System.out.println("Enter berry lifetime in number of turns:");
            berryLife = configInput.nextInt();
            System.out.println("Enter burrow lifetime in number of turns:");
            burrowLife = configInput.nextInt();
            //non-movable object lifetime

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
            //offspring chance values
        }
        System.out.println("Enter map width");
        mapWidth = configInput.nextInt();
        System.out.println("Enter map length");
        mapLength = configInput.nextInt();
        System.out.println("Enter the amount of turns:");
        turnAmount = configInput.nextInt();
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
