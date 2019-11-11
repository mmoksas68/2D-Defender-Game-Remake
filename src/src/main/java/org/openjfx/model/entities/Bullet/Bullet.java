package org.openjfx.model.entities.Bullet;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class Bullet extends LocatableObject {
    public static final int MAX_HEALTH = 1;
    public static final double WIDTH_SCALE = 5;
    public static final double HEIGHT_SCALE = 5;
    private int damage;
    private int velocity;
    private double directionX;
    private double directionY;


    public Bullet(Location location, double hitBoxWidth, double hitBoxHeight, int damage, int velocity, double directionX, double directionY) {
        super(location, hitBoxWidth, hitBoxHeight, MAX_HEALTH);
        this.damage = damage;
        this.velocity = velocity;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public void hit(){

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
