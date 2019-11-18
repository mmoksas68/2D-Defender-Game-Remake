package org.openjfx.model.entities.Enemy;

import org.openjfx.model.FireBullets;
import org.openjfx.model.Location;
import org.openjfx.model.preBoss.PreBossMap;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.utilization.PositionHelper;

public class Tier1 extends Enemy implements FireBullets {
    public static final double WIDTH_SCALE = 100;
    public static final double HEIGHT_SCALE = 60;
    public static final int MAX_HEALTH = 1;
    private int bulletVelocity;
    private int bulletDamage;
    private int gunPeriod;
    private int gunTimer = 0;

    public Tier1(Location location, double hitBoxWidth, double hitBoxHeight, int damage, int velocity, int radarRadius, int bulletVelocity, int bulletDamage, int gunPeriod, boolean isEvolved) {
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

        return new Bullet(new Location(x ,y),(Bullet.WIDTH_SCALE * PreBossMap.getHitboxHeightScale()/1080), (Bullet.HEIGHT_SCALE* PreBossMap.getHitboxHeightScale()/1080), getBulletDamage(), getBulletVelocity(), getDestinationLocation().getPositionX(), -getDestinationLocation().getPositionY());
    }
}
