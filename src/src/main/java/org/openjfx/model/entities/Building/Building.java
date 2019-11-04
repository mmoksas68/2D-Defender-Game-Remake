package org.openjfx.model.entities.Building;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public abstract class Building extends LocatableObject {

    public Building(Location location, int hitBoxWidth, int hitBoxHeight) {
        super(location, hitBoxWidth, hitBoxHeight);
    }

}
