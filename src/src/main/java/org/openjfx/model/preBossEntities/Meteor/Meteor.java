package org.openjfx.model.preBossEntities.Meteor;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Meteor extends LocatableObject {
    public static final double MIN_RADIUS = 5;
    public static final double MAX_RADIUS = 50;
    private int velocity;
    private int damage;
    private double radius;
    private double directionX;
    private double directionY;

    public Meteor(Location location) {
        super(location, 1, 1, 1);
        // randomization
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDirectionX() {
        return directionX;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }
}
