package org.openjfx.model.entities.Enemy;

import org.openjfx.model.FireBullets;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;

public class Tier3 extends Enemy implements SimpleEnemy, FireBullets {
    public static final double WIDTH_SCALE = 0.03;
    public static final double HEIGHT_SCALE = 0.05;
    private static final int MAX_HEALTH = 30;
    private int bulletVelocity;
    private int bulletDamage;
    private int gunPeriod;
    private int gunTimer = 0;

    public Tier3(Location location, double hitBoxWidth, double hitBoxHeight, int damage, int velocity, int radarRadius, int bulletVelocity, int bulletDamage, int gunPeriod, boolean isEvolved) {
        super(location, hitBoxWidth, hitBoxHeight, damage, velocity, radarRadius, isEvolved, MAX_HEALTH);
        this.bulletVelocity = bulletVelocity;
        this.bulletDamage = bulletDamage;
        this.gunPeriod = gunPeriod;
    }

    @Override
    public int getBulletVelocity() {
        return bulletVelocity;
    }

    @Override
    public void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }

    @Override
    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public void divide(){

    }

    @Override
    public int getGunPeriod() {
        return gunPeriod;
    }

    @Override
    public void setGunPeriod(int gunPeriod) {
        this.gunPeriod = gunPeriod;
    }

    @Override
    public int getGunTimer() {
        return gunTimer;
    }

    @Override
    public void setGunTimer(int gunTimer) {
        this.gunTimer = gunTimer;
    }

    @Override
    public Bullet fireBullet() {
        return new Bullet(new Location(this.getLocation().getPositionX(), this.getLocation().getPositionY()), 5,2, bulletDamage, bulletVelocity, getDestinationLocation().getPositionX(), getDestinationLocation().getPositionY());
    }

}
