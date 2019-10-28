package org.openjfx.model.entities.Meteor;

import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;

public class Meteor extends LocatableObject {
    private int velocity;
    private int damage;
    private int directionX;
    private int directionY;

    public Meteor(Location location, int hitBoxWidth, int hitBoxHeight, int velocity, int damage, int directionX, int directionY) {
        super(location, hitBoxWidth, hitBoxHeight);
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

    public void explode(){

    }
}
