package org.critters;

import org.maps.Map;

import java.util.ArrayList;

import static org.maps.Map.map;

public class Deer extends ACritter {
    public int getStaticID(ACritter ID) {
        return critterID;
    }

    /**
     * Default constructor for Deer.
     */
    public Deer() {
        super("N/A", 0,0,0);
    }

    /**
     * Constructs a Deer object with the specified species, offspring chance, and position.
     *
     * @param species          the species of the deer
     * @param offspringChance  the chance of deer reproduction
     * @param x                the x-coordinate position of the deer
     * @param y                the y-coordinate position of the deer
     */
    public Deer(String species, double offspringChance, int x, int y) {
        super(species, offspringChance, x, y);
    }

    /**
     * Scans the surrounding 3x3 grid centered on the current object's coordinates
     * (this.x, this.y) within a map. It checks if a specific object (identified by
     * objectName) exists in any of the surrounding cells, excluding the current
     * object's cell. If the specified object is found, the method returns the object's ID.
     * If the object is not found, the method returns 0.
     *
     * @param objectName The name of the object to be searched for in the surrounding environment.
     * @return Integer The ID of the object if it is found in the surrounding cells, or 0
     *         if the object is not found or if any of the surrounding cells are invalid
     *         (e.g., out of map bounds or containing null).
     */
    @Override
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
