package org.openjfx.model.entities.Boss;

import org.openjfx.model.Boss.BossMap;
import org.openjfx.model.FireBullets;
import org.openjfx.model.LocatableObject;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;


public abstract class Boss extends LocatableObject implements FireBullets {

    private static double initialX = 1000;
    private static double initialY = 250;
    private double velocity;
    private double bullet_prob;
    private double gunPower;
    private double gunFrequency;
    private int gunPeriod;
    private int gunTimer;
    private int bulletVelocity;

    public Boss( double velocity, double hitBoxWidth, double hitBoxHeight, int healthPoint) {
        super( new Location(initialX,initialY), hitBoxWidth, hitBoxHeight, healthPoint);
        this.velocity = velocity;
    }

    public double getVelocity() { return velocity; }

    public double getGunPower() {
        return gunPower;
    }

    public void setGunPower(double gunPower) {
        this.gunPower = gunPower;
    }

    public int getGunPeriod() {
        return gunPeriod;
    }

    public void setGunPeriod(int gunPeriod) {
        this.gunPeriod = gunPeriod;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }


    public Bullet fireBullet() {
        return new Bullet( new Location( 1000, 250) , 10, 10, 10, 1, -1, 0);
    }

    public double getBullet_prob() {
        return bullet_prob;
    }

    public void setBullet_prob(double bullet_prob) {
        this.bullet_prob = bullet_prob;
    }

    public int getGunTimer() {
        return gunTimer;
    }

    public void setGunTimer(int gunTimer) {
        this.gunTimer = gunTimer;
    }

    public double getGunFrequency() {
        return gunFrequency;
    }

    public void setGunFrequency(double gunFrequency) {
        this.gunFrequency = gunFrequency;
    }
}
