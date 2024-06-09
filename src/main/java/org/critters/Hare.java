package org.critters;

import org.maps.Map;

import java.util.ArrayList;

import static org.maps.Map.map;

public class Hare extends ACritter implements Burrower {
    public int getStaticID(ACritter ID) {
        return critterID;
    }
    public Hare() {
        super("N/A", 0, 0, 0);
    }
    public Hare(String species, double offspringChance, int x, int y) {
        super(species, offspringChance,x ,y);
    }

    @Override
    public void burrower() {

    }
    public Integer scanEnvironment(String objectName) {
        for (int environmentY = this.y - 1; environmentY <= this.y + 1; environmentY++) {
            for (int environmentX = this.x - 1; environmentX <= this.x + 1; environmentX++) {
                try {
                    if (environmentX < 0 || environmentY < 0) continue; // Skip invalid coordinates
                    if (environmentX == this.x && environmentY == this.y) continue; // Prevents the object from detecting itself
                    if (environmentX >= map.length || environmentY >= map[0].length) continue; // Skip coordinates outside the map bounds

                    if (map[environmentX][environmentY] == null) return 0;
                    else if (Map.getArrayObjectName(environmentX, environmentY).equals(objectName)) {
                        return Map.getArrayObjectID(environmentX, environmentY);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return 0;
    }

}
