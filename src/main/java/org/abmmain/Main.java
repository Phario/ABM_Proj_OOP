 package org.abmmain;

import org.critters.*;
import org.maps.*;
import java.util.ArrayList;

import static org.maps.Config.*;

 public class Main {
     public static ACritter[][] map;
    public static void main(String[] args) {
        config();
        for (int i = 0; i < turnAmount; i++) {
           Mapa.turnManager(map);
        }
    }
}