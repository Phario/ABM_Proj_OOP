package org.abmmain;

import org.critters.*;
import org.maps.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ACritter> listOfBears = new ArrayList<>();
        listOfBears.add(new Bear("Bear", 100, 0.5, 0,0));
        System.out.println(listOfBears.get(0).getSpecies());
    }

    public static class Berries {
        protected int amount;
        public Berries(int amount) {
            this.amount = amount;
        }
    }
}