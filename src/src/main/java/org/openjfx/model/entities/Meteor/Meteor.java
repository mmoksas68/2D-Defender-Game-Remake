package org.openjfx.model.entities.Meteor;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class Meteor extends LocatableObject {
    private static final int MAX_HEALTH = 30;
    public static final double WIDTH_SCALE = 5;
    public static final double HEIGHT_SCALE = 5;
    private int velocity;
    private int damage;
    private double directionX;
    private double directionY;

    public Meteor(Location location, int hitBoxWidth, int hitBoxHeight, int velocity, int damage, double directionX, double directionY) {
        super(location, hitBoxWidth, hitBoxHeight, MAX_HEALTH);
        this.velocity = velocity;
        this.damage = damage;
        this.directionX = directionX;
        this.directionY = directionY;
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
