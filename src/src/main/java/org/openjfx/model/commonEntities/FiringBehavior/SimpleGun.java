package org.openjfx.model.commonEntities.FiringBehavior;

import org.openjfx.controller.SoundController;
import org.openjfx.model.bossEntities.BossMap;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.PreBossMap;
import org.openjfx.utilization.PositionHelper;

import java.io.Serializable;

public abstract class SimpleGun implements FiringBehavior {
    private int gunPeriod;
    private int bulletDamage;
    private int gunTimer;
    private double bulletVelocity;
    private boolean isFiring;

    public SimpleGun(int gunPeriod, int bulletDamage, int gunTimer, double bulletVelocity) {
        this.gunPeriod = gunPeriod;
        this.bulletDamage = bulletDamage;
        this.gunTimer = gunTimer;
        this.bulletVelocity = bulletVelocity;
        isFiring = false;
    }
    public void gunTimer( BossMap bossMap) {
        this.setGunTimer(this.getGunTimer() % this.getGunPeriod());

        if (isFiring) {
            if (this.getGunTimer() == 0) {
                Bullet bullet = this.fireBullet();
                bossMap.addBullet(bullet);
                if (this instanceof SpacecraftGun) {
                    SoundController.fireBullet();
                }
            }
            this.setGunTimer(this.getGunTimer() + 1);
        } else if (this.getGunTimer() != 0)
            this.setGunTimer(this.getGunTimer() + 1);
    }
    public void gunTimer(PreBossMap preBossMap){
        this.setGunTimer(this.getGunTimer() % this.getGunPeriod());

        if (isFiring) {
            if (this.getGunTimer() == 0) {
                Bullet bullet = this.fireBullet();
                preBossMap.addBullet(bullet);
                if (this instanceof SpacecraftGun) {
                    SoundController.fireBullet();
                }
            }
            this.setGunTimer(this.getGunTimer() + 1);
        } else if (this.getGunTimer() != 0)
            this.setGunTimer(this.getGunTimer() + 1);
    }

    public int getGunPeriod() {
        return gunPeriod;
    }

    public void setGunPeriod(int gunPeriod) {
        this.gunPeriod = gunPeriod;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public int getGunTimer() {
        return gunTimer;
    }

    public void setGunTimer(int gunTimer) {
        this.gunTimer = gunTimer;
    }

    public double getBulletVelocity() {
        return bulletVelocity;
    }

    public void setBulletVelocity(double bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    public boolean isFiring() {
        return isFiring;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }
}
