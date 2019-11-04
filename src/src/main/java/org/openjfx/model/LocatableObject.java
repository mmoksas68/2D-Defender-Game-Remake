package org.openjfx.model;

public abstract class LocatableObject {

    private Location location;
    private double hitBoxWidth;
    private double hitBoxHeight;
    private long ID;
    private static long currentID = 0;
    private boolean isDead = false;
    private int healthPoint;

    public LocatableObject(Location location, double hitBoxWidth, double hitBoxHeight) {
        this.location = location;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
        this.ID = currentID++;
        healthPoint = 100;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Location getLocation() {
        return location;
    }

    public double getHitBoxWidth() {
        return hitBoxWidth;
    }

    public void setHitBoxWidth(double hitBoxWidth) {
        this.hitBoxWidth = hitBoxWidth;
    }

    public double getHitBoxHeight() {
        return hitBoxHeight;
    }

    public void setHitBoxHeight(double hitBoxHeight) {
        this.hitBoxHeight = hitBoxHeight;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public static long getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(long currentID) {
        LocatableObject.currentID = currentID;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    private void moveUp(double amount){
        location.setPositionY(location.getPositionY() - amount);
    }

    private void moveDown(double amount){
        location.setPositionY(location.getPositionY() + amount);
    }

    private void moveLeft(double amount){
        location.setPositionX(location.getPositionX() - amount);
    }

    private void moveRight(double amount){
        location.setPositionX(location.getPositionX() + amount);
    }

    public void moveToDirection(double velocity, double directionX, double directionY){
        //System.out.println(velocity + " " + directionX + " " + directionY);
        velocity = Math.abs(velocity);
        if(Math.round(directionX) ==  0.0 && Math.round(directionY) ==  0.0)
            return;
        if(Math.round(directionX) ==  0.0 ){
            if(directionY > 0)
                moveUp(velocity);
            else
                moveDown(velocity);
            return;
        }

        double amountX = Math.sqrt((velocity*velocity)/((directionY/directionX)*(directionY/directionX)+1));
        double amountY = amountX*((directionY/directionX));

        amountX = Math.abs(amountX);
        amountY = Math.abs(amountY);

        if(directionX > 0)
            moveRight(amountX);
        else
            moveLeft(amountX);

        if(directionY > 0)
            moveUp(amountY);
        else
            moveDown(amountY);
    }

}
