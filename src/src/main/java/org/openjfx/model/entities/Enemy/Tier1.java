package org.openjfx.model.entities.Enemy;

import org.openjfx.model.FireBullets;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.utilization.PositionHelper;

public class Tier1 extends Enemy implements FireBullets {
    private int bulletVelocity;
    private int bulletDamage;
    private int gunPeriod;
    private int gunTimer = 0;

    public Tier1(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int damage, int velocity, int radarRadius, int bulletVelocity, int bulletDamage, int gunPeriod) {
        super(location, hitBoxWidth, hitBoxHeight, healthPoint, damage, velocity, radarRadius);
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

    /*@Override
    public void wonderAround() {

    } */

    @Override
    public void evolve() {

    }

    @Override
    public void radarSearch() {

    }

    @Override
    public Bullet fireBullet() {
        PositionHelper enemyHelper = new PositionHelper(this);
        double radius = enemyHelper.findRadius();
        double x = Math.sqrt((radius*radius) / (Math.pow(getDestinationLocation().getPositionY()/getDestinationLocation().getPositionX(),2) + 1));
        double y = Math.abs(getDestinationLocation().getPositionY()/getDestinationLocation().getPositionX())*x;

        if(getDestinationLocation().getPositionX() < 0)
            x = -x;
        if(getDestinationLocation().getPositionY() < 0)
            y = -y;

        x += enemyHelper.getMiddlePointX();
        y += enemyHelper.getMiddlePointY();

        return new Bullet(new Location(x ,y),5, 2, 30, 5, getDestinationLocation().getPositionX(), -getDestinationLocation().getPositionY());
    }
}
