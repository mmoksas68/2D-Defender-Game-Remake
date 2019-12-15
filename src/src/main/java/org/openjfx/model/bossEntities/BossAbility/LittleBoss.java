package org.openjfx.model.bossEntities.BossAbility;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class LittleBoss extends LocatableObject {
    private int damage;
    private int hitNumber;
    private double xDir;
    private double yDir;

    public LittleBoss(Location location) {
        super(location, 10, 50, 50);
        this.damage = 50;
        this.hitNumber = 0;
        xDir = - Math.random();
        if( (int)(Math.random() * 2) == 0)
            yDir = -Math.random();
        else
            yDir = Math.random();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHitNumber() { return hitNumber; }

    public void setHitNumber(int hitNumber) { this.hitNumber = hitNumber; }

    public double getXDir() { return xDir; }

    public void setXDir( double xDir) { this.xDir = xDir; }

    public double getYDir() { return yDir; }

    public void setYDir( double yDir) { this.yDir = yDir; }
}
