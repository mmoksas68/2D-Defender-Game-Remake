package org.openjfx.model.entities.Enemy;
import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;


public abstract class Enemy extends LocatableObject {
    private int damage;
    private double velocity;
    private int radarRadius;
    private Location destinationLocation;
    private String destinationType;
    private int buffType;
    private boolean isEvolved;
    private int changeDirectionPeriod = 100;
    private int changeDirectionTimer = 0;


    public Enemy(Location location, double hitBoxWidth, double hitBoxHeight, int damage, double velocity, int radarRadius, boolean isEvolved, int health) {
        super(location, hitBoxWidth, hitBoxHeight, isEvolved ? 2*health : health);
        this.damage = damage;
        this.velocity = velocity;
        this.radarRadius = radarRadius;
        this.isEvolved = isEvolved;
        destinationLocation = new Location(0,0);
        destinationType = "empty";
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getRadarRadius() {
        return radarRadius;
    }

    public void setRadarRadius(int radarRadius) {
        this.radarRadius = radarRadius;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public int getBuffType() {
        return buffType;
    }

    public void setBuffType(int buffType) {
        this.buffType = buffType;
    }

    public boolean isEvolved() {
        return isEvolved;
    }

    public void setEvolved(boolean evolved) {
        isEvolved = evolved;
    }

    public int getChangeDirectionPeriod() {
        return changeDirectionPeriod;
    }

    public void setChangeDirectionPeriod(int changeDirectionPeriod) {
        this.changeDirectionPeriod = changeDirectionPeriod;
    }

    public int getChangeDirectionTimer() {
        return changeDirectionTimer;
    }

    public void setChangeDirectionTimer(int changeDirectionTimer) {
        this.changeDirectionTimer = changeDirectionTimer;
    }
}
