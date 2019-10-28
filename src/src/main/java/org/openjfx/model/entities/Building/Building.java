package org.openjfx.model.entities.Building;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public abstract class Building extends LocatableObject {
    private int healthPoint;

    public Building(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }
}
