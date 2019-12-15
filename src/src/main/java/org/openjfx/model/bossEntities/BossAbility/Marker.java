package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Marker extends LocatableObject {
    private double damage;
    public Marker(Location location, double radius, double damage) {
        super( location, radius, radius, 1000);
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
