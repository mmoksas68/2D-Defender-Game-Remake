package org.openjfx.model.entities.Enemy;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public abstract class Enemy extends LocatableObject {
    private int healthPoint;
    private int damage;
    private int velocity;
    private int radarRadius;
    private Location destinationLocation;
    private String destinationType;
    private int buffType;

    public Enemy(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int damage, int velocity, int radarRadius) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.velocity = velocity;
        this.radarRadius = radarRadius;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
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
}
