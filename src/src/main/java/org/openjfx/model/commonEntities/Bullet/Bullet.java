package org.openjfx.model.commonEntities.Bullet;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;

public class Bullet extends LocatableObject {
    public static final double WIDTH = 7;
    public static final double HEIGHT = 7;
    public static final double MAX_DISTANCE = 1000;
    private int damage;
    private double velocity;
    private double directionX;
    private double directionY;
    private double distanceTravelled = 0;


    public Bullet(Location location, int damage, double velocity, double directionX, double directionY) {
        super(location, WIDTH, HEIGHT, 1);
        this.damage = damage;
        this.velocity = velocity;
        this.directionX = directionX;
        this.directionY = directionY;
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

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
