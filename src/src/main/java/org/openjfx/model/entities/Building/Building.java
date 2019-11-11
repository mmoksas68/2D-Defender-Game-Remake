package org.openjfx.model.entities.Building;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public abstract class Building extends LocatableObject {
    public static final double WIDTH_SCALE = 0.03;
    public static final double HEIGHT_SCALE = 0.05;

    public Building(Location location, int hitBoxWidth, int hitBoxHeight, int health) {
        super(location, hitBoxWidth, hitBoxHeight, health);
    }
}
