package org.openjfx.model.preBossEntities.Meteor;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.utilization.PositionHelper;

public class Meteor extends LocatableObject {
    public static final double MIN_RADIUS = 50;
    public static final double MAX_RADIUS = 100;
    private double velocity;
    private int damage;
    private double radius;
    private double directionX;
    private double directionY;

    public Meteor(Location location) {
        super(location, 1, 1, 1);
        getLocation().setPositionX(Math.random()* PreBossMap.MAP_WIDTH);
        getLocation().setPositionY(52.3);
        radius = (Math.random()*(MAX_RADIUS-MIN_RADIUS)) + MIN_RADIUS;
        setHitBoxWidth(radius);
        setHitBoxHeight(radius);
        setDirectionX(Math.random() * 5 - 2.5);
        if ( Math.round( getDirectionX()) == 0.0)
            setDirectionX( getDirectionX() + 0.5);
        setDirectionY(-(Math.random() * 5 + Math.abs(getDirectionX())*2));
        if ( Math.round( getDirectionY()) == 0.0)
            setDirectionY( getDirectionY() - 0.5);
        velocity = (MAX_RADIUS - radius)/6 + 5;
        damage = (int)(radius/(MAX_RADIUS-MIN_RADIUS) * 5);
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
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
