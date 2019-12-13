package org.openjfx.model.entities.Building;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public abstract class Building extends LocatableObject {
    public static final double WIDTH_SCALE = 100;
    public static final double HEIGHT_SCALE = 100;

    public Building(Location location, double hitBoxWidth, double hitBoxHeight, int health) {
        super(location, hitBoxWidth, hitBoxHeight, health);
    }
}
