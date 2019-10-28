package org.openjfx.model;

public abstract class LocatableObject {

    private Location location;
    private int hitBoxWidth;
    private int hitBoxHeight;
    private long ID;
    private static long currentID = 0;
    private boolean isDead = false;

    public LocatableObject(Location location, int hitBoxWidth, int hitBoxHeight) {
        this.location = location;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
        this.ID = currentID++;
    }

    public Location getLocation() {
        return location;
    }

    public int getHitBoxWidth() {
        return hitBoxWidth;
    }

    public void setHitBoxWidth(int hitBoxWidth) {
        this.hitBoxWidth = hitBoxWidth;
    }

    public int getHitBoxHeight() {
        return hitBoxHeight;
    }

    public void setHitBoxHeight(int hitBoxHeight) {
        this.hitBoxHeight = hitBoxHeight;
    }

    public long getID() {
        return ID;
    }

    public static long getCurrentID() {
        return currentID;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void moveUp(int amount){
        location.setPositionY(location.getPositionY() - amount);
    }

    public void moveDown(int amount){
        location.setPositionY(location.getPositionY() + amount);
    }

    public void moveLeft(int amount){
        location.setPositionX(location.getPositionX() - amount);
    }

    public void moveRight(int amount){
        location.setPositionX(location.getPositionX() + amount);
    }

}
