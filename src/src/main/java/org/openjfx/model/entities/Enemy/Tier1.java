package org.openjfx.model.entities.Enemy;

import org.openjfx.model.FireBullets;
import org.openjfx.model.Location;
import org.openjfx.model.entities.Bullet.Bullet;
import org.openjfx.utilization.PositionHelper;

public class Tier1 extends Enemy implements SimpleEnemy, FireBullets {
    private int bulletVelocity;
    private int bulletDamage;

    public Tier1(Location location, int hitBoxWidth, int hitBoxHeight, int healthPoint, int damage, int velocity, int radarRadius, int bulletVelocity) {
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

    @Override
    public void wonderAround() {
        if(getDestinationType().equals("spacecraft"));
            fireBullet();
    }

    @Override
    public void evolve() {

    }

    @Override
    public void radarSearch() {

    }

    @Override
    public Bullet fireBullet() {
        PositionHelper enemyHelper = new PositionHelper(this);
        double x = 0;
        double y = 0;
        if(getDestinationLocation().getPositionX() > 0 && getDestinationLocation().getPositionY() > 0){
            x = enemyHelper.getRight() + 1;
            y = enemyHelper.getBottom() + 1;
        }
        if(getDestinationLocation().getPositionX() > 0 && getDestinationLocation().getPositionY() < 0){
            x = enemyHelper.getRight() + 1;
            y = enemyHelper.getTop() - 6;
        }
        if(getDestinationLocation().getPositionX() < 0 && getDestinationLocation().getPositionY() > 0){
            x = enemyHelper.getLeft() - 6;
            y = enemyHelper.getBottom() + 1;
        }
        if(getDestinationLocation().getPositionX() < 0 && getDestinationLocation().getPositionY() < 0){
            x = enemyHelper.getLeft() - 6;
            y = enemyHelper.getTop() - 6;
        }
        return new Bullet(new Location(x ,y),5, 2, 30, 5, getDestinationLocation().getPositionX(), -getDestinationLocation().getPositionY());
    }
}
