package org.abmmain;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ACritter> listOfBears = new ArrayList<>();
        listOfBears.add(new Bear("Rysiu", 100, 0.5));
        System.out.println(listOfBears.get(0).Species);
    }
}