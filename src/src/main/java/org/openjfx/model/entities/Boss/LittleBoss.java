package org.openjfx.model.entities.Boss;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class LittleBoss extends LocatableObject {
    private int damage;

    public LittleBoss(Location location, int hitBoxWidth, int hitBoxHeight, int damage) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
