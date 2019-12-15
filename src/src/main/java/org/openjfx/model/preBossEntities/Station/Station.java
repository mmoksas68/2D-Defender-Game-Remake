package org.openjfx.model.preBossEntities.Station;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public abstract class Station extends LocatableObject {
    public static final double WIDTH = 100;
    public static final double HEIGHT = 100;

    public Station(Location location, int health) {
        super(location, WIDTH, HEIGHT, health);
    }
}
