package org.openjfx.utilization;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.entities.Spacecraft.Spacecraft;

public class ModelToViewSpaceCraft {
    private double locationX;
    private double locationY;
    private double hitboxWidth;
    private double hitboxHeight;
    private long ID;
    private double currentViewLeft;
    private double currentViewRight;
    private boolean isDead;
    private boolean isDirectionLeft;
    private boolean isMoving;

    public ModelToViewSpaceCraft(Spacecraft spacecraft, double currentViewLeft, double currentViewRight, boolean isMoving) {
        this.locationX = spacecraft.getLocation().getPositionX();
        this.locationY = spacecraft.getLocation().getPositionY();
        this.hitboxWidth = spacecraft.getHitBoxWidth();
        this.hitboxHeight = spacecraft.getHitBoxHeight();
        this.ID = spacecraft.getID();
        this.isDead = spacecraft.isDead();
        this.isDirectionLeft = spacecraft.isDirectionLeft();
        this.currentViewLeft = currentViewLeft;
        this.currentViewRight = currentViewRight;
        this.isMoving = isMoving;
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

    public boolean isDirectionLeft() {
        return isDirectionLeft;
    }

    public void setDirectionLeft(boolean directionLeft) {
        isDirectionLeft = directionLeft;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
