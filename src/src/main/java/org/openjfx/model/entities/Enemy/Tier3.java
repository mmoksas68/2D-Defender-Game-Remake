package org.openjfx.model.entities.Enemy;

import org.openjfx.model.FireBullets;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;

public class Tier3 extends Enemy implements SimpleEnemy, FireBullets {
    private int bulletVelocity;
    private int bulletDamage;

    public Tier3(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int damage, int velocity, int radarRadius, int bulletVelocity) {
        super(location, hitBoxWidth, hitBoxHeight, healthPoint, damage, velocity, radarRadius);
        this.bulletVelocity = bulletVelocity;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public void divide(){

    }

    @Override
    public Bullet fireBullet() {
        return new Bullet(new Location(this.getLocation().getPositionX(), this.getLocation().getPositionY()), 5,2, bulletDamage, bulletVelocity, getDestinationLocation().getPositionX(), getDestinationLocation().getPositionY());
    }

    @Override
    public void wonderAround() {

    }

    @Override
    public void evolve() {

    }

    @Override
    public void radarSearch() {

    }
}
