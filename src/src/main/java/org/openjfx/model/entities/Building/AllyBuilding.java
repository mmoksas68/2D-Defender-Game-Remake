package org.openjfx.model.entities.Building;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class AllyBuilding extends Building {

    public AllyBuilding(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint) {
        super(location, hitBoxWidth, hitBoxHeight, healthPoint);
    }

    public Location callForBackUp(){
        return new Location(0,0);
    }
}
