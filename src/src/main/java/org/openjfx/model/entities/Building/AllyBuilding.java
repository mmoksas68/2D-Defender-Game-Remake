package org.openjfx.model.entities.Building;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class AllyBuilding extends Building {

    public AllyBuilding(Location location, int hitBoxWidth, int hitBoxHeight) {
        super(location, hitBoxWidth, hitBoxHeight);
    }

    public Location callForBackUp(){
        return new Location(0,0);
    }
}
