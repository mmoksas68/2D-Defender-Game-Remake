package org.openjfx.model.bossEntities.Boss;

import org.openjfx.model.commonEntities.LocatableObject;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.commonEntities.Bullet.Bullet;


public abstract class Boss extends LocatableObject {

    private static double initialX = 1250;
    private static double initialY = 250;
    private double velocity;
    private double bullet_prob;
    private int gunPower;
    private double gunFrequency;
    private int gunPeriod;
    private int gunTimer;
    private double bulletVelocity;

    public Boss( double velocity, double hitBoxWidth, double hitBoxHeight, int healthPoint) {
        super( new Location(initialX,initialY), hitBoxWidth, hitBoxHeight, healthPoint);
        this.velocity = velocity;
    }

    public double getVelocity() { return velocity; }

    public int getGunPower() {
        return gunPower;
    }

    public void setGunPower(int gunPower) {
        this.gunPower = gunPower;
    }

    public int getGunPeriod() {
        return gunPeriod;
    }

    public void setGunPeriod(int gunPeriod) {
        this.gunPeriod = gunPeriod;
    }

    public double getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(double bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }


    public Bullet fireBullet() {
        return new Bullet( new Location( getLocation().getPositionX(), getLocation().getPositionY()), gunPower,bulletVelocity,-1.0,0.0 );
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
