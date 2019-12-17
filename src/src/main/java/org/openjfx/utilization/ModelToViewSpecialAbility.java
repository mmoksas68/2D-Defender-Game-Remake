package org.openjfx.utilization;


import org.openjfx.model.bossEntities.BossAbility.*;

public class ModelToViewSpecialAbility {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private boolean isDead;
    private double ydir;
    private double xdir;
    private boolean isLaser = false;
    private boolean isMarker = false;
    private boolean isRocket = false;
    private boolean isLittleBoss = false;
    public ModelToViewSpecialAbility (SpecialAbility specialAbility) {
        locationX = specialAbility.getLocation().getPositionX();
        locationY = specialAbility.getLocation().getPositionY();
        hitboxWidth = specialAbility.getHitBoxWidth();
        hitboxHeight = specialAbility.getHitBoxHeight();
        ID = specialAbility.getID();
        isDead = specialAbility.isDead();
        ydir = specialAbility.getyDir();
        xdir = specialAbility.getxDir();
        findType(specialAbility);
    }
    private void findType (SpecialAbility specialAbility) {
        if ( specialAbility instanceof Laser)
            isLaser = true;
        else if ( specialAbility instanceof Marker)
            isMarker = true;
        else if ( specialAbility instanceof Rocket)
            isRocket = true;
        else if ( specialAbility instanceof LittleBoss)
            isLittleBoss = true;
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

    public double getYdir() {
        return ydir;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isLaser() {
        return isLaser;
    }

    public boolean isMarker() {
        return isMarker;
    }

    public boolean isRocket() {
        return isRocket;
    }

    public boolean isLittleBoss() {
        return isLittleBoss;
    }

    public double getXdir() {
        return xdir;
    }
}
