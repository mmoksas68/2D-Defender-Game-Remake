package org.openjfx.utilization;

import org.openjfx.model.LocatableObject;

public class ModelToView {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private double currentViewLeft;
    private double currentViewRight;
    private boolean isDead;

    public ModelToView(LocatableObject locatableObject, double currentViewLeft, double currentViewRight) {
        this.locationX = locatableObject.getLocation().getPositionX();
        this.locationY = locatableObject.getLocation().getPositionY();
        this.hitboxWidth = locatableObject.getHitBoxWidth();
        this.hitboxHeight = locatableObject.getHitBoxHeight();
        this.ID = locatableObject.getID();
        this.isDead = locatableObject.isDead();
        this.currentViewLeft = currentViewLeft;
        this.currentViewRight = currentViewRight;
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

    public double getCurrentViewLeft() {
        return currentViewLeft;
    }

    public void setCurrentViewLeft(double currentViewLeft) {
        this.currentViewLeft = currentViewLeft;
    }

    public double getCurrentViewRight() {
        return currentViewRight;
    }

    public void setCurrentViewRight(double currentViewRight) {
        this.currentViewRight = currentViewRight;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
