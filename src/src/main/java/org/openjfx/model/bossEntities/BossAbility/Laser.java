package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Laser extends LocatableObject {

    private int damage;

    public Laser(Location location, double hitBoxWidth, double hitBoxHeight, int damage) {
        super( location, hitBoxWidth, hitBoxHeight, 1000000);   // HEALTH POINT OF LASER ??????
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
