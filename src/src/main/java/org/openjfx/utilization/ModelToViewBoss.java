package org.openjfx.utilization;

import org.openjfx.model.entities.Boss.Boss;

public class ModelToViewBoss {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private boolean isDead;
    public ModelToViewBoss (Boss boss) {
        locationX = boss.getLocation().getPositionX();
        locationY = boss.getLocation().getPositionY();
        hitboxHeight = boss.getHitBoxHeight();
        hitboxWidth = boss.getHitBoxWidth();
        ID = boss.getID();
        isDead = boss.isDead();
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public double getHitboxWidth() {
        return hitboxWidth;
    }

    public double getHitboxHeight() {
        return hitboxHeight;
    }

    public long getID() {
        return ID;
    }

    public boolean isDead() {
        return isDead;
    }
}
