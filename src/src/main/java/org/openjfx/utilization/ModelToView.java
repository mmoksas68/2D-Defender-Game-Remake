package org.openjfx.utilization;

import org.openjfx.model.commonEntities.LocatableObject;

public class ModelToView {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private boolean isDead;

    public ModelToView(LocatableObject locatableObject) {
        this.locationX = locatableObject.getLocation().getPositionX();
        this.locationY = locatableObject.getLocation().getPositionY();
        this.hitboxWidth = locatableObject.getHitBoxWidth();
        this.hitboxHeight = locatableObject.getHitBoxHeight();
        this.ID = locatableObject.getID();
        this.isDead = locatableObject.isDead();
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public double getHitboxWidth() {
        return hitboxWidth;
    }

    public void setHitboxWidth(double hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
    }

    public double getHitboxHeight() {
        return hitboxHeight;
    }

    public void setHitboxHeight(double hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
