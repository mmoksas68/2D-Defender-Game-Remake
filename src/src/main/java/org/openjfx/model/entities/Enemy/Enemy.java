package org.openjfx.model.entities.Enemy;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

import java.util.Random;


public abstract class Enemy extends LocatableObject {
    public static final double WIDTH_SCALE = 0.03;
    public static final double HEIGHT_SCALE = 0.05;
    private int damage;
    private int velocity;
    private int radarRadius;
    private Location destinationLocation;
    private String destinationType;
    private int buffType;
    private int changeDirectionPeriod = 100;
    private int changeDirectionTimer = 0;

    public Enemy(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int damage, int velocity, int radarRadius) {
        super(location, hitBoxWidth, hitBoxHeight);
        this.damage = damage;
        this.velocity = velocity;
        this.radarRadius = radarRadius;
        destinationLocation = new Location(0,0);
    }


    public void evolve(){

    }
    public void radarSearch(){

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

    public int getChangeDirectionTimer() {
        return changeDirectionTimer;
    }

    public void setChangeDirectionTimer(int value) {
        this.changeDirectionTimer = value;
    }

    public int getChangeDirectionPeriod() {
        return changeDirectionPeriod;
    }

    public void setChangeDirectionPeriod(int value) {
        this.changeDirectionPeriod = value;
    }


    public int getBuffType() {
        return buffType;
    }

    public void setBuffType(int buffType) {
        this.buffType = buffType;
    }
}