package org.openjfx.model.entities.Bullet;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class Bullet extends LocatableObject {

    private int damage;
    private int velocity;
    private int directionX;
    private int directionY;

    public Bullet(Location location, int hitBoxWidth, int hitBoxHeight, int damage, int velocity, int directionX, int directionY) {
        super(location, hitBoxWidth, hitBoxHeight);
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

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
}
